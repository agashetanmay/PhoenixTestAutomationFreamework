package com.api.constant;

public enum Warrenty_status {

	IN_WARRENTY(1), OUT_WARRENTY(2);

	int code;

	private Warrenty_status(int code) {
		this.code = code;
	}

	public int getCode() {
		return code;
	}

}
