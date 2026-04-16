package com.example.demo.api;

import com.example.demo.model.Patient;
import com.example.demo.service.PatientService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/patients")

public class PatientController {

    private static final Logger logger =
            LoggerFactory.getLogger(PatientController.class);

    @Autowired
    private PatientService patientService;

    @GetMapping
    public Page<Patient> getAllPatients(@RequestParam (defaultValue = "0") int page,
                                        @RequestParam(defaultValue = "2") int size){
        logger.info("Received request to fetch all patients");
        return patientService.getAllPatients(page, size);
    }
    @PostMapping
    public Patient createPatient(@RequestBody Patient patient){
        logger.info("Received request to create patient",patient.getName());
        return patientService.createPatient(patient);
    }
    @GetMapping("/{id}")
    public Patient getPatientById(@PathVariable Long id){
        logger.info("Received request to fetch patient by id: {}",id);
        return patientService.getPatientById(id);
    }
    @DeleteMapping("/{id}")
    public void deletePatient(@PathVariable Long id){
        logger.info("Received request to delete patient {}",id);
        patientService.deletePatientById(id);
    }
    @PutMapping("/{id}")
    public void updatePatient(@PathVariable Long id, @RequestBody Patient patient){
        logger.info("Received request to update patient {}",id);
        patientService.updatePatientById(id,patient);
    }
}
