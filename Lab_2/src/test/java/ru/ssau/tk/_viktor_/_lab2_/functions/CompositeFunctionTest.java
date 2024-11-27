package ru.ssau.tk._viktor_._lab2_.functions;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

class CompositeFunctionTest {

    @Test
    void testApply1() {
        MathFunction sqr = new SqrFunction();
        MathFunction idenity = new IdentityFunction();
        CompositeFunction cmp = new CompositeFunction(sqr, idenity);
        // Композиция квадрада и простой функции
        assertEquals(4.0, cmp.apply(2.0), 1e-9);
    }

    @Test
    void testApply2() {
        MathFunction sqr = new SqrFunction();
        MathFunction cos = new CosFunction();
        CompositeFunction cmp = new CompositeFunction(cos, sqr);
        // Композиция квадрата и косинуса
        assertEquals(1.0, cmp.apply(Math.PI), 1e-9);
    }

    @Test
    void testApply3() {
        MathFunction sqr = new SqrFunction();
        MathFunction cos = new CosFunction();
        MathFunction idenity = new IdentityFunction();
        CompositeFunction cmp1 = new CompositeFunction(cos, sqr);
        CompositeFunction cmp2 = new CompositeFunction(cmp1, idenity);
        // Композиция сложной функции
        assertEquals(1.0 , cmp2.apply(Math.PI), 1e-9);
    }
}
