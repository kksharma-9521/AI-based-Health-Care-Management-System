package com.example.demo.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Bill {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private  String patientId;
    private BigDecimal amount;
    private int paymentMode;
    @JsonFormat(pattern = "dd-MM-yyyy")
    private LocalDate date;
    @JsonFormat(pattern = "HH:mm:ss")
    private LocalTime time;
    @PrePersist
    public void setDateAndTime() {
        System.out.println(">>> PrePersist is running");
        this.date = LocalDate.now();
        this.time = LocalTime.now();
    }
}
