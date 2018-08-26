package com.szpcqy.fisher.mt;

import android.content.Context;
import android.support.v7.app.AppCompatDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.szpcqy.fisher.R;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by Master on 2017/11/1.
 */

public class MTLightbox {

    public enum IconType {
        PROGRESS, ERROR, SUCCESS
    }

    public static void dismiss(Context context, final MTDialog.MTDialogContent pd, int dismissAfter) {
        dismiss(context, pd, dismissAfter, null);
    }

    public static void dismiss(Context context, final MTDialog.MTDialogContent pd, int dismissAfter, final MTDialog.OnCallback callback) {
        if (callback != null) callback.onShow(pd);
        new Timer().schedule(new TimerTask() {
            @Override
            public void run() {
                pd.close();
                if (callback != null) {
                    callback.onDismiss(pd);
                }
            }
        }, dismissAfter);
    }

    public static void update(Context context, MTDialog.MTDialogContent pd, IconType type, String msg) {
        update(context, pd, type, msg, 0);
    }

    public static void update(Context context, final MTDialog.MTDialogContent pd, IconType type, String msg, int dismissAfter) {
        update(context, pd, type, msg, dismissAfter, null);
    }

    public static void update(String msg, IconType type, MTDialog.MTDialogContent pd) {
        changeLabel(msg, pd.getContent());
        if (type != getCurrentType(pd.getContent())) {
            hideAllIcons(pd.getContent());
            showIcons(type, pd.getContent());
        }
    }

    public static void update(Context context, final MTDialog.MTDialogContent pd, IconType type, String msg, int dismissAfter, final MTDialog.OnCallback callback) {
        if (pd == null) {
            return;
        }
        if (type != getCurrentType(pd.getContent())) {
            hideAllIcons(pd.getContent());
            showIcons(type, pd.getContent());
        }
        changeLabel(msg, pd.getContent());
        if (callback != null) {
            callback.onShow(pd);
        }
        if (dismissAfter > 0) {
            new Timer().schedule(new TimerTask() {
                @Override
                public void run() {
                    pd.close();
                    if (callback != null) {
                        callback.onDismiss(pd);
                    }
                }
            }, dismissAfter);
        }
    }

    public static MTDialog.MTDialogContent show(Context context, IconType type, String msg, boolean isCancelable) {
        return show(context, type, msg, isCancelable, null);
    }

    public static MTDialog.MTDialogContent show(Context context, IconType type, String msg, boolean isCancelable, MTDialog.OnCallback callback) {
        return show(context, type, msg, isCancelable, 0, callback);
    }

    public static MTDialog.MTDialogContent show(Context context, IconType type, String msg, boolean isCancelable, int dismissAfter, MTDialog.OnCallback callback) {
        View view = LayoutInflater.from(context).inflate(R.layout.dialog_loading_global, null);

        TextView tv = view.findViewById(R.id.titleTxt);
        if (msg == null || msg.isEmpty()) tv.setVisibility(View.GONE);
        else {
            tv.setVisibility(View.VISIBLE);
            tv.setText(msg);
        }

        AppCompatDialog dia = MTDialog.make(context, view, R.style.LightboxAnimation, 0.1f, isCancelable, callback);

        hideAllIcons(view);
        showIcons(type, view);

        dia.show();

        final MTDialog.MTDialogContent pd = new MTDialog.MTDialogContent(dia, view);

        if (dismissAfter > 0) {
            new Timer().schedule(new TimerTask() {
                @Override
                public void run() {
                    pd.close();
                }
            }, dismissAfter);
        }
        return pd;
    }


    private static IconType getCurrentType(View view) {
        if (view.findViewById(R.id.doneImg).getVisibility() == View.VISIBLE)
            return IconType.SUCCESS;
        if (view.findViewById(R.id.probar).getVisibility() == View.VISIBLE)
            return IconType.PROGRESS;
        if (view.findViewById(R.id.errorImg).getVisibility() == View.VISIBLE) return IconType.ERROR;
        return IconType.PROGRESS;
    }

    private static void showIcons(IconType type, View view) {
        switch (type) {
            case SUCCESS:
                view.findViewById(R.id.doneImg).setVisibility(View.VISIBLE);
                break;
            case PROGRESS:
                view.findViewById(R.id.probar).setVisibility(View.VISIBLE);
                break;
            case ERROR:
                view.findViewById(R.id.errorImg).setVisibility(View.VISIBLE);
                break;
            default:
                break;
        }
    }

    private static void hideAllIcons(View view) {
        view.findViewById(R.id.probar).setVisibility(View.INVISIBLE);
        view.findViewById(R.id.doneImg).setVisibility(View.INVISIBLE);
        view.findViewById(R.id.errorImg).setVisibility(View.INVISIBLE);
    }

    private static void changeLabel(String msg, View view) {
        TextView tv = view.findViewById(R.id.titleTxt);
        tv.setVisibility(View.VISIBLE);
        tv.setText(msg);
    }


}
