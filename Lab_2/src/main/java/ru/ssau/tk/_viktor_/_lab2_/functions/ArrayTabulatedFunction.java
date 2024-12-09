package ru.ssau.tk._viktor_._lab2_.functions;


import ru.ssau.tk._viktor_._lab2_.exceptions.InterpolationException;

import java.util.Arrays;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class ArrayTabulatedFunction extends AbstractTabulatedFunction implements Cloneable {

    private double[] xValues;
    private double[] yValues;

    public ArrayTabulatedFunction(double[] xValues, double[] yValues){
        if (xValues == null || yValues == null) {
            throw new IllegalArgumentException("Массивы не должны быть null.");
        }

        if (xValues.length < 2 || yValues.length < 2) {
            throw new IllegalArgumentException("Нужно хотя бы 2 точки");
        }

        checkLengthIsTheSame(xValues, yValues);

        checkSorted(xValues);

        this.xValues = Arrays.copyOf(xValues, xValues.length);
        this.yValues = Arrays.copyOf(yValues, yValues.length);
        count = xValues.length;
    }

    public ArrayTabulatedFunction(MathFunction source, double xFrom, double xTo, int count){
        if (count < 2) {
            throw new IllegalArgumentException("Нужно хотя бы 2 точки");
        }

        if(xFrom > xTo){
            double temp = xFrom;
            xFrom = xTo;
            xTo = temp;
        }

        this.count = count;
        this.xValues = new double[count];
        this.yValues = new double[count];

        double step = (xTo - xFrom) / (count - 1);

        for (int i = 0; i < count; i++) {
            xValues[i] = xFrom + i * step;
            yValues[i] = source.apply(xValues[i]);
        }
    }

    @Override
    public int getCount(){
        return count;
    }

    @Override
    protected int floorIndexOfX(double x) {
        if(indexOfX(x) != -1) return indexOfX(x);

        if (x <= xValues[0]) {
            throw new IllegalArgumentException();
        }

        if (x >= xValues[count - 1]) {
            return count;
        }

        int index = 0;
        while (index < count && x > xValues[index]) {
            index++;
        }

        return index - 1;
    }

    @Override
    protected double extrapolateLeft(double x) {
        return interpolate(x, xValues[0], xValues[1], yValues[0], yValues[1]);
    }

    @Override
    protected double extrapolateRight(double x) {
        return interpolate(x, xValues[count - 2], xValues[count - 1], yValues[count - 2], yValues[count - 1]);
    }

    @Override
    protected double interpolate(double x, int floorIndex){
        double leftX = xValues[floorIndex];
        double rightX = xValues[floorIndex + 1];

        if (x < leftX || x > rightX) {
            throw new InterpolationException("Значение x находится вне интервала интерполяции.");
        }

        return interpolate(x, leftX, rightX, yValues[floorIndex], yValues[floorIndex + 1]);
    }

    @Override
    public double getX(int index) {
        if (index < 0) throw new IllegalArgumentException("Индекс не может быть отрицательным!");
        return xValues[index];
    }

    @Override
    public double getY(int index) {
        if (index < 0) throw new IllegalArgumentException("Индекс не может быть отрицательным!");
        return yValues[index];
    }

    @Override
    public void setY(int index, double value) {
        if (index < 0) throw new IllegalArgumentException("Индекс не может быть отрицательным!");
        yValues[index] = value;
    }

    @Override
    public int indexOfX(double x) {
        for(int i = 0; i < count; i++){
            if(x == xValues[i]) return i;
        }
        return -1;
    }

    @Override
    public int indexOfY(double y) {
        for(int i = 0; i < count; i++){
            if(y == yValues[i]) return i;
        }
        return -1;
    }

    @Override
    public double leftBound() {
        return xValues[0];
    }

    @Override
    public double rightBound() {
        return xValues[count-1];
    }

    @Override
    public String toString(){
        String res = "";
        if(count == 0){
            return "[]";
        }
        res += "[";
        for (int i = 0; i < count; i++){
            res += "(" + xValues[i] + "; " + yValues[i] + ")";
            if (i < count - 1) {
                res += ", ";
            }
        }
        res += "]";
        return res;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ArrayTabulatedFunction that = (ArrayTabulatedFunction) o;
        if (this.count != that.count) return false;

        for (int i = 0; i < count; i++) {
            if (this.xValues[i] != that.xValues[i] || this.yValues[i] != that.yValues[i]) {
                return false;
            }
        }
        return true;
    }

    @Override
    public int hashCode(){
        int res = 0;
        for (int i = 0; i < count; i++){
            res += (int)this.xValues[i] ^ (int)this.yValues[i];
        }
        return res;
    }

    @Override
    public ArrayTabulatedFunction clone(){
        double[] xVal = new double[count];
        double[] yVal = new double[count];
        for (int i = 0; i < count; i++){
            xVal[i] = this.xValues[i];
            yVal[i] = this.yValues[i];
        }
        ArrayTabulatedFunction result = new ArrayTabulatedFunction(xVal, yVal);
        return result;
    }

    @Override
    public Iterator<Point> iterator() {
        return new Iterator<Point>() {
            private int index = 0;

            @Override
            public boolean hasNext() {
                return index < count;
            }

            @Override
            public Point next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                Point point = new Point(xValues[index], yValues[index]);
                index++;
                return point;
            }
        };
    }
}
