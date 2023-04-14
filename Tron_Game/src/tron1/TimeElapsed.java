/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tron1;

/**
 *
 * @author argjira
 */
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Timer;

public class TimeElapsed {

    public long startTime;
    public long elapsedTime;
    public double elapsedTimeInSeconds;
    
    Timer timer = new Timer(100, new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            elapsedTime = System.currentTimeMillis() - startTime;
            elapsedTimeInSeconds = (double) elapsedTime / 1000;
        }
    });

    public TimeElapsed() {
    }
    
    /**
     * This method is used to start the timer
     */
    public void start() {
        this.timer.start();
    }
    /**
     * This method is used to stop the timer
     */
    public void stop() {
        this.timer.stop();
    }

    public double getTime() {
        return elapsedTimeInSeconds;
    }

}
