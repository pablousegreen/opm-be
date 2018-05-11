package com.intelmas.repository.cassandra;

import java.util.UUID;

import org.springframework.data.repository.CrudRepository;

import com.intelmas.dto.model.SoftAlarmsEntity;

public interface SoftAlarmsRepository extends CrudRepository<SoftAlarmsEntity, UUID> {

}
