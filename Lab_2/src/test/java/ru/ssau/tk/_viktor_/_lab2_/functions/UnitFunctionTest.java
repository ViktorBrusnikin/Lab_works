package ru.ssau.tk._viktor_._lab2_.functions;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
class UnitFunctionTest {
    @Test
    void testGetConstant() {
        UnitFunction unitFunc = new UnitFunction();

        // Проверяем, что метод getConstant() возвращает 1.0
        assertEquals(1.0, unitFunc.getConstant(), 1e-9);
    }

    @Test
    void testApply() {
        UnitFunction unitFunc = new UnitFunction();

        // Проверяем, что метод apply() возвращает 1.0 для любого значения x
        assertEquals(1.0, unitFunc.apply(10.0), 1e-9);
        assertEquals(1.0, unitFunc.apply(0.0), 1e-9);
    }
}