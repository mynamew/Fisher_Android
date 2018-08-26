package com.szpcqy.fisher.mt;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.szpcqy.fisher.event.pair.SocketResonse;
import com.szpcqy.fisher.ui.base.presenter.MvpPresenter;
import com.szpcqy.fisher.ui.base.view.MvpView;
/** 
  * mvp activity
  * @author   jzk
  * create at: 2018/8/26 10:55
  */  

public abstract class MTMvpActivity<V extends MvpView, P extends MvpPresenter<V>> extends MTActivity {

    private P presenter;
    private V view;

    public P getPresenter() {
        return presenter;
    }

    public V getView() {
        return view;
    }



    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void initMVp() {
        super.initMVp();
        /**
         * 绑定 presenter
         */
        if (this.presenter == null) {
            this.presenter = createPresenter();
        }
        /**
         * view
         */
        if (this.view == null) {
            this.view = createView();
        }
        /**
         * attach
         */
        if (this.presenter != null && this.view != null) {
            this.presenter.attachView(view);
        }
    }

    /**
     * 绑定P层
     *
     * @return
     */
    public abstract P createPresenter();

    /**
     * 创建View层
     *
     * @return
     */
    public abstract V createView();

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (this.presenter != null) {
            this.presenter.dettachView();
            this.presenter = null;
        }
    }
    @Override
    public void finish() {
        super.finish();
    }

    /**
     * 实现获取Socket返回事件
     * @param res
     */
    @Override
    public void execResponse(SocketResonse res) {
       presenter.socketResponse(res);
    }
}
