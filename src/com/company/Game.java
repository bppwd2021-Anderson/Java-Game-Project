package com.company;
import java.awt.*;
import java.awt.event.KeyListener;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.swing.JFrame;
import javax.swing.JPanel;

public abstract class Game implements KeyListener, MouseListener {
    private JFrame frame;
    private GamePanel gamePanel;
    boolean running;
    private MyGame game;

    protected void start(String title, int width, int height) throws IOException {
        this.game = (MyGame) this;
        running = true;
        frame = new JFrame(title);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        gamePanel = new GamePanel();
        frame.getContentPane().add(BorderLayout.CENTER, gamePanel);
        frame.setResizable(false);
        frame.setSize(width, height);
        frame.setLocation(350,0);
        frame.setVisible(true);
        frame.addKeyListener(this);
        frame.addMouseListener(this);
//        frame.setCursor(frame.getToolkit().createCustomCursor(new BufferedImage(3, 3, BufferedImage.TYPE_INT_ARGB), new Point(0, 0), "null"));
        frame.setCursor(Cursor.getPredefinedCursor(Cursor.CROSSHAIR_CURSOR));
        run();
    }


    class GamePanel extends JPanel {
        private static final long serialVersionUID = 1L;

        @Override
        public void paintComponent(Graphics g) {
            try {
                game.draw(g);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void run() throws IOException {
        while (true) {
            game.update();
            try {
                Thread.sleep(17);
            } catch (InterruptedException e) {
            }
            frame.repaint();
        }
    }
}