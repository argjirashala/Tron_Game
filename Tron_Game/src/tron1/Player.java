/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tron1;

import java.awt.Image;
import java.awt.Graphics;
import java.util.ArrayList;

/**
 *
 * @author argjira
 */
public class Player extends Sprite{
    private int velx;
    private int vely;
    public Image showTrace;
    public ArrayList<TraceTrack> trace = new ArrayList<TraceTrack>();
    
    public Player(int x, int y, int width, int height, Image image,int vx,int vy){
        super(x, y, width, height, image);
        velx=vx;
        vely=vy;
        
    }
    /**
     * This method is to move the in x coordinates of the motor
     * @return 
     */
    public boolean moveX() {
        x += velx;
        if (x + width >= 800 || x <= 0) {
            return true;
        }
        TraceTrack tr = new TraceTrack(this.getX(),this.getY(),this.getWidth(),this.getHeight(),this.showTrace,this.color);
        this.trace.add(tr);
        return false;
    }
    /**
     * This method is to move the in y coordinates
     * @return 
     */
    public boolean moveY() {
        y += vely;
        if (y <= 0 || y > 600) {
            return true;
        }
        TraceTrack newtrace=new TraceTrack(x,this.getY(),this.getWidth(),this.getHeight(),this.image,this.color);
        this.trace.add(newtrace);
        return false;
    }
    
    public int getVelx() {
        return velx;
    }
    
    public int getVely() {
        return velx;
    }

    public void setVelx(int velx) {
        this.velx = velx;
    }

    public void setVely(int vely) {
        this.vely = vely;
    }
    
    /**
     * This method is used to draw the traces(where the motor has been)
     * @param g 
     */
    public void drawTrace(Graphics g){
        for (TraceTrack tr : this.trace){
            tr.wasThere = true;
            tr.draw(g);
        }
    }
    
    /**
     * This method is used to detect collision between a player and the other player's trace
     * @param other representing the other player
     * @return true or false
     */
    public boolean traceCollsion(Player other){
       for (TraceTrack tr : this.trace){
            if(tr.collisonPlayer(other)){
                return true;
            }
        }
        return false;
    }
}
