package com.szpcqy.fisher.mt;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.AppCompatButton;
import android.util.AttributeSet;
import android.view.MotionEvent;

import com.szpcqy.fisher.R;


/**
 * Created by Master on 2018/4/13.
 */

public class MTButton extends AppCompatButton {

    public interface OnMTButtonCallback {
        void onPress(MotionEvent e);
        void onRelease(MotionEvent e);
    }

    private Drawable m_normal;
    private Drawable m_press;
    private Drawable m_disable;
    private OnMTButtonCallback m_listener;

    public MTButton(Context context) {
        this(context, null);
    }
    public MTButton(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }
    public MTButton(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        applyParams(attrs, defStyleAttr);
    }

    public void setCallback(OnMTButtonCallback listener){
        m_listener = listener;
    }

    private void applyParams(AttributeSet attrs, int defStyleAttr) {
        TypedArray ta = this.getContext().obtainStyledAttributes(attrs, R.styleable.MTButton, defStyleAttr, 0);

        m_disable = ta.getDrawable(R.styleable.MTButton_disableBg);
        m_normal = ta.getDrawable(R.styleable.MTButton_normalBg);
        m_press = ta.getDrawable(R.styleable.MTButton_pressBg);
        ta.recycle();

        if(this.isEnabled()) {
            if(m_normal!=null) {
                setBackgroundDrawable(m_normal);
            } else {
                setBackgroundDrawable(m_disable);
            }
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        boolean bol = super.onTouchEvent(event);
        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            if(this.isEnabled()) {
                setBackgroundDrawable(m_press);
            } else {
                setBackgroundDrawable(m_disable);
            }
            if(m_listener!=null) {
                m_listener.onPress(event);
            }
            return true;
        } else if(event.getAction()== MotionEvent.ACTION_UP) {
            if(this.isEnabled()) {
                setBackgroundDrawable(m_normal);
            } else {
                setBackgroundDrawable(m_disable);
            }
            if(m_listener!=null) {
                m_listener.onRelease(event);
            }
            return true;
        }
        return bol;
    }


}
