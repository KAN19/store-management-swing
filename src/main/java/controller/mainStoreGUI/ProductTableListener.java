package controller.mainStoreGUI;

import view.MainStoreGUI;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class ProductTableListener implements ListSelectionListener {

    private final MainStoreGUI mainStoreGUI;
    public ProductTableListener(MainStoreGUI mainStoreGUI) {
        this.mainStoreGUI = mainStoreGUI;
    }


    @Override
    public void valueChanged(ListSelectionEvent e) {
        int selectedRow = mainStoreGUI.getProductTable().getSelectedRow();
        if (selectedRow >= 0) {
            mainStoreGUI.updateDetailProduct(selectedRow);
        }
    }
}
