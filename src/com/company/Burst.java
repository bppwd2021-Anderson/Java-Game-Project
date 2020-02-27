package com.company;

import java.awt.*;
import java.io.IOException;

public class Burst extends Projectile {
    private int width,height;
    private Entity parent;
    private int x,y;
    private int velX, velY;
    private int distTraveled;
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
    public void burst() {
        if(parent != null && parent instanceof Shootable){
            //Up
            ((Shootable) parent).createBullet(new Projectile(this.x + (this.get_width() / 2)-10, this.y + (this.get_height() / 2), 10, 10,0,-20,this));
            //Down
            ((Shootable) parent).createBullet(new Projectile(this.x + (this.get_width() / 2)-10, this.y + (this.get_height() / 2), 10, 10,0,20,this));
            //Left
            ((Shootable) parent).createBullet(new Projectile(this.x + (this.get_width() / 2)-10, this.y + (this.get_height() / 2), 10, 10,-20,0,this));
            //Right
            ((Shootable) parent).createBullet(new Projectile(this.x + (this.get_width() / 2)-10, this.y + (this.get_height() / 2), 10, 10,20,0,this));
            //upLeft
            ((Shootable) parent).createBullet(new Projectile(this.x + (this.get_width() / 2)-10, this.y + (this.get_height() / 2), 10, 10,-20,-20,this));
            //upRight
            ((Shootable) parent).createBullet(new Projectile(this.x + (this.get_width() / 2)-10, this.y + (this.get_height() / 2), 10, 10,20,-20,this));
            //downLeft
            ((Shootable) parent).createBullet(new Projectile(this.x + (this.get_width() / 2)-10, this.y + (this.get_height() / 2), 10, 10,-20,20,this));
            //downRight
            ((Shootable) parent).createBullet(new Projectile(this.x + (this.get_width() / 2)-10, this.y + (this.get_height() / 2), 10, 10,20,20,this));

        }
        //         else if(parent instanceof Player && mode.equals("target")){
//             x -= (parent.get_x()-target.get_x())/30;
//             y -= (parent.get_y()-target.get_y())/30;
//         }
    }
}
