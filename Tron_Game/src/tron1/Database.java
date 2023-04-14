/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tron1;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
/**
 *
 * @author argjira
 */
public class Database {
    private final String tableName = "highscores";
    private final Connection conn;
    private final HashMap<String, Integer> highscores;
    
    public Database(){
        Connection connection = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/tron?"
                    + "serverTimezone=UTC&user=student&password=asd123");
        } catch (Exception ex) {
            System.out.println("Exception occured");
        }
        this.conn = connection;
        highscores = new HashMap<>();
        loadHighScores();
    }
    
    /**
     * This method inserts every new HighScore 
     * @param name of the player
     * @param newScore of the player
     * @return merged HighScores
     */
    public boolean insertHighScore(String name, int newScore){
        return mergeHighScores(name, newScore, newScore > 0);
    }
    
    private void loadHighScores(){
        try (Statement stmt = conn.createStatement()) {
            ResultSet rs = stmt.executeQuery("SELECT * FROM " + tableName + " ORDER BY " + "Score DESC");
            while (rs.next()){
                int id = rs.getInt("ID");
                String name = rs.getString("Name");
                int score = rs.getInt("Score");
                mergeHighScores(name, score, false);
            }
        } catch (Exception e){ System.out.println("Error: " + e.getMessage());}
    }
    
    public ArrayList<HighScore> getHighScores(){
        HashMap<String, Integer> sorted = sortByValue(highscores);
        ArrayList<HighScore> scores = new ArrayList<>();
        int i = 0;
        for (String name : sorted.keySet()){
            if (i == 10) break;
            HighScore h = new HighScore(name, highscores.get(name));
            scores.add(h);
            i++;
        }
        return scores;
    }
    /**
     * This method sorts the HashMap of scores by values
     * @param s the HashMap
     * @return the sorted HashMap
     */
    public static HashMap<String, Integer> sortByValue(HashMap<String, Integer> s){
        List<Map.Entry<String, Integer> > list =
               new LinkedList<Map.Entry<String, Integer> >(s.entrySet());
 
        Collections.sort(list, new Comparator<Map.Entry<String, Integer> >() {
            public int compare(Map.Entry<String, Integer> t1,Map.Entry<String, Integer> t2){
                return (t1.getValue()).compareTo(t2.getValue());
            }
        });
         
        HashMap<String, Integer> another = new LinkedHashMap<String, Integer>();
        Collections.reverse(list);
        for (Map.Entry<String, Integer> temp : list) {
            another.put(temp.getKey(),temp.getValue());
        }
        return another;
    }
    /**
     * This method merges the current HighScore with previous HighScores which are in the database table
     * @param name of the player
     * @param score of the player
     * @param isStored true/false
     * @return weather the table is updated or not
     */
    private boolean mergeHighScores(String name, int score, boolean isStored){
        boolean update = true;
        if (highscores.containsKey(name)){
            int prevScore = highscores.get(name);
            update = ((score > prevScore && score != 0) || prevScore == 0);
        }
        if (update){
            highscores.remove(name);
            highscores.put(name, score);
            if (isStored) return insertToDatabase(name, score) > 0;
            return true;
        }
        return false;
    }
    
    /**
     * This method stores in the database the players scores and name
     * @param name of the player
     * @param score of the player
     * @return 0 it was successful
     */
    private int insertToDatabase(String name, int score){
        try (Statement stmt = conn.createStatement()){
            String s = "INSERT INTO " + tableName + 
                    " (Name, Score) " + 
                    "VALUES('" + name + "'," + score + 
                    ") ON DUPLICATE KEY UPDATE Score=" + score;
            return stmt.executeUpdate(s);
        } catch (Exception e){
            System.out.println("Exception occured");
        }
        return 0;
    }
}

