package com.szpcqy.fisher.ui.base.presenter;


import com.szpcqy.fisher.data.CommonResponse;
import com.szpcqy.fisher.event.pair.CommonRequest;
import com.szpcqy.fisher.event.pair.SocketResonse;
import com.szpcqy.fisher.ui.base.view.MvpView;

/**
 * 抽象为接口
 * 
 * @author Dream
 *
 */
public interface MvpPresenter<V extends MvpView> {

	/**
	 * 绑定视图
	 * 
	 * @param view
	 */
	 void attachView(V view);

	/**
	 * 解除绑定
	 */
	 void dettachView();

	void socketResponse(SocketResonse res);
}
