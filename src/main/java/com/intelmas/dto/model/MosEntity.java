package com.intelmas.dto.model;

import org.springframework.cassandra.core.PrimaryKeyType;
import org.springframework.data.cassandra.mapping.PrimaryKeyColumn;
import org.springframework.data.cassandra.mapping.Table;

/** Entity class of mos_by_node table on DB.
 * @author Intelma
 *
 */
@Table(value="mos_by_node")
public class MosEntity {

	@PrimaryKeyColumn(name = "node", ordinal = 0, type = PrimaryKeyType.PARTITIONED)
	private String node;
	
	@PrimaryKeyColumn(name = "moid", ordinal = 0, type = PrimaryKeyType.CLUSTERED)
	private String moid;

	public String getNode() {
		return node;
	}

	public void setNode(String node) {
		this.node = node;
	}

	public String getMoid() {
		return moid;
	}

	public void setMoid(String moid) {
		this.moid = moid;
	}
	
	
}
