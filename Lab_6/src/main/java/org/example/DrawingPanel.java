package org.example;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.List;
import java.util.*;

public class DrawingPanel extends JPanel {

    final MainFrame mainFrame;
    final static int W = 800, H = 600;
    private int nrVertices;
    private double probability;
    private int[] x, y;

    BufferedImage image;
    Graphics2D graphics2D;

    /**
     * creation of the drawing component
     *
     * @param mainFrame the main frame
     */

    public DrawingPanel(MainFrame mainFrame) {
        this.mainFrame = mainFrame;
        createOffscreenImage();
        initPanel();
        createBoard();
    }

    /**
     * the initialization of the drawing component
     */

    private void initPanel() {
        setPreferredSize(new Dimension(W, H));
        setBorder(BorderFactory.createEtchedBorder());
    }

    /**
     * creation of the canvas, with the graphical representation of the graph
     */

    final void createBoard() {
        nrVertices = (Integer) mainFrame.configPanel.dotsNumber.getValue();
        probability = (Double) mainFrame.configPanel.lineProbability.getSelectedItem();
        createOffscreenImage();
        createVertices();
        drawVertices();
        drawLines();
        repaint();
    }

    /**
     * drawing the vertices
     */

    private void drawVertices() {
        for (int i = 0; i < nrVertices; i++) {
            graphics2D.setColor(Color.BLACK);
            graphics2D.drawOval(x[i] - 10 / 2, y[i] - 10 / 2, 10, 10);
            graphics2D.fillOval(x[i] - 10 / 2, y[i] - 10 / 2, 10, 10);
        }
    }

    /**
     * drawing the lines with the probability specified by the user
     */

    private void drawLines() {
        int maxLines = nrVertices * (nrVertices - 1) / 2;
        Map<Integer, List<Integer>> listAdiacent = new HashMap<>();

        int noOfLines = (int) (probability * 10 * maxLines) / 10;
        for (int i = 0; i < noOfLines; i++) {
            Random random = new Random();
            int node1 = random.nextInt(nrVertices);
            int node2 = random.nextInt(nrVertices);
            if (node1 == node2) {
                i--;
            } else {
                if (!listAdiacent.containsKey(node1)) {
                    listAdiacent.put(node1, new ArrayList<>());
                }
                if (!listAdiacent.containsKey(node2)) {
                    listAdiacent.put(node2, new ArrayList<>());
                }
                if (listAdiacent.get(node1).contains(node2)) {
                    i--;
                } else {
                    graphics2D.setColor(Color.black);
                    listAdiacent.get(node1).add(node2);
                    listAdiacent.get(node2).add(node1);
                    graphics2D.drawLine(x[node1], y[node1], x[node2], y[node2]);
                }
            }
        }
    }

    /**
     * creation of the vertices with their coordinates
     */

    private void createVertices() {
        int x0 = W / 2;
        int y0 = H / 2;
        int radius = H / 2 - 10;
        double alpha = 2 * Math.PI / nrVertices;
        x = new int[nrVertices];
        y = new int[nrVertices];
        for (int i = 0; i < nrVertices; i++) {
            x[i] = x0 + (int) (radius * Math.cos(alpha * i));
            y[i] = y0 + (int) (radius * Math.sin(alpha * i));
        }
    }

    private void createOffscreenImage() {
        image = new BufferedImage(W, H, BufferedImage.TYPE_INT_ARGB);
        graphics2D = image.createGraphics();
        graphics2D.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        graphics2D.setColor(Color.WHITE);
        graphics2D.fillRect(0, 0, 800, 800);
    }

    @Override
    public void update(Graphics g) {

    }

    @Override
    protected void paintComponent(Graphics g) {
        g.drawImage(image, 0, 0, this);
    }
}
