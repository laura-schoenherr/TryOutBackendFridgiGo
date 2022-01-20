package com.example.eigeneapi;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity //This tells Hibernate to make a table out of this class
public class Recipe {

    private String recipeTitle;
    private Integer RezeptID;

    public Recipe() {
    }

    @Id
    public Integer getRezeptID() {
        return RezeptID;
    }

    public void setRezeptID(Integer RezeptID) {
        this.RezeptID = RezeptID;
    }



    public String getRecipeTitle() {
        return recipeTitle;
    }

    public void setRecipeTitle(String recipeTitle) {
        this.recipeTitle = recipeTitle;
    }

    public void setId(long id) {
        this.id = id;
    }




}



    private Integer RezeptID;
    private String Name;
    private int ;
    private int Schwierigkeit;
    private String Merkmale;
    private String WissName;
    private String Picture;
    private String EnName;





    public void setName(String name) {
        Name = name;
    }

    public int getStandort() {
        return Standort;
    }

    public void setStandort(int standort) {
        Standort = standort;
    }

    public int getSchwierigkeit() {
        return Schwierigkeit;
    }

    public void setSchwierigkeit(int schwierigkeit) {
        Schwierigkeit = schwierigkeit;
    }

    public String getMerkmale() {
        return Merkmale;
    }

    public void setMerkmale(String merkmale) {
        Merkmale = merkmale;
    }

    public String getWissName() {
        return WissName;
    }

    public void setWissName(String wissName) {
        WissName = wissName;
    }

    public String getPicture() {
        return Picture;
    }

    public void setPicture(String picture) {
        Picture = picture;
    }

    public String getEnName() {
        return EnName;
    }

    public void setEnName(String enName) {
        EnName = enName;
    }
}
