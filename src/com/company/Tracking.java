package com.company;

import java.awt.*;
import java.io.IOException;

public class Tracking extends Projectile {
    private int width,height;
    private Entity parent;
    private int x,y;
    private int velX, velY;
    private Enemy target;
    public Tracking(int x, int y, int width, int height, int xVel, int yVel, Entity parent) throws IOException {
        super(x, y, width, height, xVel, yVel, parent,  Color.blue);
        this.width = width;
        this.height = height;
        this.x = x;
        this.y = y;
        this.velX = xVel;
        this.velY = yVel;
        this.parent = parent;
    }
    public void setParent(Entity parent){
        this.parent = parent;
        if(parent instanceof Player)
            target = ((Player) parent).getTarget();
    }
    @Override
    public void exitScreen(int SCREEN_WIDTH, int SCREEN_HEIGHT) {
        //Checking x boundaries for players
        if (parent instanceof Shootable) {
            if ((this.x > (SCREEN_WIDTH / 2 + SCREEN_WIDTH / 4) - 10)) {
                ((Shootable) parent).removeBullet(this);
            }
            if (this.get_x() < (SCREEN_WIDTH / 4)) {
                ((Shootable) parent).removeBullet(this);
            }
            //Checking y boundaries for players
            if (this.get_y() > (SCREEN_HEIGHT - 10)) {
                ((Shootable) parent).removeBullet(this);
            }
            if (this.get_y() < 0) {
                ((Shootable) parent).removeBullet(this);
            }
        }
    }
}
