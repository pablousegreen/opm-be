package com.intelmas.controller.dto;


/** Model class for message JSON response about result success/fail of operations
 * @author Intelma
 *
 */
public class MessageResponse {

	private String success;
	private String msg;
	private String error;
	
	public String getSuccess() {
		return success;
	}
	public void setSuccess(String success) {
		this.success = success;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	public String getError() {
		return error;
	}
	public void setError(String error) {
		this.error = error;
	}
}
