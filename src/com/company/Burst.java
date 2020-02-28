package com.company;

import java.awt.*;
import java.io.IOException;

public class Burst extends Projectile {
    private int width,height;
    private Entity parent;
    private int x,y;
    private int velX, velY;
    public boolean hasBurst;
    private int burstTimes = 2;
    public Burst(int x, int y, int width, int height, int xVel, int yVel, Entity parent) throws IOException {
        super(x, y, width, height, xVel, yVel, parent);
        this.width = width;
        this.height = height;
        this.x = x;
        this.y = y;
        this.velX = xVel;
        this.velY = yVel;
        this.parent = parent;
    }
    public void burst(int currentX, int currentY) throws IOException {
        if(parent != null && parent instanceof Shootable){
            //Up
            ((Shootable) parent).createBullet(new Standard(this.x , this.y, 10, 10,0,-20,this));
            //Down
            ((Shootable) parent).createBullet(new Standard(this.x , this.y , 10, 10,0,20,this));
            //Left
            ((Shootable) parent).createBullet(new Standard(this.x , this.y , 10, 10,-20,0,this));
            //Right
            ((Shootable) parent).createBullet(new Standard(this.x , this.y , 10, 10,20,0,this));
            //upLeft
            ((Shootable) parent).createBullet(new Standard(this.x , this.y , 10, 10,-20,-20,this));
            //upRight
            ((Shootable) parent).createBullet(new Standard(this.x , this.y , 10, 10,20,-20,this));
            //downLeft
            ((Shootable) parent).createBullet(new Standard(this.x , this.y , 10, 10,-20,20,this));
            //downRight
            ((Shootable) parent).createBullet(new Standard(this.x , this.y , 10, 10,20,20,this));
            if(burstTimes<=2) {
                hasBurst = true;
            }
            else{
                burstTimes++;
            }
        }
        //         else if(parent instanceof Player && mode.equals("target")){
//             x -= (parent.get_x()-target.get_x())/30;
//             y -= (parent.get_y()-target.get_y())/30;
//         }
    }
}

/*
            //This can be the mechanic for when the player gets hurt && has more than 1 health
            //Up
            ((Shootable) parent).createBullet(new Projectile(this.x , this.y , 10, 10,0,-20,this));
            //Down
            ((Shootable) parent).createBullet(new Projectile(this.x , this.y , 10, 10,0,20,this));
            //Left
            ((Shootable) parent).createBullet(new Projectile(this.x , this.y , 10, 10,-20,0,this));
            //Right
            ((Shootable) parent).createBullet(new Projectile(this.x , this.y , 10, 10,20,0,this));
            //upLeft
            ((Shootable) parent).createBullet(new Projectile(this.x , this.y , 10, 10,-20,-20,this));
            //upRight
            ((Shootable) parent).createBullet(new Projectile(this.x , this.y , 10, 10,20,-20,this));
            //downLeft
            ((Shootable) parent).createBullet(new Projectile(this.x , this.y , 10, 10,-20,20,this));
            //downRight
            ((Shootable) parent).createBullet(new Projectile(this.x , this.y , 10, 10,20,20,this));
            */
