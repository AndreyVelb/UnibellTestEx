package ru.unibell.unibelltestex.exception;

public class ClientAlreadyExistedException extends RuntimeException {

    public ClientAlreadyExistedException(String message) {
        super(message);
    }

}
