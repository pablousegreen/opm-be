package com.intelmas.repository.cassandra;

import java.util.List;
import java.util.UUID;

import org.springframework.data.cassandra.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.intelmas.dto.model.DashboardEntity;

public interface DashboardRepository extends CrudRepository<DashboardEntity, UUID> {

	@Query("SELECT * FROM dashboard_user WHERE dashboard_usr = :user ALLOW FILTERING")
	List<DashboardEntity> getListByUser(@Param("user") UUID user);

}
