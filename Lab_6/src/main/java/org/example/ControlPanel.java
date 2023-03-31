package org.example;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class ControlPanel extends JPanel {

    final MainFrame mainFrame;
    JButton exitBtn = new JButton("Exit");
    JButton saveBtn = new JButton("Save");
    JButton loadBtn = new JButton("Load");
    JButton resetBtn = new JButton("Reset");

    /**
     * creation of the buttons responsible for controlling app
     *
     * @param mainFrame the main frame
     */

    public ControlPanel(MainFrame mainFrame) {
        this.mainFrame = mainFrame;
        init();
    }

    /**
     * initialization of the control panel
     */

    private void init() {
        setLayout(new GridLayout(1, 4));
        add(saveBtn);
        add(loadBtn);
        add(resetBtn);
        add(exitBtn);
        exitBtn.addActionListener(this::exitGame);
        saveBtn.addActionListener(this::saveGame);
    }

    private void saveGame(ActionEvent actionEvent) {

    }

    /**
     * the action when the exit button is press
     *
     * @param e action event
     */
    private void exitGame(ActionEvent e) {
        mainFrame.dispose();
    }
}
