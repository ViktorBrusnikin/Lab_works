package ru.ssau.tk._viktor_._lab2_.functions;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ZeroFunctionTest {
    @Test
    void testGetConstant() {
        ZeroFunction zeroFunc = new ZeroFunction();

        // Проверяем, что метод getConstant() возвращает 0.0
        assertEquals(0.0, zeroFunc.getConstant(), 1e-9);
    }

    @Test
    void testApply() {
        ZeroFunction zeroFunc = new ZeroFunction();

        // Проверяем, что метод apply() возвращает 0.0 для любого значения x
        assertEquals(0.0, zeroFunc.apply(10.0), 1e-9);
        assertEquals(0.0, zeroFunc.apply(0.0), 1e-9);
    }
}