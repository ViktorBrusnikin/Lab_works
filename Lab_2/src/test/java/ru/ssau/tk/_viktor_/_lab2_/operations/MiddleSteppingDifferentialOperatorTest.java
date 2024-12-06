package ru.ssau.tk._viktor_._lab2_.operations;

import org.junit.jupiter.api.Test;
import ru.ssau.tk._viktor_._lab2_.functions.MathFunction;
import ru.ssau.tk._viktor_._lab2_.functions.SqrFunction;

import static org.junit.jupiter.api.Assertions.*;

class MiddleSteppingDifferentialOperatorTest {

    @Test
    void deriveTest() {
        MathFunction sqrFunction = new SqrFunction();

        double step = 1e-9;
        MiddleSteppingDifferentialOperator operator = new MiddleSteppingDifferentialOperator(step);
        MathFunction derivative = operator.derive(sqrFunction);

        assertEquals(0.0, derivative.apply(0.0), 1e-5);

        assertEquals(4.0, derivative.apply(2.0), 1e-5);

        assertEquals(8.0, derivative.apply(4.0), 1e-5);

        assertEquals(-30.0, derivative.apply(-15.0), 1e-5);

        assertEquals(-40.0, derivative.apply(-20.0), 1e-5);
    }
}