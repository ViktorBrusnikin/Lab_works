package ru.ssau.tk._viktor_._lab2_.functions;


import java.util.Arrays;

public class ArrayTabulatedFunction  extends AbstractTabulatedFunction  {

    private double[] xValues;
    private double[] yValues;

    public ArrayTabulatedFunction(double[] xValues, double[] yValues){
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
            return 0;
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
        return interpolate(x, 0);
    }

    @Override
    protected double extrapolateRight(double x) {
        return interpolate(x, count-2);
    }

    @Override
    protected double interpolate(double x, int floorIndex) {
        return interpolate(x, xValues[floorIndex], xValues[floorIndex+ 1], yValues[floorIndex], yValues[floorIndex+1]);
    }

    @Override
    public double getX(int index) {
        return xValues[index];
    }

    @Override
    public double getY(int index) {
        return yValues[index];
    }

    @Override
    public void setY(int index, double value) {
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
}
