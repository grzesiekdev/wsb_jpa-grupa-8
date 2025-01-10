package com.jpacourse.persistence.dao.impl;

import com.jpacourse.persistence.dao.PatientDao;
import com.jpacourse.persistence.dao.PatientRepository;
import com.jpacourse.persistence.entity.DoctorEntity;
import com.jpacourse.persistence.entity.PatientEntity;
import com.jpacourse.persistence.entity.VisitEntity;
import com.jpacourse.rest.exception.EntityNotFoundException;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Repository
@Transactional
public class PatientDaoImpl extends AbstractDao<PatientEntity, Long> implements PatientDao {
    public PatientDaoImpl() {
        super(PatientEntity.class);
    }

    @Override
    public void addVisitToPatient(Long patientId, Long doctorId, LocalDateTime visitDate, String description) {
        // Fetch the patient and doctor entities
        PatientEntity patient = entityManager.find(PatientEntity.class, patientId);
        DoctorEntity doctor = entityManager.find(DoctorEntity.class, doctorId);

        if (patient == null) {
            throw new EntityNotFoundException(patientId);
        }

        if (doctor == null) {
            throw new EntityNotFoundException(doctorId);
        }

        // Create a new visit entity
        VisitEntity visit = new VisitEntity();
        visit.setVisitDate(visitDate);
        visit.setDoctor(doctor);
        visit.setPatient(patient);

        // Add the visit to the patient
        patient.getVisits().add(visit);

        // Merge the patient to persist the changes
        entityManager.merge(patient);
    }

    public List<PatientEntity> findByLastName(String lastName) {
        return entityManager.createQuery(
                        "SELECT p FROM PatientEntity p WHERE p.lastName = :lastName", PatientEntity.class)
                .setParameter("lastName", lastName)
                .getResultList();
    }

    @Override
    public List<PatientEntity> findPatientsWithMoreThanXVisits(long visitCount) {
        return entityManager.createQuery(
                        "SELECT p FROM PatientEntity p " +
                                "JOIN p.visits v " +
                                "GROUP BY p " +
                                "HAVING COUNT(v) > :visitCount", PatientEntity.class)
                .setParameter("visitCount", visitCount)
                .getResultList();
    }

    @Override
    public List<PatientEntity> findPatientsWithShoeSizeLowerThan(int shoeSize) {
        return entityManager.createQuery(
                        "SELECT p FROM PatientEntity p WHERE p.shoeSize < :shoeSize", PatientEntity.class)
                .setParameter("shoeSize", shoeSize)
                .getResultList();
    }
}
