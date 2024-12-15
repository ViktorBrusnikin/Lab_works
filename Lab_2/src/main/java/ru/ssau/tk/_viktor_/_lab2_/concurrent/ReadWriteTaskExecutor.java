package ru.ssau.tk._viktor_._lab2_.concurrent;

import ru.ssau.tk._viktor_._lab2_.functions.ConstantFunction;
import ru.ssau.tk._viktor_._lab2_.functions.LinkedListTabulatedFunction;
import ru.ssau.tk._viktor_._lab2_.functions.TabulatedFunction;

public class ReadWriteTaskExecutor {

    public static void main(String[] args) {
        TabulatedFunction tabulatedFunction = new LinkedListTabulatedFunction(new ConstantFunction(-1.0), 1, 1000, 1000);
        Thread readTask = new Thread( new ReadTask(tabulatedFunction));
        Thread writeTask = new Thread(new WriteTask(tabulatedFunction, 0.5));
        readTask.start();
        writeTask.start();
    }
}
