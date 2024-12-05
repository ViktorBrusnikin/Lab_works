package ru.ssau.tk._viktor_._lab2_.exceptions;

public class ArrayIsNotSortedException extends RuntimeException {

    public ArrayIsNotSortedException(){
        super();
    };

    public ArrayIsNotSortedException(String message){
        super(message);
    };
}
