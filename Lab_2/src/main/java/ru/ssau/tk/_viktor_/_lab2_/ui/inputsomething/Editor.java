package ru.ssau.tk._viktor_._lab2_.ui.inputsomething;

import javax.swing.*;
import javax.swing.table.TableCellEditor;
import javax.swing.text.AbstractDocument;
import java.awt.*;

public class Editor extends AbstractCellEditor implements TableCellEditor {
    private final JTextField textField;

    public Editor() {
        textField = new JTextField();
        ((AbstractDocument) textField.getDocument()).setDocumentFilter(new DoubleNum());
    }

    @Override
    public Object getCellEditorValue() {
        try {
            return Double.parseDouble(textField.getText());
        } catch (NumberFormatException e) {
            return 0.0;
        }
    }

    @Override
    public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
        textField.setText(value != null ? value.toString() : "");
        return textField;
    }
}
