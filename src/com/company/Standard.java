package com.company;

import java.io.IOException;
public class Standard extends Projectile {
    private int width,height;
    private Entity parent;
    private int x,y;
    private int velX, velY;
    public Standard(int x, int y, int width, int height, int xVel, int yVel, Entity parent) throws IOException {
        super(x, y, width, height, xVel, yVel, parent);
        this.width = width;
        this.height = height;
        this.x = x;
        this.y = y;
        this.velX = xVel;
        this.velY = yVel;
        this.parent = parent;
    }
}
