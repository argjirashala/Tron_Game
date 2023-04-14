/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tron1;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;;
import java.util.ArrayList;
import java.io.IOException;
import java.util.logging.Logger;
import javax.swing.AbstractAction;
import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.KeyStroke;
import javax.swing.Timer;
import javax.swing.JFrame;
import javax.swing.JOptionPane;


/**
 *
 * @author argjira
*/
public class GameEngine extends JPanel {

    private final int FPS = 240;

    public boolean paused = false;
    private Motor1 m1;
    private Motor2 m2;
    private Image background;
    public int levelNum = 1;
    private Level level;
    private Timer newFrameTimer;
    public TimeElapsed stopwatch;
    private final int motorSpeedX = 1;
    private final int motorSpeedY = 1;
    private Image traceForMotor1;
    private Image traceForMotor2;
    private JFrame frame;
    public int scoresMotor1 = 0;
    public int scoresMotor2 = 0;
    private Database db;
    
    
    public GameEngine() {
        super();
        stopwatch = new TimeElapsed();
        db = new Database();
        background = new ImageIcon("data/images/ground1.png").getImage();
        this.getInputMap().put(KeyStroke.getKeyStroke("LEFT"), "pressed left");
        this.getActionMap().put("pressed left", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                m1.setVelx(-motorSpeedX);
                m1.setVely(0);
            }
        });
        this.getInputMap().put(KeyStroke.getKeyStroke("RIGHT"), "pressed right");
        this.getActionMap().put("pressed right", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                m1.setVelx(motorSpeedX);
                m1.setVely(0);
            }
        });
        this.getInputMap().put(KeyStroke.getKeyStroke("DOWN"), "pressed down");
        this.getActionMap().put("pressed down", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent ae) {
              m1.setVelx(0);
              m1.setVely(motorSpeedY);
            }
        });
        this.getInputMap().put(KeyStroke.getKeyStroke("UP"), "pressed up");
        this.getActionMap().put("pressed up", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent ae) {
              m1.setVelx(0);
              m1.setVely(-motorSpeedY);
            }
        });
        this.getInputMap().put(KeyStroke.getKeyStroke("ESCAPE"), "escape");// what we have to do when the escape button is pressed
        this.getActionMap().put("escape", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                paused = !paused;
            }
        });
  
   
        this.getInputMap().put(KeyStroke.getKeyStroke("A"), "pressed A");// inputmap is how we handle the keyboard
        // the string connects the action map with the input map
        this.getActionMap().put("pressed A", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                m2.setVelx(-motorSpeedX);
                m2.setVely(0);
            }
        });
        this.getInputMap().put(KeyStroke.getKeyStroke("D"), "pressed D");
        this.getActionMap().put("pressed D", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                m2.setVelx(motorSpeedX);
                m2.setVely(0);
            }
        });
        this.getInputMap().put(KeyStroke.getKeyStroke("S"), "pressed S");
        this.getActionMap().put("pressed S", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent ae) {
              m2.setVelx(0);
              m2.setVely(motorSpeedY);
            }
        });
        this.getInputMap().put(KeyStroke.getKeyStroke("W"), "pressed W");
        this.getActionMap().put("pressed W", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent ae) {
              m2.setVelx(0);
              m2.setVely(-motorSpeedY);
            }
        });
        restart();
        newFrameTimer = new Timer(1000 / FPS, new NewFrameListener());
        newFrameTimer.start();
        }
    
      /**
       * This method is getter for the HighScores in the database
       * @return 
       */
      public ArrayList<HighScore> getHighScores() {
        return db.getHighScores();
      }
 
    public String player1Name;
    public String player2Name;
    
    /**
     * This method restarts the game,it is called whenever a new game begins
    */
    public void restart() {
        Image motor1Image = new ImageIcon("data/images/motor1.jpg").getImage();
        Image motor2Image = new ImageIcon("data/images/motor2.jpg").getImage();
        
        try {
            if(levelNum==1)
            { 
                stopwatch.start();
                stopwatch.startTime = System.currentTimeMillis();
                scoresMotor1 = 0;
                scoresMotor1 = 0;
                player1Name = JOptionPane.showInputDialog("Player1 name: ","Player1");
                player2Name = JOptionPane.showInputDialog("Player2 name: ","Player2");
                String traceColor1 = JOptionPane.showInputDialog("Player1 trace color:","color1");
                String traceColor2 = JOptionPane.showInputDialog("Player2 trace color: ","color2");
                
              
            switch(traceColor1) {
                case "red":
                  traceForMotor1=new ImageIcon("data/images/brick01.png").getImage();
                  break;
                case "blue":
                  traceForMotor1=new ImageIcon("data/images/brick01_1.png").getImage();
                  break;
               case "yellow":
                  traceForMotor1=new ImageIcon("data/images/brick00.png").getImage();
                  break;
                case "green":
                  traceForMotor1=new ImageIcon("data/images/brick02.png").getImage();
                  break;
                default:
                   traceForMotor1=new ImageIcon("data/images/brick01_1.png").getImage();
               }
            
               switch(traceColor2) {
                case "red":
                  traceForMotor2=new ImageIcon("data/images/brick01.png").getImage();
                  break;
                case "blue":
                  traceForMotor2=new ImageIcon("data/images/brick01_1.png").getImage();
                  break;
                case "green":
                  traceForMotor2=new ImageIcon("data/images/brick02.png").getImage();
                  break;
                case "yellow":
                  traceForMotor2=new ImageIcon("data/images/brick00.png").getImage();
                  break;
                default:
                   traceForMotor2=new ImageIcon("data/images/brick01.png").getImage();
              }
                
          
            }
            if(levelNum==11){
                 stopwatch.stop();
                 JOptionPane.showMessageDialog(frame,"Congratulations all levels are done\n"+ player1Name +" scored: " + scoresMotor1 + " points "+" \n" + player2Name +" scored "+ scoresMotor2 +" points " + "\n" + "The elasped time is: "+ stopwatch.getTime() + "s");
                 paused = true;
                 db.insertHighScore(player1Name, scoresMotor1);
                 db.insertHighScore(player2Name, scoresMotor2);
            }
            if(levelNum <= 10){
                level = new Level("data/levels/level0" + levelNum + ".txt");
            }
        }catch (IOException ex) {
            Logger.getLogger(GameEngine.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        
        m1 = new Motor1(200,20,20,20, motor1Image,motorSpeedX,motorSpeedY);// here the number of level stand for the speed of the motorcycle
        m1.showTrace = traceForMotor1;
        
        m2 = new Motor2(300,200,20,20, motor2Image,motorSpeedX,motorSpeedY);
        m2.showTrace = traceForMotor2;
    }
    
    /**
     * We override the paintComponent method to be able to draw the component of the game in the frame
     * @param g 
     */
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(background, 0, 0, 800, 600, null);
        level.drawBrick(g);
        m1.draw(g);
        m2.draw(g);
        for(TraceTrack tr : m1.trace){
            tr.draw(g);
        }
        for(TraceTrack tr : m2.trace){
           tr.draw(g);
        }
    }


  class NewFrameListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent ae) {
            if (!paused) {
                if (m1.traceCollsion(m2)) {
                    scoresMotor1++;
                    JOptionPane.showMessageDialog(frame,"Level " + levelNum + " is completed, "+ player1Name +" won! "); 
                    levelNum++;
                    restart();
                }
                if(m2.traceCollsion(m1)){
                    scoresMotor2++;
                    JOptionPane.showMessageDialog(frame,"Level " + levelNum + " is completed, "+ player2Name +" won! "); 
                    levelNum++; 
                    restart();
                }
                for(Brick brick_piece:level.bricks){
                    if(m1.isCollision(brick_piece)){
                        scoresMotor2++;
                        JOptionPane.showMessageDialog(frame,"Level " + levelNum + " is completed, "+ player2Name +" won! "); 
                        levelNum++;
                        restart();
                        break;
                    }
                    if(m2.isCollision(brick_piece)){
                        scoresMotor1++;
                        JOptionPane.showMessageDialog(frame,"Level " + levelNum + " is completed, "+ player1Name +" won! "); 
                        levelNum++;
                        restart();
                        break;
                    }
                }
                m1.moveX();
                m1.moveY();
                m2.moveX();
                m2.moveY();
            }
            repaint();
        }

    }
}
