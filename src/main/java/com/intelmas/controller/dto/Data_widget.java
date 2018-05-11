package com.intelmas.controller.dto;

import java.io.Serializable;

/** Model class Data_widget for JSON objects request
 * @author Intelma
 *
 */
public class Data_widget implements Serializable {

	private static final long serialVersionUID = 7633031536162083954L;

	private String level;

	private String region;

	private String node;

	private String mo;

	private String oss;

	private String initDate;

	private String initTime;

	private String finishDate;

	private String finishTime;

	public String getLevel() {
		return level;
	}

	public void setLevel(String level) {
		this.level = level;
	}

	public String getRegion() {
		return region;
	}

	public void setRegion(String region) {
		this.region = region;
	}

	public String getNode() {
		return node;
	}

	public void setNode(String node) {
		this.node = node;
	}

	public String getMo() {
		return mo;
	}

	public void setMo(String mo) {
		this.mo = mo;
	}

	public String getOss() {
		return oss;
	}

	public void setOss(String oss) {
		this.oss = oss;
	}

	public String getInitDate() {
		return initDate;
	}

	public void setInitDate(String initDate) {
		this.initDate = initDate;
	}

	public String getInitTime() {
		return initTime;
	}

	public void setInitTime(String initTime) {
		this.initTime = initTime;
	}

	public String getFinishDate() {
		return finishDate;
	}

	public void setFinishDate(String finishDate) {
		this.finishDate = finishDate;
	}

	public String getFinishTime() {
		return finishTime;
	}

	public void setFinishTime(String finishTime) {
		this.finishTime = finishTime;
	}

}