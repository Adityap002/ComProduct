package com.agentaDemo.bean;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Test2 {

	@SerializedName("url")
	@Expose
	private String url;
	@SerializedName("username_ch1")
	@Expose
	private String usernameCh1;
	@SerializedName("pwd_ch1")
	@Expose
	private String pwdCh1;
	@SerializedName("ext_ch1")
	@Expose
	private String extCh1;
	@SerializedName("username_ch2")
	@Expose
	private String usernameCh2;
	@SerializedName("pwd_ch2")
	@Expose
	private String pwdCh2;
	@SerializedName("ext_ch2")
	@Expose
	private String extCh2;
	@SerializedName("username_ff")
	@Expose
	private String usernameFf;
	@SerializedName("pwd_ff")
	@Expose
	private String pwdFf;
	@SerializedName("ext_ff")
	@Expose
	private String extFf;

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getUsernameCh1() {
		return usernameCh1;
	}

	public void setUsernameCh1(String usernameCh1) {
		this.usernameCh1 = usernameCh1;
	}

	public String getPwdCh1() {
		return pwdCh1;
	}

	public void setPwdCh1(String pwdCh1) {
		this.pwdCh1 = pwdCh1;
	}

	public String getExtCh1() {
		return extCh1;
	}

	public void setExtCh1(String extCh1) {
		this.extCh1 = extCh1;
	}

	public String getUsernameCh2() {
		return usernameCh2;
	}

	public void setUsernameCh2(String usernameCh2) {
		this.usernameCh2 = usernameCh2;
	}

	public String getPwdCh2() {
		return pwdCh2;
	}

	public void setPwdCh2(String pwdCh2) {
		this.pwdCh2 = pwdCh2;
	}

	public String getExtCh2() {
		return extCh2;
	}

	public void setExtCh2(String extCh2) {
		this.extCh2 = extCh2;
	}

	public String getUsernameFf() {
		return usernameFf;
	}

	public void setUsernameFf(String usernameFf) {
		this.usernameFf = usernameFf;
	}

	public String getPwdFf() {
		return pwdFf;
	}

	public void setPwdFf(String pwdFf) {
		this.pwdFf = pwdFf;
	}

	public String getExtFf() {
		return extFf;
	}

	public void setExtFf(String extFf) {
		this.extFf = extFf;
	}
}
