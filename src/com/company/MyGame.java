package com.company;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

import static java.lang.StrictMath.E;

/* TODO: 2/25/20: Allow enemies to shoot; Create enemy waves
*   Go to Khan Academy
* */

public class MyGame extends Game {

    public static final String TITLE = "BPPWD Game";
    //SCREEN_WIDTH = 850
    //SCREEN_HEIGHT = 1050
    public static final int SCREEN_WIDTH = 1920;
    public static final int SCREEN_HEIGHT = 1080;
    private boolean hitCap = false,alive = true;
    private boolean start = true;
    private int pX = 240,pY = 250;
    private int enemyCount = 0;
    private ArrayList<Enemy> enemyList = new ArrayList<>();
    private Player playerRect = new Player(pX,pY,10,10);
    private int count = 0,secondsLived = 0/*,shootTime = 0*/;
    private boolean moveUp,moveDown,moveLeft,moveRight;
    private BufferedImage readImg = ImageIO.read(new File("img/maxresdefault.jpg/"));
    private boolean targetSet,rightClickPressed;
    private boolean shoot;
    private int speed = 17;
//

    public MyGame() throws IOException {
//        (int)(Math.random()*1100+1),(int)(Math.random()*750+1)
        enemyList.add(new Enemy(100,100,75,75));
    }
    public void update() throws IOException {
//        System.out.println(rightClickPressed);
        if(!start) {
            //Fixing X
            if((playerRect.get_x()>(SCREEN_WIDTH/2+SCREEN_WIDTH/4)-10)){
                playerRect.set_x(playerRect.get_x()-playerRect.getxVel());
            }
            if(playerRect.get_x()<(SCREEN_WIDTH/4)) {
                while (playerRect.get_x() < (SCREEN_WIDTH / 4)){
                    playerRect.set_x(playerRect.get_x() + playerRect.getxVel());
                }
            }
            //Fixing Y
            if(playerRect.get_y()>(SCREEN_HEIGHT-10)){
                playerRect.set_y(playerRect.get_y()-playerRect.getxVel());
            }
            if(playerRect.get_y()<0){
                playerRect.set_y(playerRect.get_y()+playerRect.getxVel());
            }
            //Dealing with slowdwown
//            for (int i = 0; i < enemyCount; i++) {
                System.out.println(enemyList.get(0).isSlow());
                if(enemyList.get(0).isSlow()){
                    playerRect.decrementSlowMeter();
                }
                if(playerRect.getSlowDownLeft()<1){
                    if(enemyList.get(0).isSlow())
                        enemyList.get(0).speedUp();
                }
//            System.out.println(playerRect.getSlowDownLeft());
//            try {
//                for (int i = 0; i < enemyCount + 1; i++) {
//                    if (playerRect.intersects(enemyList.get(i))) {
//                        loseHealth = true;
//                    }
//                }
//            } catch (Exception e) {
//                System.out.println("Error2: " + e);
//            }
            if(shoot) {
                try {
                        playerRect.shoot("default");
//                        Thread.sleep(10);

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
//            else if (rightClickPressed&&targetSet)
//                playerRect.shoot("target");
                if (enemyList.size() > 0) {
                    for (int x = 0; x < enemyCount + 1; x++) {
                        for (int y = 0; y < playerRect.getKevin().size(); y++) {
                            if (enemyList.size()!=0 && enemyList.get(x).intersects(playerRect.getKevin().get(y))){
                                playerRect.incrementScore();
                                playerRect.getKevin().remove(y);
                                enemyCount--;
                                if(enemyList.get(x).isTarget()){
                                    targetSet = false;
                                }
                                enemyList.remove(x);
                            }
                            else if (playerRect.getKevin().get(y).get_y() < 0) {
                                playerRect.getKevin().remove(y);
                            }
                        }
                    }
                }
            for (int x = 0; x < enemyCount + 1; x++) {
                for (int y = 0; y < enemyList.get(x).getKevin().size(); y++) {
                    if (playerRect.intersects(enemyList.get(x).getKevin().get(y))) {
                        playerRect.kill();
                    }
                    else if(enemyList.get(x).getKevin().get(y).get_y()>SCREEN_HEIGHT && enemyList.get(x).getKevin().size() != 0){
                        enemyList.get(x).getKevin().remove(enemyList.get(x).getKevin().get(y));
                    }
                }
            }
//            }
                count++;
            }
    }
    public void draw(Graphics pen) throws IOException {
//        pen.setFont(new Font("ZapfDingbats", Font.PLAIN, 20));
            if(start){
                pen.setColor(Color.WHITE);
                pen.fillRect(0,0,SCREEN_WIDTH,SCREEN_HEIGHT);
                playerRect.draw(pen);
                pen.setColor(Color.BLACK);
                pen.drawRect(40,475,750,60);
                pen.drawString("That white box in the middle of this ship is your core, don't let it get hurt okay? The rest of your ship is impervious to damage ",50,500);
                pen.drawString("press [F] to start",350,525);
            }
            else {
//                pen.drawImage(readImg, SCREEN_WIDTH/4, 0, SCREEN_WIDTH/2, 1080, null);
                pen.setColor(Color.BLACK);
                pen.fillRect(SCREEN_WIDTH/4, 0, SCREEN_WIDTH/2, 1080);
//                System.out.println("x "+SCREEN_WIDTH/4+"  x+width "+(SCREEN_WIDTH/4+SCREEN_WIDTH/2));
                pen.setColor(Color.WHITE);
                playerRect.draw(pen);
                pen.drawRect(200,200,50,200);
                pen.setColor(Color.GREEN);
                pen.fillRect(200,400,50,-(playerRect.getSlowDownLeft()*2));
                pen.setColor(Color.RED);
                try {
                    for (int listCounter = 0; listCounter < enemyCount + 1; listCounter++) {
                        enemyList.get(listCounter).draw(pen, listCounter, enemyList);
//                        enemyList.get(listCounter).move();
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
                if (playerRect.get_lives() > 0) {
                    pen.setColor(Color.black);
                } else {
                    alive = false;
                    pen.setColor(Color.black);
                    pen.drawString("Hit Enter to restart", 100, 100);
                    playerRect = new Player(0, 0, 0, 0);
                    playerRect.kill();
                }
                pen.drawString(playerRect.getSlowDownLeft()+"",200,200);
            }

    }
    @Override
    public void keyTyped(KeyEvent ke) {}

    @Override
    public void keyPressed(KeyEvent ke) {
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
        //Movement Keys
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


//        if(ke.getKeyCode() == KeyEvent.VK_E){
//            enemyList.add(new Enemy((int)(Math.random()*1100+1),(int)(Math.random()*750+1),75,75));
//        }

        //Start Game
        if(ke.getKeyCode() == KeyEvent.VK_F){
            start = false;
        }
        //Testing enemy shooting
        if(ke.getKeyCode() == KeyEvent.VK_Q){
            try {
                enemyList.get(0).shoot("default");
            } catch (IOException e) {}
        }
        //Player burst shot
        if(ke.getKeyCode() == KeyEvent.VK_SPACE){
            try {
                playerRect.shoot("burst");
            } catch (IOException e) {}
        }
        //Quit Game
        if(ke.getKeyCode() == KeyEvent.VK_ESCAPE){
            System.exit(0);
        }
        //Kill player test
        if(ke.getKeyCode() == KeyEvent.VK_X){
            playerRect.kill();
        }
        if(ke.getKeyCode() == KeyEvent.VK_DOWN){
            enemyList.get(0).slowDown();
        }
        if(ke.getKeyCode() == KeyEvent.VK_UP){
            enemyList.get(0).speedUp();
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
        if(me.isShiftDown() && me.getButton() == MouseEvent.BUTTON3) {
            try {
                if (!targetSet) {
                    System.out.println("bang");
                    playerRect.setTarget(enemyList.get(0));
                    enemyList.get(0).setTarget(true);
                    targetSet = true;
                } else {
                    targetSet = false;
                    enemyList.get(0).setTarget(false);
                }
            }catch (Exception e){
                System.out.println("lol");
            }
        }
        else if(me.getButton() == MouseEvent.BUTTON3){
            rightClickPressed = true;
        }
        else {
            shoot = true;
        }
    }

    @Override
    public void mouseReleased(MouseEvent me) {
        shoot = false;
        rightClickPressed = false;
    }

    @Override
    public void mouseEntered(MouseEvent me) {
    }

    @Override
    public void mouseExited(MouseEvent me) {
    }
    //Launches the Game
    public static void main(String[] args)throws IOException { new MyGame().start(TITLE, SCREEN_WIDTH,SCREEN_HEIGHT); }



}
