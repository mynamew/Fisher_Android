package com.szpcqy.fisher.mt;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.annotation.StringRes;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.widget.TextView;

import com.szpcqy.fisher.event.pair.NetRequest;
import com.szpcqy.fisher.event.pair.SocketResonse;
import com.szpcqy.fisher.event.pair.WifiResponse;
import com.szpcqy.fisher.net.Gateway;
import com.szpcqy.fisher.tool.Clock;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import butterknife.ButterKnife;

/**
 * Created by Master on 2017/10/31.
 */

public abstract class MTActivity extends AppCompatActivity {


    public MTActivity getContext() {
        return this;
    }


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
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
    public void finish() {
        super.finish();
        MTApplication.getInstance().onFinishActivity(this);
    }

    /**
     * 注册监听Socket发送的事件
     * @param res
     */
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onSocketResponse(SocketResonse res) {
        int[] list = listenProtocols();
        for (int protocol : list) {
            if (protocol == res.getProtocol()) {
                execResponse(res);
                return;
            }
        }
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
    }  /**
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
     * @return
     */
    public abstract int[] listenProtocols();

    /**
     * 界面拿到Socket的返回
     * @param res
     */
    public abstract void execResponse(SocketResonse res);

    /**
     * 初始化Mvp   用于子类实现
     */
    protected   void initMVp(){

    }
}
