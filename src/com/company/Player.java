package com.company;
/*TODO
*  Move drawing HUD elements that pertain to the player inside the player
* */

import org.w3c.dom.css.Rect;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class Player implements Entity,Shootable {
    private int _width,_height;
    public int _x,_y;
    private int xVel = 5,yVel = 5;
    private ArrayList<Projectile> kevin = new ArrayList<>();
    private boolean dead;
    private int lives = 3;
    private Enemy target;
    private String playerImg = "img/boat.png";
    private BufferedImage readImg =  ImageIO.read(new File(playerImg));
    private int score = 0;
    private int charge = 0;
    private int slowDownLeft = 0;
    private int burstBullets = 10;
    public Player(int x, int y, int width, int height) throws IOException {
        _width = width;
        _height = height;
        _x = x;
        _y = y;

    }
    public void update(int SCREEN_WIDTH, int SCREEN_HEIGHT) throws IOException {
        //Checking x boundaries for players
        if((this.get_x()>(SCREEN_WIDTH/2+SCREEN_WIDTH/4)-10)){
            this.set_x(this.get_x()-this.getxVel());
        }
        if(this.get_x()<(SCREEN_WIDTH/4)) {
            while (this.get_x() < (SCREEN_WIDTH / 4)){
                this.set_x(this.get_x() + this.getxVel());
            }
        }
        //Checking y boundaries for players
        if(this.get_y()>(SCREEN_HEIGHT-10)){
            this.set_y(this.get_y()-this.getxVel());
        }
        if(this.get_y()<0){
            this.set_y(this.get_y()+this.getxVel());
        }
        for (int i = 0; i < kevin.size(); i++) {
            kevin.get(i).update(SCREEN_WIDTH,SCREEN_HEIGHT);
        }
    }
    public boolean contains(int x, int y) {
        return (x >= _x) && (x <= _x + _width) && (y >= _y) && (y <= _y + _height);
    }

    public int getxVel() {
        return xVel;
    }

    public void set_x(int _x) {
        this._x = _x;
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
    public void createBullet(Projectile projectile) {
        kevin.add(projectile);
    }

    @Override
    public void removeBullet(Projectile projectile) {
        kevin.remove(projectile);
    }

    public void setTarget(Enemy target){
        this.target = target;
    }

    public Enemy getTarget() {
        return target;
    }

    public void move(String direction){
        if(direction.equals("up")){
//            if(_y-yVel > )
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
    public void livesLoss(){
        lives --;
    }
    public void kill(){
        dead = true;
        lives-=5;
    }
    public void restart(){
        lives = 3;
        dead = false;
    }
    public void hurt(){
        lives--;
    }
    public void shoot(String type) throws IOException {
        Projectile newBullet;
        switch (type) {
            case "super":
//                barrage of bullets? some kind of charged shot
                System.out.println("pow");
            case "burst":
                if(burstBullets > 0){
                    newBullet = new Burst(this._x + (this.get_width() / 2) - 10, this._y + (this.get_height() / 2), 15, 15, 0, -5, this);
                    burstBullets--;
                }
                else
                    newBullet = new Standard(this._x + (this.get_width() / 2) - 10, this._y + (this.get_height() / 2), 10, 10, 0, -5, this);
                break;
            case "bounce":
                newBullet = new Bounce(this._x + (this.get_width() / 2) - 10, this._y + (this.get_height() / 2), 10, 10, 0, -5, this);
                break;
            case "Tracking":
                newBullet = new Tracking(this._x + (this.get_width() / 2) - 10, this._y + (this.get_height() / 2), 10, 10, 0, -5, this);
                break;
            default:
                newBullet = new Standard(this._x + (this.get_width() / 2) - 10, this._y + (this.get_height() / 2), 10, 10, 0, -5, this);
                break;
        }
        newBullet.setParent(this);
        kevin.add(newBullet);
    }

    public void draw(Graphics pen) throws IOException {
        pen.drawImage(readImg,_x-26,_y-25,_width+50,_height+50 ,null);
        pen.fillRect(_x,_y,_width,_height);
        pen.setColor(Color.black);
        pen.drawRect(_x,_y,_width,_height);
        pen.drawRect(_x+1,_y+1,_width-2,_height-2); // Player's outline
        pen.setColor(Color.BLACK);
        // Drawing score
        pen.drawString("Score",200,500);
        pen.drawString(this.score+"",200,550);

        // Drawing lives left
        for (int i = 0; i < lives; i++) {
            pen.drawImage(readImg,120+(30*i),700,30,30,null);
        }

        // Drawing burst bullets left
        pen.drawRect(1450,300,300,100);
        int temp = burstBullets;
        while (temp!=0){
            if(temp<6) {
                drawSquare(pen, 1390 + (60 * temp), 300);
//                pen.drawRect(1390+(60*temp),300,60,50);
            }
            else{
                drawSquare(pen,1090+(60*temp),350);
//                pen.drawRect(1090+(60*temp),350,60,50);
            }
            temp--;
        }
        for (int i = 5; i > 0; i--) {
            pen.drawRect(1390+(60*i),300,60,50);
            pen.drawRect(1390+(60*i),350,60,50);
        }

        // Drawing slowdown bar
        pen.setColor(Color.GREEN);
        pen.fillRect(201,400,49,-(this.getSlowDownLeft()*2)); // Drawing slowdown bar
        pen.setColor(Color.BLACK);
        pen.drawRect(200,200,50,200); // Draw outline for slowdown bar




        // Drawing bullets
        for (int i = 0; i < kevin.size(); i++) {
            kevin.get(i).move();
            if(kevin.get(i) instanceof Burst) {
                kevin.get(i).draw(pen, Color.GREEN);
            }
            else if(kevin.get(i) instanceof Standard){
                kevin.get(i).draw(pen, Color.YELLOW);
            }
        }
    }


    public void incrementScore(int score){
        this.score+=score;
    }
    public void decrementSlowMeter(){
        slowDownLeft--;
    }
    public void incrementSlowMeter(){
        slowDownLeft++;
    }

    public int getSlowDownLeft() {
        return slowDownLeft;
    }

    public int getScore() {
        return score;
    }
    public void drawSquare(Graphics pen,int x,int y){
        pen.setColor(Color.GREEN);
        pen.fillRect(x,y,60,50);
        pen.setColor(Color.BLACK);
//        pen.drawRect(x,y,60,50);
    }

    @Override
    public String toString() {
        return "Player[x="+_x+",y="+_y+",width="+_width+",height="+_height+"]";
    }

    public int get_lives() {
        return lives;
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
    public int getKevinSize(){
        return kevin.size();
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

    public void set_y(int i) {
        _y = i;
    }
}