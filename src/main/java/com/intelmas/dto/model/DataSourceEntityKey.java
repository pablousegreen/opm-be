package com.intelmas.dto.model;

import java.io.Serializable;
import java.util.Date;

import org.springframework.cassandra.core.Ordering;
import org.springframework.cassandra.core.PrimaryKeyType;
import org.springframework.data.cassandra.mapping.PrimaryKeyClass;
import org.springframework.data.cassandra.mapping.PrimaryKeyColumn;


/** Entity class of data_source key table on DB.
 * @author Intelma
 *
 */
@PrimaryKeyClass
public class DataSourceEntityKey implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 4557134726953605780L;
	
	@PrimaryKeyColumn(name = "organisation", ordinal = 0, type = PrimaryKeyType.PARTITIONED)
	private String organisation;
	
	@PrimaryKeyColumn(name = "datetime", ordinal = 1, type = PrimaryKeyType.PARTITIONED)
	private Date datetime;
	
	@PrimaryKeyColumn(name = "node", ordinal = 2, type = PrimaryKeyType.PARTITIONED)
	private String node;
	
	@PrimaryKeyColumn(name = "moid", ordinal = 3, type = PrimaryKeyType.CLUSTERED, ordering = Ordering.DESCENDING)
	private String moid;
	
	
	
	public String getOrganisation() {
		return organisation;
	}
	
	public void setOrganisation(String organisation) {
		this.organisation = organisation;
	}

	public Date getDatetime() {
		return datetime;
	}

	public void setDatetime(Date datetime) {
		this.datetime = datetime;
	}

	public String getMoid() {
		return moid;
	}

	public void setMoid(String moid) {
		this.moid = moid;
	}

	public String getNode() {
		return node;
	}

	public void setNode(String node) {
		this.node = node;
	}

	@Override
	public String toString() {
		return "DataSourceEntityKey [organisation=" + organisation + ", datetime=" + datetime + ", node=" + node
				+ ", moid=" + moid + "]";
	}

	
	
	
	
}
