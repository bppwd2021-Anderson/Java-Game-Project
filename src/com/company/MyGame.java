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

/* TODO: 3/11/20:
*   Make enemies actually die and find a better way to handle them besides an array list
*   Go to Khan Academy
*   Burst bullets can't currently delete their child standards if they leave the screen
* */

public class MyGame extends Game {

    public static final String TITLE = "BPPWD Game";
    // SCREEN_WIDTH = 850
    // SCREEN_HEIGHT = 1050
    public static final int SCREEN_WIDTH = 1920;
    public static final int SCREEN_HEIGHT = 1080;

    private boolean alive = true; // Is player alive (I think?)
    private boolean start = true; // For the start screen
    private int pX = 1000,pY = 800; // Player start position, probably don't need this
    private ArrayList<Enemy> enemyList = new ArrayList<>();
    private Player playerRect = new Player(pX,pY,10,10);
    private int count = 0; // Akin to a framerate counter, used for cooldowns and efficiency
    private boolean moveUp,moveDown,moveLeft,moveRight; // Booleans for movement to make it smoother and nicer
    private BufferedImage readImg = ImageIO.read(new File("img/backrgound.png/"));
    private boolean targetSet,rightClickPressed; // Used for target bullets
    private boolean shoot; // Boolean to make shooting on mouse hold rather than 1 per mouse click
    private int speed = 17; // Speed used in the thread.sleep in Game.java, can be used later to edit speed if something comes to mind
    private boolean allEnemiesDead = false; // Pretty self explanatory
//

    public MyGame() throws IOException {
//        (int)(Math.random()*1100+1),(int)(Math.random()*750+1)
        enemyList.add(new Enemy(600,100,75,75));
    }
    public void update() throws IOException {
//        System.out.println(rightClickPressed);
        if(!start && alive) {
            // Checks if all enemies are dead or not

            playerRect.update(SCREEN_WIDTH,SCREEN_HEIGHT);
            if(enemyList.size()>0)
                enemyList.get(0).update(playerRect,enemyList);
            if(enemyList.size() == 0){
                allEnemiesDead = true;
            }
            // Dealing with slowdwown effect for the player
//            for (int i = 0; i < enemyCount; i++) {
            if(!allEnemiesDead) {
                if (enemyList.get(0).isSlow() && count % 2 == 0) {
                    playerRect.decrementSlowMeter();
                } else if (count % 10 == 0 && playerRect.getSlowDownLeft() < 100) {
                    playerRect.incrementSlowMeter();
                }
                if (playerRect.getSlowDownLeft() < 1) {
                    if (enemyList.get(0).isSlow())
                        enemyList.get(0).speedUp();
                }
            }
            try {
                for (int i = 0; i < enemyList.size(); i++) {
                    if (playerRect.intersects(enemyList.get(i))) {
                        playerRect.hurt();
                        System.out.println("OUCH");
                    }
                    if(playerRect.get_lives() == 0){
                        playerRect.kill();
                    }
                }
            } catch (Exception e) {
                System.out.println("Error2: " + e);
            }
            if(shoot && count%5==0) { // Shooting on boolean for better shooting plus cooldown of 5-ish frames per shot
                try {
                        playerRect.shoot("default");
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
//            else if (rightClickPressed&&targetSet)
//                playerRect.shoot("target");
            // Checking over every enemy and dealing damage/killing enemies. This should be removed and redone because this is a real bad system
                if (!allEnemiesDead) {
                    // Replace this
                    for (int x = 0; x < enemyList.size(); x++) {
                        for (int y = 0; y < enemyList.get(x).getKevin().size(); y++) {
                            // Checking if player dies
                            if (playerRect.intersects(enemyList.get(x).getKevin().get(y))) {
                                playerRect.hurt();
                                System.out.println("OUCH bullet");
                                if(playerRect.get_lives() == 0) {
                                    playerRect.kill();
                                }
                            }// Checking if enemy bullet goes past bottom boundary
                            else if (enemyList.get(x).getKevin().get(y).get_y() > SCREEN_HEIGHT && enemyList.get(x).getKevin().size() != 0) {
                                enemyList.get(x).getKevin().remove(enemyList.get(x).getKevin().get(y));
                            }
                        }
                    }
                }
//            }
            // Increments frame
                count++;
        }
    }
    public void draw(Graphics pen) throws IOException {
//        pen.setFont(new Font("ZapfDingbats", Font.PLAIN, 20));
            if(start){ // Displays the starting screen
                pen.setColor(Color.WHITE);
                pen.fillRect(0,0,SCREEN_WIDTH,SCREEN_HEIGHT);
                pen.setColor(Color.BLACK);
//                pen.drawRect(40,475,750,60);
                pen.drawString("Is this your first time playing?",(int)(SCREEN_WIDTH/2.75),500);
//                pen.drawString("That white box in the middle of this ship is your core, don't let it get hurt okay? The rest of your ship is impervious to damage ",50,500);
//                pen.drawString("press [F] to start",350,525);
            }
            else {// Main draw for most the game
//                pen.drawImage(readImg, SCREEN_WIDTH/4, 0, SCREEN_WIDTH/2, 1080, null);
                pen.setColor(Color.BLACK);
//                pen.fillRect(SCREEN_WIDTH/4, 0, SCREEN_WIDTH/2, 1080);// Filling the middle play area
                pen.drawImage(readImg,SCREEN_WIDTH/4, 0, SCREEN_WIDTH/2, 1080,null);
//                System.out.println("x "+SCREEN_WIDTH/4+"  x+width "+(SCREEN_WIDTH/4+SCREEN_WIDTH/2));
                pen.setColor(Color.WHITE);
                playerRect.draw(pen); // Draw player
                pen.setColor(Color.RED);
                try {
                    for (int listCounter = 0; listCounter < enemyList.size(); listCounter++) {
                        enemyList.get(listCounter).draw(pen, listCounter, enemyList); // Draw enemies in arraylist FIX
//                        enemyList.get(listCounter).move();
                    }
                } catch (Exception e) {
                    System.out.println("Error3:  " + e);
                }
                // Movement boolean checks DO NOT MAKE ELSE IF
                if(alive) {
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
                }
                if (playerRect.get_lives() > 0) {
                    pen.setColor(Color.black);
                } else {
                    // If player dies
                    alive = false;
                    pen.setColor(Color.BLACK);
                    pen.drawString("Hit Enter to restart", 100, 100);
//                    playerRect = new Player(0, 0, 0, 0);
                    playerRect.kill();
                }
                pen.drawString(playerRect.getSlowDownLeft()+"",200,200); // Draw the number above slowdown bar
                if(allEnemiesDead){
                    pen.setColor(Color.WHITE);
                    pen.drawString("ENEMIES CLEARED",1000,300);
                }
            }

    }
    @Override
    public void keyTyped(KeyEvent ke) {}

    @Override
    public void keyPressed(KeyEvent ke) {
        if (ke.getKeyCode() == 10) { // Respawn player
            if(playerRect.isDead()) {
                alive = true;
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
        if (ke.getKeyCode() == KeyEvent.VK_H){
            System.out.println(playerRect.getKevinSize());
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
