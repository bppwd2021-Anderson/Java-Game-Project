package com.company;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

// TODO: 2/7/2020 Fix framerate issues caused by bullet-enemy collision detection

public class MyGame extends Game {

    public static final String TITLE = "BPPWD Game";
    public static final int SCREEN_WIDTH = 850;
    public static final int SCREEN_HEIGHT = 1050;
    private boolean hitCap = false,alive = true;
    private boolean start = true;
    private int pX = 250,pY = 250;
    private int enemyCount = 0;
    private ArrayList<Enemy> enemyList = new ArrayList<>();
    private Player playerRect = new Player(pX,pY,10,10);
    private int count = 0,secondsLived = 0,shootTime = 0;
    private boolean moveUp,moveDown,moveLeft,moveRight;
    private BufferedImage readImg = ImageIO.read(new File("/D://Anderson/Junior Year/Java/w7/maxresdefault.jpg/"));
    public MyGame() throws IOException {
        enemyList.add(new Enemy((int)(Math.random()*1100+1),(int)(Math.random()*750+1),75,75));
    }
    public void update() throws IOException {
        if(!start) {
            boolean loseHealth = false;
            shootTime++;
            try {
                for (int i = 0; i < enemyCount + 1; i++) {
                    if (playerRect.intersects(enemyList.get(i))) {
                        loseHealth = true;
                    }
                }
            } catch (Exception e) {
                System.out.println("Error2: " + e);
            }
//        RunPython run = new RunPython();
            if (loseHealth && count % 15 == 0) {
                playerRect.healthLoss();
            }
//            if (count % 0 == 0) {
                if (enemyList.size() > 0) {
                    for (int x = 0; x < enemyCount + 1; x++) {
                        for (int y = 0; y < playerRect.getKevin().size(); y++) {
                            System.out.println(playerRect.getKevin().get(y).intersects(enemyList.get(x)));
                            if (playerRect.getKevin().get(y).intersects(enemyList.get(x))  /*ion(enemyList.get(x)).get_height() != 0*/) {
                                System.out.println("I hit the enemy");
                                enemyList.remove(x);
                                playerRect.getKevin().remove(y);
                            } else if (playerRect.getKevin().get(y).get_y() < 0) {
                                playerRect.getKevin().remove(y);
                                System.out.println("I have left the screen ");
                            }
                        }
                    }
                }
//            }
                count++;
                if (alive && count % 60 == 0) {
                    secondsLived++;
                }
            }
    }
    public void draw(Graphics pen) throws IOException {
            if(start){
                pen.setColor(Color.WHITE);
                pen.fillRect(0,0,SCREEN_WIDTH,SCREEN_HEIGHT);
                playerRect.draw(pen);
                pen.setColor(Color.BLACK);
                pen.drawRect(40,475,750,60);
                pen.drawString("That white box in the middle of this ship is your core, don't let it get hurt okay? The rest of your ship can get hurt as much as it wants ",50,500);
                pen.drawString("press [F] to start",350,525);
            }
            else {
                pen.drawImage(readImg, 0, 0, 850, 1025, null);
                pen.setColor(Color.WHITE);
                playerRect.draw(pen);
                pen.setColor(Color.RED);
                try {
                    for (int listCounter = 0; listCounter < enemyCount + 1; listCounter++) {
                        enemyList.get(listCounter).draw(pen, listCounter, enemyList);
                        enemyList.get(listCounter).move();
                    }
                } catch (Exception e) {
                    System.out.println("Error3:  " + e);
                }
                if (moveUp) {
                    playerRect.move("up");
                }
                if (moveDown) {
                    playerRect.move("down");
                }
                if (moveLeft) {
                    playerRect.move("left");
                }
                if (moveRight) {
                    playerRect.move("right");
                }
                if (playerRect.get_health() > 0) {
                    pen.setColor(Color.black);
                    pen.setFont(new Font("ZapfDingbats", Font.PLAIN, 20));
//            pen.drawString("Enemies: " + (enemyCount + 1), 100, 700);
//            pen.drawString("X Speed: " + enemyList[0].getVelX() + "  Y Speed: " + enemyList[0].getVelY(), 100, 800);
//            pen.drawString("Health: " + playerRect.get_health(), 100, 900);
                } else {
                    alive = false;
                    pen.setColor(Color.black);
                    pen.setFont(new Font("ZapfDingbats", Font.PLAIN, 20));
                    pen.drawString("Hit Enter to restart", 100, 100);
                    pen.drawString("You lived " + secondsLived + " seconds", 100, SCREEN_WIDTH / 2);
                    playerRect = new Player(0, 0, 0, 0);
                    playerRect.kill();
                }
            }

    }
    @Override
    public void keyTyped(KeyEvent ke) {}

    @Override
    public void keyPressed(KeyEvent ke) {
        if(ke.getKeyCode() == KeyEvent.VK_9){
//            System.out.println(playerRect.intersects((double)enemyList.get(0).get_x(),(double)enemyList.get(0).get_y(),(double)enemyList.get(0).get_width(),(double)enemyList.get(0).get_height()));
        }
        if (ke.getKeyCode() == 10) {
            if(playerRect.isDead()) {
                alive = true;
                secondsLived = 0;
                try {
                    playerRect = new Player(pX, pY, 10, 10);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                playerRect.restart();
            }
        }
        if (ke.getKeyCode() == 87) {
            moveUp = true;
        }
        if (ke.getKeyCode() == 68) {
            moveRight = true;
        }
        if (ke.getKeyCode() == 65) {
            moveLeft = true;
        }
        if (ke.getKeyCode() == 83) {
            moveDown = true;
        }
        if(ke.getKeyCode() == KeyEvent.VK_E){
            enemyList.add(new Enemy((int)(Math.random()*1100+1),(int)(Math.random()*750+1),75,75));
        }
        if(ke.getKeyCode() == KeyEvent.VK_F){
            start = false;
        }
    }

    @Override
    public void keyReleased(KeyEvent ke) {
        if (ke.getKeyCode() == 87) {
            moveUp = false;
        }
        if (ke.getKeyCode() == 68) {
            moveRight = false;
        }
        if (ke.getKeyCode() == 65) {
            moveLeft = false;
        }
        if (ke.getKeyCode() == 83) {
            moveDown = false;
        }
    }

    @Override
    public void mouseClicked(MouseEvent me) {
    }

    @Override
    public void mousePressed(MouseEvent me) {
        if(me.isShiftDown()){
            System.out.println("bang");
        }
        else {
            try {
                playerRect.shoot("up");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void mouseReleased(MouseEvent me) {}

    @Override
    public void mouseEntered(MouseEvent me) {
    }

    @Override
    public void mouseExited(MouseEvent me) {
    }

    //Launches the Game
    public static void main(String[] args)throws IOException { new MyGame().start(TITLE, SCREEN_WIDTH,SCREEN_HEIGHT); }



}
