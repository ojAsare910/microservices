package com.ojasare.grpcservice.exception;

public class DataIntegrityViolationException extends RuntimeException{
    public DataIntegrityViolationException(String message) { super (message); }
}
