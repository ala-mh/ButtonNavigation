package com.example.buttonnavigation.model;

public class Programme {
    private String num_p;
    private String Maladie;
    private String Date_debut;
    private String Duree;

    public Programme(String maladie, String date_debut, String duree) {
        Maladie = maladie;
        Date_debut = date_debut;
        Duree = duree;
    }

    public Programme(String num_p,String maladie,String date_debut,String duree) {
        this.num_p = num_p;
        Maladie = maladie;
        Date_debut = date_debut;
        Duree = duree;
    }

    public String getNum_p() { return num_p; }

    public void setNum_p(String num_p) { this.num_p = num_p; }

    public String getMaladie() {
        return Maladie;
    }

    public void setMaladie(String maladie) {
        Maladie = maladie;
    }

    public String getDate_debut() {
        return Date_debut;
    }

    public void setDate_debut(String date_debut) {
        Date_debut = date_debut;
    }

    public String getDuree() {
        return Duree;
    }

    public void setDuree(String duree) {
        Duree = duree;
    }
}

