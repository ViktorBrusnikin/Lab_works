package ru.ssau.tk._viktor_._lab2_.functions.factory;

import ru.ssau.tk._viktor_._lab2_.functions.LinkedListTabulatedFunction;
import ru.ssau.tk._viktor_._lab2_.functions.TabulatedFunction;

public class LinkedListTabulatedFunctionFactory implements TabulatedFunctionFactory{

    @Override
    public TabulatedFunction create(double[] xValues, double[] yValues) {
        return new LinkedListTabulatedFunction(xValues, yValues);
    }
}
