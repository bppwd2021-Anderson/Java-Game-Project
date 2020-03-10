package com.company;

import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;

public class Enemy implements Entity,Shootable {
    private int _width;
    private int _height;
    private int _x;
    private int _y;
    private int velX = 2;
    private int velY = 2;
    private boolean left = (Math.random() * 2 + 1 % 2 == 0),right = (Math.random() * 2 + 1 % 2 == 0),up = (Math.random() * 2 + 1 % 2 == 0),down = (Math.random() * 2 + 1 % 2 == 0);
    private boolean isTarget;
    private ArrayList<Projectile> kevin = new ArrayList<>();
    private int health = 10;
    private boolean slowDown = false;

    public Enemy(int x, int y, int width, int height) {
        _width = width;
        _height = height;
        _x = x;
        _y = y;
    }

    public Enemy(){
        _width = 0;
        _height = 0;
        _x = 0;
        _y = 0;
    }
    public void update(Player playerRect, ArrayList<Enemy> enemies){
        isHit(playerRect);
        if(health<=0){
            die(enemies);
        }
    }
    public void move(){
        checkBoundaries();
        if(right)_x += velX;
        else _x-= velX;
        if(down)_y += velY;
        else _y-= velY;
    }
    public void isHit(Player playerRect){
        for (int y = 0; y < playerRect.getKevin().size(); y++) {
            if (this.intersects(playerRect.getKevin().get(y))) {
                health-=1;
                playerRect.getKevin().remove(y);
                System.out.println("Hello");
                if (this.isTarget()) {
                    isTarget = false;
                }
            }
        }
    }
    public void die(ArrayList<Enemy> enemies){
        enemies.remove(this);
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

    @Override
    public void createBullet(Projectile projectile) {
        kevin.add(projectile);
    }

    public void shoot(String type) throws IOException {
        Projectile newBullet;
        if(!slowDown) {
            switch (type) {
                case "burst":
                    newBullet = new Burst(this._x + (this.get_width() / 2) - 10, this._y + (this.get_height() / 2), 10, 10, 0, 20, this);
                    break;
                case "bounce":
                    newBullet = new Bounce(this._x + (this.get_width() / 2) - 10, this._y + (this.get_height() / 2), 10, 10, 0, 20, this);
                    break;
                case "Tracking":
                    newBullet = new Tracking(this._x + (this.get_width() / 2) - 10, this._y + (this.get_height() / 2), 10, 10, 0, 20, this);
                    break;
                default:
                    newBullet = new Standard(this._x + (this.get_width() / 2) - 10, this._y + (this.get_height() / 2), 10, 10, 0, 20, this);
                    break;
            }
        }
        else {
            switch (type) {
                case "burst":
                    newBullet = new Burst(this._x + (this.get_width() / 2) - 10, this._y + (this.get_height() / 2), 10, 10, 0, 5, this);
                    break;
                case "bounce":
                    newBullet = new Bounce(this._x + (this.get_width() / 2) - 10, this._y + (this.get_height() / 2), 10, 10, 0, 5, this);
                    break;
                case "Tracking":
                    newBullet = new Tracking(this._x + (this.get_width() / 2) - 10, this._y + (this.get_height() / 2), 10, 10, 0, 5, this);
                    break;
                default:
                    newBullet = new Standard(this._x + (this.get_width() / 2) - 10, this._y + (this.get_height() / 2), 10, 10, 0, 5, this);
                    break;
            }

        }
        newBullet.setParent(this);
        kevin.add(newBullet);
    }

    public void flipDirectionX(){
        if(left){
            right = true;
            left = false;
        }
        else{
            right = false;
            left = true;
        }
    }
    public void loseHealth(){
        health--;
    }
    public void flipDirectionY(){
        if(up){
            down = true;
            up = false;
        }
        else{
            down = false;
            up = true;
        }
    }
    public void slowDown(){
        slowDown = true;
    }
    public void speedUp(){
        slowDown = false;
    }
    public void checkBoundaries(){
        if(_x+velX >= 840){
            right = false;
            left = true;
        }

        else if(_x-velX <= 0){
            right = true;
            left = false;
        }
        if(_y+velY >=1040){
            down = false;
            up = true;
        }
        else if(_y-velY <= 0){
            down = true;
            up = true;
        }
    }
    @Override
    public void removeBullet(Projectile projectile) {
        kevin.remove(projectile);
    }

    public boolean isSlow() {
        return slowDown;
    }
//    _x+(int)(Math.random()*100+1) >= 1149
//    _y+randomY >= 749
    public void draw(Graphics pen, int listCounter, ArrayList<Enemy> enemyList) throws IOException {
        pen.fillRect(enemyList.get(listCounter).get_x(), enemyList.get(listCounter).get_y(), enemyList.get(listCounter).get_width(), enemyList.get(listCounter).get_height());
        pen.setColor(Color.RED);
        if(isTarget){
            pen.setColor(Color.blue);
            pen.fillRect(enemyList.get(listCounter).get_x(), enemyList.get(listCounter).get_y(), enemyList.get(listCounter).get_width(), enemyList.get(listCounter).get_height());
        }
        for (Projectile bullet:kevin) {
            bullet.move();
            bullet.draw(pen,Color.CYAN);
        }
//        System.out.println(kevin.size());
    }

    public int get_height() {
        return _height;
    }

    public int get_width() {
        return _width;
    }

    public int get_x() {
        return _x;
    }


    public int get_y() {
        return _y;
    }

    public int getVelX() {
        return velX;
    }

    public int getVelY() {
        return velY;
    }

    public ArrayList<Projectile> getKevin() {
        return kevin;
    }

    public void setTarget(boolean target) {
        isTarget = target;
    }

    public void get_direction(){
        boolean horrizontal = false;
        boolean vertical = false;
        if(right)
            horrizontal = true;
        if(left)
            horrizontal = false;
        if(up)
            vertical = true;
        if(down)
            vertical = false;
        System.out.println("Right = "+horrizontal+"  Left = "+!horrizontal + "  Up = "+vertical+"  Down = "+!vertical);
    }
    public String toString() {
        return "Enemy[x="+_x+",y="+_y+",width="+_width+",height="+_height+"]";
    }

    public boolean isTarget() {
        return isTarget;
    }

}