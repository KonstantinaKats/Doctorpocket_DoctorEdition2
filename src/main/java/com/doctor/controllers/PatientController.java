package com.doctor.controllers;

import com.doctor.model.Location;
import com.doctor.model.Patient;
import com.doctor.repository.LocationRepository;
import com.doctor.repository.PatientMongoRepository;
import com.doctor.repository.PatientSearchRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.io.*;
import java.util.Properties;

@Controller
public class PatientController {

	@Autowired
	PatientMongoRepository patientRepository;
	
	@Autowired
	PatientSearchRepository patientSearchRepository;

	@Autowired
	LocationRepository locationRepository;
	
	@RequestMapping("/patients-list")
	public String home2(Model model) {
		model.addAttribute("patientsList", patientRepository.findAll());
		return "patients-list";
	}

	@RequestMapping("/patient/{id}/{name}")
	public String patientPage(@PathVariable("id") String id, @PathVariable("name") String name, Model model) {
		Patient patient = patientRepository.findByIdAndName(id, name);
		setCollectionName(patient);
		Location location = locationRepository.findFirstByOrderByDate();
		model.addAttribute("patientInfo", patient);
		model.addAttribute("location", location);
		return "patient";
	}

	private void setCollectionName(Patient patient) {
		OutputStream out = null;
		try {

			Properties props = new Properties();

			File f = new File("application.properties");
			if(f.exists()){

				props.load(new FileReader(f));
				//Change your values here
				props.setProperty("collection.name", patient.getPersonal_number());
			}



			out = new FileOutputStream( f );

			System.out.println(props.get("collection.name"));

		}
		catch (Exception e ) {
			e.printStackTrace();
		}
		finally{

			if(out != null){

				try {

					out.close();
				}
				catch (IOException ex) {

					System.out.println("IOException: Could not close myApp.properties output stream; " + ex.getMessage());
					ex.printStackTrace();
				}
			}
		}
	}

	@RequestMapping(value = "/addCar", method = RequestMethod.POST)
	public String addCar(@ModelAttribute Patient car) {
		patientRepository.save(car);
		return "redirect:home2";
	}
	
	@RequestMapping(value = "/search")
	public String search(Model model, @RequestParam String search) {
		model.addAttribute("carList", patientSearchRepository.searchPatients(search));
		model.addAttribute("search", search);
		return "home2";
	}
	
}
