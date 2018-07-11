package com.doctor.controllers;

import com.doctor.model.Patient;
import com.doctor.repository.PatientMongoRepository;
import com.doctor.repository.PatientSearchRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class PatientController {

	@Autowired
	PatientMongoRepository patientRepository;
	
	@Autowired
	PatientSearchRepository patientSearchRepository;
	
	@RequestMapping("/patients-list")
	public String home2(Model model) {
		model.addAttribute("patientsList", patientRepository.findAll());
		return "patients-list";
	}

	@RequestMapping("/edit-patient-rate/{id}/{name}/{rate}")
	public String editPatientRate(@PathVariable("id") String id, @PathVariable("name") String name, @PathVariable("rate") String rate, Model model) {
		Patient patient = patientRepository.findByIdAndName(id,name);
		patient.setRate(rate);
		patientRepository.save(patient);
		model.addAttribute("patientInfo", patient);
		return "patient";
	}

	@RequestMapping("/patient/{id}/{name}")
	public String patientPage(@PathVariable("id") String id, @PathVariable("name") String name, Model model) {
		Patient patient = patientRepository.findByIdAndName(id, name);
		model.addAttribute("patientInfo", patient);
		return "patient";
	}

	
}
