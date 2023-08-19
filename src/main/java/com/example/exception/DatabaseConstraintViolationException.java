package com.example.exception;

import lombok.experimental.StandardException;

@StandardException
public class DatabaseConstraintViolationException extends RuntimeException{
}
