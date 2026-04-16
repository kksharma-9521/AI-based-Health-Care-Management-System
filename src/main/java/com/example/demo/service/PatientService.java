package com.example.demo.service;

import com.example.demo.model.Patient;
import com.example.demo.repository.PatientRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PatientService {

    private static final Logger logger = LoggerFactory.getLogger(PatientService.class);
    @Autowired
    private PatientRepository patientRepository;

    public Page<Patient> getAllPatients(int page, int size){
        try{
            logger.info("Fetching all patients");
            Pageable pageable = PageRequest.of(page, size);
            //interact with repository layer
            return patientRepository.findAll(pageable);
        }
        catch (Exception e){
            logger.error("Error fetching all patients: ",e);
            return null;
        }
    }

    public Patient updatePatientById(Long id, Patient updatedpatient){
        try {
            logger.info("Updating patient with id {}", id);
            Optional<Patient> existingPatient = patientRepository.findById(id);
            if (existingPatient.isPresent()){
                Patient p = existingPatient.get();
                p.setName(updatedpatient.getName());
                p.setAge(updatedpatient.getAge());
                p.setGender(updatedpatient.getGender());
                patientRepository.save(p);

                return updatedpatient;
            }
            else {
                logger.error("Patient with ID {} not found",id);
                return null;
            }
        }
        catch (Exception e){
            logger.error("Error Updating patient {}", id, e);
            return null;
        }
    }

    public Patient getPatientById(Long id){
        try {
            logger.info("getting patient by id {}",id);
            Optional<Patient> patient = patientRepository.findById(id);
            return patient.orElse(null);
        }
        catch (Exception e){
            logger.error("Error fetching patient with id {}", id, e);
            return null;
        }
    }

    public Patient createPatient(Patient patient){
        try {
            logger.info("Creating new patient");
            patientRepository.save(patient);
            return null;
        }
        catch (Exception e){
            logger.error("Error creating patient", e);
            return null;
        }
    }

    public void deletePatientById(Long id){
        try {
            logger.info("Deleting patient with id {}", id);
            patientRepository.deleteById(id);
        }
        catch (Exception e){
            logger.error("An error occurred while deleting patient with id: {}",id,e);
        }
    }

}
