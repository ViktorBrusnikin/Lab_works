package ru.ssau.tk._viktor_._lab2_.ui.specfunc;

import ru.ssau.tk._viktor_._lab2_.functions.MathFunction;

public class CosinusFunction implements MathFunction {
    private final double constant;
    public CosinusFunction(){
        this.constant = 1;
    }
    @Override
    public double apply(double x) {
        return constant*Math.cos(x);
    }
}