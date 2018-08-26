package com.szpcqy.fisher.ui.base.presenter.impl;

import android.content.Context;

import com.szpcqy.fisher.event.pair.SocketResonse;
import com.szpcqy.fisher.ui.base.presenter.MvpPresenter;
import com.szpcqy.fisher.ui.base.view.MvpView;

import java.lang.ref.WeakReference;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;


/**
 * 抽象类 统一管理View层绑定和解除绑定
 *
 * @param <V>
 * @author Dream
 */
public abstract class MvpBasePresenter< V extends MvpView> implements MvpPresenter<V> {

    private WeakReference<Context> weakContext;
    private WeakReference<V> weakView;
    private V proxyView;

    public MvpBasePresenter(Context context) {
        this.weakContext = new WeakReference<Context>(context);
        initModel();
    }

    public Context getContext() {
        return weakContext.get();
    }

    public V getView() {
        return proxyView;
    }

    /**
     * 用于检查View是否为空对象
     *
     * @return
     */
    public boolean isAttachView() {
        if (this.weakView != null && this.weakView.get() != null) {
            return true;
        }
        return false;
    }

    @SuppressWarnings("unchecked")
    @Override
    public void attachView(V view) {
        this.weakView = new WeakReference<V>(view);
        MvpViewInvocationHandler invocationHandler = new MvpViewInvocationHandler(
                this.weakView.get());
        // 在这里采用动态代理
        proxyView = (V) Proxy.newProxyInstance(
                view.getClass().getClassLoader(), view.getClass()
                        .getInterfaces(), invocationHandler);
    }

    @Override
    public void dettachView() {
        if (this.weakView != null) {
            this.weakView.clear();
            this.weakView = null;
        }
    }

    private class MvpViewInvocationHandler implements InvocationHandler {

        private MvpView mvpView;

        public MvpViewInvocationHandler(MvpView mvpView) {
            this.mvpView = mvpView;
        }

        @Override
        public Object invoke(Object arg0, Method arg1, Object[] arg2)
                throws Throwable {
            if (isAttachView()) {
                return arg1.invoke(mvpView, arg2);
            }
            return null;
        }

    }

    /**
     * 初始化model
     */
    public abstract void initModel();

    @Override
    public void socketResponse(SocketResonse res) {
//        getView().dismisProgressDialog();

        if (res.getCode() == 1000) {
            onSucess(res.getProtocol(), res);
        } else {
            onFail(res.getProtocol(), res.getMsg());
        }
    }

    /**
     * socket请求成功
     *
     * @param protocol
     * @param res
     */
    public abstract void onSucess(int protocol, SocketResonse res);

    /**
     * socket请求失败
     *
     * @param protocol
     * @param msg
     */
    public abstract void onFail(int protocol, String msg);
}
