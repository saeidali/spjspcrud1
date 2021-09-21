package com.example.spjspcrud.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.transaction.TransactionSystemException;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;


public class ValidationExceptionHandler {
    private static final Logger LOGGER = LoggerFactory.getLogger(ValidationExceptionHandler.class);

    public List<String> handleConstraintViolation(Exception ex) {
        List<String> errors = new ArrayList<>();
        Throwable cause = ((TransactionSystemException) ex).getRootCause();
        if (cause instanceof ConstraintViolationException) {
            Set<ConstraintViolation<?>> constraintViolations = ((ConstraintViolationException) cause).getConstraintViolations();
            for (ConstraintViolation<?> s : constraintViolations) {
                errors.add(s.getMessage() + "<br/>");
            }
        }
        LOGGER.error(ex.getMessage());
        return errors;
    }
}
