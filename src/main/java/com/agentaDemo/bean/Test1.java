package com.agentaDemo.bean;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Test1 {

	@SerializedName("url")
	@Expose
	private String url;
	@SerializedName("username")
	@Expose
	private String username;
	@SerializedName("pwd")
	@Expose
	private String pwd;
	@SerializedName("ext")
	@Expose
	private String ext;

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPwd() {
		return pwd;
	}

	public void setPwd(String pwd) {
		this.pwd = pwd;
	}

	public String getExt() {
		return ext;
	}

	public void setExt(String ext) {
		this.ext = ext;
	}
	
	public String toString(){
		return "Test1="+url+"\'"+username+"\'"+pwd+"\'"+ext;
		
	}

}
