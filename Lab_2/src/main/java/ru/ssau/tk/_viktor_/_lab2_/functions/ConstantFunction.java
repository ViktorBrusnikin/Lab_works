package ru.ssau.tk._viktor_._lab2_.functions;

public class ConstantFunction implements MathFunction{
    private final double constant;

    public ConstantFunction(double num){
        this.constant = num;
    }

    public double getConstant(){
        return constant;
    }

    @Override
    public double apply(double x){
        return constant;

    }
}
