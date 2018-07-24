package com.agentaDemo.bean;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class TestData {

	@SerializedName("Test1")
	@Expose
	private List<Test1> test1 = null;
	@SerializedName("Test2")
	@Expose
	private List<Test2> test2 = null;

	public List<Test1> getTest1() {
		return test1;
	}

	public void setTest1(List<Test1> test1) {
		this.test1 = test1;
	}

	public List<Test2> getTest2() {
		return test2;
	}

	public void setTest2(List<Test2> test2) {
		this.test2 = test2;
	}

}
