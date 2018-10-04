package com.masmovil.phone.api.repository;

import com.masmovil.phone.api.repository.entity.Phone;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PhoneRepository  extends JpaRepository<Phone, Long> {

}