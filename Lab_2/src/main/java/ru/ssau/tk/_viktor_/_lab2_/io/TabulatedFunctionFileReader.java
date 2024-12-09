package ru.ssau.tk._viktor_._lab2_.io;

import ru.ssau.tk._viktor_._lab2_.functions.TabulatedFunction;
import ru.ssau.tk._viktor_._lab2_.functions.factory.ArrayTabulatedFunctionFactory;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class TabulatedFunctionFileReader {
    public static void main(String[] args){
        try(
                FileReader arrRead = new FileReader("C:/Users/Victor/Desktop/Labs/Lab_2/input/function.txt");
                FileReader listRead = new FileReader("C:/Users/Victor/Desktop/Labs/Lab_2/input/function.txt");
                BufferedReader bufferedArrayReader = new BufferedReader(arrRead);
                BufferedReader bufferedLinkedReader = new BufferedReader(listRead);
        ){
            TabulatedFunction arrFunc = FunctionsIO.readTabulatedFunction(bufferedArrayReader, new ArrayTabulatedFunctionFactory());
            TabulatedFunction listFunc = FunctionsIO.readTabulatedFunction(bufferedLinkedReader, new ArrayTabulatedFunctionFactory());
            System.out.println(arrFunc);
            System.out.println(listFunc);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
