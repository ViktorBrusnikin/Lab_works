package ru.ssau.tk._viktor_._lab2_.operations;

import ru.ssau.tk._viktor_._lab2_.exceptions.InconsistentFunctionsException;
import ru.ssau.tk._viktor_._lab2_.functions.Point;
import ru.ssau.tk._viktor_._lab2_.functions.TabulatedFunction;
import ru.ssau.tk._viktor_._lab2_.functions.factory.ArrayTabulatedFunctionFactory;
import ru.ssau.tk._viktor_._lab2_.functions.factory.TabulatedFunctionFactory;

public class TabulatedFunctionOperationService {

    private TabulatedFunctionFactory factory;

    public TabulatedFunctionOperationService(TabulatedFunctionFactory factory){
        this.factory = factory;
    }

    public TabulatedFunctionOperationService() {
        this.factory = new ArrayTabulatedFunctionFactory();
    }

    public TabulatedFunctionFactory FactoryGet(){
        return factory;
    }

    public void FactorySet(TabulatedFunctionFactory factory){
        this.factory = factory;
    }

    public static Point[] asPoints(TabulatedFunction tabulatedFunction){
        Point[] points = new Point[tabulatedFunction.getCount()];
        int index = 0;
        for (Point point : tabulatedFunction) {
            points[index] = point;
            index++;
        }
        return points;
    }

    private interface BiOperation {
        double apply(double u, double v);
    }

    private TabulatedFunction doOperation(TabulatedFunction a, TabulatedFunction b, BiOperation operation) {
        if (a.getCount() != b.getCount()) {
            throw new InconsistentFunctionsException("Разное количество элементов!");
        }

        Point[] pointsA = asPoints(a);
        Point[] pointsB = asPoints(b);
        double[] xValues = new double[pointsA.length];
        double[] yValues = new double[pointsA.length];

        for (int i = 0; i < pointsA.length; i++) {
            if (pointsA[i].x != pointsB[i].x) {
                throw new InconsistentFunctionsException("Значения x разные!");
            }
            xValues[i] = pointsA[i].x;
            yValues[i] = operation.apply(pointsA[i].y, pointsB[i].y);
        }

        return factory.create(xValues, yValues);
    }

    public TabulatedFunction sum(TabulatedFunction a, TabulatedFunction b) {
        return doOperation(a, b, (u, v) -> u + v);
    }

    public TabulatedFunction subtract(TabulatedFunction a, TabulatedFunction b) {
        return doOperation(a, b, (u, v) -> u - v);
    }

    public TabulatedFunction multiply(TabulatedFunction a, TabulatedFunction b){
        return doOperation(a, b, (u, v) -> u * v);
    }

    public TabulatedFunction division(TabulatedFunction a, TabulatedFunction b){
        return doOperation(a, b, (u, v) -> u / v);
    }
}
