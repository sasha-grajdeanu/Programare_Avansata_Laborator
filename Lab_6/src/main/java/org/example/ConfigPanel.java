package org.example;

import javax.swing.*;
import java.awt.event.ActionEvent;

public class ConfigPanel extends JPanel {
    final MainFrame frame;
    JLabel dotsLabel, probability;
    JSpinner dotsNumber;
    JComboBox<Double> lineProbability;
    JButton createButton;

    /**
     * creation of the configuration panel
     *
     * @param frame the main frame
     */
    public ConfigPanel(MainFrame frame) {
        this.frame = frame;
        init();
    }

    /**
     * initialization of the configuration panel
     */
    private void init() {
        dotsLabel = new JLabel("Number of dots: ");
        dotsNumber = new JSpinner(new SpinnerNumberModel(5, 3, 100, 1));
        probability = new JLabel("The probabilities: ");
        Double[] prob = new Double[]{0.0, 0.1, 0.2, 0.3, 0.4, 0.5, 0.6, 0.7, 0.8, 0.9, 1.0};
        lineProbability = new JComboBox<Double>(prob);
        createButton = new JButton("Generate");
        add(dotsLabel);
        add(dotsNumber);
        add(probability);
        add(lineProbability);
        add(createButton);
        createButton.addActionListener(this::generation);
    }

    /**
     * refreshing the canvas
     *
     * @param actionEvent the action event
     */
    private void generation(ActionEvent actionEvent) {
        this.frame.canvas.createBoard();
    }
}
