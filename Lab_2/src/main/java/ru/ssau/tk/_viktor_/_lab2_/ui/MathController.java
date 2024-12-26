package ru.ssau.tk._viktor_._lab2_.ui;

import ru.ssau.tk._viktor_._lab2_.functions.*;
import ru.ssau.tk._viktor_._lab2_.functions.factory.LinkedListTabulatedFunctionFactory;
import ru.ssau.tk._viktor_._lab2_.functions.factory.TabulatedFunctionFactory;
import ru.ssau.tk._viktor_._lab2_.ui.inputsomething.DoubleNum;
import ru.ssau.tk._viktor_._lab2_.ui.inputsomething.IntNum;
import ru.ssau.tk._viktor_._lab2_.ui.specfunc.CosinusFunction;
import ru.ssau.tk._viktor_._lab2_.ui.specfunc.SinusFunction;
import ru.ssau.tk._viktor_._lab2_.ui.specfunc.TangFunction;

import javax.swing.*;
import javax.swing.text.AbstractDocument;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Map;
import java.util.TreeMap;

public class MathController extends JDialog {
    private final JComboBox<String> functionComboBox;
    private final JTextField leftBoundField;
    private final JTextField rightBoundField;
    private final JTextField pointsCountField;
    private final Map<String, MathFunction> functionMap;
    final int PANEL_ROWS = 5;
    final int PANEL_COLUMNS = 2;
    private final TabulatedFunctionFactory factory;
    private TabulatedFunction tabulatedFunction;

    public MathController(JFrame frame, TabulatedFunctionFactory factory) {
        super(frame, true);
        this.factory = new LinkedListTabulatedFunctionFactory();
        this.functionMap = createFunctionMap();
        setTitle("Создать табулированную функцию");
        setSize(500, 300);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());

        // Устанавливаем общий шрифт для всех компонентов
        Font commonFont = new Font("Arial", Font.BOLD, 14);
        setFontForAllComponents(this, commonFont);

        JPanel panel = new JPanel(new GridBagLayout()) {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2d = (Graphics2D) g;

                g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                        RenderingHints.VALUE_ANTIALIAS_ON);

                GradientPaint gp = new GradientPaint(200, 0, new Color(0x365536), 0, getHeight(), new Color(0x718A71));

                g2d.setPaint(gp);
                g2d.fillRect(0, 0, getWidth(), getHeight());
            }
        };

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5); // Уменьшенные отступы
        gbc.anchor = GridBagConstraints.WEST;
        gbc.fill = GridBagConstraints.HORIZONTAL;

        JLabel functionLabel = new JLabel("Выберите функцию:");
        functionLabel.setForeground(new Color(0xCCDBE2));
        gbc.gridx = 0;
        gbc.gridy = 0;
        panel.add(functionLabel, gbc);

        functionComboBox = new JComboBox<>(functionMap.keySet().toArray(new String[0]));
        gbc.gridx = 1;
        panel.add(functionComboBox, gbc);

        JLabel leftBoundLabel = new JLabel("Левая граница:");
        leftBoundLabel.setForeground(new Color(0xCCDBE2));
        gbc.gridx = 0;
        gbc.gridy = 1;
        panel.add(leftBoundLabel, gbc);

        leftBoundField = new JTextField();
        ((AbstractDocument) leftBoundField.getDocument()).setDocumentFilter(new DoubleNum());
        gbc.gridx = 1;
        panel.add(leftBoundField, gbc);

        JLabel rightBoundLabel = new JLabel("Правая граница:");
        rightBoundLabel.setForeground(new Color(0xCCDBE2));
        gbc.gridx = 0;
        gbc.gridy = 2;
        panel.add(rightBoundLabel, gbc);

        rightBoundField = new JTextField();
        ((AbstractDocument) rightBoundField.getDocument()).setDocumentFilter(new DoubleNum());
        gbc.gridx = 1;
        panel.add(rightBoundField, gbc);

        JLabel pointsCountLabel = new JLabel("Количество точек:");
        pointsCountLabel.setForeground(new Color(0xCCDBE2));
        gbc.gridx = 0;
        gbc.gridy = 3;
        panel.add(pointsCountLabel, gbc);

        pointsCountField = new JTextField();
        ((AbstractDocument) pointsCountField.getDocument()).setDocumentFilter(new IntNum());
        gbc.gridx = 1;
        panel.add(pointsCountField, gbc);

        JButton createButton = new RoundedButton("Создать", new Color(0x365536));
        createButton.addActionListener(new CreateFunctionListener());
        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        panel.add(createButton, gbc);

        add(panel, BorderLayout.CENTER);
    }

    private void setFontForAllComponents(Component component, Font font) {
        component.setFont(font);
        if (component instanceof Container) {
            for (Component child : ((Container) component).getComponents()) {
                setFontForAllComponents(child, font);
            }
        }
    }

    private Map<String, MathFunction> createFunctionMap() {
        Map<String, MathFunction> map = new TreeMap<>();
        map.put("Квадратичная функция", new SqrFunction());
        map.put("Тождественная функция", new IdentityFunction());
        map.put("Функция константы 0", new ZeroFunction());
        map.put("Функция константы 1", new UnitFunction());
        map.put("Синус", new SinusFunction());
        map.put("Косинус", new CosinusFunction());
        map.put("Тангенс", new TangFunction());
        return map;
    }

    public static class RoundedButton extends JButton {
        public RoundedButton(String label, Color textColor) {
            super(label);
            setContentAreaFilled(false);
            setFocusPainted(false);
            setForeground(textColor);
            setBackground(new Color(0xCCDBE2));
            setFont(new Font("Times New Roman", Font.BOLD, 14));
        }

        @Override
        protected void paintComponent(Graphics g) {
            Graphics2D g2d = (Graphics2D) g;
            g2d.setColor(getBackground());
            g2d.fillRoundRect(0, 0, getWidth(), getHeight(), 15, 15);
            super.paintComponent(g);
        }
    }

    private class CreateFunctionListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                String selectedFunctionName = (String) functionComboBox.getSelectedItem();
                MathFunction selectedFunction = functionMap.get(selectedFunctionName);

                double leftX = Double.parseDouble(leftBoundField.getText());
                double rightX = Double.parseDouble(rightBoundField.getText());
                int pointsCount = Integer.parseInt(pointsCountField.getText());

                if (leftX >= rightX) {
                    throw new IllegalArgumentException("Левая граница должна быть меньше правой.");
                }
                if (pointsCount < 2) {
                    throw new IllegalArgumentException("Количество точек должно быть больше 1.");
                }

                double[] xValues = new double[pointsCount];
                double[] yValues = new double[pointsCount];
                double step = (rightX - leftX) / (pointsCount - 1);
                for (int i = 0; i < pointsCount; i++) {
                    xValues[i] = leftX + i * step;
                    yValues[i] = selectedFunction.apply(xValues[i]);
                }

                tabulatedFunction = factory.create(xValues, yValues);
                JOptionPane.showMessageDialog(MathController.this, "Функция создана!");
                dispose();
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(MathController.this, "Некорректный ввод!", "Error", JOptionPane.ERROR_MESSAGE);
            } catch (IllegalArgumentException ex) {
                JOptionPane.showMessageDialog(MathController.this, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    public TabulatedFunction getTabulatedFunction() {
        return tabulatedFunction;
    }
}