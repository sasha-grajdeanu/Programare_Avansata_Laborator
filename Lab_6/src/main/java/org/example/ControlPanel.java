package org.example;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.File;
import java.io.IOException;
import java.util.Random;

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
        resetBtn.addActionListener(this::resetGame);
        loadBtn.addActionListener(this::loadGame);
    }

    /**
     * the actionEvent for the load button
     *
     * @param actionEvent
     */
    private void loadGame(ActionEvent actionEvent) {
        try {
            this.mainFrame.canvas.loadData();
        } catch (IOException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * the actionEvent for the reset button
     *
     * @param actionEvent
     */
    private void resetGame(ActionEvent actionEvent) {
        this.mainFrame.configPanel.dotsNumber.setValue(5);
        this.mainFrame.configPanel.lineProbability.setSelectedItem(0.0);
        this.mainFrame.canvas.createBoard();
    }

    /**
     * the actionEvent for the save button
     *
     * @param actionEvent
     */
    private void saveGame(ActionEvent actionEvent) {
        this.mainFrame.canvas.graphics2D.dispose();
        Random random = new Random();
        int x = random.nextInt(10000);
        try {
            ImageIO.write(this.mainFrame.canvas.image, "png", new File("./poze/revenge" + x + ".png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            this.mainFrame.canvas.savingData();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
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
