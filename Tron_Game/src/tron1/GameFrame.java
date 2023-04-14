/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tron1;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.AbstractAction;

/**
 *
 * @author argjira
 */
public class GameFrame{
    
    private JFrame frame;
    private GameEngine gameFrame;

    public GameFrame() {
        frame = new JFrame("Tron Game");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        gameFrame = new GameEngine();
        frame.getContentPane().add(gameFrame);
        JMenuBar menuBar = new JMenuBar();
        frame.setJMenuBar(menuBar);
        JMenu gameMenu = new JMenu("Game");
        menuBar.add(gameMenu);
        JMenuItem restart = new JMenuItem("Restart");
        gameMenu.add(restart);
        
        restart.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                gameFrame.levelNum = 1;
                gameFrame.scoresMotor1 = 0;
                gameFrame.scoresMotor2 = 0;
                gameFrame.restart();
            }
        });
        
        JMenuItem showHighScores = new JMenuItem(new AbstractAction("Highscores") {
            @Override
            public void actionPerformed(ActionEvent e) {
                new ShowHighScores(gameFrame.getHighScores(), frame);
            }
        });
        gameMenu.add(showHighScores);

        JMenuItem pause = new JMenuItem("Pause");
        gameMenu.add(pause);
        pause.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                gameFrame.paused = !gameFrame.paused;
            }
        });
        JMenuItem exit = new JMenuItem("Exit");
        gameMenu.add(exit);
        exit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                System.exit(0);
            }
        });

        
        frame.setPreferredSize(new Dimension(800, 600));
        frame.setResizable(false);
        frame.pack();
        frame.setVisible(true);
    }
   

    
}
