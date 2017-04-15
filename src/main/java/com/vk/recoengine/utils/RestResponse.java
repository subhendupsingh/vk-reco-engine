package com.vk.recoengine.utils;

import java.util.ArrayList;
import java.util.List;

public class RestResponse<T> {
	
	private int status;
	private List<T> data;
	private String message;
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public List<T> getData() {
		return data;
	}
	public void setData(List<T> data) {
		this.data = data;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public void addData(T t){
		this.data=new ArrayList<T>();
		this.data.add(t);
	}
	

}
