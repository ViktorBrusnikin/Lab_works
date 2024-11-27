package ru.ssau.tk._viktor_._lab2_.functions;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ArrayTabulatedFunctionTest {

    @Test
    void getCount() {
        double[] xArr = {0.0, 1.2, 4.0};
        double[] yArr = {0.0, 4.3, 1.0};
        ArrayTabulatedFunction testArr= new ArrayTabulatedFunction(xArr, yArr);

        assertEquals(3, testArr.getCount(), 1e-9);
    }

    @Test
    void floorIndexOfX() {
        double[] xArr = {0.0, 1.2, 4.0};
        double[] yArr = {0.0, 4.3, 1.0};
        ArrayTabulatedFunction testArr= new ArrayTabulatedFunction(xArr, yArr);

        assertEquals(1, testArr.floorIndexOfX(3.0), 1e-9);
    }

    @Test
    void extrapolateLeft() {
        double[] xArr = {0.0, 1.0, 4.0};
        double[] yArr = {0.0, 4.0, 1.0};
        ArrayTabulatedFunction testArr= new ArrayTabulatedFunction(xArr, yArr);

        double result = testArr.extrapolateLeft(-1.0);
        assertEquals(-4.0, result, 1e-9);
    }

    @Test
    void extrapolateRight() {
        double[] xArr = {0.0, 1.0, 4.0};
        double[] yArr = {0.0, 4.0, 1.0};
        ArrayTabulatedFunction testArr= new ArrayTabulatedFunction(xArr, yArr);

        double result = testArr.extrapolateRight(-1.0);
        assertEquals(6.0, result, 1e-9);
    }

    @Test
    void interpolate() {
        double[] xArr = {0.0, 1.0, 4.0};
        double[] yArr = {0.0, 4.0, 1.0};
        ArrayTabulatedFunction testArr= new ArrayTabulatedFunction(xArr, yArr);

        double result = testArr.interpolate(-1.0, 1);
        assertEquals(6.0, result, 1e-9);
    }

    @Test
    void getX() {
        double[] xArr = {-2.0, 3.1, 6.0};
        double[] yArr = {4.0, 4.3, 2.0};
        ArrayTabulatedFunction testArr= new ArrayTabulatedFunction(xArr, yArr);

        assertEquals(6.0, testArr.getX(2), 1e-9);

    }

    @Test
    void getY() {
        double[] xArr = {-2.0, 3.1, 6.0};
        double[] yArr = {4.0, 4.3, 2.0};
        ArrayTabulatedFunction testArr= new ArrayTabulatedFunction(xArr, yArr);

        assertEquals(2.0, testArr.getY(2), 1e-9);
    }

    @Test
    void setY() {
        double[] xArr = {-2.0, 3.1, 6.0};
        double[] yArr = {4.0, 4.3, 2.0};
        ArrayTabulatedFunction testArr= new ArrayTabulatedFunction(xArr, yArr);

        testArr.setY(2, 7.0);
        assertEquals(7.0, testArr.getY(2), 1e-9);
    }

    @Test
    void indexOfX() {
        double[] xArr = {0.0, 1.2, 4.0};
        double[] yArr = {0.0, 4.3, 1.0};
        ArrayTabulatedFunction testArr= new ArrayTabulatedFunction(xArr, yArr);

        assertEquals(1, testArr.indexOfX(1.2), 1e-9);
        assertEquals(0, testArr.indexOfX(0.0), 1e-9);
    }

    @Test
    void indexOfY() {
        double[] xArr = {0.0, 1.2, 4.0};
        double[] yArr = {0.0, 4.3, 1.0};
        ArrayTabulatedFunction testArr= new ArrayTabulatedFunction(xArr, yArr);

        assertEquals(2, testArr.indexOfY(1.0), 1e-9);
        assertEquals(0, testArr.indexOfY(0.0), 1e-9);
    }

    @Test
    void leftBound() {
        double[] xArr = {0.0, 1.2, 4.0};
        double[] yArr = {0.0, 4.3, 1.0};
        ArrayTabulatedFunction testArr= new ArrayTabulatedFunction(xArr, yArr);

        assertEquals(0.0, testArr.leftBound(), 1e-9);
    }

    @Test
    void rightBound() {
        double[] xArr = {0.0, 1.2, 4.0};
        double[] yArr = {0.0, 4.3, 1.0};
        ArrayTabulatedFunction testArr= new ArrayTabulatedFunction(xArr, yArr);

        assertEquals(4.0, testArr.rightBound(), 1e-9);
    }
}