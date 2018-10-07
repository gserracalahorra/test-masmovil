package com.masmovil.phone.api.service;

import com.masmovil.phone.api.repository.PhoneRepository;
import com.masmovil.phone.api.repository.entity.PhoneEntity;
import com.masmovil.phone.api.repository.factory.PhoneEntityStubFactory;
import com.masmovil.phone.domain.Phone;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.test.util.ReflectionTestUtils;

import java.util.List;

import static org.mockito.Mockito.*;

import static org.junit.Assert.*;

@RunWith(MockitoJUnitRunner.class)
public class PhoneServiceUnitTest {

    private PhoneService phoneService = new PhoneService();

    @Mock
    private PhoneRepository phoneRepository;

    @Before
    public void before() {
        ReflectionTestUtils.setField(phoneService, "phoneRepository", phoneRepository);
    }


    @Test
    public void getCatalogueTest() {
        List<PhoneEntity> entities = PhoneEntityStubFactory.createValidPhoneList();

        when(phoneRepository.findAll()).thenReturn(entities);

        List<Phone> results = phoneService.findCatalogue();

        assertEquals(3, results.size());

        results.stream().forEach(domainPhone -> {
            PhoneEntity entityPhone = entities.stream()
                    .filter(entity -> entity.getId().equals(domainPhone.getId()))
                    .findFirst().get();

            assertTrue(domainPhone.getName().equals(entityPhone.getName()));
            assertTrue(domainPhone.getDescription().equals(entityPhone.getDescription()));
            assertTrue(domainPhone.getImagePath().equals(entityPhone.getImagePath()));
            assertTrue(domainPhone.getPrice().equals(entityPhone.getPrice()));
        });

    }

    @Test
    public void getEmptyCatalogueTest() {
        List<PhoneEntity> entities = PhoneEntityStubFactory.createEmptyPhoneList();

        when(phoneRepository.findAll()).thenReturn(entities);

        List<Phone> results = phoneService.findCatalogue();

        assertEquals(0, results.size());
    }

}