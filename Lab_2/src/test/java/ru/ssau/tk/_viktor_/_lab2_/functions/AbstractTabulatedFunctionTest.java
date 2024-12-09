package ru.ssau.tk._viktor_._lab2_.functions;

import org.junit.jupiter.api.Test;
import ru.ssau.tk._viktor_._lab2_.exceptions.ArrayIsNotSortedException;
import ru.ssau.tk._viktor_._lab2_.exceptions.DifferentLengthOfArraysException;

import static org.junit.jupiter.api.Assertions.*;
import static ru.ssau.tk._viktor_._lab2_.functions.AbstractTabulatedFunction.checkLengthIsTheSame;
import static ru.ssau.tk._viktor_._lab2_.functions.AbstractTabulatedFunction.checkSorted;

class AbstractTabulatedFunctionTest {

    @Test
    void checkLengthIsTheSameTest() {
        double xVal[] = {0.0, 1.0, 2.0};
        double yVal[] = {0.0, 1.0};
        try {
            checkLengthIsTheSame(xVal, yVal);
            fail("Ожидалось исключение DifferentLengthOfArraysException");
        } catch (DifferentLengthOfArraysException e) {
        }
    }

    @Test
    void checkSortedTest() {
        double xVal[] = {-2.0, 3.0, 0.0};

        try {
            checkSorted(xVal);
            fail("Ожидалось исключение ArrayIsNotSortedException");
        } catch (ArrayIsNotSortedException e) {
        }
    }

    @Test
    void testToStringArr() {
        double[] xArr = {0.0, 1.0, 2.0};
        double[] yArr = {3.0, 4.0, 5.0};
        AbstractTabulatedFunction testArr= new ArrayTabulatedFunction(xArr, yArr);

        assertEquals("[(0.0; 3.0), (1.0; 4.0), (2.0; 5.0)]", testArr.toString());
    }

    @Test
    void testToStringList() {
        double[] xArr = {0.0, 1.0, 2.0};
        double[] yArr = {3.0, 4.0, 5.0};
        AbstractTabulatedFunction testList= new LinkedListTabulatedFunction(xArr, yArr);

        assertEquals("[(0.0; 3.0), (1.0; 4.0), (2.0; 5.0)]", testList.toString());
    }

}