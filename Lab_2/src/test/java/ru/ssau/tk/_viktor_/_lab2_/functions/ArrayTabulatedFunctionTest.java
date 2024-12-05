package ru.ssau.tk._viktor_._lab2_.functions;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ArrayTabulatedFunctionTest {

    @Test
    void getCount() {
        MathFunction func = new SqrFunction();
        ArrayTabulatedFunction test = new ArrayTabulatedFunction(func,0,10,10);

        assertEquals(10, test.getCount(), 1e-9);
    }

    @Test
    void floorIndexOfX() {
        double[] xArr = {0.0, 1.2, 4.0};
        double[] yArr = {0.0, 4.3, 1.0};
        ArrayTabulatedFunction testArr= new ArrayTabulatedFunction(xArr, yArr);

        assertEquals(1, testArr.floorIndexOfX(3.0), 1e-9);
    }

    @Test
    void floorIndexOfXException() {
        double[] xArr = {0.0, 1.2, 4.0};
        double[] yArr = {0.0, 4.3, 1.0};
        try {
            ArrayTabulatedFunction func = new ArrayTabulatedFunction(xArr, yArr);
            func.floorIndexOfX(-3.0);
            fail("Ожидалось исключение IllegalArgumentException");
        } catch (IllegalArgumentException e) {
        }
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

    @Test
    void testToString() {
        double[] xArr = {0.0, 1.0, 2.0};
        double[] yArr = {3.0, 4.0, 5.0};
        ArrayTabulatedFunction testArr= new ArrayTabulatedFunction(xArr, yArr);

        assertEquals("[ (0.0 ; 3.0), (1.0 ; 4.0), (2.0 ; 5.0) ]", testArr.toString());
    }

    @Test
    void testEquals() {
        double[] xArr1 = {0.0, 1.0, 2.0};
        double[] yArr1 = {3.0, 4.0, 5.0};
        ArrayTabulatedFunction testArr1 = new ArrayTabulatedFunction(xArr1, yArr1);

        double[] xArr2 = {0.0, 1.0, 2.0};
        double[] yArr2 = {3.0, 4.0, 5.0};
        ArrayTabulatedFunction testArr2 = new ArrayTabulatedFunction(xArr2, yArr2);

        double[] xArr3 = {0.0, 21.0, 32.0};
        double[] yArr3 = {3.0, 12.0, 42.0};
        ArrayTabulatedFunction testArr3= new ArrayTabulatedFunction(xArr3, yArr3);

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
        ArrayTabulatedFunction testArr= new ArrayTabulatedFunction(xArr, yArr);

        assertEquals(38, testArr.hashCode());
    }

    @Test
    void testClone() {
        double[] xValues = {0.0, 1.0, 2.0};
        double[] yValues = {3.0, 4.0, 5.0};
        ArrayTabulatedFunction function = new ArrayTabulatedFunction(xValues, yValues);
        ArrayTabulatedFunction clonedFunction = function.clone();

        assertEquals(function, clonedFunction);
        assertNotSame(function, clonedFunction);
    }

    @Test
    public void testXValuesLessThanTwo() {
        double[] xValues = {1.0};
        double[] yValues = {2.0, 3.0};
        try {
            new ArrayTabulatedFunction(xValues, yValues);
            fail("Ожидалось исключение IllegalArgumentException");
        } catch (IllegalArgumentException e) {
        }
    }

    @Test
    public void testYValuesLessThanTwo() {
        // Arrange
        double[] xValues = {1.0, 2.0};
        double[] yValues = {2.0};
        try {
            new ArrayTabulatedFunction(xValues, yValues);
            fail("Ожидалось исключение IllegalArgumentException");
        } catch (IllegalArgumentException e) {
        }
    }

    @Test
    public void testBothValuesLessThanTwo() {
        double[] xValues = {1.0};
        double[] yValues = {2.0};
        try {
            new ArrayTabulatedFunction(xValues, yValues);
            fail("Ожидалось исключение IllegalArgumentException");
        } catch (IllegalArgumentException e) {
        }
    }

    @Test
    public void testValidInput() {
        double[] xValues = {1.0, 2.0};
        double[] yValues = {2.0, 3.0};
        ArrayTabulatedFunction function = new ArrayTabulatedFunction(xValues, yValues);

        assertNotNull(function);
    }
}