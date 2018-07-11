package com.doctor.controllers;

import com.doctor.repository.NotificationsRepository;
import com.doctor.repository.PatientMongoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class MessagesController {

    @Autowired
    PatientMongoRepository patientMongoRepository;

    @Autowired
    NotificationsRepository notificationsRepository;

    @RequestMapping("/messages-list")
    public String messages(Model model) {
        model.addAttribute("messagesList", notificationsRepository.findAll());
        return "messages-list";
    }
}
