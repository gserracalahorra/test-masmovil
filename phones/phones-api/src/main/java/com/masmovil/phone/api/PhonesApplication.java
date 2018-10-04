package com.masmovil.phone.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories
public class PhonesApplication
{
    public static void main( String[] args )
    {
        SpringApplication.run(PhonesApplication.class);
    }

}
