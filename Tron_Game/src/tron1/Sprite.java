/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tron1;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.Color;

/**
 *
 * @author argjira
 */
public class Sprite {
   
    protected int x;
    protected int y;
    protected int width;
    protected int height;
    public Color color;
    protected Image image;
    
  
  public Sprite(int x, int y, int width, int height, Image image) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.image = image;
    }  
    
  /**
   * This method is used to draw the sprites
   * @param g 
   */
    public void draw(Graphics g) {
        g.drawImage(image, x, y, width, height, null);
    }
    
    /**
     * This method is used to detect the collision between two rectangles figure
     * @param b which is a brick
     * @return weather they are intersected
     */
    public boolean isCollision(Brick b){
        Rectangle rect = new Rectangle(x, y, width, height);
        Rectangle otherRect = new Rectangle(b.getX(),b.getY(),b.getWidth(),b.getHeight());        
        return rect.intersects(otherRect);
    }
    
    public int getX() {
        return x;
    }
    
    public int getY() {
        return y;
    }
    
    public int getWidth() {
        return width;
    }
    
    public int getHeight() {
        return height;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public void setHeight(int height) {
        this.height = height;
    }
}
