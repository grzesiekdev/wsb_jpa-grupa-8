package com.jpacourse.persistence.dao.impl;

import com.jpacourse.persistence.dao.AddressDao;
import com.jpacourse.persistence.entity.AddressEntity;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public class AddressDaoImpl extends AbstractDao<AddressEntity, Long> implements AddressDao {

    public AddressDaoImpl() {
        super(AddressEntity.class);
    }
}
