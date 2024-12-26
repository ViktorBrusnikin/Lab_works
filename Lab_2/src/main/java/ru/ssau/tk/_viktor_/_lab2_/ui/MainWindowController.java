package ru.ssau.tk._viktor_._lab2_.ui;

import ru.ssau.tk._viktor_._lab2_.operations.TabulatedDifferentialOperator;
import ru.ssau.tk._viktor_._lab2_.operations.TabulatedFunctionOperationService;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class MainWindowController extends JFrame {
    private final int WIDTH_WINDOW = 1200;
    private final int HEIGHT_WINDOW = 800;
    private TabulatedFunctionOperationService factoryService;
    private ChooseFactory settingsWindow;

    public MainWindowController() {
        setTitle("Главное окно");
        setSize(WIDTH_WINDOW, HEIGHT_WINDOW);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        getContentPane().setBackground(new Color(0x003400));

        factoryService = new TabulatedFunctionOperationService();
        setLayout(new BorderLayout());

        // Устанавливаем общий шрифт для всех компонентов
        Font commonFont = new Font("Arial", Font.BOLD, 14);
        setFontForAllComponents(this, commonFont);

        JPanel buttonPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2d = (Graphics2D) g;
                GradientPaint gp = new GradientPaint(0, 0, new Color(0x003400), 0, getHeight(), new Color(0x003400));
                g2d.setPaint(gp);
                g2d.fillRect(0, 0, getWidth(), getHeight());
            }
        };

        buttonPanel.setLayout(new GridLayout(3, 1, 10, 10)); // 3 строки и 1 колонка, отступы между кнопками

        JButton operationsButton = new RoundedButton("Элементарные операции", new Color(0x365536));
        operationsButton.addActionListener(_ -> openOperationsWindow());

        JButton differentialOperation = new RoundedButton("Операция дифференцирования", new Color(0x365536));
        differentialOperation.addActionListener(_ -> openDifferentialOperations());

        JButton settingsButton = new RoundedButton("Настройки", new Color(0x365536));
        settingsButton.addActionListener(_ -> openSettingsWindow());

        buttonPanel.add(operationsButton);
        buttonPanel.add(differentialOperation);
        buttonPanel.add(settingsButton);

        add(buttonPanel, BorderLayout.CENTER);

        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });

        setVisible(true);
    }

    private void setFontForAllComponents(Component component, Font font) {
        component.setFont(font);
        if (component instanceof Container) {
            for (Component child : ((Container) component).getComponents()) {
                setFontForAllComponents(child, font);
            }
        }
    }

    public static class RoundedButton extends JButton {
        public RoundedButton(String label, Color textColor) {
            super(label);
            setContentAreaFilled(false);
            setFocusPainted(false);
            setForeground(textColor);
            setBackground(new Color(0x819C6E));
            setFont(new Font("Times New Roman", Font.BOLD, 24));
        }

        @Override
        protected void paintComponent(Graphics g) {
            Graphics2D g2d = (Graphics2D) g;
            g2d.setColor(getBackground());
            g2d.fillRoundRect(0, 0, getWidth(), getHeight(), 15, 15);
            super.paintComponent(g);
        }
    }

    private void openSettingsWindow() {
        if (settingsWindow == null || !settingsWindow.isShowing()) {
            settingsWindow = new ChooseFactory(this, factoryService);
            settingsWindow.setVisible(true);
        }
    }


    private void openOperationsWindow() {
        new OperationsWindow(this, factoryService);
    }

    private void openDifferentialOperations() {
        new DiffOperations(this, new TabulatedDifferentialOperator(factoryService.FactoryGet()));
    }


}