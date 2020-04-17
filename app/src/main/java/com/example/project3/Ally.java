package com.example.project3;

public class Ally extends Character {
    int Job;
    int Str;
    int Wis;
    int Con;
    int Level;

    public Ally(String name, int job, int str, int wis, int con){
        super(name);
        Job = job;
        Str = str;
        Wis = wis;
        Con = con;
    }

    void setJob(int job){
        Job = job;
    }
    int getJob(){
        return Job;
    }
    void setStr(int str){
        Str = str;
    }
    int getStr(){
        return Str;
    }
    void setWis(int wis){
        Wis = wis;
    }
    int getWis(){
        return Wis;
    }
    void setCon(int con){
        Con = con;
    }
    int getCon(){
        return Con;
    }
    void setLevel(int level){Level = level;}
    int getLevel(){return Level;}
}
