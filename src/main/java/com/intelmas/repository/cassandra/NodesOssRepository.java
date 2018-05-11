package com.intelmas.repository.cassandra;

import java.util.UUID;

import org.springframework.data.cassandra.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.intelmas.dto.model.NodeTopologyEntity;

public interface NodesOssRepository extends CrudRepository<NodeTopologyEntity, UUID> {
	
	@Query("SELECT * FROM nodes_by_oss WHERE node_name = :node")
	NodeTopologyEntity findByNodeName(@Param("node") String node);

}
