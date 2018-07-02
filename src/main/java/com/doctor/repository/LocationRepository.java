package com.doctor.repository;

import com.doctor.model.Location;
import org.springframework.data.repository.CrudRepository;

public interface LocationRepository extends CrudRepository<Location, String> {

    Location findFirstByOrderByDate();
}
