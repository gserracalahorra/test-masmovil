package com.masmovil.order.api.client;

import com.masmovil.order.api.client.dto.CustomerDto;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(name="customers", url="${masmovil.customers.api}")
public interface CustomerRestClient {

    @RequestMapping(path = "/customer/{id}", method = RequestMethod.GET)
    CustomerDto findById(@PathVariable("id") Long id);

}