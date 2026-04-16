package com.example.demo.api;

import com.example.demo.dto.AppointmentRequestDTO;
import com.example.demo.model.Appointment;
import com.example.demo.service.AppointmentService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;
import com.example.demo.service.WebhookService;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/appointments")
public class AppointmentController {

    public static final Logger logger = (Logger) LoggerFactory.getLogger(AppointmentController.class);

    @Autowired
    private AppointmentService appointmentService;
    @Autowired
    private WebhookService webhookService;

    @GetMapping
    public Page<Appointment> getAppointments(Pageable pageable){
        logger.info("Received request to fetch all appointments");
        System.out.println("🔥 CONTROLLER CALLED 🔥");
        return appointmentService.getAllAppointments(pageable);
    }
    @PostMapping
    public Appointment addAppointment(@RequestBody AppointmentRequestDTO dto){
        logger.info("Received request to add appointment");
        Appointment appointment = appointmentService.createAppointment(dto);
        //Prepare the webhook payload
        Map<String,Object> payload = new HashMap<>();
        payload.put("appointmentId", appointment.getId());
        payload.put("patientId", appointment.getPatientId());
        payload.put("doctorId", appointment.getDoctorId());
        payload.put("appointmentDate", appointment.getDate());
        // Send the Webhook
        String webhookUrl = "http://localhost:8082/webhook";
        try {
            webhookService.sendWebhook(appointment);
        } catch (Exception e) {
            System.out.println("Webhook failed, but continuing...");
        }
        return appointment;
    }
    @GetMapping("/{id}")
    public Appointment getAppointmentById(@PathVariable Long id){
        logger.info("Received request to fetch appointment by id");
        return appointmentService.getAppointmentById(id);
    }

    @PutMapping("/{id}")
    public void updateAppointment(@PathVariable Long id, @RequestBody Appointment appointment){
        logger.info("Received request to update appointment");
        appointmentService.updateAppointmentById(id,appointment);
    }
    @DeleteMapping("/{id}")
    public void deleteAppointmentById(@PathVariable Long id){
        logger.info("Received request to delete appointment");
        appointmentService.deleteAppointmentById(id);
    }
}
