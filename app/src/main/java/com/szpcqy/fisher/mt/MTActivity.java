package com.szpcqy.fisher.mt;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.annotation.StringRes;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import com.szpcqy.fisher.R;
import com.szpcqy.fisher.data.login.LoginResponse;
import com.szpcqy.fisher.event.pair.NetRequest;
import com.szpcqy.fisher.event.pair.NetResponse;
import com.szpcqy.fisher.event.pair.SocketResonse;
import com.szpcqy.fisher.event.pair.WifiRequest;
import com.szpcqy.fisher.event.pair.WifiResponse;
import com.szpcqy.fisher.tool.CacheTool;
import com.szpcqy.fisher.tool.Clock;
import com.szpcqy.fisher.ui.login.LoginActivity;
import com.szpcqy.fisher.utils.ActivityUtils;
import com.szpcqy.fisher.view.MyDialog;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import butterknife.ButterKnife;

import static com.szpcqy.fisher.net.SocketProtocol.KICK_OUT_RES;

/**
 * MTActivity
 *
 * @author jzk
 * create at: 2018/9/5 22:12
 */

public abstract class MTActivity extends AppCompatActivity {


    public MTActivity getContext() {
        return this;
    }


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //去掉标题栏
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        //去掉信息栏
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(setLayoutId());
        ButterKnife.bind(this);
        MTApplication.getInstance().onCreateActivity(this);
        EventBus.getDefault().register(this);
        initMVp();
        initView();
        initData();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState, @Nullable PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);
        MTApplication.getInstance().onCreateActivity(this);
        EventBus.getDefault().register(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (null != dialogKickDowm) {
            dialogKickDowm.dismiss();
        }
    }

    @Override
    public void finish() {
        super.finish();
        MTApplication.getInstance().onFinishActivity(this);
    }

    /**
     * 自动连接wifi
     *
     * @param wifiName
     * @param wifiPsw
     */
    public void autoConnectWifi(String wifiName, String wifiPsw) {
        EventBus.getDefault().post(new WifiRequest(wifiName, wifiPsw));
    }

    /**
     * wifi连接成功的监听事件
     *
     * @param res
     */
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onWifiResponse(WifiResponse res) {
        if (res.getIsConnected()) {
            connectWifiSuccess(res);
        }
    }

    /**
     * 连接wifi成功  界面重写继续连接socket
     *
     * @param res
     */
    protected void connectWifiSuccess(WifiResponse res) {

    }

    /**
     * 自动连接socket
     *
     * @param serverip
     */
    public void autoConnectSocket(String serverip) {
        //连上机器wifi-连接socket
        Clock.create(this).interval(500).count(1).onCompleteOnUI(new Runnable() {
            @Override
            public void run() {
                EventBus.getDefault().post(new NetRequest(NetRequest.RequestType.CONNECT, serverip));
            }
        }).start();
    }

    /**
     * 注册链接socket的事件
     *
     * @param res
     */
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onNetResponse(NetResponse res) {
        /**
         * 连接wifi成功 则连接socket
         */
        if (res.getIsConnected()) {
            connectSocketSuccess(res);
        }
        /**
         * 设备断开
         */
        else if (!res.getIsConnected() && res.getIsRemote()) {
            /**
             * 是否在前台
             */
            if (ActivityUtils.isForeground(this)) {
                if(null==dialogKickDowm||!dialogKickDowm.isShowing()){
                    //被远程关闭，退出登录
                    final AppCompatActivity atx = MTApplication.getInstance().getCurrentActivity();
                    android.app.AlertDialog.Builder bd = new android.app.AlertDialog.Builder(atx);
                    bd.setTitle("警告");
                    bd.setMessage("您与设备之间已失联，将退出到登录界面!");
                    bd.setCancelable(false);
                    bd.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            MTApplication.getInstance().onFinishAllActivities();
                            atx.startActivity(new Intent(atx, LoginActivity.class));
                        }
                    });
                    bd.show();
                }
            }
        }
    }

    /**
     * 连接Socket成功  界面重写处理其他逻辑
     *
     * @param res
     */
    protected void connectSocketSuccess(NetResponse res) {

    }

    public MyDialog dialogKickDowm = null;

    /**
     * 注册监听Socket发送的事件
     *
     * @param res
     */
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onSocketResponse(SocketResonse res) {
        /**
         * 强制退出
         */
        if (res.getProtocol() == KICK_OUT_RES) {
            //如果在登录界面不作处理
            if (!(MTApplication.getInstance().getCurrentActivity() instanceof LoginActivity)) {
                /**
                 * 是否在前台
                 */
                if (ActivityUtils.isForeground(this)) {
                    if(null == dialogKickDowm ){
                        dialogKickDowm = new MyDialog(getContext(), R.layout.dialog_error_tip);
                        dialogKickDowm.setTextViewContent(R.id.tv_title, "警告");
                        dialogKickDowm.setTextViewContent(R.id.tv_content, res.getMsg() + ",您将返回登录页面!");
                        dialogKickDowm.setButtonListener(R.id.btn_cancel, "去登录", new MyDialog.DialogClickListener() {
                            @Override
                            public void dialogClick(MyDialog dialog) {
                                dialog.dismiss();
                                //清空登录信息
                                CacheTool.setCurrentLoginResponse(null);
                                getContext().startActivity(new Intent(getContext(), LoginActivity.class));
                                //清除activity
                                MTApplication.getInstance().onFinishAllActivities();
                            }
                        });
                        dialogKickDowm.setImageViewListener(R.id.iv_close, new MyDialog.DialogClickListener() {
                            @Override
                            public void dialogClick(MyDialog dialog) {
                                dialog.dismiss();
                                //清空登录信息
                                CacheTool.setCurrentLoginResponse(null);
                                getContext().startActivity(new Intent(getContext(), LoginActivity.class));
                                //清除activity
                                MTApplication.getInstance().onFinishAllActivities();
                            }
                        });
                    }
                    dialogKickDowm.setTextViewContent(R.id.tv_title, "警告");
                    dialogKickDowm.setTextViewContent(R.id.tv_content, res.getMsg() + ",您将返回登录页面!");
                    dialogKickDowm.show();
                }
            }
            return;
        }
        int[] list = listenProtocols();
        for (int protocol : list) {
            if (protocol == res.getProtocol()) {
                execResponse(res);
                return;
            }
        }
    }

    /**
     * 注册监听更新了用户的事件发送的事件
     *
     * @param res
     */
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onUserInfoUpdate(LoginResponse res) {
        updateUserInfo(res);
    }


    /*************RecycleView和NesrollView滑动冲突解决*********************************************/
    /**
     * 设置recycleview  滑动更流畅
     *
     * @param rlv
     * @param layoutManager
     */
    public void setRecycleViewScrollSmooth(RecyclerView rlv, LinearLayoutManager layoutManager) {
        layoutManager.setSmoothScrollbarEnabled(true);
        layoutManager.setAutoMeasureEnabled(true);
        rlv.setLayoutManager(layoutManager);
        rlv.setNestedScrollingEnabled(false);
        rlv.setHasFixedSize(true);
    }

    /**
     * 设置 文本
     *
     * @param tv
     * @param fomat
     * @param content
     */
    public void setTextViewText(TextView tv, @StringRes int fomat, String content) {
        tv.setText(String.format(getString(fomat), content));
    }

    /**
     * 设置 文本
     *
     * @param tv
     * @param fomat
     * @param content
     */
    public void setTextViewText(TextView tv, @StringRes int fomat, int content) {
        tv.setText(String.format(getString(fomat), String.valueOf(content)));
    }

    /**
     * 设置 文本
     *
     * @param tv
     */
    public void setTextViewContent(TextView tv, Object object) {
        String content = "";
        if (object instanceof String) {
            content = TextUtils.isEmpty(String.valueOf(object)) ? "" : String.valueOf(object);
        }
        //为空 什么都不填
        else if (null == object) {

        } else {
            content = String.valueOf(object);
        }
        tv.setText(content);
    }

    /**
     * 设置布局id
     *
     * @return
     */
    public abstract int setLayoutId();

    /**
     * 初始化View
     */
    public abstract void initView();

    /**
     * 初始化数据
     */
    public abstract void initData();

    /**
     * 设置当前Activity拦截的事件
     *
     * @return
     */
    public abstract int[] listenProtocols();

    /**
     * 界面拿到Socket的返回
     *
     * @param res
     */
    public abstract void execResponse(SocketResonse res);

    /**
     * 初始化Mvp   用于子类实现
     */
    protected void initMVp() {

    }

    /**
     * 更新用戶信息（重写方法即可更新数据）
     *
     * @return
     */
    protected void updateUserInfo(LoginResponse userinfo) {

    }
}
