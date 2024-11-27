package ru.ssau.tk._viktor_._lab2_.functions;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class IdentityFunctionTest {

    @Test
    void testApply() {
        MathFunction testFunc = new IdentityFunction();

        assertEquals(0.0, testFunc.apply(0.0));
        assertEquals(34.0, testFunc.apply(34.0));
        assertEquals(-2.1252, testFunc.apply(-2.1252));
    }

    @Test
    void testToString() {
        MathFunction testF = new IdentityFunction();

        assertEquals("[IdentityFunction] Объекты этого класса выполняют тождественное преобразование", testF.toString());
    }

    @Test
    void testEquals() {
        MathFunction testF1 = new IdentityFunction();
        MathFunction testF2 = new IdentityFunction();
        MathFunction testF3 = new UnitFunction();

        assertEquals(true, testF1.equals(testF2));
        assertEquals(false, testF1.equals(testF3));
        assertEquals(false, testF2.equals(testF3));
    }

    @Test
    void testHashCode() {
        MathFunction testF = new IdentityFunction();

        assertEquals(1, testF.hashCode());
    }

    @Test
    void testClone() {
        IdentityFunction obj1 = new IdentityFunction();
        IdentityFunction obj2 = obj1.clone();

        assertEquals(obj1, obj2);
    }
}