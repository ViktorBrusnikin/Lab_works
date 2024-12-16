package ru.ssau.tk._viktor_._lab2_.concurrent;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.ssau.tk._viktor_._lab2_.functions.ArrayTabulatedFunction;
import ru.ssau.tk._viktor_._lab2_.functions.LinkedListTabulatedFunction;
import ru.ssau.tk._viktor_._lab2_.functions.Point;
import ru.ssau.tk._viktor_._lab2_.functions.TabulatedFunction;

import java.util.Iterator;

import static org.junit.jupiter.api.Assertions.*;

class SynchronizedTabulatedFunctionTest {
    private ArrayTabulatedFunction func;
    private SynchronizedTabulatedFunction synFunc;

    @BeforeEach
    public void setUp() {
        double[] xValues = {1.0, 2.0, 3.0, 4.0};
        double[] yValues = {2.0, 3.0, 4.0, 5.0};
        func = new ArrayTabulatedFunction(xValues,yValues);
        synFunc = new SynchronizedTabulatedFunction(func);
    }

    @Test
    void getCount() {
        assertEquals(func.getCount(), synFunc.getCount());
    }

    @Test
    void getX() {
        assertEquals(func.getX(1), synFunc.getX(1));
    }

    @Test
    void getY() {
        assertEquals(5, synFunc.getY(3));
    }

    @Test
    void setY() {
        synFunc.setY(2, 5);
        assertEquals(5, synFunc.getY(2));
    }

    @Test
    void indexOfX() {
        assertEquals(-1, synFunc.indexOfX(10));
    }

    @Test
    void indexOfY() {
        assertEquals(1, synFunc.indexOfY(3));
    }

    @Test
    void leftBound() {
        assertEquals(1, synFunc.leftBound());
    }

    @Test
    void rightBound() {
        assertEquals(4, synFunc.rightBound());
    }

    @Test
    public void testApply() {
        assertEquals(3.0, synFunc.apply(2.0));
    }

    @Test
    public void testIterator() {
        Iterator<Point> iterator = synFunc.iterator();
        assertTrue(iterator.hasNext());
        assertEquals(1.0, iterator.next().x);
        assertTrue(iterator.hasNext());
        assertEquals(2.0, iterator.next().x);
        assertTrue(iterator.hasNext());
        assertEquals(3.0, iterator.next().x);
        assertTrue(iterator.hasNext());
        assertEquals(4.0, iterator.next().x);
        assertFalse(iterator.hasNext());
    }
}