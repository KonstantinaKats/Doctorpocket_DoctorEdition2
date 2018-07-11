package com.doctor.Services;

import java.util.ArrayList;
import java.util.concurrent.CompletableFuture;

import com.doctor.Services.HeaderRequestInterceptor;
import org.springframework.http.HttpEntity;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class AndroidPushNotificationsService {

    private static final String FIREBASE_SERVER_KEY = "AAAAr1izuu8:APA91bEyU4JXBIhulblooOE4pyLeVcXnFmSQiqdyJAm8fYNe_fDX8-WsU492d7j2zit5jufieoS7bLqnbHDU3zwNJ351uyavbfpJbccuEzHKA1mpm1KDKboZej1nZVhMSiabkbXTHims33jXm71OykPVTvs64i2uHQ";
    private static final String FIREBASE_API_URL = "https://fcm.googleapis.com/fcm/send";

    @Async
    public CompletableFuture<String> send(HttpEntity<String> entity) {

        RestTemplate restTemplate = new RestTemplate();


        ArrayList<ClientHttpRequestInterceptor> interceptors = new ArrayList<>();
        interceptors.add(new HeaderRequestInterceptor("Authorization", "key=" + FIREBASE_SERVER_KEY));
        interceptors.add(new HeaderRequestInterceptor("Content-Type", "application/json"));
        restTemplate.setInterceptors(interceptors);

        String firebaseResponse = restTemplate.postForObject(FIREBASE_API_URL, entity, String.class);

        return CompletableFuture.completedFuture(firebaseResponse);
    }
}
