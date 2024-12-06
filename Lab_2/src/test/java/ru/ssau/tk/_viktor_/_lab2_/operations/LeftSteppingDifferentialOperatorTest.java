package ru.ssau.tk._viktor_._lab2_.operations;

import org.junit.jupiter.api.Test;
import ru.ssau.tk._viktor_._lab2_.functions.MathFunction;
import ru.ssau.tk._viktor_._lab2_.functions.SqrFunction;

import static org.junit.jupiter.api.Assertions.*;
class LeftSteppingDifferentialOperatorTest {

    @Test
    void deriveTest() {
        MathFunction sqrFunction = new SqrFunction();

        double step = 1e-9;
        LeftSteppingDifferentialOperator operator = new LeftSteppingDifferentialOperator(step);

        MathFunction derivative = operator.derive(sqrFunction);

        assertEquals(0.0, derivative.apply(0.0), 1e-5);

        assertEquals(2.0, derivative.apply(1.0), 1e-5);

        assertEquals(6.0, derivative.apply(3.0), 1e-5);

        assertEquals(8.0, derivative.apply(4.0), 1e-5);

        assertEquals(14.0, derivative.apply(7.0), 1e-5);
    }
}