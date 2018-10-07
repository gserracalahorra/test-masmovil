package com.masmovil.order.domain;

import com.masmovil.customer.domain.Customer;
import com.masmovil.phone.domain.Phone;
import lombok.*;

import java.math.BigDecimal;
import java.util.List;
import java.util.Random;

@Getter
@Setter
public class Order {

    private Long id;

    private Customer customer;

    private List<Phone> phones;

    private BigDecimal totalPrice;

    public Order(Customer customer, List<Phone> phones) {
        this.id = new Random().nextLong();
        this.customer = customer;
        this.phones = phones;
    }

    public void calculateTotalPrice() {
        this.totalPrice = phones.stream().map(phone -> phone.getPrice()).reduce(BigDecimal.ZERO, (a, b) -> a.add(b));
    }

    public String toString() {
        return String.format("SUCCESS ORDER [ id = %s ; total price = %s ; customer = [ %s ] ;  phones = [ %s] \n]",
                this.id,
                this.totalPrice,
                this.customer,
                buildPhonesString());
    }

    private String buildPhonesString() {
        String phones = "";
        for (Phone phone : this.phones) {
            phones += phone.toString() + " ; ";
        }
        return phones;
    }

}