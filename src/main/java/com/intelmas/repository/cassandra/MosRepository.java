package com.intelmas.repository.cassandra;

import java.util.List;

import org.springframework.data.cassandra.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.intelmas.dto.model.MosEntity;

public interface MosRepository extends CrudRepository<MosEntity, String> {

	@Query("SELECT * FROM mos_by_node WHERE node = :node")
	List<MosEntity> getCells(@Param("node") String node);

}
