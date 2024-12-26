package ru.ssau.tk._viktor_._lab2_.ui;

import ru.ssau.tk._viktor_._lab2_.functions.factory.ArrayTabulatedFunctionFactory;
import ru.ssau.tk._viktor_._lab2_.functions.factory.LinkedListTabulatedFunctionFactory;
import ru.ssau.tk._viktor_._lab2_.operations.TabulatedFunctionOperationService;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;



public class ChooseFactory extends JDialog {
    private final int WIDTH_DIALOG = 600;
    private final int HEIGHT_DIALOG = 375;

    public ChooseFactory(JFrame owner, TabulatedFunctionOperationService factoryService) {
        super(owner, "Настройки", true); // Модальное окно
        setSize(WIDTH_DIALOG, HEIGHT_DIALOG);
        setLocationRelativeTo(null);

        JPanel gradientPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2d = (Graphics2D) g;

                g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                        RenderingHints.VALUE_ANTIALIAS_ON);

                GradientPaint gp = new GradientPaint(200, 0, new Color(113, 138, 113), 0, getHeight(), new Color(54, 85, 54));

                g2d.setPaint(gp);
                g2d.fillRect(0, 0, getWidth(), getHeight());
            }
        };

        gradientPanel.setLayout(new GridLayout(2, 1, 10, 10)); // Две строки, равномерное распределение и отступы

        RoundedButton arrayFactoryButton = new RoundedButton("Массив", new Color(129, 156, 110), new Color(54, 85, 54));
        arrayFactoryButton.addActionListener(_ -> {
            factoryService.FactorySet(new ArrayTabulatedFunctionFactory());
            dispose();
        });

        RoundedButton listFactoryButton = new RoundedButton("Связный список", new Color(129, 156, 110), new Color(54, 85, 54));
        listFactoryButton.addActionListener(_ -> {
            factoryService.FactorySet(new LinkedListTabulatedFunctionFactory());
            dispose();
        });

        gradientPanel.add(arrayFactoryButton);
        gradientPanel.add(listFactoryButton);

        add(gradientPanel);

        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                dispose();
            }
        });
    }

    // Кастомная кнопка с округлыми углами
    public static class RoundedButton extends JButton {
        public RoundedButton(String label, Color bgColor, Color textColor) {
            super(label);
            setContentAreaFilled(false);
            setFocusPainted(false);
            setForeground(textColor);
            setBackground(bgColor);
            setFont(new Font("Arial", Font.BOLD, 14));
        }

        @Override
        protected void paintComponent(Graphics g) {
            Graphics2D g2d = (Graphics2D) g;
            g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            g2d.setColor(getBackground());
            g2d.fillRoundRect(0, 0, getWidth(), getHeight(), 25, 25);
            super.paintComponent(g);
        }

        @Override
        protected void paintBorder(Graphics g) {
            Graphics2D g2d = (Graphics2D) g;
            g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            g2d.setColor(getForeground());
            g2d.drawRoundRect(0, 0, getWidth() - 1, getHeight() - 1, 25, 25);
        }
    }
}