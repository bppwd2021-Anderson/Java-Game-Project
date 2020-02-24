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
     private int _x;
     private int _y;
     private int velX = 20;
     private int velY = 20;
     private String _direction;
     private String playerImg = "D:/Anderson/Junior Year/Java/w7/shot.png";
     private BufferedImage readImg =  ImageIO.read(new File(playerImg));
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

     public boolean contains(int x, int y) {
         return (x >= _x) && (x <= _x + _width) && (y >= _y) && (y <= _y + _height);
     }

//     public Player intersection(Enemy rect) throws IOException {
//         Player newRect;
//         boolean firstEncounter = true;
//         boolean widthCheck = true;
//         int coordx = 0;
//         int coordy = 0;
//         int counter = 0;
//         int height = 0;
//         int width = 0;
//         for (int x = _x; x < _x + _width; x++) {
//             widthCheck = true;
//             for (int y = _y; y < _y + _width; y++) {
//                 if (rect.contains(x, y)) {
//                     counter++;
//                     if (widthCheck) {
//                         widthCheck = false;
//                         width++;
//                     }
//                     if (firstEncounter) {
//                         coordx = x;
//                         coordy = y;
//                         firstEncounter = false;
//                     }
//                 }
//             }
//         }
//         if (width != 0) height = counter / width;
//         return newRect = new Player(coordx, coordy, width, height);
//     }

     public void move() {
         if(_direction.equals("left")){
             _x+=velX;
         }
         if(_direction.equals("right")){
             _x-=velX;
         }
         if(_direction.equals("up")){
             _y-=velY;
         }
         if(_direction.equals("down")){
             _y+=velY;
         }
     }
     public void draw(Graphics pen){
         pen.setColor(Color.yellow);
         pen.drawImage(readImg,_x,_y,_width,_height+3,null);

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
