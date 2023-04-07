package org.example;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.Line2D;
import java.awt.geom.Point2D;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.List;
import java.util.*;

public class DrawingPanel extends JPanel implements Serializable {

    final static int W = 800, H = 600;
    final MainFrame mainFrame;
    int contor = 0;
    Player player1 = new Player();
    Player player2 = new Player();
    boolean tura = true;
    boolean start = false;
    BufferedImage image;
    Graphics2D graphics2D;
    private int nrVertices;
    private double probability;
    private int[] x, y;
    private Map<Line2D, Integer> lines = new HashMap<>();

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
        reconstructionImage();
    }

    /**
     * the method to save information about actual game
     *
     * @throws IOException
     */
    public void savingData() throws IOException {
        FileOutputStream fos = new FileOutputStream("./serialization/testing.ser");
        ObjectOutputStream out = new ObjectOutputStream(fos);
        out.writeObject(nrVertices);
        out.writeObject(x);
        out.writeObject(y);
        out.writeObject(lines);
        out.writeObject(contor);
        out.writeObject(tura);
        out.writeObject(player1);
        out.writeObject(player2);
        out.flush();
        fos.close();
        reloadGame();
    }

    /**
     * a method to reload the previous game
     *
     * @throws IOException
     * @throws ClassNotFoundException
     */
    public void loadData() throws IOException, ClassNotFoundException {
        FileInputStream fos = new FileInputStream("./serialization/testing.ser");
        ObjectInputStream out = new ObjectInputStream(fos);
        nrVertices = (int) out.readObject();
        x = (int[]) out.readObject();
        y = (int[]) out.readObject();
        lines = (Map<Line2D, Integer>) out.readObject();
        contor = (int) out.readObject();
        tura = (boolean) out.readObject();
        player1 = (Player) out.readObject();
        player2 = (Player) out.readObject();
        fos.close();
        reloadGame();
    }

    /**
     * the method that reconstructs the graphics of the previous game
     */
    private void reloadGame() {
        createOffscreenImage();
        initPanel();
        reconstructionImage();
        repaint();
    }

    /**
     * the method that reconstructs the lines and the vertices
     */
    private void reconstructionImage() {
        for (Map.Entry<Line2D, Integer> entry : lines.entrySet()) {
            if (entry.getValue() == 0) {
                graphics2D.setColor(Color.black);
                graphics2D.draw(entry.getKey());
            }
            if (entry.getValue() == 1) {
                graphics2D.setColor(Color.blue);
                graphics2D.draw(entry.getKey());
            }
            if (entry.getValue() == 2) {
                graphics2D.setColor(Color.red);
                graphics2D.draw(entry.getKey());
            }
        }
        drawVertices();
    }

    /**
     * the initialization of the drawing component
     */

    private void initPanel() {
        setPreferredSize(new Dimension(W, H));
        setBorder(BorderFactory.createEtchedBorder());
        //the listener that make possible the selection of the lines
        this.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (!player1.hasATriangle() && !player2.hasATriangle() && (contor < lines.size())) {
                    System.out.println("Yey");
                    Point2D point = getMousePosition();
                    System.out.println(point.getX());
                    System.out.println(point.getY());
                    double boxX = point.getX() - 2;
                    double boxY = point.getY() - 2;
                    double lenght = 4;
                    for (Map.Entry<Line2D, Integer> entry : lines.entrySet()) {
                        if (entry.getKey().intersects(boxX, boxY, lenght, lenght)) {
                            System.out.println("YES");
                            if (entry.getValue() != 0) //player1
                            {
                                System.out.println("Colorat deja");
                            } else {
                                if (tura) {
                                    int node1 = -1;
                                    int node2 = -1;
                                    for (int i = 0; i < nrVertices; i++) {
                                        if (x[i] == entry.getKey().getX1()) {
                                            node1 = i;
                                        }
                                    }
                                    for (int i = 0; i < nrVertices; i++) {
                                        if (x[i] == entry.getKey().getX2()) {
                                            node2 = i;
                                        }
                                    }
                                    player1.addEdge(node1, node2);
                                    System.out.println(player1.subgraph);
                                    graphics2D.setColor(Color.BLUE);
                                    graphics2D.draw(entry.getKey());
                                    tura = false;
                                    lines.put(entry.getKey(), 1);
                                    contor++;
                                } else {
                                    int node1 = -1;
                                    int node2 = -1;
                                    for (int i = 0; i < nrVertices; i++) {
                                        if (x[i] == entry.getKey().getX1() && y[i] == entry.getKey().getY1()) {
                                            node1 = i;
                                        }
                                    }
                                    for (int i = 0; i < nrVertices; i++) {
                                        if (x[i] == entry.getKey().getX2() && y[i] == entry.getKey().getY2()) {
                                            node2 = i;
                                        }
                                    }
                                    player2.addEdge(node1, node2);
                                    System.out.println(player2.subgraph);
                                    graphics2D.setColor(Color.RED);
                                    graphics2D.draw(entry.getKey());
                                    tura = true;
                                    lines.put(entry.getKey(), 2);
                                    contor++;
                                }
                            }
                            break;
                        }

                    }
                    drawVertices();
                    if (player1.hasATriangle()) {
                        System.out.println("BLUE WIN");
                        createOffscreenImage();
                        graphics2D.setColor(Color.BLUE);
                        graphics2D.setFont(new Font("DIALOG", Font.BOLD, 50));
                        graphics2D.drawString("BLUE WIN", 250, 300);
                        graphics2D.setFont(new Font("DIALOG", Font.BOLD, 25));
                        graphics2D.drawString("Press reset to restart the game", 250, 400);


                    }
                    if (player2.hasATriangle()) {
                        System.out.println("RED WIN");
                        createOffscreenImage();
                        graphics2D.setColor(Color.RED);
                        graphics2D.setFont(new Font("DIALOG", Font.BOLD, 50));
                        graphics2D.drawString("RED WIN", 250, 300);
                        graphics2D.setFont(new Font("DIALOG", Font.BOLD, 25));
                        graphics2D.drawString("Press reset to restart the game", 250, 400);
                    } else {
                        if (contor == lines.size()) {
                            System.out.println("REMIZA");
                            createOffscreenImage();
                            graphics2D.setColor(Color.GREEN);
                            graphics2D.setFont(new Font("DIALOG", Font.BOLD, 50));
                            graphics2D.drawString("REMIZA", 250, 300);
                            graphics2D.setFont(new Font("DIALOG", Font.BOLD, 25));
                            graphics2D.drawString("Press reset to restart the game", 250, 400);
                        }
                    }
                    repaint();
                }
            }
        });
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
        lines.clear();
        player1.edges.clear();
        player2.edges.clear();
        player1.subgraph.clear();
        player2.subgraph.clear();
        contor = 0;
        tura = true;
        start = false;
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
                    Line2D line = new Line2D.Double(x[node1], y[node1], x[node2], y[node2]);
                    lines.put(line, 0);
                    graphics2D.draw(line);

                }
            }
        }
        start = true;
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

    /**
     * create the base of the image
     */
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
