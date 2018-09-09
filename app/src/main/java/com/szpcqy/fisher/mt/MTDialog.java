package com.szpcqy.fisher.mt;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.AppCompatDialog;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
/**
 * Created by Master on 2017/10/30.
 */

public class MTDialog {

    static public class MTDialogContent {
        private AppCompatDialog dia;
        private View view;

        public MTDialogContent(AppCompatDialog dia, View view) {
            this.dia = dia;
            this.view = view;
        }

        public void close() {
            MTDialog.close(this.dia);
            this.dia = null;
            this.view = null;
        }

        public AppCompatDialog getDialog() {
            return this.dia;
        }

        public View getContent() {
            return this.view;
        }
    }

    public interface OnCallback {
        void onShow(MTDialogContent dialog);

        void onDismiss(MTDialogContent dialog);
    }

    public static AppCompatDialog show(Context context, int viewRes, int aniRes, float bgAlpha, boolean isCancelable, final OnCallback callback) {
        AppCompatDialog dia = make(context, viewRes, aniRes, bgAlpha, isCancelable, callback);
        dia.show();
        return dia;
    }

    protected static AppCompatDialog make(Context context, int viewRes, int aniRes, float bgAlpha, boolean isCancelable, final OnCallback callback) {
        View view = LayoutInflater.from(context).inflate(viewRes, null);
        return make(context, view, aniRes, bgAlpha, isCancelable, callback);
    }

    protected static AppCompatDialog make(Context context, final View view, int aniRes, float bgAlpha, boolean isCancelable, final OnCallback callback) {
        final AppCompatDialog dialog = new AppCompatDialog(context);
        dialog.setContentView(view);
        dialog.setCancelable(isCancelable);

        dialog.getWindow().setBackgroundDrawable(new ColorDrawable());

        WindowManager.LayoutParams lp = dialog.getWindow().getAttributes();
        lp.width = WindowManager.LayoutParams.WRAP_CONTENT;
        lp.height = WindowManager.LayoutParams.WRAP_CONTENT;
        dialog.getWindow().setGravity(Gravity.CENTER);
        dialog.getWindow().setAttributes(lp);
        dialog.getWindow().setDimAmount(bgAlpha);
        if (aniRes != 0) {
            dialog.getWindow().setWindowAnimations(aniRes);
        }

        dialog.setOnShowListener(new DialogInterface.OnShowListener() {
            @Override
            public void onShow(DialogInterface dialogInterface) {
                if (callback != null) {
                    callback.onShow(new MTDialogContent(dialog, view));
                }
            }
        });
        dialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
            @Override
            public void onDismiss(DialogInterface dialogInterface) {
                if (callback != null) {
                    callback.onDismiss(new MTDialogContent(dialog, view));
                }
                dialog.setOnDismissListener(null);
                dialog.setOnShowListener(null);
            }
        });

        return dialog;
    }

    public static void close(Dialog mDialogUtils) {
        if (mDialogUtils != null && mDialogUtils.isShowing()) {
            mDialogUtils.dismiss();
        }
    }
}
