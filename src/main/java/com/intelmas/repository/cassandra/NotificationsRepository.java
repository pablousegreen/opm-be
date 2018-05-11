package com.intelmas.repository.cassandra;

import java.util.UUID;

import org.springframework.data.repository.CrudRepository;

import com.intelmas.dto.model.NotificationsEntity;

public interface NotificationsRepository extends CrudRepository<NotificationsEntity, UUID>{

}
