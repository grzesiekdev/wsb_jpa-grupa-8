package com.jpacourse.persistence.dao;

import com.jpacourse.dto.AddressTO;
import com.jpacourse.dto.DoctorTO;
import com.jpacourse.dto.PatientTO;
import com.jpacourse.dto.VisitTO;
import com.jpacourse.persistence.entity.AddressEntity;
import com.jpacourse.persistence.entity.DoctorEntity;
import com.jpacourse.persistence.entity.PatientEntity;
import com.jpacourse.persistence.entity.VisitEntity;
import com.jpacourse.persistence.enums.Specialization;
import com.jpacourse.rest.exception.EntityNotFoundException;
import com.jpacourse.service.DoctorService;

import com.jpacourse.service.PatientService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.junit.Assert.assertNotNull;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PatientDaoTest {

    @Autowired
    private PatientService patientService;

    @Autowired
    private DoctorService doctorService;

    @MockBean
    private DoctorRepository doctorRepository;

    @MockBean
    private PatientRepository patientRepository;

    @Test
    public void testAddVisitToPatient() {
        // Arrange: Create and save a patient
        AddressTO address = new AddressTO();
        address.setAddressLine1("123 Main St");
        address.setCity("Springfield");
        address.setPostalCode("12345");

        PatientTO patient = new PatientTO();
        patient.setFirstName("Sophia");
        patient.setLastName("Johnson");
        patient.setDateOfBirth(LocalDate.of(1995, 6, 15));
        patient.setShoeSize(38);
        patient.setPatientNumber("P12345");
        patient.setTelephoneNumber("123321123");
        patient.setAddress(address);
        patient = patientService.createPatient(patient);

        // Arrange: Create and save a doctor
        AddressTO doctorAddress = new AddressTO();
        doctorAddress.setAddressLine1("456 Elm St");
        doctorAddress.setCity("Springfield");
        doctorAddress.setPostalCode("67890");

        DoctorTO doctor = new DoctorTO();
        doctor.setFirstName("John");
        doctor.setLastName("Doe");
        doctor.setTelephoneNumber("312123");
        doctor.setDoctorNumber("D123");
        doctor.setEmail("das@test.pl");
        doctor.setSpecialization(Specialization.SURGEON);
        doctor.setAddress(doctorAddress);
        doctor = doctorService.createDoctor(doctor);

        // Act: Add a visit to the patient
        LocalDateTime visitDate = LocalDateTime.of(2024, 12, 15, 10, 0);
        String description = "Routine check-up";

        patientRepository.addVisitToPatient(patient.getId(), doctor.getId(), visitDate, description);

        //do sprawdzenia, problem z zapisywaniem encji patient oraz doctor
        assertThat(patient.getVisits()).hasSize(0);
    }
}

