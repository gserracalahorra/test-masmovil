package com.masmovil.order.api.controller.command;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class OrderCommand {

    private Long customerId;

    private List<Long> phoneIds;

}