package com.company;
/*TODO
*  Take projectiles from MyGame and bring them in here
*  idk finish it
* */

import org.w3c.dom.css.Rect;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class Player implements Entity {
    private int _width,_height;
    public int _x,_y;
    private int xVel = 5,yVel = 5;
    private ArrayList<Projectile> kevin = new ArrayList<>();
    private boolean dead;
    private int health;
    private Enemy target;
    private String playerImg = "D:/Anderson/Junior Year/Java/w7/boat.png";
    private BufferedImage readImg =  ImageIO.read(new File(playerImg));
    public Player(int x, int y, int width, int height) throws IOException {
        _width = width;
        _height = height;
        _x = x;
        _y = y;
        health = 5;

    }

    public boolean contains(int x, int y) {
        return (x >= _x) && (x <= _x + _width) && (y >= _y) && (y <= _y + _height);
    }

    public boolean intersects(Entity rect) {
        if((_y)<=(rect.get_y()+rect.get_height()) && (rect.get_y()+rect.get_height()<=_y+_height) && ((rect.get_x()+rect.get_width())>_x) && (rect.get_x()<(_x+_width))){
            return true;
        }
        if(rect.get_y()<=(_y+_height) && (rect.get_y()>=_y) && ((rect.get_x()+rect.get_width())>_x) && (rect.get_x()<(_x+_width))) {
            return true;
        }
        if((rect.get_x())<=(_x+_width) && (rect.get_x()>=_x) && (rect.get_y()<(_y+_height)) && ((rect.get_y()+rect.get_height())>_y)){
            return true;
        }
        if ((((rect.get_x()+rect.get_width()))>=_x) && (rect.get_x()<=_x) && (rect.get_y()<(_y+_height)) && ((rect.get_y()+rect.get_height())>_y)){
            return true;
        }
        return false;
    }
    public void setTarget(Enemy target){
        this.target = target;
    }

    public Enemy getTarget() {
        return target;
    }

    public void move(String direction){
        if(direction.equals("up")){
            _y-=yVel;
        }
        if(direction.equals("down")){
            _y+=yVel;
        }
        if(direction.equals("left")){
            _x-=xVel;
        }
        if(direction.equals("right")){
            _x+=xVel;
        }
    }
    public void healthLoss(){
        health --;
    }
    public void kill(){
        dead = true;
        health-=5;
    }
    public void restart(){
        health = 5;
        dead = false;
    }
    public void shoot(String direction) throws IOException {
        Projectile newBullet = new Projectile(this._x + (this.get_width() / 2)-10, this._y + (this.get_height() / 2), 10, 10, direction);
        newBullet.setParent(this);
        kevin.add(newBullet);
    }

    public void draw(Graphics pen) throws IOException {
        pen.drawImage(readImg,_x-26,_y-25,_width+50,_height+50 ,null);
        pen.fillRect(_x,_y,_width,_height);
        pen.setColor(Color.black);
        pen.drawRect(_x,_y,_width,_height);
        pen.drawRect(_x+1,_y+1,_width-2,_height-2);
        for (int i = 0; i < kevin.size(); i++) {
            kevin.get(i).move();
            kevin.get(i).draw(pen);
        }
    }

    @Override
    public String toString() {
        return "Player[x="+_x+",y="+_y+",width="+_width+",height="+_height+"]";
    }

    public int get_health() {
        return health;
    }

    public boolean isDead(){
        return dead;
    }

    public int get_height() {
        return _height;
    }
    public int get_width() {
        return _width;
    }

    public ArrayList<Projectile> getKevin() {
        return kevin;
    }

    public String getPlayerImg() {
        return playerImg;
    }

    public int get_x() {
        return _x;
    }

    public int get_y() {
        return _y;
    }
}