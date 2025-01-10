package com.jpacourse.persistence.dao;

import com.jpacourse.persistence.entity.MedicalTreatmentEntity;
import com.jpacourse.persistence.entity.VisitEntity;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.orm.ObjectOptimisticLockingFailureException;
import org.springframework.test.context.junit4.SpringRunner;

import javax.persistence.EntityManager;
import javax.persistence.OptimisticLockException;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.junit.Assert.assertThrows;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MedicalTreatmentDaoTest {

    @Autowired
    private MedicalTreatmentDao medicalTreatmentDao;

    @PersistenceContext
    private EntityManager entityManager;

    @Test
    @Transactional
    public void testOptimisticLocking_ShouldThrowException() {
        // Given: Load the same entity twice from the database
        MedicalTreatmentEntity treatment1 = medicalTreatmentDao.findById(1L).orElseThrow();
        MedicalTreatmentEntity treatment2 = medicalTreatmentDao.findById(1L).orElseThrow();

        entityManager.clear();

        treatment1.setDescription("Updated Description");
        entityManager.merge(treatment1);
        entityManager.flush();  // Forces the version increment to the database

        treatment2.setDescription("Another Update");

//         Then: Attempting to merge should throw an OptimisticLockingFailureException
        assertThrows(OptimisticLockException.class, () -> {
            entityManager.merge(treatment2);
            entityManager.flush();
        });
    }
}
