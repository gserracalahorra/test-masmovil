package com.masmovil.phone.api.controller;

import com.masmovil.phone.api.controller.dto.PhoneDto;
import com.masmovil.phone.api.controller.exception.NoContentException;
import com.masmovil.phone.api.service.PhoneService;
import com.masmovil.phone.domain.Phone;
import com.masmovil.phone.domain.factory.PhoneDomainStubFactory;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.test.util.ReflectionTestUtils;

import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.*;

import java.util.List;

@RunWith(MockitoJUnitRunner.class)
public class PhoneControllerUnitTest {

    private PhoneController phoneController = new PhoneController();

    @Mock
    private PhoneService phoneService;

    @Before
    public void before() {
        ReflectionTestUtils.setField(phoneController, "phoneService", phoneService);
    }

    @Test
    public void getCatalogueTest() {
        List<Phone> phoneDomains = PhoneDomainStubFactory.createValidPhoneList();

        when(phoneService.findCatalogue()).thenReturn(phoneDomains);

        List<PhoneDto> phoneDtos = phoneController.findCatalogue();

        phoneDtos.stream().forEach(dtoPhone -> {
            Phone domainPhone = phoneDomains.stream()
                    .filter(entity -> entity.getId().equals(dtoPhone.getId()))
                    .findFirst().get();

            assertTrue(dtoPhone.getName().equals(domainPhone.getName()));
            assertTrue(dtoPhone.getDescription().equals(domainPhone.getDescription()));
            assertTrue(dtoPhone.getImagePath().equals(domainPhone.getImagePath()));
            assertTrue(dtoPhone.getPrice().equals(domainPhone.getPrice()));
        });
    }

    @Test(expected = NoContentException.class)
    public void getContentNotFoundException() {
        List<Phone> phones = PhoneDomainStubFactory.createEmptyList();

        when(phoneService.findCatalogue()).thenReturn(phones);

        phoneController.findCatalogue();
    }

}