package com.doctor;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

import static org.springframework.boot.SpringApplication.*;

@SpringBootApplication
@EnableMongoRepositories("com.doctor.repository")
public class DoctorApplication {

	public static void main(String[] args) {
	    run(DoctorApplication.class, args);
	}

}
