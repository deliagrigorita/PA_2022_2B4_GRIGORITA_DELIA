package com.gui;

import javax.swing.*;
import java.awt.event.ActionEvent;

public class ConfigPanel extends JPanel {
    final MainFrame frame;
    JLabel label;
    JSpinner spinner;
     public ConfigPanel(MainFrame frame) {
        this.frame = frame;
        init();
    }
    private void init() {
//create the label and the spinner
        label = new JLabel("Grid size:");
        spinner = new JSpinner(new SpinnerNumberModel(10, 2, 100, 1));
        add(label); //JPanel uses FlowLayout by default
        add(spinner);
    }

    private void exitGame(ActionEvent e) {
        frame.dispose();
    }
}
