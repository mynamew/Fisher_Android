package com.szpcqy.fisher.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.View;

import com.szpcqy.fisher.data.fish.FishGetAllDeskResponse;

/**
 * Created by Master on 2018/4/14.
 */

abstract public class DeskBaseFragment extends Fragment implements IDesk {

    public interface OnClickListener{
        void onDeviceClick();
        void onSlotClick(int pos);
    }

    private View m_rootView;
    protected OnClickListener m_listener;
    private FishGetAllDeskResponse m_fishVO;

    public void setDeskVO(FishGetAllDeskResponse vo){
        m_fishVO = vo;
        updateDesk();
    }

    protected void updateDesk(){
        //
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        m_rootView = view;
        m_rootView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(m_listener!=null) {
                    m_listener.onDeviceClick();
                }
            }
        });
    }

    public void setOnClickListener(OnClickListener lis){
        m_listener = lis;
    }

    @Override
    public View getContent() {
        return m_rootView;
    }

    public FishGetAllDeskResponse getDeskFishVO() {
        return m_fishVO;
    }
}
