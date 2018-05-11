package com.intelmas.controller.dto;

import java.io.Serializable;

/** Model class for WidgetList JSON object request
 * @author Intelma
 *
 */
public class WidgetList implements Serializable {

	private static final long serialVersionUID = -2589692065595402562L;

	private Integer id;

	private String name;

	private String type;

	private Data_widget data;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Data_widget getData() {
		return data;
	}

	public void setData(Data_widget data) {
		this.data = data;
	}

}