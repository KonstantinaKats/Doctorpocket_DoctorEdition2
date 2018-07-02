package com.doctor.controllers;

import com.doctor.model.Doctor;
import com.doctor.model.validation.LoginValidator;
import com.doctor.repository.DoctorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class HomeController {
    @Autowired
    private DoctorRepository doctorRepository;

    @Autowired
    private LoginValidator loginValidator;

    @InitBinder({"doctor"})
    protected void initBinder(WebDataBinder binder) {
        binder.setValidator(loginValidator);
    }

    @RequestMapping(value = "/home")
    public ModelAndView home(Model model) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("home");
        return modelAndView;
    }

    @RequestMapping(value = "/menu")
    public ModelAndView menu(Model model) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("menu");
        return modelAndView;
    }

    @RequestMapping(value = "/login")
    public ModelAndView mainDisplay(Model model, @Validated @ModelAttribute("doctor") Doctor doctor,
                                    BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("error", "Your username and password is invalid.");
            ModelAndView modelAndView = new ModelAndView();
            modelAndView.setViewName("home");
            return modelAndView;
        } else {
            ModelAndView modelAndView = new ModelAndView();
            modelAndView.setViewName("menu");
            return modelAndView;
        }
    }
}
