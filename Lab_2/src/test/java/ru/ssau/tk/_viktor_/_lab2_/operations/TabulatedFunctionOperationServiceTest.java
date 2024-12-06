package ru.ssau.tk._viktor_._lab2_.operations;

import org.junit.jupiter.api.Test;
import ru.ssau.tk._viktor_._lab2_.functions.ArrayTabulatedFunction;
import ru.ssau.tk._viktor_._lab2_.functions.Point;
import ru.ssau.tk._viktor_._lab2_.functions.TabulatedFunction;
import ru.ssau.tk._viktor_._lab2_.functions.factory.ArrayTabulatedFunctionFactory;
import ru.ssau.tk._viktor_._lab2_.functions.factory.LinkedListTabulatedFunctionFactory;
import ru.ssau.tk._viktor_._lab2_.functions.factory.TabulatedFunctionFactory;

import static org.junit.jupiter.api.Assertions.*;

class TabulatedFunctionOperationServiceTest {

    @Test
    void asPointsTest() {
        double[] xValues = {0.0, 1.0, 2.0};
        double[] yValues = {3.0, 4.0, 5.0};

        TabulatedFunction function = new ArrayTabulatedFunction(xValues, yValues);

        Point[] points = TabulatedFunctionOperationService.asPoints(function);

        assertEquals(function.getCount(), points.length);

        for (int i = 0; i < points.length; i++) {
            assertEquals(xValues[i], points[i].x);
            assertEquals(yValues[i], points[i].y);
        }
    }

    @Test
    void sumTest() {
        TabulatedFunctionFactory factory = new ArrayTabulatedFunctionFactory();
        TabulatedFunctionOperationService service = new TabulatedFunctionOperationService(factory);

        double[] xValues = {0.5, 1.5, 2.5};
        double[] yValues1 = {2.5, 4.5, 6.5};
        double[] yValues2 = {0.5, 1.5, 2.5};

        TabulatedFunction f1 = factory.create(xValues, yValues1);
        TabulatedFunction f2 = factory.create(xValues, yValues2);

        TabulatedFunction result = service.sum(f1, f2);
        double[] expectedY = {3.0, 6.0, 9.0};

        for (int i = 0; i < result.getCount(); i++) {
            assertEquals(expectedY[i], result.getY(i));
        }
    }

    @Test
    void subtractTest() {
        TabulatedFunctionFactory arrayFactory = new ArrayTabulatedFunctionFactory();
        TabulatedFunctionFactory linkedListFactory = new LinkedListTabulatedFunctionFactory();
        TabulatedFunctionOperationService service = new TabulatedFunctionOperationService(arrayFactory);

        double[] xValues = {5.0, 10.0, 15.0};
        double[] yValues1 = {20.0, 30.0, 40.0};
        double[] yValues2 = {15.0, 20.0, 25.0};

        TabulatedFunction f1 = arrayFactory.create(xValues, yValues1);
        TabulatedFunction f2 = linkedListFactory.create(xValues, yValues2);

        TabulatedFunction result = service.subtract(f1, f2);
        double[] expectedY = {5.0, 10.0, 15.0};

        for (int i = 0; i < result.getCount(); i++) {
            assertEquals(expectedY[i], result.getY(i));
        }
    }

    @Test
    void multiplyTest() {
        TabulatedFunctionFactory arrayFactory = new ArrayTabulatedFunctionFactory();
        TabulatedFunctionFactory linkedListFactory = new LinkedListTabulatedFunctionFactory();
        TabulatedFunctionOperationService service = new TabulatedFunctionOperationService(arrayFactory);

        double[] xValues = {1.0, 2.0, 3.0};
        double[] yValues1 = {5.0, 6.0, 7.0};
        double[] yValues2 = {1.0, 2.0, 3.0};

        TabulatedFunction f1 = arrayFactory.create(xValues, yValues1);
        TabulatedFunction f2 = linkedListFactory.create(xValues, yValues2);

        TabulatedFunction result = service.multiply(f1, f2);
        double[] expectedY = {5.0, 12.0, 21.0};

        for (int i = 0; i < result.getCount(); i++) {
            assertEquals(expectedY[i], result.getY(i));
        }
    }

    @Test
    void divisionTest() {
        TabulatedFunctionFactory arrayFactory = new ArrayTabulatedFunctionFactory();
        TabulatedFunctionFactory linkedListFactory = new LinkedListTabulatedFunctionFactory();
        TabulatedFunctionOperationService service = new TabulatedFunctionOperationService(arrayFactory);

        double[] xValues = {1.0, 2.0, 3.0};
        double[] yValues1 = {5.0, 6.0, 6.0};
        double[] yValues2 = {1.0, 2.0, 3.0};

        TabulatedFunction f1 = arrayFactory.create(xValues, yValues1);
        TabulatedFunction f2 = linkedListFactory.create(xValues, yValues2);

        TabulatedFunction result = service.division(f1, f2);
        double[] expectedY = {5.0, 3.0, 2.0};

        for (int i = 0; i < result.getCount(); i++) {
            assertEquals(expectedY[i], result.getY(i));
        }
    }
}