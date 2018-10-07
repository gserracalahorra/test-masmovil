package com.masmovil.order.api.client;

import com.masmovil.order.api.client.dto.PhoneDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@FeignClient(name="phones", url="${masmovil.phones.api}")
public interface PhoneRestClient {

    @RequestMapping(path = "/catalogue", method = RequestMethod.GET)
    List<PhoneDto> findCatalogue();

}