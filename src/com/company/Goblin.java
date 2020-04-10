package com.company;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class Goblin extends Enemy{
    private BufferedImage readImg =  ImageIO.read(new File("img/alien.png"));
    private ArrayList<Enemy> enemies;
    private long lastShot;
    private int counter = 0;
    public Goblin(int x, int y, int width, int height) throws IOException {
        super(x, y, width, height);
        lastShot = System.currentTimeMillis();
        enemies = new ArrayList<>();
    }
    public ArrayList<Enemy> getEnemies(){
        return enemies;
    }
    public void update(Player playerRect, ArrayList<Enemy> enemies) throws IOException {
        super.update(playerRect, enemies);
        move();
        attackPattern();
        for (int i = 0; i < super.kevin.size(); i++) {
            kevin.get(i).update(1920,1080);
        }
        counter++;
    }
    public void attackPattern() throws IOException {
        if((System.currentTimeMillis()-lastShot)/1000 == 3){
            shoot("burst",3);
            lastShot = System.currentTimeMillis();
        }
    }
    public int getCounter(){
        return counter;
    }
}
