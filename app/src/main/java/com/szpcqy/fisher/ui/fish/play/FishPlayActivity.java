package com.szpcqy.fisher.ui.fish.play;

import android.animation.ObjectAnimator;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.jzk.utilslibrary.LogUitls;
import com.linkcard.media.VideoSurfaceView;
import com.szpcqy.fisher.R;
import com.szpcqy.fisher.data.login.LoginResponse;
import com.szpcqy.fisher.data.login.UserVo;
import com.szpcqy.fisher.event.pair.SocketRequest;
import com.szpcqy.fisher.mt.MTDialog;
import com.szpcqy.fisher.mt.MTLightbox;
import com.szpcqy.fisher.mt.MTMvpActivity;
import com.szpcqy.fisher.net.SocketProtocol;
import com.szpcqy.fisher.tool.CacheTool;
import com.szpcqy.fisher.tool.Clock;
import com.szpcqy.fisher.ui.fish.desk.FishDeskActivity;
import com.szpcqy.fisher.utils.ActivityUtils;
import com.szpcqy.fisher.vcard.VideoCard;
import com.szpcqy.fisher.view.AddCoinDialog;

import org.greenrobot.eventbus.EventBus;

import butterknife.BindView;
import butterknife.OnClick;
import es.dmoral.toasty.Toasty;
import io.github.controlwear.virtual.joystick.android.JoystickView;

import static com.szpcqy.fisher.ui.fish.desk.FishDeskActivity.DESKID;
import static com.szpcqy.fisher.ui.fish.desk.FishDeskActivity.DES_RELOG;
import static com.szpcqy.fisher.ui.fish.desk.FishDeskActivity.DES_TYPE;

/**
 * 捕鱼的界面  播放视频流
 *
 * @author jzk
 * create at: 2018/8/27 17:26
 */
public class FishPlayActivity extends MTMvpActivity<FishPlayView, FishPlayPresenter> implements FishPlayView {
    //金币数
    static public final String RATIO_COIN_MULTIPLY = "RATIO_COIN_MULTIPLY";
    //位置的bundle
    static public final String SLOT_POSITION = "SLOT_POSITION";
    //桌子的类型 8还是6
    static public final String DESK_TYPE = "DESK_TYPE";
    @BindView(R.id.video_play)
    VideoSurfaceView videoPlay;
    @BindView(R.id.joystick)
    JoystickView joystick;
    @BindView(R.id.iv_add_coin)
    ImageView ivAddCoin;
    @BindView(R.id.iv_return_coin)
    ImageView ivReturnCoin;
    @BindView(R.id.ll_left_menu)
    LinearLayout llLeftMenu;
    @BindView(R.id.tv_gold_qty)
    TextView tvGoldQty;
    @BindView(R.id.iv_add_gun)
    ImageView ivAddGun;
    @BindView(R.id.iv_shoot)
    ImageView ivShoot;
    @BindView(R.id.ll_content)
    LinearLayout llContent;
    @BindView(R.id.ll_bottom)
    LinearLayout llBottom;
    @BindView(R.id.cons_top)
    RelativeLayout consTop;
    /**
     * 左边菜单的属性
     */
    private float fromPosition = 0;
    private float toPosition = 0;
    private float toTopPosition = 0;
    private float toBottomPosition = 0;
    private boolean isShowLeftMenu = true;
    private boolean isShowTopMenu = true;
    private boolean isShowBottomMenu = true;
    /**
     * 方向 开炮
     */
    private int isLeftTouch = 0;
    private int isRightTouch = 0;
    private int isUpTouch = 0;
    private int isDownTouch = 0;
    private int isFireTouch = 0;

    private Clock mClockPlay;
    private int deviceType;
    //计时器
    Clock clockAnima;

    @Override
    public int setLayoutId() {
        return R.layout.activity_fish_play;
    }

    @SuppressLint("ClickableViewAccessibility")
    @Override
    public void initView() {
        /**
         * 操作杆
         */
        joystick.setOnMoveListener(new JoystickView.OnMoveListener() {
            @Override
            public void onMove(int angle, int strength) {
                isLeftTouch = 0;
                isRightTouch = 0;
                isUpTouch = 0;
                isDownTouch = 0;
                if (strength > 50) {
                    if (angle < 45 || angle >= 315) {
                        //right
                        isRightTouch = 1;
                    } else if (angle < 135 && angle >= 45) {
                        //up
                        isUpTouch = 1;
                    } else if (angle < 225 && angle >= 135) {
                        //left
                        isLeftTouch = 1;
                    } else if (angle < 315 && angle >= 225) {
                        //down
                        isDownTouch = 1;
                    }
                }
            }
        }, 1000 / 30);
        /**
         * 发射
         */
        ivShoot.setOnTouchListener((v, event) -> {
            boolean bol = super.onTouchEvent(event);
            if (ivShoot.isEnabled()) {
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    isFireTouch = 1;
                    return true;
                } else if (event.getAction() == MotionEvent.ACTION_UP) {
                    isFireTouch = 0;
                    return true;
                }
            }
            return bol;
        });
        //动画隐藏上下 左右menu
        clockAnima = Clock.create(this).interval(500).count(1).onCompleteOnUI(new Runnable() {
            @Override
            public void run() {
                if (isShowLeftMenu) {
                    toPosition = -llLeftMenu.getWidth() / 3 * 2;
                    //显示隐藏 加币 退币的界面  平移动画
                    ObjectAnimator animator = null;
                    animator = ObjectAnimator.ofFloat(llLeftMenu, "translationX", fromPosition, toPosition);
                    animator.setDuration(500);
                    animator.start();
                    isShowLeftMenu = !isShowLeftMenu;
                }
                if (isShowBottomMenu) {
                    toBottomPosition = llBottom.getHeight() * 2 / 3;
                    //显示隐藏 加币 退币的界面  平移动画
                    ObjectAnimator animatorBottom = null;
                    animatorBottom = ObjectAnimator.ofFloat(llBottom, "translationY", fromPosition, toBottomPosition);
                    animatorBottom.setDuration(500);
                    animatorBottom.start();
                    isShowBottomMenu = !isShowBottomMenu;
                }
                if (isShowTopMenu) {
                    toTopPosition = -consTop.getHeight() / 3;
                    //显示隐藏 加币 退币的界面  平移动画
                    ObjectAnimator animatorTop = null;
                    animatorTop = ObjectAnimator.ofFloat(consTop, "translationY", fromPosition, toTopPosition);
                    animatorTop.setDuration(500);
                    animatorTop.start();
                    isShowTopMenu = !isShowTopMenu;
                }
            }
        });
        clockAnima.start();
    }

    @Override
    public void initData() {
        deviceType = getIntent().getIntExtra(DES_TYPE, 1);
        //分数
        Double ratiocoinscore = super.getIntent().getDoubleExtra(RATIO_COIN_MULTIPLY, 1);
        tvGoldQty.setText(String.valueOf(CacheTool.getCurentGold()));
        String url = "rtsp://" + CacheTool.getCurrentFishDesk().getVideoip() + "/1/h264major";
        VideoCard.playVideo(videoPlay, url);
        mClockPlay = Clock.create(this).interval(10).count(-1).onCountOnIO(new Runnable() {
            @Override
            public void run() {
                if (isFireTouch == 0 && isLeftTouch == 0 && isRightTouch == 0 && isUpTouch == 0 && isDownTouch == 0) {
                    return;
                }
                SocketRequest req = new SocketRequest(SocketProtocol.DIRECTION_FIRE_REQ);
                req.add("isfire", isFireTouch);
                req.add("isleft", isLeftTouch);
                req.add("isright", isRightTouch);
                req.add("isup", isUpTouch);
                req.add("isdown", isDownTouch);
                EventBus.getDefault().post(req);
            }
        }).start();
    }

    @Override
    public int[] listenProtocols() {
        return new int[]{
                SocketProtocol.SWITCH_STRENTH_RES,
                SocketProtocol.DIRECTION_FIRE_RES,
                SocketProtocol.COININ_RES,
                SocketProtocol.COINOUT_RES,
                SocketProtocol.QUIT_SLOT_RES
        };
    }

    @Override
    public FishPlayPresenter createPresenter() {
        return new FishPlayPresenter(this);
    }

    @Override
    public FishPlayView createView() {
        return this;
    }

    MTDialog.MTDialogContent dia;

    @Override
    public void showProgressDialog(int protocol) {

    }

    @Override
    public void dismisProgressDialog() {
    }

    @OnClick({R.id.iv_return, R.id.iv_add_coin, R.id.iv_return_coin, R.id.cons_top, R.id.ll_bottom, R.id.ll_left_menu, R.id.iv_add_gun})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_add_coin:
                LogUitls.e("加币");
                //竞技场
                new AddCoinDialog(this, new AddCoinDialog.OnAddCoinListener() {
                    @Override
                    public void commit(int score) {
                        if (score > 0) {
                            dia = MTLightbox.show(FishPlayActivity.this, MTLightbox.IconType.PROGRESS, "正在投币", false, score * 200, null);
                            //加币的请求
                            getPresenter().addCoin(score);
                        }
                    }
                }).setCurrentScore(String.valueOf(CacheTool.getCurentGold())).show();
                break;
            case R.id.iv_return_coin:
                getPresenter().returnCoin();
                break;
            case R.id.ll_left_menu:
                toPosition = -llLeftMenu.getWidth() / 3 * 2;
                //显示隐藏 加币 退币的界面  平移动画
                ObjectAnimator animator = null;
                if (isShowLeftMenu) {
                    animator = ObjectAnimator.ofFloat(llLeftMenu, "translationX", fromPosition, toPosition);
                } else {
                    animator = ObjectAnimator.ofFloat(llLeftMenu, "translationX", toPosition, fromPosition);
                }
                animator.setDuration(500);
                animator.start();
                isShowLeftMenu = !isShowLeftMenu;
                break;
            case R.id.iv_add_gun:
                //
                //切换大炮
                getPresenter().switchStrength();
                break;
            case R.id.cons_top:
                toTopPosition = -consTop.getHeight() / 3;
                //显示隐藏 加币 退币的界面  平移动画
                ObjectAnimator animatorTop = null;
                if (isShowTopMenu) {
                    animatorTop = ObjectAnimator.ofFloat(consTop, "translationY", fromPosition, toTopPosition);
                } else {
                    animatorTop = ObjectAnimator.ofFloat(consTop, "translationY", toTopPosition, fromPosition);
                }
                animatorTop.setDuration(500);
                animatorTop.start();
                isShowTopMenu = !isShowTopMenu;
                break;
            case R.id.ll_bottom:
                toBottomPosition = llBottom.getHeight() * 2 / 3;
                //显示隐藏 加币 退币的界面  平移动画
                ObjectAnimator animatorBottom = null;
                if (isShowBottomMenu) {
                    animatorBottom = ObjectAnimator.ofFloat(llBottom, "translationY", fromPosition, toBottomPosition);
                } else {
                    animatorBottom = ObjectAnimator.ofFloat(llBottom, "translationY", toBottomPosition, fromPosition);
                }
                animatorBottom.setDuration(500);
                animatorBottom.start();
                isShowBottomMenu = !isShowBottomMenu;
                break;
            case R.id.iv_return:
                onBackPressed();
                break;

            default:
                break;
        }
    }

    @Override
    public void onBackPressed() {
        getPresenter().quitSlot();
    }

    @Override
    public void finish() {
        super.finish();
        if (null != mClockPlay) {
            mClockPlay.stop();
        }
        VideoCard.stopVideo();
        if (dia != null) {
            dia.close();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (dia != null) {
            dia.close();
        }
    }

    @Override
    public void addCoinSuccess(UserVo userVo) {
        LoginResponse currentLoginResponse = CacheTool.getCurrentLoginResponse();
        currentLoginResponse.setUserVO(userVo);
        CacheTool.setCurrentLoginResponse(currentLoginResponse);
    }

    @Override
    public void quitSlotSuccess() {
        Toasty.success(getApplicationContext(), "您已退出游戏，若有分数未退，系统将自动退还到您的账户").show();
        /**
         * 如果Activity栈中有桌位的Activity即FishDeskActivity 则直接返回否则执行跳转操作
         */
        if (ActivityUtils.isExsitActivity(FishDeskActivity.class, this)) {
            finish();
        } else {
            Intent it = new Intent(this, FishDeskActivity.class);
            it.putExtra(DESKID, CacheTool.getCurrentFishDesk().getId());
            it.putExtra(DES_TYPE, deviceType);
            it.putExtra(DES_RELOG, true);
            startActivity(it);
            finish();
        }
    }

    @Override
    public void returnCoinSuccess() {

    }

    @Override
    public void fireSuccess() {
        LogUitls.e("发炮成功！");
    }

    @Override
    public void switchStrengthSuccess() {
        LogUitls.e("切换炮弹成功！");
    }

    /**
     * 更新金币数
     *
     * @param userinfo
     */
    @Override
    protected void updateUserInfo(LoginResponse userinfo) {
        setTextViewContent(tvGoldQty, CacheTool.getCurentGold());
    }
}
