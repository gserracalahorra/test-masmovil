package com.masmovil.phone.api.controller;

import com.masmovil.phone.api.controller.exception.NoContentException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class ExceptionController {

    @ResponseStatus(value=HttpStatus.NO_CONTENT, reason="Phone catalogue is empty")
    @ExceptionHandler(NoContentException.class)
    public void noContent() {

    }

}