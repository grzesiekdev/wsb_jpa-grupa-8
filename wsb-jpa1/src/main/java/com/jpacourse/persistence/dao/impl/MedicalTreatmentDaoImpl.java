package com.jpacourse.persistence.dao.impl;

import com.jpacourse.persistence.dao.MedicalTreatmentDao;
import com.jpacourse.persistence.entity.MedicalTreatmentEntity;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.Optional;

@Repository
@Transactional
public class MedicalTreatmentDaoImpl extends AbstractDao<MedicalTreatmentEntity, Long> implements MedicalTreatmentDao {

    @PersistenceContext
    private EntityManager entityManager;

    public MedicalTreatmentDaoImpl() {super(MedicalTreatmentEntity.class);}

    @Override
    public Optional<MedicalTreatmentEntity> findById(Long id) {
        return Optional.ofNullable(entityManager.find(MedicalTreatmentEntity.class, id));
    }
}
