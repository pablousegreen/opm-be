package com.intelmas.controller.dto;

import com.fasterxml.jackson.annotation.JsonInclude;

/** Model class for nodes model JSON response
 * @author Intelma
 *
 */
@JsonInclude(value=JsonInclude.Include.NON_NULL) 
public class NodesModel {

//	private String node_id;
	private String node_name;
	private String region;
	private String tech;
	private String oss;
	
//	public String getNode_id() {
//		return node_id;
//	}
//	public void setNode_id(String node_id) {
//		this.node_id = node_id;
//	}
	public String getNode_name() {
		return node_name;
	}
	public void setNode_name(String node_name) {
		this.node_name = node_name;
	}
	public String getRegion() {
		return region;
	}
	public void setRegion(String region) {
		this.region = region;
	}
	public String getTech() {
		return tech;
	}
	public void setTech(String tech) {
		this.tech = tech;
	}
	public String getOss() {
		return oss;
	}
	public void setOss(String oss) {
		this.oss = oss;
	}
}
