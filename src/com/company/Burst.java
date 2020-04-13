package com.company;

import java.awt.*;
import java.io.IOException;

public class Burst extends Projectile {
    private int width,height;
    private Entity parent;
    private int x,y;
    private int velX, velY;
    private int distTraveled = 0;
    private int burstTimes;
    public Burst(int x, int y, int width, int height, int xVel, int yVel, Entity parent,int burstTimes) throws IOException {
        super(x, y, width, height, xVel, yVel, parent, Color.green);
        this.width = width;
        this.height = height;
        this.x = x;
        this.y = y;
        this.velX = xVel;
        this.velY = yVel;
        this.parent = parent;
        this.burstTimes = burstTimes;
    }

    @Override
    public void update(int SCREEN_WIDTH, int SCREEN_HEIGHT) throws IOException {
        super.update(SCREEN_WIDTH, SCREEN_HEIGHT);
        distTraveled+=Math.abs(velY);
        if(distTraveled>=20){
            multiBurst(super.get_x(),super.get_y());
        }
//        System.out.println(this.x+"  "+this.y);
    }

    public void burst(int currentX, int currentY) throws IOException {
        if(parent != null && parent instanceof Shootable){
            //Up
            ((Shootable) parent).createBullet(new Standard(this.x , currentY , 1, 1,0,-2,this));
            //Down
            ((Shootable) parent).createBullet(new Standard(this.x , currentY  , 1, 1,0,2,this));
            //Left
            ((Shootable) parent).createBullet(new Standard(this.x , currentY  , 1, 1,-2,0,this));
            //Right
            ((Shootable) parent).createBullet(new Standard(currentX , currentY  , 1, 1,2,0,this));
            //upLeft
            ((Shootable) parent).createBullet(new Standard(currentX , currentY  , 1, 1,-2,-2,this));
            //upRight
            ((Shootable) parent).createBullet(new Standard(currentX , currentY  , 1, 1,2,-2,this));
            //downLeft
            ((Shootable) parent).createBullet(new Standard(currentX , currentY  , 1, 1,-2,2,this));
            //downRight
            ((Shootable) parent).createBullet(new Standard(currentX , currentY  , 1, 1,2,2,this));

            //Inner angles. Not labeling them all
            ((Shootable)parent).createBullet(new Standard(currentX, currentY, 1, 1, 2,1,this));
            ((Shootable)parent).createBullet(new Standard(currentX, currentY, 1, 1, 1,2,this));
            ((Shootable)parent).createBullet(new Standard(currentX, currentY, 1, 1, -1,2,this));
            ((Shootable)parent).createBullet(new Standard(currentX, currentY, 1, 1, 2,-1,this));
            ((Shootable)parent).createBullet(new Standard(currentX, currentY, 1, 1, -2,-1,this));
            ((Shootable)parent).createBullet(new Standard(currentX, currentY, 1, 1, -1,-2,this));
            ((Shootable)parent).createBullet(new Standard(currentX, currentY, 1, 1, 1,-2,this));
            ((Shootable)parent).createBullet(new Standard(currentX, currentY, 1, 1, -2,1,this));

            ((Shootable) parent).removeBullet(this); // Removes the parent bullet because we don't need it anymore
//            System.out.println("BURSTING  "+currentX+"  "+currentY);
//            System.out.println("ACTUAL    "+this.x+"  "+this.y);
        }
        //         else if(parent instanceof Player && mode.equals("target")){
//             x -= (parent.get_x()-target.get_x())/30;
//             y -= (parent.get_y()-target.get_y())/30;
//         }
    }
    public void multiBurst(int currentX,int currentY) throws IOException {
        if (parent != null && parent instanceof Shootable) {
            if(burstTimes!=0) {
                ((Shootable) parent).createBullet(new Burst(this.x, currentY, 10, 1, 0, -2, this, burstTimes));
                ((Shootable) parent).createBullet(new Burst(this.x , currentY  , 1, 1,0,2,this, burstTimes));
                ((Shootable) parent).createBullet(new Burst(this.x, currentY, 10, 1, -2, 0, this, burstTimes));
                ((Shootable) parent).createBullet(new Burst(currentX, currentY, 10, 1, 2, 0, this, burstTimes));
                ((Shootable) parent).createBullet(new Burst(currentX, currentY, 10, 1, -2, -2, this, burstTimes));
                ((Shootable) parent).createBullet(new Burst(currentX, currentY, 10, 1, 2, -2, this, burstTimes));
                ((Shootable) parent).createBullet(new Burst(currentX, currentY, 10, 1, -2, 2, this, burstTimes));
                ((Shootable) parent).createBullet(new Burst(currentX, currentY, 10, 1, 2, 2, this, burstTimes));
                ((Shootable) parent).createBullet(new Burst(currentX, currentY, 10, 1, 2, 1, this, burstTimes));
                ((Shootable) parent).createBullet(new Burst(currentX, currentY, 10, 1, 1, 2, this, burstTimes));
                ((Shootable) parent).createBullet(new Burst(currentX, currentY, 10, 1, -1, 2, this, burstTimes));
                ((Shootable) parent).createBullet(new Burst(currentX, currentY, 10, 1, 2, -1, this, burstTimes));
                ((Shootable) parent).createBullet(new Burst(currentX, currentY, 10, 1, -2, -1, this, burstTimes));
                ((Shootable) parent).createBullet(new Burst(currentX, currentY, 10, 1, -1, -2, this, burstTimes));
                ((Shootable) parent).createBullet(new Burst(currentX, currentY, 10, 1, 1, -2, this, burstTimes));
                ((Shootable) parent).createBullet(new Burst(currentX, currentY, 10, 1, -2, 1, this, burstTimes));
                burstTimes--;
            }
            else{
                System.out.println("Out of burst :(");
            }
            ((Shootable) parent).removeBullet(this); // Removes the parent bullet because we don't need it anymore
        }
        else {
            System.out.println("parent: "+parent+"   parent instanceof Shootable");
        }
    }

    @Override
    public void exitScreen(int SCREEN_WIDTH, int SCREEN_HEIGHT) {
        //Checking x boundaries for players
        if (parent instanceof Shootable) {
            if ((this.x > (SCREEN_WIDTH / 2 + SCREEN_WIDTH / 4) - 1)) {
                ((Shootable) parent).removeBullet(this);
            }
            if (this.get_x() < (SCREEN_WIDTH / 4)) {
                ((Shootable) parent).removeBullet(this);
            }
            //Checking y boundaries for players
            if (this.get_y() > (SCREEN_HEIGHT - 1)) {
                ((Shootable) parent).removeBullet(this);
            }
            if (this.get_y() < 0) {
                ((Shootable) parent).removeBullet(this);
            }
        }
    }
    public Shootable getParent(){
        return (Shootable) parent;
    }
}

/*
            //This can be the mechanic for when the player gets hurt && has more than 1 health
            //Up
            ((Shootable) parent).createBullet(new Projectile(this.x , this.y  distTraveled , 1, 1,0,2,this));
            //Down
            ((Shootable) parent).createBullet(new Projectile(this.x , this.y  distTraveled , 1, 1,0,2,this));
            //Left
            ((Shootable) parent).createBullet(new Projectile(this.x , this.y  distTraveled , 1, 1,2,0,this));
            //Right
            ((Shootable) parent).createBullet(new Projectile(this.x , this.y  distTraveled , 1, 1,2,0,this));
            //upLeft
            ((Shootable) parent).createBullet(new Projectile(this.x , this.y  distTraveled , 1, 1,2,2,this));
            //upRight
            ((Shootable) parent).createBullet(new Projectile(this.x , this.y  distTraveled , 1, 1,2,2,this));
            //downLeft
            ((Shootable) parent).createBullet(new Projectile(this.x , this.y  distTraveled , 1, 1,2,2,this));
            //downRight
            ((Shootable) parent).createBullet(new Projectile(this.x , this.y  distTraveled , 1, 1,2,2,this));
            */
