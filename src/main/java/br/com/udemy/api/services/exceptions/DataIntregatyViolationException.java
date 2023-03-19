package br.com.udemy.api.services.exceptions;

public class DataIntregatyViolationException extends RuntimeException{

    public DataIntregatyViolationException(String message) {
        super(message);
    }
}
