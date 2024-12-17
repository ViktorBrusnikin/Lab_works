package ru.ssau.tk._viktor_._lab2_.operations;

import org.junit.jupiter.api.Test;
import ru.ssau.tk._viktor_._lab2_.functions.ArrayTabulatedFunction;
import ru.ssau.tk._viktor_._lab2_.functions.LinkedListTabulatedFunction;
import ru.ssau.tk._viktor_._lab2_.functions.SqrFunction;
import ru.ssau.tk._viktor_._lab2_.functions.TabulatedFunction;
import ru.ssau.tk._viktor_._lab2_.functions.factory.ArrayTabulatedFunctionFactory;
import ru.ssau.tk._viktor_._lab2_.functions.factory.LinkedListTabulatedFunctionFactory;

import static org.junit.jupiter.api.Assertions.*;
class TabulatedDifferentialOperatorTest {

    @Test
    void testConstructor1() {
        TabulatedDifferentialOperator operator = new TabulatedDifferentialOperator(new LinkedListTabulatedFunctionFactory());
        assertInstanceOf(LinkedListTabulatedFunctionFactory.class, operator.getFactory());
    }

    @Test
    void testConstructor2() {
        TabulatedDifferentialOperator operator = new TabulatedDifferentialOperator();
        assertInstanceOf(ArrayTabulatedFunctionFactory.class, operator.getFactory());
    }

    @Test
    void setFactory() {
        TabulatedDifferentialOperator operator = new TabulatedDifferentialOperator();
        operator.setFactory(new LinkedListTabulatedFunctionFactory());
        assertInstanceOf(LinkedListTabulatedFunctionFactory.class, operator.getFactory());
    }

    @Test
    void deriveArrayTab() {
        TabulatedDifferentialOperator operator = new TabulatedDifferentialOperator(new ArrayTabulatedFunctionFactory());
        TabulatedFunction function = operator.derive(new ArrayTabulatedFunction(x -> Math.pow(x, 2), -2, 1, 4));

        double[] xValues = {-2.0, -1.0, 0.0, 1.0};
        double[] yValues = {-3.0, -1.0, 1.0, 1.0};

        for (int i = 0; i < function.getCount(); i++) {
            assertEquals(xValues[i], function.getX(i));
            assertEquals(yValues[i], function.getY(i));
        }
    }

    @Test
    void deriveLinkedList() {
        TabulatedDifferentialOperator operator = new TabulatedDifferentialOperator(new LinkedListTabulatedFunctionFactory());
        TabulatedFunction function = operator.derive(new LinkedListTabulatedFunction(x -> Math.pow(x, 2) + 1, -2, 1, 4));

        double[] xValues = {-2.0, -1.0, 0.0, 1.0};
        double[] yValues = {-3.0, -1.0, 1.0, 1.0};

        for (int i = 0; i < function.getCount(); i++) {
            assertEquals(xValues[i], function.getX(i));
            assertEquals(yValues[i], function.getY(i));
        }
    }

    @Test
    void deriveSynchronously() {
        TabulatedFunction tabulatedFunction = new LinkedListTabulatedFunction(new double[]{-3, 1.5, 6, 10.5, 15}, new double[]{9, 2.25, 36, 110.25, 225});
        TabulatedDifferentialOperator differentialOperator = new TabulatedDifferentialOperator(new LinkedListTabulatedFunctionFactory());
        TabulatedFunction derivedFunction = differentialOperator.deriveSynchronously(tabulatedFunction);

        assertEquals(16.5, derivedFunction.apply(6), 0.01);
    }
}