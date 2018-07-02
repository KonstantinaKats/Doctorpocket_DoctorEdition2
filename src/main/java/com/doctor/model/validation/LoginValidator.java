package com.doctor.model.validation;

import com.doctor.model.Doctor;
import com.doctor.repository.DoctorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;

@Component
public class LoginValidator extends DoctorPocketValidator{
    @Autowired
    private DoctorRepository doctorRepository;

    @Override
    public boolean supports(Class<?> aClass) {
        return Doctor.class.isAssignableFrom(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {

        String username = ((Doctor) o).getUsername();
        String password = ((Doctor) o).getPassword();
        Doctor doctor = doctorRepository.findByUsernameAndPassword(username, password);
        if ("".equals(username) || "".equals(password)){
            rejectField(errors,"username", ERROR_FIELD_REQUIRED);
        }
        if (doctor == null){
            rejectField(errors,"username", FAIL_LOGIN);
        }

    }
}
