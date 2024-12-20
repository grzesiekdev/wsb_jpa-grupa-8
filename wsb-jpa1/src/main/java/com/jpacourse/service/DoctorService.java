package com.jpacourse.service;

import com.jpacourse.dto.DoctorTO;

import java.util.List;

public interface DoctorService {
    DoctorTO getDoctorById(Long id);
    List<DoctorTO> getAllDoctors();
    DoctorTO createDoctor(DoctorTO doctorTO);
    DoctorTO updateDoctor(Long id, DoctorTO doctorTO);
    void deleteDoctor(Long id);
}
