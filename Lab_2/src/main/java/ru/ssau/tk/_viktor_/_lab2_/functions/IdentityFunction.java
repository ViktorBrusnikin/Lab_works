package ru.ssau.tk._viktor_._lab2_.functions;

public class IdentityFunction implements MathFunction{
    @Override
    public double apply(double x){
        return x;
    }
    @Override
    public String toString() {
        return "[IdentityFunction] Объекты этого класса выполняют тождественное преобразование";
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        return o instanceof IdentityFunction;
    }
    @Override
    public int hashCode(){
        return 1;
    }
    @Override
    public IdentityFunction clone() {
        return new IdentityFunction();
    }
}
