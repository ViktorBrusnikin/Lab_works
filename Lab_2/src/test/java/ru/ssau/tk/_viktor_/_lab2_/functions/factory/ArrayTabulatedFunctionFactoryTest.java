package ru.ssau.tk._viktor_._lab2_.functions.factory;

import org.junit.jupiter.api.Test;
import ru.ssau.tk._viktor_._lab2_.functions.AbstractTabulatedFunction;
import ru.ssau.tk._viktor_._lab2_.functions.ArrayTabulatedFunction;
import ru.ssau.tk._viktor_._lab2_.functions.LinkedListTabulatedFunction;

import static org.junit.jupiter.api.Assertions.*;

class ArrayTabulatedFunctionFactoryTest {

    @Test
    void createTest() {
        double xVal[] = {0.0, 1.0, 2.0};
        double yVal[] = {3.0, 4.0, 5.0};

        ArrayTabulatedFunctionFactory factory = new ArrayTabulatedFunctionFactory();

        ArrayTabulatedFunction function = (ArrayTabulatedFunction) factory.create(xVal, yVal);

        assertInstanceOf(ArrayTabulatedFunction.class, function);
    }
}