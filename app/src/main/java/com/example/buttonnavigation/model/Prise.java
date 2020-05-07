package com.example.buttonnavigation.model;

public class Prise {

    int num_p;
    int num_prise;

    String medicament;
    String heure ;
    String qt;

    String maladie;
    String date_debut_prog;
    String duree_prog;
    String date_deb_cons;
    String date_prise;
    String duree_med;
    String description;


    public Prise(int num_p,int num_prise,String medicament,String date_prise, String heure,String qt) {
        this.num_p = num_p;
        this.num_prise = num_prise;
        this.medicament = medicament;
        this.date_prise = date_prise;
        this.heure = heure;
        this.qt = qt;

    }

    public Prise(String medicament,String date_prise,String heure,String qt) {
        this.medicament = medicament;
        this.date_prise=date_prise;
        this.heure = heure;
        this.qt = qt;
    }

    public Prise(int num_p,int num_prise,String maladie,String date_debut_prog,String duree_prog,
                 String date_deb_cons,String date_prise,String duree_med,String description){
        this.num_p = num_p;
        this.num_prise = num_prise;
        this.maladie = maladie;
        this.date_debut_prog = date_debut_prog;
        this.duree_prog = duree_prog;
        this.date_deb_cons = date_deb_cons;
        this.date_prise = date_prise;
        this.duree_med = duree_med;
        this.description = description;
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

    public String getDate_prise() {
        return date_prise;
    }

    public void setDate_prise(String date_prise) {
        this.date_prise = date_prise;
    }

    public String getMaladie() {
        return maladie;
    }

    public void setMaladie(String maladie) {
        this.maladie = maladie;
    }

    public String getDate_debut_prog() {
        return date_debut_prog;
    }

    public void setDate_debut_prog(String date_debut_prog) {
        this.date_debut_prog = date_debut_prog;
    }

    public String getDuree_prog() {
        return duree_prog;
    }

    public void setDuree_prog(String duree_prog) {
        this.duree_prog = duree_prog;
    }

    public String getDate_deb_cons() {
        return date_deb_cons;
    }

    public void setDate_deb_cons(String date_deb_cons) {
        this.date_deb_cons = date_deb_cons;
    }

    public String getDuree_med() {
        return duree_med;
    }

    public void setDuree_med(String duree_med) {
        this.duree_med = duree_med;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getNum_p() {
        return num_p;
    }

    public void setNum_p(int num_p) {
        this.num_p = num_p;
    }

    public int getNum_prise() {
        return num_prise;
    }

    public void setNum_prise(int num_prise) {
        this.num_prise = num_prise;
    }
}
