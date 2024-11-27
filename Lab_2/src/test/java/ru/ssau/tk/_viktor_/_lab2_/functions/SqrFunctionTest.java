package ru.ssau.tk._viktor_._lab2_.functions;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SqrFunctionTest {

    @Test
    void testApply() {
        SqrFunction testFunc = new SqrFunction();

        assertEquals(4.0, testFunc.apply(2.0), 1e-9);
        assertEquals(36.0, testFunc.apply(6.0), 1e-9);
        assertEquals(49.0, testFunc.apply(-7.0), 1e-9);
    }
}