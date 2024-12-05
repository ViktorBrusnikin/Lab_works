package ru.ssau.tk._viktor_._lab2_.functions.factory;

import ru.ssau.tk._viktor_._lab2_.functions.ArrayTabulatedFunction;
import ru.ssau.tk._viktor_._lab2_.functions.TabulatedFunction;

public class ArrayTabulatedFunctionFactory implements TabulatedFunctionFactory{

    @Override
    public TabulatedFunction create(double[] xValues, double[] yValues) {
        return new ArrayTabulatedFunction(xValues, yValues);
    }
}
