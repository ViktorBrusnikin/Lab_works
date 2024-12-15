package ru.ssau.tk._viktor_._lab2_.concurrent;

import ru.ssau.tk._viktor_._lab2_.functions.TabulatedFunction;

public class MultiplyingTask implements Runnable{

    private TabulatedFunction tabulatedFunction;

    MultiplyingTask(TabulatedFunction tabulatedFunction){
        this.tabulatedFunction = tabulatedFunction;
    }

    @Override
    public void run() {
        for(int i = 0; i < tabulatedFunction.getCount(); i++){
            this.tabulatedFunction.setY(i, this.tabulatedFunction.getY(i)*2);
        }
        System.out.println("Текущий поток " + Thread.currentThread().getName() + " закончил своё выполнение.");
    }
}
