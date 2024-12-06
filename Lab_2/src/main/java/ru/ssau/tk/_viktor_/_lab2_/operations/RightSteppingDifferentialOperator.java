package ru.ssau.tk._viktor_._lab2_.operations;

import ru.ssau.tk._viktor_._lab2_.functions.MathFunction;

public class RightSteppingDifferentialOperator extends SteppingDifferentialOperator{

    public RightSteppingDifferentialOperator(double step) {
        super(step);
    }

    @Override
    public MathFunction derive(MathFunction function) {
        return new MathFunction() {
            @Override
            public double apply(double x) {
                return (function.apply(x + step) - function.apply(x)) / step;
            }
        };
    }

    @Override
    public double apply(double x) {
        throw new UnsupportedOperationException();
    }
}
