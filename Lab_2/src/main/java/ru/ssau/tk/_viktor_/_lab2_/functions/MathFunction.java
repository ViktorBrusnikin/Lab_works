package ru.ssau.tk._viktor_._lab2_.functions;

public interface MathFunction {

    double apply(double x);

    default CompositeFunction andThen(MathFunction afterFunction) {
        if (afterFunction == null) {
            throw new IllegalArgumentException("afterFunction не может быть null");
        }
        return new CompositeFunction(this, afterFunction);
    }
}
