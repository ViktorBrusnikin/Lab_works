package ru.ssau.tk._viktor_._lab2_.io;

import ru.ssau.tk._viktor_._lab2_.functions.TabulatedFunction;

import java.io.*;

public final class FunctionsIO {

    private FunctionsIO() {
        throw new UnsupportedOperationException();
    }

    public static void writeTabulatedFunction(BufferedWriter writer, TabulatedFunction function) {
        PrintWriter printWriter = new PrintWriter(writer);

        printWriter.println(function.getCount());

        for (int i = 0; i < function.getCount(); i++) {
            double x = function.getX(i);
            double y = function.getY(i);
            printWriter.printf("%f %f%n", x, y);
        }

        printWriter.flush();
    }

    public static void writeTabulatedFunction(BufferedOutputStream outputStream, TabulatedFunction function) throws IOException {
        DataOutputStream dataOutputStream = new DataOutputStream(outputStream);
        dataOutputStream.writeInt(function.getCount());

        for (int i = 0; i < function.getCount(); i++) {
            double x = function.getX(i);
            double y = function.getY(i);
            dataOutputStream.writeDouble(x);
            dataOutputStream.writeDouble(y);
        }

        dataOutputStream.flush();
    }
}