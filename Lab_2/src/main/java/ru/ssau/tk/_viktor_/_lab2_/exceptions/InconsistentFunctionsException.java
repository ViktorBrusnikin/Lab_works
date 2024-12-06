package ru.ssau.tk._viktor_._lab2_.exceptions;

public class InconsistentFunctionsException extends RuntimeException {

    public InconsistentFunctionsException(){
      super();
    }

    public InconsistentFunctionsException(String message) {
        super(message);
    }
}
