package com.intelmas.repository.cassandra;

import java.util.List;
import java.util.Set;

import org.springframework.data.cassandra.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.intelmas.dto.model.DataSourceEntity;
import com.intelmas.dto.model.DataSourceEntityKey;

public interface DataSourceRepository extends CrudRepository<DataSourceEntity, DataSourceEntityKey>{

	@Query("SELECT * FROM data_source WHERE organisation = :vendor AND datetime = :datetime AND node = :node")
	List<DataSourceEntity> findCells(@Param("vendor") String vendor, @Param("datetime") String datetime, @Param("node") String node);
	
	@Query("SELECT * FROM data_source WHERE organisation = :vendor AND datetime = :datetime AND node = :node AND moid = :mo")
	List<DataSourceEntity> findByNodeMoDatetime(@Param("vendor") String vendor, @Param("datetime") String datetime, @Param("node") String node, @Param("mo") String mo);

	@Query("SELECT * FROM data_source WHERE organisation = :vendor AND datetime in (:datetime) AND node = :node AND moid = :mo")
	List<DataSourceEntity> findByResolution(@Param("vendor") String vendor, @Param("datetime") Set<String> datetime, @Param("node") String node, @Param("mo") String mo);

}
