package com.masmovil.phone.api.repository;

import com.masmovil.phone.api.repository.entity.PhoneEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PhoneRepository  extends JpaRepository<PhoneEntity, Long> {

}