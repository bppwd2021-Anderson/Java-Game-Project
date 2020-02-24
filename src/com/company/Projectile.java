package com.company;
import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Projectile extends Rectangle{
     private int _width;
     private int _height;

     //    Top Left
     private Player parent;
     private int _x;
     private int _y;
     private int velX = 20;
     private int velY = 20;
     private Enemy target;
     private String _direction;
//     private String playerImg = "D:/Anderson/Junior Year/Java/w7/shot.png";
//     private BufferedImage readImg =  ImageIO.read(new File(playerImg));
//    Bottom Right
//    _xbr = _x+_width;
//    _ybr = _y+_height;

//    Bottom Left
//    _xbl = _x;d
//    _ybl = _y+_height;

//    Top Right
//    _xtr = _x+_width;
//    _ytr = _y;

     public Projectile(int x, int y, int width, int height, String direction) throws IOException {
         _width = width;
         _height = height;
         _x = x;
         _y = y;
         _direction = direction;
     }
     public void setParent(Player player){
         parent = player;
         target = player.getTarget();
     }

     public boolean contains(int x, int y) {
         return (x >= _x) && (x <= _x + _width) && (y >= _y) && (y <= _y + _height);
     }
    public boolean myIntersection(Enemy rect) {
        if((y)<=(rect.getY()+rect.getHeight()) && (rect.getY()+rect.getHeight()<=y+height) && ((rect.getX()+rect.getWidth())>x) && (rect.getX()<(x+width))){
            return true;
        }
       if(rect.getY()<=(y+height) && (rect.getY()>=y) && ((rect.getX()+rect.getWidth())>x) && (rect.getX()<(x+width))) {
            return true;
        }
       if((rect.getX())<=(x+width) && (rect.getX()>=x) && (rect.getY()<(y+height)) && ((rect.getY()+rect.getHeight())>y)){
            return true;
        }
        if ((((rect.getX()+rect.getWidth()))>=x) && (rect.getX()<=x) && (rect.getY()<(y+height)) && ((rect.getY()+rect.getHeight())>y)){
            return true;
        }
        return false;
    }

     public void move() {
         if(_direction.equals("left")){
             _x+=velX;
         }
         else if(_direction.equals("right")){
             _x-=velX;
         }
         else if(_direction.equals("up")){
             _y-=velY;
         }
         else if(_direction.equals("down")){
             _y+=velY;
         }
         else if(_direction.equals("target")){
             _x -= (parent.get_x()-target.get_x())/30;
             _y -= (parent.get_y()-target.get_y())/30;
         }
     }
//     public void target(Enemy target){
//         this.target = target;
//     }
//     public void target(Player target){
//
//     }
     public void draw(Graphics pen){
         pen.setColor(Color.yellow);
         pen.fillRect(_x,_y,_width,_height);
//         pen.drawImage(readImg,_x,_y,_width,_height+3,null);

     }

     @Override
     public String toString() {
         return "Projectile[x=" + _x + ",y=" + _y + ",width=" + _width + ",height=" + _height + "]";
     }

     public void set_direction(String _direction) {
         this._direction = _direction;
     }

     public String get_direction() {
         return _direction;
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
 }
