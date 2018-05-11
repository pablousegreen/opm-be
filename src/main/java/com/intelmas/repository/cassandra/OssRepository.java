package com.intelmas.repository.cassandra;

import java.util.List;
import java.util.UUID;

import org.springframework.data.cassandra.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.intelmas.dto.model.OssEntity;

public interface OssRepository extends CrudRepository<OssEntity, UUID>{

	@Query("SELECT * FROM oss_list WHERE oss_organisation = :vendor")
	List<OssEntity> findByOrganisation(@Param("vendor") String vendor);

}
