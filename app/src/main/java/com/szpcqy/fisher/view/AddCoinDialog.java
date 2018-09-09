package com.szpcqy.fisher.view;

import android.content.Context;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import com.szpcqy.fisher.R;

import java.util.ArrayList;

import es.dmoral.toasty.Toasty;


/**
 * 加币的弹出框
 *
 * @author jzk
 * create at: 2018/8/29 13:51
 */

public class AddCoinDialog extends MyDialog {
    private TextView tvAdd1, tvAdd5, tvAdd2, tvAdd10, tvAdd50, tvAdd20;
    private TextView tvInputScore, tvCurrentScore;
    private ImageView ivAdd, ivClose, ivSubstrace, ivCommit;
    private ArrayList<TextView> addScores = new ArrayList<>();
    //当前点击要增加/减少的分数
    private int currentAddScore = 0;

    public AddCoinDialog(Context context, OnAddCoinListener listener) {
        super(context, R.layout.dialog_add_coin);
        WindowManager.LayoutParams lp = getWindow().getAttributes();
        lp.width = WindowManager.LayoutParams.MATCH_PARENT;
        lp.height = WindowManager.LayoutParams.WRAP_CONTENT;
        getWindow().setGravity(Gravity.CENTER);
        getWindow().setAttributes(lp);
        initView(listener);
    }

    /**
     * 设置当前分数
     *
     * @param score
     * @return
     */
    public AddCoinDialog setCurrentScore(String score) {
        tvCurrentScore.setText(score);
        return this;
    }

    /**
     * 设置监听器
     *
     * @param listener
     */
    private void initView(OnAddCoinListener listener) {
        tvAdd1 = getRootView().findViewById(R.id.tv_add_1);
        tvAdd5 = getRootView().findViewById(R.id.tv_add_5);
        tvAdd2 = getRootView().findViewById(R.id.tv_add_2);
        tvAdd10 = getRootView().findViewById(R.id.tv_add_10);
        tvAdd50 = getRootView().findViewById(R.id.tv_add_50);
        tvAdd20 = getRootView().findViewById(R.id.tv_add_20);
        addScores.add(tvAdd1);
        addScores.add(tvAdd5);
        addScores.add(tvAdd2);
        addScores.add(tvAdd10);
        addScores.add(tvAdd50);
        addScores.add(tvAdd20);

        tvInputScore = getRootView().findViewById(R.id.tv_input_score);
        tvCurrentScore = getRootView().findViewById(R.id.tv_current_score);
        ivAdd = getRootView().findViewById(R.id.iv_add);
        ivClose = getRootView().findViewById(R.id.iv_close);
        ivSubstrace = getRootView().findViewById(R.id.iv_substract);
        ivCommit = getRootView().findViewById(R.id.iv_ok);

        tvAdd1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setScoreSelect(0);
            }
        });
        tvAdd5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setScoreSelect(1);
            }
        });
        tvAdd2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setScoreSelect(2);
            }
        });
        tvAdd10.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setScoreSelect(3);
            }
        });
        tvAdd50.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setScoreSelect(4);
            }
        });
        tvAdd20.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setScoreSelect(5);
            }
        });
        ivAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String inputScoreStr = tvInputScore.getText().toString().trim();
                int inputScore = Integer.parseInt(inputScoreStr);
                tvInputScore.setText(String.valueOf(inputScore + currentAddScore));
            }
        });
        ivClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });
        ivSubstrace.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String inputScoreStr = tvInputScore.getText().toString().trim();
                int inputScore = Integer.parseInt(inputScoreStr);
                if (inputScore - currentAddScore < 0) {
                    tvInputScore.setText(String.valueOf(0));
                } else {
                    tvInputScore.setText(String.valueOf(inputScore - currentAddScore));
                }

            }
        });
        ivCommit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String inputScoreStr = tvInputScore.getText().toString().trim();
                int inputScore = Integer.parseInt(inputScoreStr);
                if(inputScore<=0){
                    Toasty.warning(getContext(),"加币数量不能小于等于0！").show();
                    return;
                }
                listener.commit(inputScore);
                AddCoinDialog.this.dismiss();
            }
        });
        //设置默认选择为1
        setScoreSelect(0);
        this.setCancelByOutside(true);
    }

    /**
     * 设置是否选中
     *
     * @param position
     */
    public void setScoreSelect(int position) {
        for (int i = 0; i < addScores.size(); i++) {
            if (i == position) {
                addScores.get(i).setSelected(true);
            } else {
                addScores.get(i).setSelected(false);
            }
        }
        switch (position) {
            case 0:
                currentAddScore = 1;
                break;
            case 1:
                currentAddScore = 5;
                break;
            case 2:
                currentAddScore = 2;
                break;
            case 3:
                currentAddScore = 10;
                break;
            case 4:
                currentAddScore = 50;
                break;
            case 5:
                currentAddScore = 20;
                break;
            default:
                break;
        }
        tvInputScore.setText(String.valueOf(currentAddScore));
    }

    /**
     * 点击事件
     */
    public interface OnAddCoinListener {
        void commit(int score);
    }
}
