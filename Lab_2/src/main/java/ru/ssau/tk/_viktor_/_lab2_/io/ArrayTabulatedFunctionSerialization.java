package ru.ssau.tk._viktor_._lab2_.io;

import ru.ssau.tk._viktor_._lab2_.functions.ArrayTabulatedFunction;
import ru.ssau.tk._viktor_._lab2_.functions.TabulatedFunction;
import ru.ssau.tk._viktor_._lab2_.operations.TabulatedDifferentialOperator;

import java.io.*;

public class ArrayTabulatedFunctionSerialization {
    public static void main(String[] args) {
        String filePath = "output/serialized array functions.bin";

        try (BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(filePath))) {
            double xValues[] = {1.0, 2.0, 3.0};
            double yValues[] = {4.0, 5.0, 6.0};

            ArrayTabulatedFunction function = new ArrayTabulatedFunction(xValues, yValues);

            TabulatedDifferentialOperator operator = new TabulatedDifferentialOperator();
            TabulatedFunction firstDerivative = operator.derive(function);
            TabulatedFunction secondDerivative = operator.derive(firstDerivative);

            FunctionsIO.serialize(bos, function);
            FunctionsIO.serialize(bos, firstDerivative);
            FunctionsIO.serialize(bos, secondDerivative);
        } catch (IOException e) {
            e.printStackTrace();
        }

        try (BufferedInputStream bis = new BufferedInputStream(new FileInputStream(filePath))) {
            TabulatedFunction deserializedFunction = FunctionsIO.deserialize(bis);
            TabulatedFunction deserializedFirstDerivative = FunctionsIO.deserialize(bis);
            TabulatedFunction deserializedSecondDerivative = FunctionsIO.deserialize(bis);

            System.out.println("Исходная функция: " + deserializedFunction.toString());
            System.out.println("Первая производная: " + deserializedFirstDerivative.toString());
            System.out.println("Вторая производная: " + deserializedSecondDerivative.toString());
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
