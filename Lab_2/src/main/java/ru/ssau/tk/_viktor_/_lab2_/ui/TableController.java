package ru.ssau.tk._viktor_._lab2_.ui;

import ru.ssau.tk._viktor_._lab2_.functions.TabulatedFunction;
import ru.ssau.tk._viktor_._lab2_.functions.factory.LinkedListTabulatedFunctionFactory;
import ru.ssau.tk._viktor_._lab2_.functions.factory.TabulatedFunctionFactory;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class TableController extends JDialog {

    private JTextField pointCount;
    private JTable table;
    private DefaultTableModel tableModel;
    private JPanel tablePanel;
    private LinkedListTabulatedFunctionFactory factory;
    private TabulatedFunction tabulatedFunction;

    public TableController(JFrame owner, TabulatedFunctionFactory tabulatedFunctionFactory) {
        super(owner, true);
        factory = new LinkedListTabulatedFunctionFactory();
        setTitle("Создать Табулированную функцию");
        setSize(600, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JPanel gradientPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2d = (Graphics2D) g;


                g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                        RenderingHints.VALUE_ANTIALIAS_ON);

                GradientPaint gp = new GradientPaint(0, 0, new Color(0x819C6E), 0, getHeight(), new Color(0x365536)); // Нижняя часть фона (более светлый серый)

                g2d.setPaint(gp);
                g2d.fillRect(0, 0, getWidth(), getHeight());
            }
        };

        setLayout(new BorderLayout());
        gradientPanel.setLayout(new FlowLayout());
        JLabel pointCountLabel = new JLabel("Количество точек:");
        pointCountLabel.setForeground(new Color(0xCCDBE2));
        pointCount = new JTextField(10);
        JButton createTableButton = new RoundedButton("добавить", new Color(0xCCDBE2));
        gradientPanel.add(pointCountLabel);
        gradientPanel.add(pointCount);
        gradientPanel.add(createTableButton);

        tablePanel = new JPanel();
        tableModel = new DefaultTableModel(new Object[]{"X", "Y"}, 0);
        table = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(table);
        tablePanel.setLayout(new BorderLayout());
        tablePanel.add(scrollPane, BorderLayout.CENTER);

        JButton createFunctionButton = new RoundedButton("Создать функцию", new Color(0xCCDBE2));
        JPanel buttonPanel = new JPanel(){
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2d = (Graphics2D) g;

                g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                        RenderingHints.VALUE_ANTIALIAS_ON);

                GradientPaint gp = new GradientPaint(0, 0, new Color(0x819C6E), 0, getHeight(), new Color(0x365536)); // Нижняя часть фона (более светлый серый)

                g2d.setPaint(gp);
                g2d.fillRect(0, 0, getWidth(), getHeight());
            }
        };
        buttonPanel.add(createFunctionButton);

        add(gradientPanel, BorderLayout.NORTH);
        add(tablePanel, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);

        createTableButton.addActionListener(e -> createTable());
        createFunctionButton.addActionListener(e -> createTabulatedFunction());
        gradientPanel.setVisible(true);
    }

    private void createTable() {
        int pointCount;
        String input = this.pointCount.getText();

        try {
            pointCount = ExceptionHandling.getPointCount(input);
        } catch (IllegalArgumentException e) {
            ExceptionHandling.showErrorDialog(e.getMessage());
            return;
        }

        tableModel.setRowCount(0);
        for (int i = 0; i < pointCount; i++) {
            tableModel.addRow(new Object[]{"", ""});
        }
    }

    private void createTabulatedFunction() {
        if (table.isEditing()) {
            table.getCellEditor().stopCellEditing();
        }
        int rowCount = tableModel.getRowCount();
        double[] xValues = new double[rowCount];
        double[] yValues = new double[rowCount];
        try {
            for (int i = 0; i < rowCount; i++) {
                xValues[i] = Double.parseDouble(tableModel.getValueAt(i, 0).toString());
                yValues[i] = Double.parseDouble(tableModel.getValueAt(i, 1).toString());
            }

            for (int i = 1; i < rowCount; i++) {
                if (xValues[i] <= xValues[i - 1]) {
                    JOptionPane.showMessageDialog(this, "X должен увеличиваться!", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }
            }

            tabulatedFunction = factory.create(xValues, yValues);
            JOptionPane.showMessageDialog(this, "Функция создана!", "Victory!", JOptionPane.INFORMATION_MESSAGE);
            dispose();
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Введите корректное значение точек!", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public static class RoundedButton extends JButton {
        public RoundedButton(String label, Color textColor) {
            super(label);
            setContentAreaFilled(false);
            setFocusPainted(false);
            setForeground(textColor);
            setBackground(new Color(113, 138, 113));
            setFont(new Font("Times New Roman", Font.BOLD, 16));
        }
    }

    public TabulatedFunction getTabulatedFunction() {
        return tabulatedFunction;
    }
}