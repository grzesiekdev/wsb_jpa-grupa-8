package com.jpacourse.persistence.entity;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;


@Entity
@Table(name = "VISIT")
public class VisitEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String description;

	@Column(name = "visit_date")
	private LocalDateTime visitDate;

	@ManyToOne(optional = false)
	@JoinColumn(name = "doctor_id", nullable = false)
	private DoctorEntity doctor;

	@ManyToOne(optional = false, fetch = FetchType.EAGER)
	@JoinColumn(name = "patient_id", nullable = false)
	private PatientEntity patient;

	@OneToMany(mappedBy = "visit", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private List<MedicalTreatmentEntity> treatments;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public LocalDateTime getVisitDate() {
		return visitDate;
	}

	public void setVisitDate(LocalDateTime visitDate) {
		this.visitDate = visitDate;
	}

	public PatientEntity getPatient() {
		return patient;
	}

	public void setPatient(PatientEntity patient) {
		this.patient = patient;
	}

	public DoctorEntity getDoctor() {
		return doctor;
	}

	public void setDoctor(DoctorEntity doctor) {
		this.doctor = doctor;
	}

	public List<MedicalTreatmentEntity> getTreatments() {
		return this.treatments;
	}

	public void setTreatments(List<MedicalTreatmentEntity> treatments) {
		this.treatments = treatments;
	}

}
