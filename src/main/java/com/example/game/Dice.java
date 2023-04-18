package com.example.game;

public class Dice  {
    // it will give a number between 1 to 6 randomly
    public int getrolledDiceValue(){
        return (int)(Math.random()*6+1);
    }
}
