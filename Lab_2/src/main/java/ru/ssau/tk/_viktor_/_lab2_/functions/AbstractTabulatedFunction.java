package ru.ssau.tk._viktor_._lab2_.functions;


import ru.ssau.tk._viktor_._lab2_.exceptions.ArrayIsNotSortedException;
import ru.ssau.tk._viktor_._lab2_.exceptions.DifferentLengthOfArraysException;

public abstract class AbstractTabulatedFunction implements TabulatedFunction{

    protected int count;

    protected abstract int floorIndexOfX(double x);

    protected abstract double extrapolateLeft(double x);

    protected abstract double extrapolateRight(double x);

    protected abstract double interpolate(double x, int floorIndex);

    protected double interpolate(double x, double leftX, double rightX, double leftY, double rightY) {
        return leftY + (x - leftX) * (rightY - leftY) / (rightX - leftX);
    }

    @Override
    public double apply(double x) {
        if (x < leftBound()) {
            return extrapolateLeft(x);
        } else if (x > rightBound()) {
            return extrapolateRight(x);
        } else {
            int index = indexOfX(x);
            if (index != -1) {
                return getY(index);
            } else {
                int floorIndex = floorIndexOfX(x);
                return interpolate(x, floorIndex);
            }
        }
    }

    static void checkLengthIsTheSame(double[] xValues, double[] yValues){
        if(xValues.length != yValues.length){
            throw new DifferentLengthOfArraysException("Длины массивов xValues и yValues не совпадают.");
        }
    }

    static void checkSorted(double[] xValues){
        for (int i = 0; i < xValues.length-1; i++){
            if(xValues[i] > xValues[i+1]){
                throw new ArrayIsNotSortedException("Массив xValues должен быть отсортирован по возрастанию.");
            }
        }
    }
}
