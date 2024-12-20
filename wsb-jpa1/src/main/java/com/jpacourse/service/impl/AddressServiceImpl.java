package com.jpacourse.service.impl;

import com.jpacourse.dto.AddressTO;
import com.jpacourse.dto.PatientTO;
import com.jpacourse.mapper.AddressMapper;
import com.jpacourse.mapper.PatientMapper;
import com.jpacourse.persistence.dao.AddressDao;
import com.jpacourse.persistence.entity.AddressEntity;
import com.jpacourse.persistence.entity.PatientEntity;
import com.jpacourse.service.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@Transactional
public class AddressServiceImpl implements AddressService
{
    private final AddressDao addressDao;

    @Autowired
    public AddressServiceImpl(AddressDao pAddressDao)
    {
        addressDao = pAddressDao;
    }

    @Override
    public AddressTO findById(Long id) {
        final AddressEntity entity = addressDao.findOne(id);
        return AddressMapper.mapToTO(entity);
    }

    @Override
    public AddressTO createAddress(AddressTO addressTO) {
        AddressEntity addressEntity = AddressMapper.mapToEntity(addressTO);
        addressEntity = addressDao.save(addressEntity);
        return AddressMapper.mapToTO(addressEntity);
    }
}
