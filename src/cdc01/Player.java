/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cdc01;

/**
 *
 * @author nele
 */
public class Player {
    boolean isHuman;
    int points;
    
    Player() {
        this.isHuman=true;
        this.points=0;
    }
    
    Player(boolean isHuman,int points) {
        this.isHuman=isHuman;
        this.points=points;
    }
    
    public int getPoints() {
        return this.points;
    }
    
    public void addPoints(int points) {
        this.points=this.points+points;
    }
    
}
