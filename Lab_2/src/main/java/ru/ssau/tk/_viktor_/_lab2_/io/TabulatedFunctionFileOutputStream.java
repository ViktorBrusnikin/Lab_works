package ru.ssau.tk._viktor_._lab2_.io;

import ru.ssau.tk._viktor_._lab2_.functions.ArrayTabulatedFunction;
import ru.ssau.tk._viktor_._lab2_.functions.LinkedListTabulatedFunction;
import ru.ssau.tk._viktor_._lab2_.functions.TabulatedFunction;

import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class TabulatedFunctionFileOutputStream {

    public static void main(String[] args) {
        double[] xValues = {0.0, 0.5, 1.0};
        double[] yValues = {0.0, 0.25, 1.0};

        TabulatedFunction arrayFunction = new ArrayTabulatedFunction(xValues, yValues);
        TabulatedFunction linkedListFunction = new LinkedListTabulatedFunction(xValues, yValues);

        try (
                BufferedOutputStream arrayOutputStream = new BufferedOutputStream(new FileOutputStream("output/array function.bin"));
                BufferedOutputStream linkedListOutputStream = new BufferedOutputStream(new FileOutputStream("output/linked list function.bin"))
        ) {
            FunctionsIO.writeTabulatedFunction(arrayOutputStream, arrayFunction);
            FunctionsIO.writeTabulatedFunction(linkedListOutputStream, linkedListFunction);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
