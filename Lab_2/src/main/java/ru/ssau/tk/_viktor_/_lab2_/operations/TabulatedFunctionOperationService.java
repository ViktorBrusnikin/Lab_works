package ru.ssau.tk._viktor_._lab2_.operations;

import ru.ssau.tk._viktor_._lab2_.functions.Point;
import ru.ssau.tk._viktor_._lab2_.functions.TabulatedFunction;

public class TabulatedFunctionOperationService {
    public static Point[] asPoints(TabulatedFunction tabulatedFunction){
        Point[] points = new Point[tabulatedFunction.getCount()];
        int index = 0;
        for (Point point : tabulatedFunction) {
            points[index] = point;
            index++;
        }
        return points;
    }
}
