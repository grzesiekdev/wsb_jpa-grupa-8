package com.jpacourse.service;

import com.jpacourse.dto.AddressTO;
import com.jpacourse.dto.DoctorTO;
import com.jpacourse.dto.PatientTO;
import com.jpacourse.dto.VisitTO;
import com.jpacourse.persistence.dao.Dao;
import com.jpacourse.persistence.dao.DoctorRepository;
import com.jpacourse.persistence.enums.Specialization;
import com.jpacourse.rest.exception.EntityNotFoundException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import javax.print.Doc;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static com.jpacourse.persistence.enums.Specialization.SURGEON;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.junit.Assert.assertNotNull;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PatientServiceTest {

    @Autowired
    private PatientService patientService;

    @Autowired
    private DoctorService doctorService;

    @MockBean
    private DoctorRepository doctorRepository;

    @Test
    @Transactional
    public void testShouldDeletePatientAndCascadeVisits() {
        // Arrange: Create an address
        AddressTO addressTO = new AddressTO();
        addressTO.setAddressLine1("123 Main St");
        addressTO.setCity("Springfield");
        addressTO.setPostalCode("12345");

        PatientTO patientTO = new PatientTO();
        patientTO.setFirstName("Sophia");
        patientTO.setLastName("Johnson");
        patientTO.setDateOfBirth(LocalDate.of(1995, 6, 15));
        patientTO.setShoeSize(38);
        patientTO.setPatientNumber("P12345");
        patientTO.setTelephoneNumber("123321123");
        patientTO.setAddress(addressTO); // Set address

        PatientTO savedPatient = patientService.createPatient(patientTO);
        Long id = savedPatient.getId();
        assertThat(patientService.getPatientById(id)).isNotNull(); // Ensure patient is saved

        // Act: Delete the patient
        patientService.deletePatient(id);

        // Assert: Ensure patient is deleted
        assertThatThrownBy(() -> patientService.getPatientById(id))
                .isInstanceOf(EntityNotFoundException.class)
                .hasMessageContaining("Could not find entity of id " + id);
    }

    @Test
    public void testShouldFetchPatientByIdWithCorrectStructure() {
        // Arrange: Create an address
        AddressTO addressTO = new AddressTO();
        addressTO.setAddressLine1("123 Main St");
        addressTO.setCity("Springfield");
        addressTO.setPostalCode("12345");

        PatientTO patientTO = new PatientTO();
        patientTO.setFirstName("Sophia");
        patientTO.setLastName("Johnson");
        patientTO.setDateOfBirth(LocalDate.of(1995, 6, 15));
        patientTO.setShoeSize(38);
        patientTO.setPatientNumber("P12345");
        patientTO.setTelephoneNumber("123321123");
        patientTO.setAddress(addressTO); // Set address

        PatientTO savedPatient = patientService.createPatient(patientTO);

        // Act: Fetch the patient by ID
        PatientTO fetchedPatient = patientService.getPatientById(savedPatient.getId());

        // Assert: Check address details
        assertThat(fetchedPatient.getAddress()).isNotNull();
        assertThat(fetchedPatient.getAddress().getCity()).isEqualTo("Springfield");
    }

    @Test
    public void testShouldDeletePatientAndCascadeVisitsButNotDoctors() {
        // Arrange: Create an address
        AddressTO addressTO = new AddressTO();
        addressTO.setAddressLine1("123 Main St");
        addressTO.setCity("Springfield");
        addressTO.setPostalCode("12345");

        AddressTO addressTO2 = new AddressTO();
        addressTO2.setAddressLine1("123 Maidddn St");
        addressTO2.setCity("Sprindddgfield");
        addressTO2.setPostalCode("12ddd345");

        // Create a doctor
        DoctorTO doctorTO = new DoctorTO();
        doctorTO.setFirstName("John");
        doctorTO.setLastName("Doe");
        doctorTO.setTelephoneNumber("312123");
        doctorTO.setDoctorNumber("D123");
        doctorTO.setEmail("das@test.pl");
        doctorTO.setSpecialization(Specialization.SURGEON);
        doctorTO.setAddress(addressTO2);

        DoctorTO savedDoctor = doctorService.createDoctor(doctorTO);
        assertNotNull(savedDoctor);


        PatientTO patientTO = new PatientTO();
        patientTO.setFirstName("Sophia");
        patientTO.setLastName("Johnson");
        patientTO.setDateOfBirth(LocalDate.of(1995, 6, 15));
        patientTO.setShoeSize(38);
        patientTO.setPatientNumber("P12345");
        patientTO.setTelephoneNumber("123321123");
        patientTO.setAddress(addressTO);


        PatientTO savedPatient = patientService.createPatient(patientTO);
        assertNotNull(savedPatient);

        List<VisitTO> visits = savedPatient.getVisits();
        assertThat(visits).hasSize(0);

        // Save the patient with the linked visit

        Long patientId = savedPatient.getId();
        assertThat(patientService.getPatientById(patientId)).isNotNull();

        // Act: Delete the patient
        patientService.deletePatient(patientId);

        // Assert: Ensure visits are deleted
        assertThat(patientService.getVisitsByPatientId(patientId)).isEmpty();

        //do sprawdzenia, problem z zapisywaniem encji patient oraz doctor
        // Verify TO structure correctness
        List<VisitTO> visits2 = savedPatient.getVisits();
        assertThat(visits2).hasSize(0);
    }

    @Test
    @Transactional
    public void testShouldFetchPatientByIdWithCorrectTO() {
        // Arrange: Create an address
        AddressTO addressTO = new AddressTO();
        addressTO.setAddressLine1("456 Elm St");
        addressTO.setCity("Shelbyville");
        addressTO.setPostalCode("67890");

        PatientTO patientTO = new PatientTO();
        patientTO.setFirstName("James");
        patientTO.setLastName("Smith");
        patientTO.setDateOfBirth(LocalDate.of(1988, 3, 22));
        patientTO.setShoeSize(42);
        patientTO.setPatientNumber("P67890");
        patientTO.setTelephoneNumber("987654321");
        patientTO.setAddress(addressTO);

        PatientTO savedPatient = patientService.createPatient(patientTO);
        Long patientId = savedPatient.getId();

        // Act: Fetch patient by ID
        PatientTO fetchedPatient = patientService.getPatientById(patientId);

        // Assert: Verify the structure of the fetched TO
        assertThat(fetchedPatient).isNotNull();
        assertThat(fetchedPatient.getFirstName()).isEqualTo("James");
        assertThat(fetchedPatient.getLastName()).isEqualTo("Smith");
        assertThat(fetchedPatient.getDateOfBirth()).isEqualTo(LocalDate.of(1988, 3, 22));
        assertThat(fetchedPatient.getShoeSize()).isEqualTo(42);
        assertThat(fetchedPatient.getPatientNumber()).isEqualTo("P67890");
        assertThat(fetchedPatient.getTelephoneNumber()).isEqualTo("987654321");
        assertThat(fetchedPatient.getAddress().getAddressLine1()).isEqualTo("456 Elm St");
        assertThat(fetchedPatient.getAddress().getCity()).isEqualTo("Shelbyville");
        assertThat(fetchedPatient.getAddress().getPostalCode()).isEqualTo("67890");
    }
}
