package ru.ssau.tk._viktor_._lab2_.concurrent;

import ru.ssau.tk._viktor_._lab2_.functions.TabulatedFunction;

public class ReadTask implements Runnable{
    private final TabulatedFunction tabulatedFunction;

    ReadTask(TabulatedFunction tabulatedFunction){
        this.tabulatedFunction = tabulatedFunction;
    }

    @Override
    public void run() {
        for(int i = 0; i < tabulatedFunction.getCount(); i++) {
            synchronized (tabulatedFunction) {
                System.out.printf("After read: i = %d, x = %f, y = %f%n", i, this.tabulatedFunction.getX(i), this.tabulatedFunction.getY(i));
            }
        }
    }
}
