package com.szpcqy.fisher.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.support.annotation.Nullable;
import android.support.v7.widget.AppCompatImageView;
import android.util.AttributeSet;
import android.view.MotionEvent;

import com.szpcqy.fisher.R;

/**
 * Created by Master on 2018/3/21.
 */

public class MTImageView extends AppCompatImageView {

    public interface OnMTImageViewCallback {
        void onPress(MotionEvent e);
        void onRelease(MotionEvent e);
    }


    private Drawable m_normal;
    private Drawable m_press;
    private OnMTImageViewCallback m_listener;


    public MTImageView(Context context) {
        this(context, null);
    }

    public MTImageView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public MTImageView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        applyParams(attrs, defStyleAttr);
    }

    public void setCallback(OnMTImageViewCallback listener){
        m_listener = listener;
    }

    private void applyParams(AttributeSet attrs, int defStyleAttr) {
        TypedArray ta = this.getContext().obtainStyledAttributes(attrs, R.styleable.MTImageView, defStyleAttr, 0);

        m_normal = ta.getDrawable(R.styleable.MTImageView_normalImg);
        m_press = ta.getDrawable(R.styleable.MTImageView_pressImg);
        ta.recycle();

        setImageDrawable(m_normal);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        boolean bol = super.onTouchEvent(event);
        if(super.isEnabled()) {
            if (event.getAction() == MotionEvent.ACTION_DOWN) {
                setImageDrawable(m_press);
                if (m_listener != null) m_listener.onPress(event);
                return true;
            } else if (event.getAction() == MotionEvent.ACTION_UP) {
                setImageDrawable(m_normal);
                if (m_listener != null) m_listener.onRelease(event);
                return true;
            }
        }
        return bol;
    }


    @Override
    public void setEnabled(boolean enabled) {
        super.setEnabled(enabled);
        if(!enabled){
            m_normal.setAlpha(200);
            m_press.setAlpha(200);
        }else{
            m_normal.setAlpha(255);
            m_press.setAlpha(255);
        }
    }
}
