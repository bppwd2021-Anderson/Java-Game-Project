package com.company;
import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Projectile implements Entity{
     private int _width;
     private int _height;

     //    Top Left
     private Entity parent;
     private int _x;
     private int _y;
     private int velX = 20;
     private int velY = 20;
     private Enemy target;
     private String _direction;
//     private String playerImg = "D:/Anderson/Junior Year/Java/w7/shot.png";
//     private BufferedImage readImg =  ImageIO.read(new File(playerImg));

     public Projectile(int x, int y, int width, int height, String direction) throws IOException {
         _width = width;
         _height = height;
         _x = x;
         _y = y;
         _direction = direction;
     }
     public void setParent(Entity parent){
         this.parent = parent;
         if(parent instanceof Player)
             target = ((Player) parent).getTarget();
     }

     public boolean contains(int x, int y) {
         return (x >= _x) && (x <= _x + _width) && (y >= _y) && (y <= _y + _height);
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
         else if(parent instanceof Player && _direction.equals("target")){
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
     public void draw(Graphics pen, Color color){
         pen.setColor(color);
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

    @Override
    public boolean intersects(Entity bullet) {
        return false;
    }


    public int get_y() {
         return _y;
     }
 }
