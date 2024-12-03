package com.jpacourse.persistence.entity;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "VISIT")
public class VisitEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String description;

	@Column(nullable = false)
	private LocalDateTime time;

	@ManyToOne(optional = false)
	@JoinColumn(name = "doctor_id", nullable = false)
	private DoctorEntity doctor;

	@ManyToOne(optional = false)
	@JoinColumn(name = "patient_id", nullable = false)
	private PatientEntity patient;

	@OneToOne(cascade = CascadeType.ALL, optional = false)
	@JoinColumn(name = "medical_treatment_id", referencedColumnName = "id", nullable = false)
	private MedicalTreatmentEntity medicalTreatment;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public LocalDateTime getTime() {
		return time;
	}

	public void setTime(LocalDateTime time) {
		this.time = time;
	}

}
