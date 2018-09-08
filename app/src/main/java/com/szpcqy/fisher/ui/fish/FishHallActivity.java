package com.szpcqy.fisher.ui.fish;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.jzk.utilslibrary.LogUitls;
import com.szpcqy.fisher.R;
import com.szpcqy.fisher.adapter.MyAdapter;
import com.szpcqy.fisher.data.fish.FishGetAllDeskResponse;
import com.szpcqy.fisher.data.login.LoginResponse;
import com.szpcqy.fisher.event.pair.NetResponse;
import com.szpcqy.fisher.mt.MTApplication;
import com.szpcqy.fisher.mt.MTDialog;
import com.szpcqy.fisher.mt.MTLightbox;
import com.szpcqy.fisher.mt.MTMvpActivity;
import com.szpcqy.fisher.net.SocketProtocol;
import com.szpcqy.fisher.tool.CacheTool;
import com.szpcqy.fisher.ui.fragment.DeskBaseFragment;
import com.szpcqy.fisher.ui.fragment.DeskEightFragment;
import com.szpcqy.fisher.ui.fragment.DeskSixFragment;
import com.szpcqy.fisher.ui.login.LoginActivity;
import com.szpcqy.fisher.utils.ToastUtils;
import com.szpcqy.fisher.view.MTImageView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import de.hdodenhof.circleimageview.CircleImageView;

/**
 * 捕鱼大厅
 *
 * @author jzk
 * create at: 2018/8/25 18:21
 */
public class FishHallActivity extends MTMvpActivity<FishHallView, FishHallPresenter> implements FishHallView {
    MTDialog.MTDialogContent dia;
    @BindView(R.id.vp_fish_hall)
    ViewPager vpFishHall;
    @BindView(R.id.tv_desk_num)
    TextView tvDeskNum;
    @BindView(R.id.tv_min_carry)
    TextView tvMinCarry;
    @BindView(R.id.tv_max_fire)
    TextView tvMaxFire;
    @BindView(R.id.tv_coin_fire)
    TextView tvCoinFire;
    @BindView(R.id.tv_min_fire)
    TextView tvMinFire;
    @BindView(R.id.iv_head)
    CircleImageView ivHead;
    @BindView(R.id.tv_gold_qty)
    TextView tvGoldQty;
    @BindView(R.id.tv_current_desk)
    TextView tvCurrentDesk;
    @BindView(R.id.iv_return)
    ImageView ivReturn;
    @BindView(R.id.iv_set)
    ImageView ivSet;
    @BindView(R.id.leftImg)
    MTImageView leftImg;
    @BindView(R.id.rightImg)
    MTImageView rightImg;


    private ArrayList<DeskBaseFragment> mDeskFragments;
    private MyAdapter mAdapter;


    private ArrayList<FishGetAllDeskResponse> mFishDesks;

    @Override
    public FishHallPresenter createPresenter() {
        return new FishHallPresenter(this);
    }

    @Override
    public FishHallView createView() {
        return this;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (dia != null) {
            dia.close();
        }
    }

    @Override
    protected void connectSocketSuccess(NetResponse res) {
        super.connectSocketSuccess(res);
        if (res.getIsConnected() == false && res.getIsRemote()) {
            //被远程关闭，退出登录
            final AppCompatActivity atx = MTApplication.getInstance().getCurrentActivity();
            AlertDialog.Builder bd = new AlertDialog.Builder(atx);
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

    @Override
    public int setLayoutId() {
        return R.layout.activity_hall;
    }

    @Override
    public void initView() {
        ivSet.setVisibility(View.INVISIBLE);
    }

    @Override
    public void initData() {
        getPresenter().getAllDesk();
        //设置当前的金币数
        setTextViewContent(tvGoldQty,CacheTool.getCurentGold());
        mFishDesks = new ArrayList<>();
        mDeskFragments = new ArrayList<>();

        mAdapter = new MyAdapter(getSupportFragmentManager(), mDeskFragments, this);
        int curPost = mDeskFragments.size() == 0 ? 0 : 1;
        tvCurrentDesk.setText(curPost + " / " + mDeskFragments.size());
        vpFishHall.setAdapter(mAdapter);
        vpFishHall.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                //
            }

            @Override
            public void onPageSelected(int position) {
                tvCurrentDesk.setText((position + 1) + " / " + mDeskFragments.size());
            }

            @Override
            public void onPageScrollStateChanged(int state) {
                //
            }
        });
    }

    @Override
    public int[] listenProtocols() {
        return new int[]{
                SocketProtocol.GET_DESK_ALL_RES,
                SocketProtocol.KICK_OUT_RES,
                SocketProtocol.DEVICE_UPDATE_RES,
                SocketProtocol.COINOUT_RES,
        };
    }

    @Override
    public void showProgressDialog(int protocol) {
        dia = MTLightbox.show(getContext(), MTLightbox.IconType.PROGRESS, "获取桌位信息", false);
    }

    @Override
    public void dismisProgressDialog() {
        dia.close();
    }

    @Override
    public void getAllDeskSuccess(List<FishGetAllDeskResponse> mDatas) {
        MTLightbox.update(getContext(), dia, MTLightbox.IconType.SUCCESS, "获取桌位成功", 1000);
        LogUitls.d("获取到的桌位信息 FishHall-->" + mDatas);
        mFishDesks.clear();
        mFishDesks.addAll(mDatas);
        updateDesks(null);
    }

    @Override
    public void deviceUpdate(FishGetAllDeskResponse res) {
        updateDesks(res);
    }

    @Override
    public void getAllDeskFail(String mesg) {
        AlertDialog.Builder bd = new AlertDialog.Builder(getContext());
        bd.setTitle("警告");
        bd.setMessage(mesg + ",是否重新加载");
        bd.setCancelable(false);
        bd.setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                getPresenter().getAllDesk();
            }
        });
        bd.show();
    }

    @Override
    public void userKickDown(String msg) {
        AlertDialog.Builder bd = new AlertDialog.Builder(getContext());
        bd.setTitle("警告");
        bd.setMessage(msg + ",您将返回登录页面!");
        bd.setCancelable(false);
        bd.setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                MTApplication.getInstance().onFinishAllActivities();
                getContext().startActivity(new Intent(getContext(), LoginActivity.class));
            }
        });
        bd.show();
    }

    @Override
    public void coinOutSuccess(LoginResponse response) {
        CacheTool.setCurrentLoginResponse(response);
        //设置当前的金币数
        setTextViewContent(tvGoldQty,CacheTool.getCurentGold());
    }

    @Override
    public void coinOutFail(String msg) {
        ToastUtils.showShort(this, msg);
    }


    private void updateDesks(FishGetAllDeskResponse response) {
        if (response != null) {
            boolean isnew = true;
            for (FishGetAllDeskResponse vo : mFishDesks) {
                if (vo.getId().equals(response.getId())) {
                    int index = mFishDesks.indexOf(vo);
                    mFishDesks.remove(vo);
                    if (index >= mFishDesks.size()) {
                        mFishDesks.add(response);
                    } else {
                        mFishDesks.add(index, response);
                    }
                    isnew = false;
                    break;
                }
            }
            if (isnew) {
                mFishDesks.add(response);
            }
        }

        mDeskFragments.clear();
        for (FishGetAllDeskResponse vo : mFishDesks) {
            DeskBaseFragment desk = null;
            if (vo.getDevicetype() == 0) {
                desk = new DeskSixFragment();
            } else if (vo.getDevicetype() == 1) {
                desk = new DeskEightFragment();
            }
            desk.setDeskVO(vo);
            mDeskFragments.add(desk);
        }

        if (mFishDesks.size() > 0) {
//            m_machineNameTxt.setText(mFishDesks.get(0).getDevicename());
            // TODO: 2018/8/26 设置当前桌号
        }
        mAdapter.notifyDataSetChanged();
    }

    /**
     * 当用户信息发生变化是更新用户信息
     *
     * @param userinfo
     */
    @Override
    protected void updateUserInfo(LoginResponse userinfo) {
        super.updateUserInfo(userinfo);
        //设置当前的金币数
        setTextViewContent(tvGoldQty,CacheTool.getCurentGold());
    }

    @OnClick({R.id.iv_return, R.id.leftImg, R.id.rightImg})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            /**
             * 返回
             */
            case R.id.iv_return:
                onBackPressed();
                break;
            /**
             * 左滑
             */
            case R.id.leftImg:
                int preview = vpFishHall.getCurrentItem() - 1;
                if (preview < 0) {
                    preview = 0;
                }
                vpFishHall.setCurrentItem(preview, true);
                break;
            /**
             * 右滑
             */
            case R.id.rightImg:
                int next = vpFishHall.getCurrentItem() + 1;
                if (next >= mDeskFragments.size()) {
                    next = mDeskFragments.size() - 1;
                }
                vpFishHall.setCurrentItem(next, true);
                break;
            default:
                break;
        }
    }
}
