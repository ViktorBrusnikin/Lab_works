package ru.ssau.tk._viktor_._lab2_.ui;

import javax.swing.*;


public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            MainWindowController window = new MainWindowController ();
            window.setVisible(true);
        });
    }
}