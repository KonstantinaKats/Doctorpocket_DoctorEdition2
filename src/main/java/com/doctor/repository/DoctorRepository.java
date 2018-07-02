package com.doctor.repository;

import com.doctor.model.Doctor;
import org.springframework.data.repository.CrudRepository;

public interface DoctorRepository extends CrudRepository<Doctor, String> {

    Doctor findByUsernameAndPassword(String username, String password);
}
