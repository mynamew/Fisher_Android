package com.szpcqy.fisher.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.szpcqy.fisher.tool.CacheTool;
import com.szpcqy.fisher.ui.fish.desk.FishDeskActivity;
import com.szpcqy.fisher.ui.fragment.DeskBaseFragment;

import java.util.ArrayList;

/**
 * $dsc fragment的adapter
 * author: timi
 * create at: 2018-08-26 15:20
 */
public class MyAdapter extends FragmentPagerAdapter {
    private int mChildCount = 0;
    private  ArrayList<DeskBaseFragment> mDeskFragments;
    private Context mContext;
    public MyAdapter(FragmentManager fm, ArrayList<DeskBaseFragment> mDeskFragments, Context context) {
        super(fm);
        this.mDeskFragments=mDeskFragments;
        this.mContext=context;
    }

    @Override
    public DeskBaseFragment getItem(int position) {
        DeskBaseFragment frag = mDeskFragments.get(position);
        final String deskId = frag.getDeskFishVO().getId();
        final int devicetype = frag.getDeskFishVO().getDevicetype();
        frag.setOnClickListener(new DeskBaseFragment.OnClickListener() {
            @Override
            public void onDeviceClick() {
                CacheTool.setCurrentFishDesk(frag.getDeskFishVO());
                // TODO: 2018/8/26 跳转到桌位
                Intent it = new Intent(mContext, FishDeskActivity.class);
                it.putExtra(FishDeskActivity.DESKID, deskId);
                it.putExtra(FishDeskActivity.DES_TYPE, devicetype);
                mContext.startActivity(it);
            }

            @Override
            public void onSlotClick(int pos) {
                CacheTool.setCurrentFishDesk(frag.getDeskFishVO());
                // TODO: 2018/8/26 跳转到桌位
                Intent it = new Intent(mContext, FishDeskActivity.class);
                it.putExtra(FishDeskActivity.DESKID, deskId);
                it.putExtra(FishDeskActivity.DES_TYPE, devicetype);
                mContext.startActivity(it);
            }
        });
        return frag;
    }

    @Override
    public void notifyDataSetChanged() {
        mChildCount = getCount();

//            FragmentManager fm = getSupportFragmentManager();
//            FragmentTransaction ft = fm.beginTransaction();
//            List<Fragment> fragments = fm.getFragments();
//            if(fragments != null && fragments.size() >0){
//                for (int i = 0; i < fragments.size(); i++) {
//                    ft.remove(fragments.get(i));
//                }
//
//            }
//            ft.commit();

        super.notifyDataSetChanged();
    }

    @Override
    public int getItemPosition(Object object) {
        return POSITION_NONE;

        //            if ( mChildCount > 0) {
//                // 这里利用判断执行若干次不缓存，刷新
//                mChildCount --;
//                // 返回这个是强制ViewPager不缓存，每次滑动都刷新视图
//                return POSITION_NONE;
//            }
//            // 这个则是缓存不刷新视图
//            return super.getItemPosition(object);
    }

    @Override
    public int getCount() {
        return mDeskFragments.size();
    }


}

