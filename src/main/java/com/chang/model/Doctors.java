package com.chang.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.io.Serializable;

@Data
@NoArgsConstructor
@Entity
@Table
public class Doctors implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int doctor_id;
    private String doctor_name;

    @ManyToOne
    private Hospital hospital;

    @OneToOne(cascade = CascadeType.ALL)
    private DoctorSchedule doctorSchedule;


    public Doctors(String name, Hospital hospital) {
        this.doctor_name = name;
        this.hospital = hospital;
    }

    public Doctors(String doctor_name) {
        this.doctor_name = doctor_name;
    }
}