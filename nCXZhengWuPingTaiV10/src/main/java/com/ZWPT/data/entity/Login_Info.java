package com.ZWPT.data.entity;

import java.util.List;
import java.util.Map;

public class Login_Info {

	List<Map<String, Object>> list_app;
	Map<String, Object> map_login_status;

	public List<Map<String, Object>> getList_app() {
		return list_app;
	}

	public void setList_app(List<Map<String, Object>> list_app) {
		this.list_app = list_app;
	}

	public Map<String, Object> getMap_login_status() {
		return map_login_status;
	}

	public void setMap_login_status(Map<String, Object> map_login_status) {
		this.map_login_status = map_login_status;
	}
}
