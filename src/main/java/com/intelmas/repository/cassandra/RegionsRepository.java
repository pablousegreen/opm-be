package com.intelmas.repository.cassandra;

import java.util.List;

import org.springframework.data.cassandra.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.intelmas.dto.model.RegionsEntity;

public interface RegionsRepository extends CrudRepository<RegionsEntity, String> {

	@Query("select * from region_list where region_organisation = :vendor")
	List<RegionsEntity> findByOrganisation(@Param("vendor") String vendor);
}
