package model;

public class ExeResultModel {

	private boolean success;
	private String msg;
	private String code;

	public ExeResultModel() {

	}

	public ExeResultModel(boolean success, String msg) {
		this.success = success;
		this.msg = msg;
	}

	public boolean getSuccess() {
		return this.success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}

	public String getMsg() {
		return this.msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public String getCode() {
		return this.code;
	}

	public void setCode(String code) {
		this.code = code;
	}
}
