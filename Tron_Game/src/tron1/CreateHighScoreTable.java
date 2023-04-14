/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tron1;

import java.util.ArrayList;
import javax.swing.table.AbstractTableModel;
/**
 *
 * @author argjira
 */

public class CreateHighScoreTable extends AbstractTableModel {
    
    private final ArrayList<HighScore> scores;
    private final String[] column = new String[]{"Name", "Score" };
    
    public CreateHighScoreTable(ArrayList<HighScore> highScores){
        this.scores = highScores;
        System.out.println(this.scores);
    }
    
    @Override
    public int getRowCount() { 
        return scores.size() < 10 ? scores.size() : 10; 
    }

    @Override
    public int getColumnCount() { 
        return 2; 
    }

    @Override
    public Object getValueAt(int r, int c) {
        HighScore hs = scores.get(r);
        if      (c == 0) return hs.name;
        else if (c == 1) return hs.score;
        return (hs.score == 0) ? "0" : hs.score;
    }

    @Override
    public String getColumnName(int i) { return column[i]; }  
}
