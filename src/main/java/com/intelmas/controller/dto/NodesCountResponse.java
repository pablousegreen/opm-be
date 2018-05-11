package com.intelmas.controller.dto;

import java.util.List;


/** Model class forq nodes count JSON response
 * @author Intelma
 *
 */
public class NodesCountResponse {
	
	private String tech;
	private int total;
	private List<NodeTypeCountResponse> types;
	public String getTech() {
		return tech;
	}
	public void setTech(String tech) {
		this.tech = tech;
	}
	public int getTotal() {
		return total;
	}
	public void setTotal(int total) {
		this.total = total;
	}
	public List<NodeTypeCountResponse> getTypes() {
		return types;
	}
	public void setTypes(List<NodeTypeCountResponse> types) {
		this.types = types;
	}
}
