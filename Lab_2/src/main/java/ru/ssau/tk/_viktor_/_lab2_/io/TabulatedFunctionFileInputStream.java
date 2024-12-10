package ru.ssau.tk._viktor_._lab2_.io;

import ru.ssau.tk._viktor_._lab2_.functions.TabulatedFunction;
import ru.ssau.tk._viktor_._lab2_.functions.factory.ArrayTabulatedFunctionFactory;
import ru.ssau.tk._viktor_._lab2_.functions.factory.LinkedListTabulatedFunctionFactory;
import ru.ssau.tk._viktor_._lab2_.operations.TabulatedDifferentialOperator;

import java.io.*;

public class TabulatedFunctionFileInputStream {
    public static void main(String[] args) {
        try (FileInputStream fileInputStream = new FileInputStream("input/binary function.bin");
             BufferedInputStream bufferedInputStream = new BufferedInputStream(fileInputStream)) {

            TabulatedFunction functionFromFile = FunctionsIO.readTabulatedFunction(bufferedInputStream, new ArrayTabulatedFunctionFactory());
            System.out.println("Функция из файла: " + functionFromFile.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println("Введите размер и значения функции");
        BufferedReader consoleReader = new BufferedReader(new InputStreamReader(System.in));
        try {
            TabulatedFunction functionFromConsole = FunctionsIO.readTabulatedFunction(consoleReader, new LinkedListTabulatedFunctionFactory());
            System.out.println("Производная функции: " + new TabulatedDifferentialOperator().derive(functionFromConsole).toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
