package ru.ssau.tk._viktor_._lab2_.operations;

import ru.ssau.tk._viktor_._lab2_.functions.MathFunction;

public abstract class SteppingDifferentialOperator implements DifferentialOperator<MathFunction>{
    protected double step;

    public SteppingDifferentialOperator(double step){
        if(step <= 0 || Double.isInfinite(step) || Double.isNaN(step)){
            throw new IllegalArgumentException();
        }
        this.step = step;
    }
    public void stepSet(double step){
        if(step <= 0 || Double.isInfinite(step) || Double.isNaN(step)){
            throw new IllegalArgumentException();
        }
        this.step = step;
    }
    public double stepGet(){
        return step;
    }

    @Override
    public abstract MathFunction derive(MathFunction function);
}
