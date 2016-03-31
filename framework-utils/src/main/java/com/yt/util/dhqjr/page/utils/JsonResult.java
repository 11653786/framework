package com.yt.util.dhqjr.page.utils;


import com.yt.util.yt.annotation.RestAttribute;

/**
 * 
 * JSON模型
 * 
 * 用户后台向前台返回的JSON对象
 * 
 * @author liuyijun
 * 
 */
public class JsonResult implements java.io.Serializable {

	public JsonResult() {
		super();
	}

	public JsonResult(boolean success, String message) {
		super();
		this.success = success;
		this.msg = message;
	}

	public JsonResult(boolean success) {
		super();
		this.success = success;
	}

	public JsonResult(boolean success, String message, Object obj) {
		super();
		this.msg = message;
		this.success = success;
		this.obj = obj;
	}

	public JsonResult(boolean success, Object obj) {
		super();
		this.success = success;
		this.obj = obj;
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = -5116979826265269142L;

	@RestAttribute(name = "是否成功", remark = "是否成功")
	private boolean success;
	@RestAttribute(name = "消息信息", remark = "消息信息，一般情况下只有出现错误才会有错误呀信息，如果成功该值为空", notnull = false)
	private String msg;
	@RestAttribute(name = "返回结果中的对象信息", remark = "返回结果中的对象信息，该信息在特定情况有值，一般情况下为空", notnull = false)
	private Object obj;

	public boolean getSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public Object getObj() {
		return obj;
	}

	public void setObj(Object obj) {
		this.obj = obj;
	}

}
