package com.jpacourse.service;

import com.jpacourse.dto.PatientTO;
import com.jpacourse.dto.VisitTO;

import java.util.List;

public interface PatientService {
    PatientTO getPatientById(Long id);
    List<PatientTO> getAllPatients();
    PatientTO createPatient(PatientTO patientTO);
    PatientTO updatePatient(Long id, PatientTO patientTO);
    void deletePatient(Long id);
    List<VisitTO> getVisitsByPatientId(Long patientId);
}
