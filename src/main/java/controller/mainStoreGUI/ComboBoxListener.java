package controller.mainStoreGUI;

import domain.model.Category;
import view.MainStoreGUI;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ComboBoxListener implements ActionListener {

    private final MainStoreGUI mainStoreGUI;

    public ComboBoxListener(MainStoreGUI mainStoreGUI) {
        this.mainStoreGUI = mainStoreGUI;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JComboBox comboBox = (JComboBox) e.getSource();
        Category category = (Category)comboBox.getSelectedItem();

        mainStoreGUI.filterProductsByCategory(category);
    }
}
