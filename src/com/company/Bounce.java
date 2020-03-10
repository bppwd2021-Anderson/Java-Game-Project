package com.company;

public class Bounce extends Projectile {
    private int width,height;
    private Entity parent;
    private int x,y;
    private int velX, velY;
    private int timesBounced = 0;
    public Bounce(int x, int y, int width, int height, int xVel, int yVel, Entity parent) {
        super(x, y, width, height, xVel, yVel, parent);
        this.width = width;
        this.height = height;
        this.x = x;
        this.y = y;
        this.velX = xVel;
        this.velY = yVel;
        this.parent = parent;
    }
    public void bounce(){
        this.x = -this.x;
        this.y = -this.y;
        timesBounced++;
    }

    @Override
    public void exitScreen(int SCREEN_WIDTH, int SCREEN_HEIGHT) {
        if (timesBounced > 0) {
            //Checking x boundaries for players
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
        else{
            bounce();
        }
    }
}
