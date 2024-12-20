package com.jpacourse.service;

import com.jpacourse.dto.AddressTO;
import com.jpacourse.dto.PatientTO;

public interface AddressService
{
    public AddressTO findById(final Long id);
    public AddressTO createAddress(AddressTO addressTO);
}
