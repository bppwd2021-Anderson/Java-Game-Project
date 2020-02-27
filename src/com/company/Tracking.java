package com.company;

import java.io.IOException;

public class Tracking extends Projectile {
    private int width,height;
    private Entity parent;
    private int x,y;
    private int velX, velY;
    private Enemy target;
    public Tracking(int x, int y, int width, int height, int xVel, int yVel, Entity parent) throws IOException {
        super(x, y, width, height, xVel, yVel, parent);
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
}
