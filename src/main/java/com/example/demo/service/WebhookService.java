package com.example.demo.service;

import com.example.demo.model.Appointment;
import org.springframework.http.*;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

@Async
@Service
public class WebhookService {

    private final RestTemplate restTemplate = new RestTemplate();

    public void sendWebhook(Appointment appointment) {

        String url = "http://localhost:8082/webhook";

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        // Convert appointment to payload
        Map<String, Object> payload = new HashMap<>();
        payload.put("appointmentId", appointment.getId());
        payload.put("patientId", appointment.getPatientId());
        payload.put("doctorId", appointment.getDoctorId());
        payload.put("appointmentDate", appointment.getDate());
        payload.put("time", appointment.getTime());

        HttpEntity<Map<String, Object>> request =
                new HttpEntity<>(payload, headers);

        restTemplate.postForEntity(url, request, String.class);
    }
}