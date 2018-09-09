package com.szpcqy.fisher.view;

import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;

import com.jzk.utilslibrary.SpUtils;
import com.szpcqy.fisher.R;
import com.szpcqy.fisher.net.Gateway;

import es.dmoral.toasty.Toasty;


/**
 * 加币的弹出框
 *
 * @author jzk
 * create at: 2018/8/29 13:51
 */

public class ServerSetDialog extends MyDialog {
    private EditText etWifiName, etWifiPsw, etServerIp;

    public ServerSetDialog(Context context) {
        super(context, R.layout.dialog_set_server);
        getWindow().setBackgroundDrawable(new ColorDrawable());
        WindowManager.LayoutParams lp = getWindow().getAttributes();
        lp.width = WindowManager.LayoutParams.WRAP_CONTENT;
        lp.height = WindowManager.LayoutParams.WRAP_CONTENT;
        getWindow().setGravity(Gravity.CENTER);
        getWindow().setAttributes(lp);
        initView();
    }

    /**
     * 设置监听器
     */
    private void initView() {
        etWifiName = getRootView().findViewById(R.id.et_wifi_name);
        etWifiPsw = getRootView().findViewById(R.id.et_wifi_psw);
        etServerIp = getRootView().findViewById(R.id.et_server_ip);
        etWifiName.setText(SpUtils.getInstance().getString(getContext(), Gateway.SERVER_SSID));
        etWifiPsw.setText(SpUtils.getInstance().getString(getContext(), Gateway.SERVER_SSPW));
        etServerIp.setText(SpUtils.getInstance().getString(getContext(), Gateway.SERVER_IP));
        getRootView().findViewById(R.id.iv_close).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ServerSetDialog.this.dismiss();
            }
        });
        getRootView().findViewById(R.id.btn_confirm).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String wifiName = etWifiName.getText().toString().trim();
                if (TextUtils.isEmpty(wifiName)) {
                    Toasty.warning(getContext(), "请输入wifi名称").show();
                    return;
                }
                String wifiPsw = etWifiPsw.getText().toString().trim();
                if (TextUtils.isEmpty(wifiPsw)) {
                    Toasty.warning(getContext(), "请输入wifi密码").show();
                    return;
                }
                String serverIp = etServerIp.getText().toString().trim();
                if (TextUtils.isEmpty(serverIp)) {
                    Toasty.warning(getContext(), "请输入serverIp").show();
                    return;
                }
                SpUtils.getInstance().putString(getContext(), Gateway.SERVER_SSID, wifiName);
                SpUtils.getInstance().putString(getContext(), Gateway.SERVER_SSPW, wifiPsw);
                SpUtils.getInstance().putString(getContext(), Gateway.SERVER_IP, serverIp);
                Toasty.success(getContext(), "保存成功！").show();
                ServerSetDialog.this.dismiss();
            }
        });
        this.setCancelByOutside(true);
    }

}
