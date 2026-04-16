package com.example.demo.dto;

import lombok.Data;

import java.time.LocalDate;

@Data
public class AppointmentRequestDTO {

    private LocalDate date;
    private String time;
    private String patientId;
    private String doctorId;
}