package com.example.demo.service;

import com.example.demo.model.Doctor;
import com.example.demo.repository.DoctorRepository;
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
public class DoctorService {

    private static final Logger logger = LoggerFactory.getLogger(DoctorService.class);
    @Autowired
    private DoctorRepository doctorRepository;
    public Page<Doctor> getAllDoctors(int page, int size){
        try {
            logger.info("Fetching all doctors");
            Pageable pageable = PageRequest.of(page, size);
            return doctorRepository.findAll(pageable);
        }catch (Exception e){
            logger.error("Error fetching all doctors: ",e);
            return null;
        }
    }

    public Doctor createDoctor(Doctor doctor){
        try {
            logger.info("Creating doctor{}",doctor.getName());
            doctorRepository.save(doctor);
            return doctor;
        }catch (Exception e){
            logger.error("Error creating doctor: ",e);
            return null;
        }
    }

    public Doctor updateDoctorById(Long id, Doctor updatedDoctor){
        try {
            logger.info("Updating doctor by id {}", id);
            Optional<Doctor> existingDoctor = doctorRepository.findById(id);
            if (existingDoctor.isPresent()){
                Doctor d = existingDoctor.get();
                d.setId(updatedDoctor.getId());
                d.setName(updatedDoctor.getName());
                d.setAge(updatedDoctor.getAge());
                d.setSpeciality(updatedDoctor.getSpeciality());
            }
            else {
                logger.error("Doctor with ID {} not found",id);
                return null;
            }
            return null;
        }catch (Exception e){
            logger.error("Error updating doctor : {}",id,e);
            return null;
        }
    }

    public Doctor getDoctorById(Long id){
        try {
            logger.info("Fetching doctor");
            doctorRepository.findById(id);
            return null;
        }catch (Exception e){
            logger.error("Error fetching doctor by id: {}",id,e);
            return null;
        }
    }

    public void deleteDoctorById(Long id){
        try {
            logger.info("Deleting doctor");
            doctorRepository.deleteById(id);
        }catch (Exception e){
            logger.error("Error deleting doctor: {}",id,e);

        }
    }
}
