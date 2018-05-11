package com.intelmas.controller.dto;

/** Model class for node types JSON response
 * @author Intelma
 *
 */
public class NodeTypeCountResponse {

	private String type;
	private int total;
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public int getTotal() {
		return total;
	}
	public void setTotal(int total) {
		this.total = total;
	}
}
