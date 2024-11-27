package ru.ssau.tk._viktor_._lab2_.functions;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

class CosFunctionTest {

    @Test
    void testApply() {
        MathFunction testCos = new CosFunction();

        assertEquals(0.0, testCos.apply(Math.PI / 2), 1e-9);
        assertEquals(1.0, testCos.apply(0.0), 1e-9);
        assertEquals(0.0, testCos.apply(3* Math.PI / 2), 1e-9);
        assertEquals(-1.0, testCos.apply(Math.PI ), 1e-9);
    }
}