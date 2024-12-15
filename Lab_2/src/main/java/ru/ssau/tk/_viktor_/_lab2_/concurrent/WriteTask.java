package ru.ssau.tk._viktor_._lab2_.concurrent;

import ru.ssau.tk._viktor_._lab2_.functions.TabulatedFunction;

public class WriteTask implements Runnable{
    private final TabulatedFunction tabulatedFunction;
    private final double value;
    WriteTask(TabulatedFunction tabulatedFunction, double value){
        this.tabulatedFunction = tabulatedFunction;
        this.value = value;
    }


    @Override
    public void run() {
        for (int i = 0; i < tabulatedFunction.getCount(); i++){
            this.tabulatedFunction.setY(i, this.value);
            System.out.printf("Writing for index %d complete%n", i);
        }
    }
}
