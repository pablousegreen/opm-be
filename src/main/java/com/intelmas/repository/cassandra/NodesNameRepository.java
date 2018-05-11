package com.intelmas.repository.cassandra;

import java.util.List;

import org.springframework.data.cassandra.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.intelmas.dto.model.NodesOssEntity;

public interface NodesNameRepository extends CrudRepository<NodesOssEntity, String> {

	@Query("SELECT * FROM nodes_by_organisation where node_organisation = :vendor and node_type = :node_type")
	List<NodesOssEntity> findByTypeAndOrganisation(@Param("vendor") String vendor, @Param("node_type") String node_type);
}
