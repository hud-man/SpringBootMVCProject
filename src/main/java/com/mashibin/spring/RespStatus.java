package com.mashibin.spring;

public class RespStatus {
	/**
	 * JSON报文
	 * 状态码
	 * 200=成功
	 * 400/500=失败
	 * 
	 * msg＝信息
	 */
	
	private int code;
	private String data;
	private String msg;
	public RespStatus() {}
	
	
	public RespStatus(int code, String data, String msg) {
		super();
		this.code = code;
		this.data = data;
		this.msg = msg;
	}


	public int getCode() {
		return code;
	}
	public void setCode(int code) {
		this.code = code;
	}
	public String getData() {
		return data;
	}
	public void setData(String data) {
		this.data = data;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	
}
