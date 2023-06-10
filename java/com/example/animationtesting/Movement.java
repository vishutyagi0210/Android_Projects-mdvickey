package com.example.animationtesting;

import android.util.Log;

public class Movement {
    private boolean canMove;
    private String direction;
    private int value;
    public int getValue() {
        return value;
    }
    public void setValue(int value) {
        this.value = value;

    }
    public Movement() { }
    public void setCanMove(boolean canMove) {
        this.canMove = canMove;
    }
    public boolean getCanMove(){return canMove;}
    public String getDirection() {
        return direction;
    }
    public void setDirection(String direction) {
        this.direction = direction;
    }
    public Movement findMovement() {
        Logic logic1 = new Logic();
        Movement l1 = logic1.findNeighbourElement(value);
        return l1;
    }
    public void change99position(){
        Logic l2 =new Logic();
        l2.change99Index();
    }
    public boolean winner(){
        Logic l3 = new Logic();
        boolean bool = l3.winOrNot();
        return bool;
    }
}
