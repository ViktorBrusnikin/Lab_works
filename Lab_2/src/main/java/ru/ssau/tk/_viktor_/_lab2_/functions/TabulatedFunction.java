package ru.ssau.tk._viktor_._lab2_.functions;

import java.util.Iterator;

public interface TabulatedFunction extends MathFunction, Iterable<Point> {
    int getCount();

    double getX(int index);

    double getY(int index);

    void setY(int index, double value);

    int indexOfX(double x);

    int indexOfY(double y);

    double leftBound();

    double rightBound();

    @Override
    Iterator<Point> iterator();
}
