package com.example.demo.service;

import com.example.demo.dto.AppointmentRequestDTO;
import com.example.demo.model.Appointment;
import com.example.demo.repository.AppointmentRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

@Service
public class AppointmentService {

    private static final Logger logger = LoggerFactory.getLogger(AppointmentService.class);
    @Autowired
    private AppointmentRepository appointmentRepository;
    public Page<Appointment> getAllAppointments(Pageable pageable){
        System.out.println("🔥 SERVICE METHOD CALLED 🔥");
        try {
            logger.info("Fetching all Appointments");
            return appointmentRepository.findAll(pageable);
        }
        catch (Exception e){
            logger.error("Error fetching all appointments: ",e);
            return null;
        }
    }

    public Appointment getAppointmentById(Long id){
        try {
            logger.info("Fetching Appointment");
            appointmentRepository.findById(String.valueOf(id)).orElse(null);
            return appointmentRepository.findById(String.valueOf(id)).orElse(null);
        }
        catch (Exception e){
            logger.error("Error fetching appointment by id: {} ",id,e);
            return null;
        }
    }

    public Appointment updateAppointmentById(Long id, Appointment updatedAppointment){
        try {
            logger.info("Updating Appointment");
            Optional<Appointment> existingAppointment = appointmentRepository.findById(String.valueOf(id));
            if (existingAppointment.isPresent()){
                Appointment a = existingAppointment.get();
                a.setDate(updatedAppointment.getDate());
                a.setTime(updatedAppointment.getTime());
                a.setDoctorId(updatedAppointment.getDoctorId());
                a.setPatientId(updatedAppointment.getPatientId());
                return appointmentRepository.save(a);
            }
            else {
                logger.error("Appointment with ID {} not found",id);
                return null;
            }

        }
        catch (Exception e){
            logger.error("Error updating appointment: {}",id,e);
            return null;
        }
    }

    public Appointment createAppointment(AppointmentRequestDTO dto) {

        Appointment appointment = new Appointment();

        appointment.setDate(dto.getDate());

        appointment.setTime(LocalTime.parse(dto.getTime()));

        appointment.setPatientId(dto.getPatientId());   // ✅ FIXED
        appointment.setDoctorId(dto.getDoctorId());     // ✅ FIXED

        return appointmentRepository.save(appointment);
    }

    public void deleteAppointmentById(Long id){
        try {
            logger.info("Deleting Appointment");
            appointmentRepository.deleteById(String.valueOf(id));
        }
        catch (Exception e){
            logger.error("Error deleting appointment: ",e);
        }
    }
}
