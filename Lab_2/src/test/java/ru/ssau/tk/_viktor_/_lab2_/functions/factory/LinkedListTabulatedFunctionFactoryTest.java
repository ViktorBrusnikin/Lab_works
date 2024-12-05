package ru.ssau.tk._viktor_._lab2_.functions.factory;

import org.junit.jupiter.api.Test;
import ru.ssau.tk._viktor_._lab2_.functions.LinkedListTabulatedFunction;

import static org.junit.jupiter.api.Assertions.*;

class LinkedListTabulatedFunctionFactoryTest {

    @Test
    void createTest() {
        double xVal[] = {0.0, 1.0, 2.0};
        double yVal[] = {3.0, 4.0, 5.0};

        LinkedListTabulatedFunctionFactory factory = new LinkedListTabulatedFunctionFactory();

        LinkedListTabulatedFunction function = (LinkedListTabulatedFunction) factory.create(xVal, yVal);

        assertInstanceOf(LinkedListTabulatedFunction.class, function);
    }
}