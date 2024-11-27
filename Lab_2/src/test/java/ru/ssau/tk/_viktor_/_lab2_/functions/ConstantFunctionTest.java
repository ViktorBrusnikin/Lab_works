package ru.ssau.tk._viktor_._lab2_.functions;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ConstantFunctionTest {

    @Test
    void testGetConstant() {
        ConstantFunction testObj = new ConstantFunction(5.0);

        assertEquals(5.0, testObj.getConstant(), 1e-9);
    }

    @Test
    void testApply() {
        ConstantFunction testObj = new ConstantFunction(2.0);

        assertEquals(2.0, testObj.apply(35.0), 1e-9);
    }
}