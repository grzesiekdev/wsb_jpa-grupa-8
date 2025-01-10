package com.jpacourse.service.impl;

import com.jpacourse.dto.PatientTO;
import com.jpacourse.dto.VisitTO;
import com.jpacourse.mapper.PatientMapper;
import com.jpacourse.persistence.dao.PatientDao;
import com.jpacourse.persistence.dao.VisitDao;
import com.jpacourse.persistence.entity.PatientEntity;
import com.jpacourse.persistence.entity.VisitEntity;
import com.jpacourse.rest.exception.EntityNotFoundException;
import com.jpacourse.service.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PatientServiceImpl implements PatientService {

    @Autowired
    private PatientDao patientRepository;

    @Autowired
    private VisitDao visitRepository;

    @Override
    public PatientTO getPatientById(Long id) {
        PatientEntity patientEntity = patientRepository.findOne(id);
        if (patientEntity == null) {
            throw new EntityNotFoundException(id);
        }
        return PatientMapper.toPatientTO(patientEntity);
    }


    @Override
    public List<PatientTO> getAllPatients() {
        return patientRepository.findAll()
                .stream()
                .map(PatientMapper::toPatientTO)
                .collect(Collectors.toList());
    }

    @Override
    public PatientTO createPatient(PatientTO patientTO) {
        PatientEntity patientEntity = PatientMapper.toPatientEntity(patientTO);
        patientRepository.save(patientEntity);
        return PatientMapper.toPatientTO(patientEntity);
    }

    @Override
    public PatientTO updatePatient(Long id, PatientTO patientTO) {
        PatientEntity patientEntity = patientRepository.findOne(id);

        if (patientEntity == null) {
            throw new EntityNotFoundException(id);
        }

        patientEntity.setFirstName(patientTO.getFirstName());
        patientEntity.setLastName(patientTO.getLastName());
        patientEntity.setDateOfBirth(patientTO.getDateOfBirth());
        patientEntity.setShoeSize(patientTO.getShoeSize());

        patientEntity = patientRepository.save(patientEntity);
        return PatientMapper.toPatientTO(patientEntity);
    }

    @Override
    public void deletePatient(Long id) {
        if (!patientRepository.exists(id)) {
            throw new EntityNotFoundException(id);
        }
        patientRepository.delete(id);
    }

    public List<VisitTO> getVisitsByPatientId(Long patientId) {
        List<VisitEntity> visitEntities = visitRepository.findAllByPatientId(patientId);
        if (visitEntities.isEmpty()) {
            return Collections.emptyList();
        }

        List<VisitTO> visitTOs = visitEntities.stream().map(this::convertToVisitTO).collect(Collectors.toList());
        Collections.reverse(visitTOs); // Reverse the list in place
        return visitTOs;
    }

    private VisitTO convertToVisitTO(VisitEntity visitEntity) {
        VisitTO visitTO = new VisitTO();
        visitTO.setVisitDate(visitEntity.getVisitDate());
        return visitTO;
    }

}
