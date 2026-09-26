package com.devSenior.campusflow.common.exception;

public class EmailDuplicadoException extends RuntimeException {

    public EmailDuplicadoException(String mensaje) {
        super(mensaje);
    }
}