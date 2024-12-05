package ru.ssau.tk._viktor_._lab2_.operations;

import org.junit.jupiter.api.Test;
import ru.ssau.tk._viktor_._lab2_.functions.ArrayTabulatedFunction;
import ru.ssau.tk._viktor_._lab2_.functions.Point;
import ru.ssau.tk._viktor_._lab2_.functions.TabulatedFunction;

import static org.junit.jupiter.api.Assertions.*;

class TabulatedFunctionOperationServiceTest {

    @Test
    void asPointsTest() {
        // Создаем тестовые данные
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
}