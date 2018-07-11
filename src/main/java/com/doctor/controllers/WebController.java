package com.doctor.controllers;

import java.util.Date;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

import com.doctor.Services.AndroidPushNotificationsService;
import com.doctor.model.Notifications;
import com.doctor.model.Patient;
import com.doctor.repository.NotificationsRepository;
import com.doctor.repository.PatientMongoRepository;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;


@RestController
public class WebController {

    private final String TOPIC = "/topics/Ear disorders";
    private final String TOKEN = "eAXesPZE8E4:APA91bF8VE2ZDWKcqVoj_tanwkjeEeNOsnFDu6XBehgBHymdNH1eM9yAG0MgL8HPbNFnqLgiYiVhv4HQe8D-u8Xe7VPYuoqW5ibUh7d2HSZ7jFTkzGphYVkT4N_hulf6xMAcI6eJ3S_seZ6r6tSQhxQD2QJOkuxUoQ";

    @Autowired
    AndroidPushNotificationsService androidPushNotificationsService;

    @Autowired
    NotificationsRepository notificationsRepository;

    @Autowired
    PatientMongoRepository patientMongoRepository;

    @RequestMapping(value = "/send/{id}", method = RequestMethod.GET, produces = "application/json")
    public ModelAndView send(@PathVariable(value = "id") String patient_id, @ModelAttribute("newMessage") Notifications notifications) throws JSONException {

        Optional<Patient> patient = patientMongoRepository.findById(patient_id);
        notifications.setPatient_name(patient.get().getName());
        notifications.setPatient_surname(patient.get().getSurname());
        notifications.setPatient_id(patient_id);
        notifications.setDate(new Date());
        notificationsRepository.save(notifications);

        JSONObject body = new JSONObject();
        body.put("to", TOKEN);
        body.put("priority", "high");

        JSONObject notification = new JSONObject();
        notification.put("title", notifications.getTitle());
        notification.put("body", notifications.getBody());


        body.put("notification", notification);

/**
 {
 "notification": {
 "title": "JSA Notification",
 "body": "Happy Message!"
 },
 "to": "/topics/JavaSampleApproach",
 "priority": "high"
 }
 */

        HttpEntity<String> request = new HttpEntity<>(body.toString());

        CompletableFuture<String> pushNotification = androidPushNotificationsService.send(request);
        CompletableFuture.allOf(pushNotification).join();
        try {
            String firebaseResponse = pushNotification.get();

            //return new ResponseEntity<>(firebaseResponse, HttpStatus.OK);
            final RedirectView redirectView = new RedirectView("/patient/" + patient_id + "/" + patient.get().getName() +"", true);
            return new ModelAndView(redirectView);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        //return new ResponseEntity<>("Push Notification ERROR!", HttpStatus.BAD_REQUEST);
        final RedirectView redirectView = new RedirectView("/patient/" + patient_id + "/" + patient.get().getName() +"", true);
        return new ModelAndView(redirectView);
    }
}