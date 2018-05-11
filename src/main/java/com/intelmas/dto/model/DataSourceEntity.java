package com.intelmas.dto.model;

import java.io.Serializable;
import java.util.Date;
import java.util.Map;

import org.springframework.data.cassandra.mapping.PrimaryKey;
import org.springframework.data.cassandra.mapping.Table;

/** Entity class of data_source table on DB.
 * @author Intelma
 *
 */
@Table("data_source")
public class DataSourceEntity implements Serializable{

	private static final long serialVersionUID = 4557134726953605780L;
	
	@PrimaryKey
	private DataSourceEntityKey pk;
	
	private String oss;
	
	private Date uploadtime;
	
	private Map<String, String> properties;
	
	public DataSourceEntityKey getPk() {
		return pk;
	}

	public void setPk(DataSourceEntityKey pk) {
		this.pk = pk;
	}

	public String getOss() {
		return oss;
	}

	public void setOss(String oss) {
		this.oss = oss;
	}
	
	public Date getUploadtime() {
		return uploadtime;
	}
	
	public void setUploadtime(Date uploadtime) {
		this.uploadtime = uploadtime;
	}

	public Map<String, String> getProperties() {
		return properties;
	}

	public void setProperties(Map<String, String> properties) {
		this.properties = properties;
	}

	@Override
	public String toString() {
		return "DataSourceEntity [pk=" + pk + ", oss=" + oss + ", uploadtime=" + uploadtime + ", properties="
				+ properties + "]";
	}
	
	
}
