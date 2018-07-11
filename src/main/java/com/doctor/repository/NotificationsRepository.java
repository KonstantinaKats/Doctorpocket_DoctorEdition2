package com.doctor.repository;

import com.doctor.model.Notifications;
import org.springframework.data.repository.CrudRepository;

public interface NotificationsRepository extends CrudRepository<Notifications, String> {
}
