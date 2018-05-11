package com.intelmas.dto.model;

import java.util.UUID;

import org.springframework.data.cassandra.mapping.Column;
import org.springframework.data.cassandra.mapping.PrimaryKey;
import org.springframework.data.cassandra.mapping.Table;


/** Entity class mapping of nodes_type table in DB.
 * @author Intelma
 *
 */
@Table(value="node_type_list")
public class NodesTypeEntity {

	@PrimaryKey
	private UUID type_id;
	
	@Column(value="type_organisation")
	private String type_organisation;
	
	@Column(value="type_name")
	private String type_name;
	
	@Column(value="type_description")
	private String type_description;
	
	@Column(value="type_tech")
	private String type_tech;

	public UUID getType_id() {
		return type_id;
	}

	public void setType_id(UUID type_id) {
		this.type_id = type_id;
	}

	public String getType_organisation() {
		return type_organisation;
	}

	public void setType_organisation(String type_organisation) {
		this.type_organisation = type_organisation;
	}

	public String getType_name() {
		return type_name;
	}

	public void setType_name(String type_name) {
		this.type_name = type_name;
	}

	public String getType_description() {
		return type_description;
	}

	public void setType_description(String type_description) {
		this.type_description = type_description;
	}

	public String getType_tech() {
		return type_tech;
	}

	public void setType_tech(String type_tech) {
		this.type_tech = type_tech;
	}	
}
