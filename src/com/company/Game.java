package com.company;
import java.awt.*;
import java.awt.event.KeyListener;
import java.awt.event.MouseListener;
import java.io.IOException;
import javax.swing.JFrame;
import javax.swing.JPanel;

public abstract class Game implements KeyListener, MouseListener {
    private JFrame frame;
    private GamePanel gamePanel;
    boolean running;
    private MyGame game;

    protected void start() throws IOException {
        this.game = (MyGame) this;
        running = true;
        frame = new JFrame(MyGame.TITLE);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        gamePanel = new GamePanel();
        frame.getContentPane().add(BorderLayout.CENTER, gamePanel);
        frame.setResizable(false);
        frame.setSize(MyGame.SCREEN_WIDTH, MyGame.SCREEN_HEIGHT);
        //-1920 puts it on the second monitor
        //0 puts it on main monitor
        frame.setLocation(1920,0);
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        frame.setUndecorated(true);
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