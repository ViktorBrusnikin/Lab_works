package ru.ssau.tk._viktor_._lab2_.functions.factory;

import ru.ssau.tk._viktor_._lab2_.functions.TabulatedFunction;

public interface TabulatedFunctionFactory {

    TabulatedFunction create(double[] xValues, double[] yValues);
}
