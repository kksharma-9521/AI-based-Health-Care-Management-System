package com.example.demo.api;

import com.example.demo.model.Doctor;
import com.example.demo.model.Patient;
import com.example.demo.service.DoctorService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/doctors")

public class DoctorController {

    public static final Logger logger = (Logger) LoggerFactory.getLogger(DoctorController.class);
    @Autowired
    private DoctorService doctorService;

    @GetMapping
    public Page<Doctor> getAllDoctors(@RequestParam(defaultValue = "0")int page,
                                      @RequestParam(defaultValue = "2")int size){
        logger.info("Received request to fetch all doctors");
        return doctorService.getAllDoctors(page, size);
    }
    @PostMapping
    public Doctor createDoctor(@RequestBody Doctor doctor){
        logger.info("Received request to create doctor");
        return doctorService.createDoctor(doctor);
    }
    @GetMapping("/{id}")
    public Doctor getDoctorById(@PathVariable Long id){
        logger.info("Received request to fetch doctor by id");
        return doctorService.getDoctorById(id);
    }
    @PutMapping("/{id}")
    public void updateDoctor(@PathVariable Long id, @RequestBody Doctor doctor){
        logger.info("Received request to update doctor");
         doctorService.updateDoctorById(id,doctor);
    }
    @DeleteMapping("/{id}")
    public void deleteDoctorById(@PathVariable Long id){
        logger.info("Received request to delete doctor");
        doctorService.deleteDoctorById(id);
    }

}
