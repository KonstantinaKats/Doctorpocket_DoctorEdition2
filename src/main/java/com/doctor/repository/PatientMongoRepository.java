package com.doctor.repository;

import org.springframework.data.repository.CrudRepository;

import com.doctor.model.Patient;

import java.util.Optional;

public interface PatientMongoRepository extends CrudRepository<Patient, String>{

    Patient findByIdAndName(String id, String name);
}