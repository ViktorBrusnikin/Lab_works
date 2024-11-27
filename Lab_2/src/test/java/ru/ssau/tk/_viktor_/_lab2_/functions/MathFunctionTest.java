package ru.ssau.tk._viktor_._lab2_.functions;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MathFunctionTest {

    @Test
    void testAndThen() {
        MathFunction testFunc1 = new SqrFunction();
        MathFunction testFunc2 = new CosFunction();
        MathFunction testFunc3 = new UnitFunction();

        CompositeFunction testFunc4 = testFunc1.andThen(testFunc2.andThen(testFunc3));
        assertEquals(1.0, testFunc4.apply(Math.PI));
    }
}