package ru.ssau.tk._viktor_._lab2_.concurrent;

import ru.ssau.tk._viktor_._lab2_.functions.LinkedListTabulatedFunction;
import ru.ssau.tk._viktor_._lab2_.functions.TabulatedFunction;
import ru.ssau.tk._viktor_._lab2_.functions.UnitFunction;

import java.util.ArrayList;
import java.util.List;

public class MultiplyingTaskExecutor {
    public static void main(String[] args) {
        TabulatedFunction tabulatedFunction = new LinkedListTabulatedFunction(new UnitFunction(), 1, 1000, 1000);
        List<Thread> threads = new ArrayList<>();
        for (int i = 0; i < 10; i++){
            MultiplyingTask mlp= new MultiplyingTask(tabulatedFunction);
            Thread thread = new Thread(mlp);
            threads.add(thread);
        }

        for (Thread thread : threads) {
            thread.start();
        }

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        for (int i = 0; i < tabulatedFunction.getCount(); i++) {
            System.out.printf("x = %f, y = %f%n", tabulatedFunction.getX(i), tabulatedFunction.getY(i));
        }
    }
}
