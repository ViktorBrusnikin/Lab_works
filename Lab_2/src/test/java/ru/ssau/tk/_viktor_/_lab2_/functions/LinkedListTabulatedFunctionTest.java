package ru.ssau.tk._viktor_._lab2_.functions;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class LinkedListTabulatedFunctionTest {

    @Test
    void floorIndexOfX() {
        double[] xArr = {0.0, 1.0, 4.0};
        double[] yArr = {0.0, 4.0, 1.0};
        LinkedListTabulatedFunction test = new LinkedListTabulatedFunction(xArr, yArr);

        assertEquals(1, test.floorIndexOfX(3.0), 1e-9);

        MathFunction temp1 = new SqrFunction();
        MathFunction temp2 = new UnitFunction();

        CompositeFunction func = temp1.andThen(temp2);

        LinkedListTabulatedFunction test2 = new LinkedListTabulatedFunction(func, 0, 5, 5);

        assertEquals(2, test2.floorIndexOfX(3.0), 1e-9);
    }

    @Test
    void extrapolateLeft() {
        double[] xArr = {1.0, 2.0, 3.0};
        double[] yArr = {2.0, 3.0, 5.0};
        LinkedListTabulatedFunction test = new LinkedListTabulatedFunction(xArr, yArr);

        assertEquals(1.5, test.extrapolateLeft(0.5));
    }

    @Test
    void extrapolateRight() {
        double[] xArr = {1.0, 2.0, 3.0};
        double[] yArr = {2.0, 3.0, 5.0};
        LinkedListTabulatedFunction test = new LinkedListTabulatedFunction(xArr, yArr);

        assertEquals(6, test.extrapolateRight(3.5));
    }

    @Test
    void interpolate() {
        double[] xArr = {1.0, 2.0, 3.0};
        double[] yArr = {2.0, 3.0, 5.0};
        LinkedListTabulatedFunction test = new LinkedListTabulatedFunction(xArr, yArr);

        assertEquals(2.5, test.interpolate(1.5, 0));
    }

    @Test
    void getCount() {
        MathFunction func = new SqrFunction();
        LinkedListTabulatedFunction test = new LinkedListTabulatedFunction(func,0,10,10);

        assertEquals(10, test.getCount(), 1e-9);

        MathFunction temp1 = new SqrFunction();
        MathFunction temp2 = new UnitFunction();

        CompositeFunction func1 = temp1.andThen(temp2);

        LinkedListTabulatedFunction test2 = new LinkedListTabulatedFunction(func1, 0, 5, 5);

        assertEquals(5, test2.getCount(), 1e-9);
    }

    @Test
    void getX() {
        double[] xArr = {0.0, 1.0, 4.0};
        double[] yArr = {0.0, 4.0, 1.0};
        LinkedListTabulatedFunction test = new LinkedListTabulatedFunction(xArr, yArr);

        assertEquals(4, test.getX(2), 1e-9);
    }

    @Test
    void getY() {
        double[] xArr = {0.0, 1.0, 4.0};
        double[] yArr = {0.0, 4.0, 1.0};
        LinkedListTabulatedFunction test = new LinkedListTabulatedFunction(xArr, yArr);

        assertEquals(1, test.getY(2), 1e-9);
    }

    @Test
    void setY() {
        double[] xArr = {0.0, 1.0, 4.0};
        double[] yArr = {0.0, 4.0, 1.0};
        LinkedListTabulatedFunction test = new LinkedListTabulatedFunction(xArr, yArr);

        test.setY(2, 3.6);
        assertEquals(3.6, test.getY(2), 1e-9);
    }

    @Test
    void indexOfX() {
        double[] xArr = {0.0, 1.0, 4.0};
        double[] yArr = {0.0, 4.0, 1.0};
        LinkedListTabulatedFunction test = new LinkedListTabulatedFunction(xArr, yArr);

        assertEquals(2, test.indexOfX(4), 1e-9);
        assertEquals(-1, test.indexOfX(4.4), 1e-9);
    }

    @Test
    void indexOfY() {
        double[] xArr = {0.0, 1.0, 4.0};
        double[] yArr = {0.0, 1.7, 3.0};
        LinkedListTabulatedFunction test = new LinkedListTabulatedFunction(xArr, yArr);

        assertEquals(1, test.indexOfY(1.7), 1e-9);
        assertEquals(-1, test.indexOfY(1.8), 1e-9);
    }

    @Test
    void leftBound() {
        double[] xArr = {0.0, 1.0, 4.0};
        double[] yArr = {0.0, 1.7, 3.0};
        LinkedListTabulatedFunction test = new LinkedListTabulatedFunction(xArr, yArr);

        assertEquals(0.0, test.leftBound(), 1e-9);
    }

    @Test
    void rightBound() {
        double[] xArr = {0.0, 1.0, 4.0};
        double[] yArr = {0.0, 1.7, 3.0};
        LinkedListTabulatedFunction test = new LinkedListTabulatedFunction(xArr, yArr);

        assertEquals(4.0, test.rightBound(), 1e-9);

        MathFunction temp1 = new SqrFunction();
        MathFunction temp2 = new UnitFunction();

        CompositeFunction func = temp1.andThen(temp2);

        LinkedListTabulatedFunction test2 = new LinkedListTabulatedFunction(func, 0, 5, 5);

        assertEquals(5, test2.rightBound(), 1e-9);
    }
}