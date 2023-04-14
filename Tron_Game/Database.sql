/* 
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Other/SQLTemplate.sql to edit this template
 */
/**
 * Author:  argjira
 * Created: Dec 8, 2022
 */

DROP TABLE IF EXISTS Tron.Highscores;

CREATE DATABASE IF NOT EXISTS Tron;

CREATE TABLE IF NOT EXISTS Highscores{
     ID INT NOT NULL PRIMARY KEY AUTO_INCREMENT;
     Name VARCHAR(100) NOT NULL;
     Score INT NOT NULL
};

USE Tron;

