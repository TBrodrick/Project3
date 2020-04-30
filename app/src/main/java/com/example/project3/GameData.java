package com.example.project3;
import android.os.Bundle;

import com.google.firebase.database.DatabaseReference;

import java.util.ArrayList;
public class GameData {
    ArrayList<ArrayList<Integer>> FloorList = new ArrayList<ArrayList<Integer>>();
    ArrayList<Character> Characters = new ArrayList<Character>();
    int gold;
    private int arrayPos;
    int floodHeight;
    int floodValue;
    int currentFloor;
    private int currentScore;
    private int HighScore;

    public GameData(){
        FloorList = new ArrayList<ArrayList<Integer>>();
        Characters = new ArrayList<Character>();
        gold = 0;
        arrayPos = 0;
        floodHeight = 0;
        floodValue = 0;
        currentFloor = 0;
        currentFloor = 0;
        HighScore = 0;
    }
    public GameData(int[][] FL, Character[] chars, int g, int ap, int fh, int fv, int cf, int score, int hs){
        /*for(int i = 0; i < FL.length; i++){
            ArrayList<Integer> temp = new ArrayList<Integer>();
            for(int a = 0; a < 13; a++){
                temp.add(FL[i][a]);
            }
            FloorList.add(temp);
        }
        //FloorList = FL;
        for(int i = 0; i < chars.length; i++){
            Characters.add(chars[i]);
        }*/
        gold = g;
        arrayPos = ap;
        floodHeight = fh;
        floodValue = fv;
        currentFloor = cf;
        currentFloor = score;
        HighScore = hs;
    }

    public void setFloorList(int[][] fl){
        for(int i = 0; i < fl.length; i++){
        ArrayList<Integer> temp = new ArrayList<Integer>();
        for(int a = 0; a < 13; a++){
            temp.add(fl[i][a]);
        }
        FloorList.add(temp);
    }}
    public void setCharacters(Character[] chars){
        for(int i = 0; i < chars.length; i++){
            Characters.add(chars[i]);
        }
        //Characters = chars;
    }
    public void setGold(int g){gold = g;}
    public void setArrayPos(int ap){arrayPos = ap;}
    public void setCurrentFloor(int cf){currentFloor = cf;}
    public void setFloodHeight(int fh){floodHeight = fh;}
    public void setFloodValue(int fv){floodValue = fv;}
    public void setCurrentScore(int sc){currentScore = sc;}
    public void setHighScore(int hs){HighScore = hs;}

    public int[][] getFloorList(){
        int[][] ret = new int[1000][13];
        for(int i = 0; i < FloorList.size(); i++){
            for(int a = 0; a < FloorList.get(i).size(); i++){
                ret[i][a] = FloorList.get(i).get(a);
            }
        }
        return ret;
        //return FloorList;
    }
    public Character[] getCharacters() {
        Character[] temp = new Character[Characters.size()];
        for(int i = 0; i < Characters.size(); i++){
            temp[i] = Characters.get(i);
        }
        return temp;
        // return Characters;
    }
    public int getGold(){return gold;}
    public int getArrayPos(){return arrayPos;}
    public int getFloodHeight(){return floodHeight;}
    public int getFloodValue(){return floodValue;}
    public int getCurrentFloor(){return currentFloor;}
    public int getCurrentScore(){return currentScore;}
    public int getHighScore(){return HighScore;}
}
