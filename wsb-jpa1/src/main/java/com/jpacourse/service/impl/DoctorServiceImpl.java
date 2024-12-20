package com.jpacourse.service.impl;

import com.jpacourse.dto.DoctorTO;
import com.jpacourse.mapper.DoctorMapper;
import com.jpacourse.persistence.dao.DoctorRepository;
import com.jpacourse.persistence.entity.DoctorEntity;
import com.jpacourse.rest.exception.EntityNotFoundException;
import com.jpacourse.service.DoctorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class DoctorServiceImpl implements DoctorService {

    @Autowired
    private DoctorRepository doctorRepository;

    @Override
    public DoctorTO getDoctorById(Long id) {
        DoctorEntity doctorEntity = doctorRepository.findOne(id);
        if (doctorEntity == null) {
            throw new EntityNotFoundException(id);
        }
        return DoctorMapper.toDoctorTO(doctorEntity);
    }

    @Override
    public List<DoctorTO> getAllDoctors() {
        return doctorRepository.findAll()
                .stream()
                .map(DoctorMapper::toDoctorTO)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public DoctorTO createDoctor(DoctorTO doctorTO) {
        DoctorEntity doctorEntity = DoctorMapper.toDoctorEntity(doctorTO);
        doctorRepository.save(doctorEntity);
        return DoctorMapper.toDoctorTO(doctorEntity);
    }

    @Override
    public DoctorTO updateDoctor(Long id, DoctorTO doctorTO) {
        DoctorEntity doctorEntity = doctorRepository.findOne(id);

        if (doctorEntity == null) {
            throw new EntityNotFoundException(id);
        }

        doctorEntity.setFirstName(doctorTO.getFirstName());
        doctorEntity.setLastName(doctorTO.getLastName());
        doctorEntity.setTelephoneNumber(doctorTO.getTelephoneNumber());
        doctorEntity.setEmail(doctorTO.getEmail());
        doctorEntity.setDoctorNumber(doctorTO.getDoctorNumber());
        doctorEntity.setSpecialization(doctorTO.getSpecialization());
        doctorEntity.setAddress(DoctorMapper.toDoctorEntity(doctorTO).getAddress());

        doctorEntity = doctorRepository.save(doctorEntity);
        return DoctorMapper.toDoctorTO(doctorEntity);
    }

    @Override
    public void deleteDoctor(Long id) {
        if (!doctorRepository.exists(id)) {
            throw new EntityNotFoundException(id);
        }
        doctorRepository.delete(id);
    }
}
