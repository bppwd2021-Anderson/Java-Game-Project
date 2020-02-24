package com.company;

import java.awt.*;
import java.util.ArrayList;

public class Enemy extends Rectangle {
    private int _width;
    private int _height;
    private int _x;
    private int _y;
    private int velX = 2;
    private int velY = 2;
    private boolean left = (Math.random() * 2 + 1 % 2 == 0),right = (Math.random() * 2 + 1 % 2 == 0),up = (Math.random() * 2 + 1 % 2 == 0),down = (Math.random() * 2 + 1 % 2 == 0);
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
    public void move(){
        checkBoundaries();
        if(right)_x += velX;
        else _x-= velX;
        if(down)_y += velY;
        else _y-= velY;
    }
    public Enemy intersection(Enemy rect) {
        boolean firstEncounter = true;
        boolean widthCheck;
        int coordx = 0;
        int coordy = 0;
        int counter = 0;
        int height = 0;
        int width = 0;
        for (int x = _x; x < _x+_width; x++) {
            widthCheck = true;
            for (int y = _y; y < _y+_width; y++){
                if(rect.contains(x,y)){
                    counter++;
                    if(widthCheck){
                        widthCheck = false;
                        width++;
                    }
                    if(firstEncounter){
                        coordx = x;
                        coordy = y;
                        firstEncounter = false;
                    }
                }
            }
        }
        if(width != 0)height = counter/width;
        return new Enemy(coordx, coordy, width, height);
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
    //    _x+(int)(Math.random()*100+1) >= 1149
//    _y+randomY >= 749
    public boolean contains(int x, int y) {
        return (x >= _x) && (x <= _x + _width) && (y >= _y) && (y <= _y + _height);
    }
    public void speedUp(){
        velX+=1;
        velY+=1;
    }
    public void draw(Graphics pen, int listCounter, ArrayList<Enemy> enemyList){
        pen.fillRect(enemyList.get(listCounter).get_x(), enemyList.get(listCounter).get_y(), enemyList.get(listCounter).get_width(), enemyList.get(listCounter).get_height());
        pen.setColor(Color.RED);
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
}