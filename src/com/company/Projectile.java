package com.company;
import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Projectile implements Entity{
     private int width,height;
     private Entity parent;
     private int x,y;
     private int velX, velY;
     protected int distTraveled;
//     private String playerImg = "D:/Anderson/Junior Year/Java/w7/shot.png";
//     private BufferedImage readImg =  ImageIO.read(new File(playerImg));

     public Projectile(int x, int y, int width, int height,int xVel, int yVel, Entity parent) {
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
     }

     public void move() {
         x+=velX;
         y+=velY;
//         if(mode.equals("left")){
//             x+=velX;
//         }
//         else if(mode.equals("right")){
//             x-=velX;
//         }
//         else if(mode.equals("up")){
//             y-=velY;
//         }
//         else if(mode.equals("down")){
//             y+=velY;
//         }
//         else if(parent instanceof Player && mode.equals("target")){
//             x -= (parent.get_x()-target.get_x())/30;
//             y -= (parent.get_y()-target.get_y())/30;
//         }
     }
//     public void target(Enemy target){
//         this.target = target;
//     }
//     public void target(Player target){
//
//     }
     public void draw(Graphics pen, Color color) throws IOException {
         pen.setColor(color);
         pen.fillRect(x,y,width,height);
         distTraveled+=Math.abs(velY);
         if(this instanceof Burst && distTraveled > 300 && !(((Burst) this).hasBurst)){
             System.out.println(this.x+" "+this.y);
             ((Burst) this).burst(this.x,this.y);
         }
//         pen.drawImage(readImg,x,y,width,height+3,null);

     }
     @Override
     public String toString() {
         return "Projectile[x=" + x + ",y=" + y + ",width=" + width + ",height=" + height + "]";
     }
     public int get_height() {
         return height;
     }
     public int get_width() {
         return width;
     }
     public int get_x() {
         return x;
     }
    public int get_y() {
         return y;
     }
 }
