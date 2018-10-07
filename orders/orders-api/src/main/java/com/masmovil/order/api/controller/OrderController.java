package com.masmovil.order.api.controller;

import com.masmovil.customer.domain.Customer;
import com.masmovil.order.api.client.CustomerClientWrapper;
import com.masmovil.order.api.client.PhoneClientWrapper;
import com.masmovil.order.api.controller.command.OrderCommand;
import com.masmovil.order.api.controller.exception.BadRequestException;
import com.masmovil.order.api.service.OrderService;
import com.masmovil.order.domain.Order;
import com.masmovil.phone.domain.Phone;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class OrderController {

    @Autowired
    private OrderService orderService;

    @Autowired
    private CustomerClientWrapper customerClientWrapper;

    @Autowired
    private PhoneClientWrapper phoneClientWrapper;

    @ResponseStatus(HttpStatus.CREATED)
    @RequestMapping(path = "/order", method = RequestMethod.POST, consumes = "application/json")
    public void order(@RequestBody OrderCommand orderCommand) {

        Customer customer = findCustomer(orderCommand);

        List<Phone> phones = findInCatalogue(orderCommand);

        orderService.order(new Order(customer, phones));
    }

    private Customer findCustomer(@RequestBody OrderCommand orderCommand) {
        Optional<Customer> customer = customerClientWrapper.findById(orderCommand.getCustomerId());

        if (!customer.isPresent()) {
            throw new BadRequestException(String.format("Customer identified by id = %s does not exist",
                    orderCommand.getCustomerId()));
        }
        return customer.get();
    }

    private List<Phone> findInCatalogue(@RequestBody OrderCommand orderCommand) {
        List<Phone> phones = phoneClientWrapper.findInCataloge(orderCommand.getPhoneIds());

        if (phones.isEmpty()) {
            throw new BadRequestException(String.format("No phone matches with catalogue list. Requested phones = %s",
                    orderCommand.getPhoneIds()));
        }
        return phones;
    }

}