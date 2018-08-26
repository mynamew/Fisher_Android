package com.szpcqy.fisher.ui.game;

import android.content.Intent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.szpcqy.fisher.R;
import com.szpcqy.fisher.event.pair.SocketResonse;
import com.szpcqy.fisher.mt.MTActivity;
import com.szpcqy.fisher.tool.CacheTool;
import com.szpcqy.fisher.ui.fish.FishHallActivity;

import butterknife.BindView;
import butterknife.OnClick;
import de.hdodenhof.circleimageview.CircleImageView;

/**
 * 选择游戏的界面
 *
 * @author jzk
 * create at: 2018/8/26 13:03
 */
public class GameSelectActivity extends MTActivity {

    @BindView(R.id.iv_head)
    CircleImageView ivHead;
    @BindView(R.id.tv_gold_qty)
    TextView tvGoldQty;
    @BindView(R.id.iv_return)
    ImageView ivReturn;
    @BindView(R.id.iv_set)
    ImageView ivSet;
    @BindView(R.id.iv_arena)
    ImageView ivArena;
    @BindView(R.id.iv_fish)
    ImageView ivFish;
    @BindView(R.id.iv_relax)
    ImageView ivRelax;

    @Override
    public int setLayoutId() {
        return R.layout.activity_game_select;
    }

    @Override
    public void initView() {

    }

    @Override
    public void initData() {
        //设置当前的金币数
        setTextViewContent(tvGoldQty,CacheTool.getCurentGold());
    }

    @Override
    public int[] listenProtocols() {
        return new int[0];
    }

    @Override
    public void execResponse(SocketResonse res) {

    }

    @OnClick({R.id.iv_head, R.id.iv_return, R.id.iv_set, R.id.iv_arena, R.id.iv_fish, R.id.iv_relax})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_head:
                //头像
                break;
            case R.id.iv_return:
                //返回
                onBackPressed();
                break;
            case R.id.iv_set:
                //设置

                break;
            case R.id.iv_arena:
                //竞技场
                break;
            case R.id.iv_fish:
                //捕鱼
                startActivity(new Intent(this, FishHallActivity.class));
                break;
            case R.id.iv_relax:
                //休闲场

                break;
            default:
                break;
        }
    }
}
