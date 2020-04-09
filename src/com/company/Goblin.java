package com.company;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class Goblin extends Enemy{
    private BufferedImage readImg =  ImageIO.read(new File("img/alien.png"));
    private ArrayList<Enemy> enemies;
    public Goblin(int x, int y, int width, int height) throws IOException {
        super(x, y, width, height);
        enemies = new ArrayList<>();
    }
    public ArrayList<Enemy> getEnemies(){
        return enemies;
    }
    public void update(Player playerRect, ArrayList<Enemy> enemies){
        move();
        isHit(playerRect);
        if(health<=0){
            die(enemies);
            playerRect.incrementScore(100);
        }
    }


}
