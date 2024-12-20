package com.jpacourse.mapper;

import com.jpacourse.dto.DoctorTO;
import com.jpacourse.dto.VisitTO;
import com.jpacourse.persistence.entity.DoctorEntity;

import java.util.stream.Collectors;

public class DoctorMapper {

    public static DoctorTO toDoctorTO(DoctorEntity entity) {
        if (entity == null) return null;

        DoctorTO doctorTO = new DoctorTO();
        doctorTO.setId(entity.getId());
        doctorTO.setFirstName(entity.getFirstName());
        doctorTO.setLastName(entity.getLastName());
        doctorTO.setTelephoneNumber(entity.getTelephoneNumber());
        doctorTO.setEmail(entity.getEmail());
        doctorTO.setDoctorNumber(entity.getDoctorNumber());
        doctorTO.setSpecialization(entity.getSpecialization());
        doctorTO.setAddress(AddressMapper.mapToTO(entity.getAddress())); // Map address
        doctorTO.setVisits(entity.getVisits().stream()
                .map(VisitMapper::toVisitTO)
                .collect(Collectors.toList())); // Map visits

        return doctorTO;
    }

    public static DoctorEntity toDoctorEntity(DoctorTO doctorTO) {
        if (doctorTO == null) return null;

        DoctorEntity doctorEntity = new DoctorEntity();
        doctorEntity.setId(doctorTO.getId());
        doctorEntity.setFirstName(doctorTO.getFirstName());
        doctorEntity.setLastName(doctorTO.getLastName());
        doctorEntity.setTelephoneNumber(doctorTO.getTelephoneNumber());
        doctorEntity.setEmail(doctorTO.getEmail());
        doctorEntity.setDoctorNumber(doctorTO.getDoctorNumber());
        doctorEntity.setSpecialization(doctorTO.getSpecialization());
        doctorEntity.setAddress(AddressMapper.mapToEntity(doctorTO.getAddress())); // Map address

        // Map visits
        doctorEntity.setVisits(doctorTO.getVisits().stream()
                .map(visitTO -> VisitMapper.toVisitEntity(visitTO, doctorEntity, null)) // Pass null for PatientEntity
                .collect(Collectors.toList()));

        System.out.println("Doctor AddressEntity: " + doctorEntity.getAddress());

        return doctorEntity;
    }
}
