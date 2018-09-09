package com.szpcqy.fisher.view;

import android.content.Context;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.szpcqy.fisher.R;

import es.dmoral.toasty.Toasty;


/**
 * 加币的弹出框
 *
 * @author jzk
 * create at: 2018/8/29 13:51
 */

public class RegistDialog extends MyDialog {
    private Button btnCommint;
    private ImageView ivClose;
    private EditText etUser, etPsw, etRepeatPsw, etQuestion, etAnswer, etTel;

    public RegistDialog(Context context, RegistListener listener) {
        super(context, R.layout.dialog_regist);
        WindowManager.LayoutParams lp = getWindow().getAttributes();
        lp.width = WindowManager.LayoutParams.WRAP_CONTENT;
        lp.height = WindowManager.LayoutParams.WRAP_CONTENT;
        getWindow().setGravity(Gravity.CENTER);
        getWindow().setAttributes(lp);
        initView(context, listener);
    }

    /**
     * 初始化view
     *
     * @param context
     * @param listener
     */
    private void initView(Context context, RegistListener listener) {
        etUser = getRootView().findViewById(R.id.et_username);
        etPsw = getRootView().findViewById(R.id.et_psw);
        etRepeatPsw = getRootView().findViewById(R.id.et_repeat_psw);
        etTel = getRootView().findViewById(R.id.et_tel);
        etQuestion = getRootView().findViewById(R.id.et_question);
        etAnswer = getRootView().findViewById(R.id.et_answer);
        btnCommint = getRootView().findViewById(R.id.btn_confirm);
        ivClose = getRootView().findViewById(R.id.iv_close);
        ivClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RegistDialog.this.dismiss();
            }
        });
        btnCommint.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String user = etUser.getText().toString().trim();
                if (TextUtils.isEmpty(user)) {
                    Toasty.warning(context, "请输入用户名！").show();
                    return;
                }
                String psw = etPsw.getText().toString().trim();
                if (TextUtils.isEmpty(psw)) {
                    Toasty.warning(context, "请输入密码！").show();
                    return;
                }
                String repeatPsw = etRepeatPsw.getText().toString().trim();
                if (TextUtils.isEmpty(repeatPsw)) {
                    Toasty.warning(context, "请确认密码！").show();
                    return;
                }
                if (!psw.equals(repeatPsw)) {
                    Toasty.warning(context, "两次输入的密码不一致！").show();
                    return;
                }
                String tel = etTel.getText().toString().trim();
                if (TextUtils.isEmpty(tel)) {
                    Toasty.warning(context, "请输入手机号！").show();
                    return;
                }
                String question = etQuestion.getText().toString().trim();
                if (TextUtils.isEmpty(question)) {
                    Toasty.warning(context, "请输入问题！").show();
                    return;
                }
                String answer = etAnswer.getText().toString().trim();
                if (TextUtils.isEmpty(answer)) {
                    Toasty.warning(context, "请输入答案！").show();
                    return;
                }
                RegistDialog.this.dismiss();
                listener.commitRegist(user, psw, repeatPsw,tel, question, answer);
            }
        });
    }

    /**
     * 注冊的监听器
     */
    public interface RegistListener {
        void commitRegist(String user, String psw, String repeatPsw, String tel,String question, String answer);
    }
}
