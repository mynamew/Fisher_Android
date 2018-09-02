package com.szpcqy.fisher.net.vo;

import com.szpcqy.fisher.data.login.LoginResponse;

import java.io.Serializable;

public class GameSLotVO implements Serializable {

	private String createdate;
	private String id;
	private String com;
	private int enable;
	private LoginResponse.UserVOBeanXX userVO;



	public String getCreatedate() {
		return createdate;
	}
	public void setCreatedate(String createdate) {
		this.createdate = createdate;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getCom() {
		return com;
	}
	public void setCom(String com) {
		this.com = com;
	}
	public int getEnable() {
		return enable;
	}
	public void setEnable(int enable) {
		this.enable = enable;
	}
	public LoginResponse.UserVOBeanXX getUserVO() {
		return userVO;
	}
	public void setUserVO(LoginResponse.UserVOBeanXX userVO) {
		this.userVO = userVO;
	}

}
