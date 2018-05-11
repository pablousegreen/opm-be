package com.intelmas.repository.cassandra;

import java.util.List;
import java.util.UUID;

import org.springframework.data.cassandra.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.intelmas.dto.model.NodesTypeEntity;

public interface NodesTypeRepository extends CrudRepository<NodesTypeEntity, UUID>{

	
	@Query("SELECT * FROM node_type_list WHERE type_organisation = :vendor AND type_tech = :tech")
	List<NodesTypeEntity> findByOrganisationAndTech(@Param("vendor") String vendor, @Param("tech") String tech);
}
