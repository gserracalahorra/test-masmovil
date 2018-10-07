package com.masmovil.order.api.client;

import com.masmovil.order.api.client.dto.PhoneDto;
import com.masmovil.order.api.client.dto.PhoneDtoStubFactory;
import com.masmovil.phone.domain.Phone;

import feign.FeignException;
import feign.Response;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.test.util.ReflectionTestUtils;

import java.util.HashMap;
import java.util.List;

import static org.mockito.Mockito.*;

import static org.junit.Assert.*;

@RunWith(MockitoJUnitRunner.class)
public class PhoneClientWrapperUnitTest {

    private PhoneClientWrapper phoneClientWrapper = new PhoneClientWrapper();

    @Mock
    private PhoneRestClient phoneRestClient;

    @Before
    public void before() {
        ReflectionTestUtils.setField(phoneClientWrapper, "phoneRestClient", phoneRestClient);
    }

    @Test
    public void allPhoneInInputMatchesWithCatalogueTest() {
        List<PhoneDto> phoneDtos = PhoneDtoStubFactory.createValidPhoneList();

        when(phoneRestClient.findCatalogue()).thenReturn(phoneDtos);

        List<Long> paramsList = List.of(Long.valueOf(1), Long.valueOf(2), Long.valueOf(3));

        List<Phone> result = phoneClientWrapper.findInCataloge(paramsList);

        phoneDtos.stream().forEach(dto -> {
            Phone domain = result.stream().filter(e -> e.getId().equals(dto.getId())).findFirst().get();

            assertEquals(dto.getId(), domain.getId());
            assertEquals(dto.getName(), domain.getName());
            assertEquals(dto.getDescription(), domain.getDescription());
            assertEquals(dto.getImagePath(), domain.getImagePath());
            assertEquals(dto.getPrice(), domain.getPrice());
        });
    }

    @Test
    public void noPhoneInInputMatchesWithcatalogueTest() {
        List<PhoneDto> phoneDtos = PhoneDtoStubFactory.createValidPhoneList();

        when(phoneRestClient.findCatalogue()).thenReturn(phoneDtos);

        List<Long> paramsList = List.of(Long.valueOf(8), Long.valueOf(9), Long.valueOf(10));

        List<Phone> result = phoneClientWrapper.findInCataloge(paramsList);

        assertEquals(0, result.size());
    }

    @Test
    public void onlyOnPhoneInInputMatchesWithcatalogueTest() {
        List<PhoneDto> phoneDtos = PhoneDtoStubFactory.createValidPhoneList();

        when(phoneRestClient.findCatalogue()).thenReturn(phoneDtos);

        List<Long> paramsList = List.of(Long.valueOf(8), Long.valueOf(2), Long.valueOf(10));

        List<Phone> result = phoneClientWrapper.findInCataloge(paramsList);

        PhoneDto dto = phoneDtos.get(1);
        Phone domain = result.get(0);
        assertEquals(dto.getId(), domain.getId());
        assertEquals(dto.getName(), domain.getName());
        assertEquals(dto.getDescription(), domain.getDescription());
        assertEquals(dto.getImagePath(), domain.getImagePath());
        assertEquals(dto.getPrice(), domain.getPrice());
    }


    @Test(expected = FeignException.class)
    public void restClienReturnsExceptionTest() {
        when(phoneRestClient.findCatalogue())
                .thenThrow(FeignException.errorStatus("1", Response.builder()
                                                                    .status(500)
                                                                    .reason("Internal server error")
                                                                    .headers(new HashMap<>())
                                                                    .build()));

        List<Long> paramsList = List.of(Long.valueOf(8), Long.valueOf(2), Long.valueOf(10));

        phoneClientWrapper.findInCataloge(paramsList);
    }

}