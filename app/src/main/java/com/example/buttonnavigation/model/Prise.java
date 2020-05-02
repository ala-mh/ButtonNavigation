package com.example.buttonnavigation.model;

public class Prise {

    String medicament;
    String heure ;
    String qt;


    public Prise(String medicament,String heure,String qt) {
        this.medicament = medicament;
        this.heure = heure;
        this.qt = qt;
    }

    public String getMedicament() {
        return medicament;
    }

    public void setMedicament(String medicament) {
        this.medicament = medicament;
    }

    public String getHeure() {
        return heure;
    }

    public void setHeure(String heure) {
        this.heure = heure;
    }

    public String getQt() {
        return qt;
    }

    public void setQt(String qt) {
        this.qt = qt;
    }
}
