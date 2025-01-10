package com.jpacourse.persistence.dao;

import com.jpacourse.persistence.entity.AddressEntity;
import com.jpacourse.persistence.entity.VisitEntity;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
public class VisitDaoTest {

    @Autowired
    private VisitDao visitDao;

    @Test
    public void testFindByPatientId_ShouldReturnVisits() {
        // Assuming patient with ID 1 exists in data.sql and has visits
        List<VisitEntity> result = visitDao.findAllByPatientId(1L);

        // Then
        assertThat(result).isNotNull();
        assertThat(result).isNotEmpty();
        assertThat(result.get(0).getPatient().getId()).isEqualTo(1L);
    }

    @Test
    public void testFindByPatientId_NoVisits() {
        // Assuming patient with ID 99 exists but has no visits in data.sql
        List<VisitEntity> result = visitDao.findAllByPatientId(4L);

        // Then
        assertThat(result).isNotNull();
        assertThat(result).isEmpty();
    }
}
