/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tron1;

import java.awt.Graphics;
import java.awt.Image;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import javax.swing.ImageIcon;

/**
 *
 * @author argjira
 */
public class Level {

    private final int BRICK_WIDTH = 20;
    private final int BRICK_HEIGHT = 20;
    public ArrayList<Brick> bricks;
   
    public Level(String levelPath) throws IOException {
        loadLevel(levelPath);
    }
    
    /**
     * This method is to load the file because each level is represented by a file
     * @param levelPath this is the path->filename
     * @throws FileNotFoundException
     * @throws IOException 
     */
    public void loadLevel(String levelPath) throws FileNotFoundException, IOException {
        BufferedReader br = new BufferedReader(new FileReader(levelPath));
        bricks = new ArrayList<>();
        int y = 0;
        String line;
        while ((line = br.readLine()) != null) {
            int x = 0;
            for (char blockType : line.toCharArray()) {
                if (Character.isDigit(blockType)) {
                    Image image = new ImageIcon("data/images/brick0" + blockType + ".png").getImage();
                    bricks.add(new Brick(x * BRICK_WIDTH, y * BRICK_HEIGHT, BRICK_WIDTH, BRICK_HEIGHT, image));
                }
                x++;
            }
            y++;
        }
    }
    
    public boolean gameOver = false;
    
    /**
     * This method returns weather a player collides with a brick
     * @param p is a player
     * @return 
     */
    public boolean collides(Player p) {
        Brick collidedWith = null;
        for (Brick brick : bricks) {
            if (p.isCollision(brick)) {
                collidedWith = brick;
                break;
            }
        }
        if (collidedWith != null) {
            gameOver = true;
            return gameOver;
        } else {
            gameOver = false;
            return gameOver;
        }
    }
  
    /**
     * This method is used to draw the bricks in the game
     * @param g 
     */
    public void drawBrick(Graphics g) {
        for (Brick brick : bricks) {
            brick.draw(g);
        }
    }

}
