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

    @Test
    void testToString() {
        double[] xArr = {0.0, 1.0, 2.0};
        double[] yArr = {3.0, 4.0, 5.0};
        LinkedListTabulatedFunction test = new LinkedListTabulatedFunction(xArr, yArr);

        assertEquals("[ (0.0 ; 3.0), (1.0 ; 4.0), (2.0 ; 5.0) ]", test.toString());
    }

    @Test
    void testEquals() {
        double[] xArr = {0.0, 1.0, 2.0};
        double[] yArr = {3.0, 4.0, 5.0};
        LinkedListTabulatedFunction testArr1 = new LinkedListTabulatedFunction(xArr, yArr);

        double[] xArr2 = {0.0, 1.0, 2.0};
        double[] yArr2 = {3.0, 4.0, 5.0};
        LinkedListTabulatedFunction testArr2 = new LinkedListTabulatedFunction(xArr2, yArr2);

        double[] xArr3 = {0.0, 21.0, 32.0};
        double[] yArr3 = {3.0, 12.0, 42.0};
        LinkedListTabulatedFunction testArr3= new LinkedListTabulatedFunction(xArr3, yArr3);

        IdentityFunction test4 = new IdentityFunction();

        assertEquals(true, testArr1.equals(testArr2));
        assertEquals(false, testArr1.equals(testArr3));
        assertEquals(false, testArr2.equals(testArr3));
        assertEquals(false, testArr1.equals(test4));
    }

    @Test
    void testHashCode() {
        double[] xArr = {0.0, 21.0, 32.0};
        double[] yArr = {3.0, 12.0, 42.0};
        LinkedListTabulatedFunction testArr= new LinkedListTabulatedFunction(xArr, yArr);

        assertEquals(38, testArr.hashCode());
    }

    @Test
    void testClone() {
        double[] xValues = {0.0, 1.0, 2.0};
        double[] yValues = {3.0, 4.0, 5.0};
        LinkedListTabulatedFunction function = new LinkedListTabulatedFunction(xValues, yValues);
        LinkedListTabulatedFunction clonedFunction = function.clone();

        assertEquals(function, clonedFunction);
        assertNotSame(function, clonedFunction);
    }

    @Test
    void testToStringNode() {
        double tempX = 1.5;
        double tempY = 3.2;

        LinkedListTabulatedFunction.Node test = new LinkedListTabulatedFunction.Node(tempX, tempY);
        assertEquals("(1.5 ; 3.2)", test.toString());
    }

    @Test
    void testEqualsNode() {
        double tempX = 3.5;
        double tempY = 6.1;

        LinkedListTabulatedFunction.Node test1 = new LinkedListTabulatedFunction.Node(tempX, tempY);
        LinkedListTabulatedFunction.Node test2 = new LinkedListTabulatedFunction.Node(tempX, tempY);
        LinkedListTabulatedFunction.Node test3 = new LinkedListTabulatedFunction.Node(0, 0);
        assertEquals(true, test1.equals(test2));
        assertEquals(false, test1.equals(test3));
    }

    @Test
    void testHashCodeNode() {
        double tempX = 1.5;
        double tempY = 3.2;

        LinkedListTabulatedFunction.Node test = new LinkedListTabulatedFunction.Node(tempX, tempY);
        assertEquals(2, test.hashCode());
    }

    @Test
    void testCloneNode() {
        double tempX = 1.5;
        double tempY = 3.2;

        LinkedListTabulatedFunction.Node test = new LinkedListTabulatedFunction.Node(tempX, tempY);
        LinkedListTabulatedFunction.Node result = test.clone();
        assertEquals(result, test);
        assertNotSame(result, test);
    }
}