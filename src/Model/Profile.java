package Model;

import java.util.HashMap;

public class Profile {
    String nom, prenom, pseudo;
    int cycle,annee;
    HashMap<String, Integer> languages = new HashMap<String, Integer>();


    public Profile(String nom,  String prenom,String pseudo) {
        this.nom = nom;
        this.pseudo = pseudo;
        this.prenom = prenom;
    }

    public Profile(String nom, int annee, int cycle, String pseudo, String prenom) {
        this.nom = nom;
        this.annee = annee;
        this.cycle = cycle;
        this.pseudo = pseudo;
        this.prenom = prenom;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getPseudo() {
        return pseudo;
    }

    public void setPseudo(String pseudo) {
        this.pseudo = pseudo;
    }

    public int getCycle() {
        return cycle;
    }

    public void setCycle(int cycle) {
        this.cycle = cycle;
    }

    public HashMap<String, Integer> getLanguages() {
        return languages;
    }

    public void setLanguages(HashMap<String, Integer> languages) {
        this.languages = languages;
    }

    public int getAnnee() {
        return annee;
    }

    public void setAnnee(int annee) {
        this.annee = annee;
    }

    @Override
    public String toString() {
        return "Profile{" +
                "nom='" + nom + '\'' +
                ", prenom='" + prenom + '\'' +
                ", pseudo='" + pseudo + '\'' +
                '}';
    }
}