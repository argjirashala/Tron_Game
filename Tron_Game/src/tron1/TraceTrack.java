/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package tron1;

import java.awt.Image;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
/**
 *
 * @author argjira
 */
public class TraceTrack extends Sprite{
       
     public TraceTrack(int x, int y, int width, int height, Image image,Color color){
        super(x, y, width, height, image);
        this.color = color;
    }
   /**
    * This method return the collision between two players(considered as rectangles)
    * @param other representing the other player
    * @return 
    */
    public boolean collisonPlayer(Player other){
        Rectangle rect = new Rectangle(x, y, width, height);
        Rectangle otherRect = new Rectangle(other.x, other.y, other.width, other.height);   
        return rect.intersects(otherRect);
    }
    
    public boolean wasThere = false;
    
    /**
     * This method is used to draw the trail
     * @param g 
     */
    public void showTrail(Graphics g){
        if(wasThere){
            g.drawImage(image, x, y,this.color,null);
        }
    }
}
