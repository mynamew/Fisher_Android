package com.szpcqy.fisher.ui.fragment;

import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.szpcqy.fisher.R;
import com.szpcqy.fisher.net.vo.GameSLotVO;
import com.szpcqy.fisher.tool.CacheTool;


/**
 * Created by Master on 2018/4/14.
 */
public class DeskEightFragment extends DeskBaseFragment {


    private View rootView;
    @Override
    public DeskType getDeskType() {
        return DeskType.EIGHT_SLOT;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootView = LayoutInflater.from(getContext()).inflate(R.layout.fragment_eight, container, false);
        updateDesk();
        return rootView;
    }

    @Override
    protected void updateDesk(){
        if(rootView==null) {
            return;
        }
        View user1 = rootView.findViewById(R.id.user1);
        View user2 = rootView.findViewById(R.id.user2);
        View user3 = rootView.findViewById(R.id.user3);
        View user4 = rootView.findViewById(R.id.user4);
        View user5 = rootView.findViewById(R.id.user5);
        View user6 = rootView.findViewById(R.id.user6);
        View user7 = rootView.findViewById(R.id.user7);
        View user8 = rootView.findViewById(R.id.user8);
        updateUser(user1, getDeskFishVO()==null? null : getDeskFishVO().getSlotVO1());
        updateUser(user2, getDeskFishVO()==null? null : getDeskFishVO().getSlotVO2());
        updateUser(user3, getDeskFishVO()==null? null : getDeskFishVO().getSlotVO3());
        updateUser(user4, getDeskFishVO()==null? null : getDeskFishVO().getSlotVO4());
        updateUser(user5, getDeskFishVO()==null? null : getDeskFishVO().getSlotVO5());
        updateUser(user6, getDeskFishVO()==null? null : getDeskFishVO().getSlotVO6());
        updateUser(user7, getDeskFishVO()==null? null : getDeskFishVO().getSlotVO7());
        updateUser(user8, getDeskFishVO()==null? null : getDeskFishVO().getSlotVO8());

        user1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(m_listener!=null) {
                    m_listener.onSlotClick(1);
                }
            }
        });
        user2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(m_listener!=null) {
                    m_listener.onSlotClick(2);
                }
            }
        });
        user3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(m_listener!=null) {
                    m_listener.onSlotClick(3);
                }
            }
        });
        user4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(m_listener!=null) {
                    m_listener.onSlotClick(4);
                }
            }
        });
        user5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(m_listener!=null) {
                    m_listener.onSlotClick(5);
                }
            }
        });
        user6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(m_listener!=null) {
                    m_listener.onSlotClick(6);
                }
            }
        });
        user7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(m_listener!=null) {
                    m_listener.onSlotClick(7);
                }
            }
        });
        user8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(m_listener!=null) {
                    m_listener.onSlotClick(8);
                }
            }
        });
    }

    private void updateUser(View userView, GameSLotVO slotVO){
        //名字
        TextView nameTxt = userView.findViewById(R.id.nameTxt);
        //空缺
        ImageView lackImg = userView.findViewById(R.id.lackImg);
        //用户
        ImageView userImg = userView.findViewById(R.id.userImg);
        //维修
        ImageView repareImg = userView.findViewById(R.id.repareImg);
        //座椅
        ImageView userSeat = userView.findViewById(R.id.user_seat);
        AnimationDrawable userSeatAnimate = (AnimationDrawable) userSeat.getDrawable();
        //userSeatAnimate.start();

        if(getDeskFishVO()==null || getDeskFishVO().getEnable()==0 || slotVO==null || slotVO.getEnable()==0){
            //直接设置为不可用
            nameTxt.setVisibility(View.INVISIBLE);
            lackImg.setVisibility(View.INVISIBLE);
            userImg.setVisibility(View.INVISIBLE);
            repareImg.setVisibility(View.VISIBLE);
            userSeat.setVisibility(View.VISIBLE);
            userSeatAnimate.stop();
        }else{
            if(slotVO.getUserVO()==null) {
                //空闲中
                nameTxt.setVisibility(View.INVISIBLE);
                lackImg.setVisibility(View.VISIBLE);
                userImg.setVisibility(View.INVISIBLE);
                repareImg.setVisibility(View.INVISIBLE);
                userSeat.setVisibility(View.VISIBLE);
                userSeatAnimate.start();
            }else{
                //使用中
                nameTxt.setVisibility(View.VISIBLE);
                lackImg.setVisibility(View.INVISIBLE);
                userImg.setVisibility(View.VISIBLE);
                repareImg.setVisibility(View.INVISIBLE);
                userSeat.setVisibility(View.VISIBLE);
                userSeatAnimate.stop();
                if(slotVO.getUserVO().getId().equals(CacheTool.getCurrentLoginResponse().getUserVO().getId())){
                    nameTxt.setText("我自己");
                }else{
                    nameTxt.setText(slotVO.getUserVO().getLoginname());
                    Log.d("MT", "名字");
                }
            }
        }
    }


}
