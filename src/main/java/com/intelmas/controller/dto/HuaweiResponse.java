package com.intelmas.controller.dto;

import java.util.Map;

public class HuaweiResponse {
	
	private String node;
	private String datetime;
	private Map<String, String> properties;
	public String getNode() {
		return node;
	}
	public void setNode(String node) {
		this.node = node;
	}
	public String getDatetime() {
		return datetime;
	}
	public void setDatetime(String datetime) {
		this.datetime = datetime;
	}
	public Map<String, String> getProperties() {
		return properties;
	}
	public void setProperties(Map<String, String> properties) {
		this.properties = properties;
	}
}
