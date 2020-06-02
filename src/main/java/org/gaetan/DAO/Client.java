package org.gaetan.DAO;

public class Client {
    private int id;
    private String Nom;
    private String Prenom;
    private String Ville;

    public Client(int id) {
        this.id=id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNom() {
        return Nom;
    }

    public void setNom(String nom) {
        Nom = nom;
    }

    public String getPrenom() {
        return Prenom;
    }

    public void setPrenom(String prenom) {
        Prenom = prenom;
    }

    public String getVille() {
        return Ville;
    }

    public void setVille(String ville) {
        Ville = ville;
    }

    public Client(int id, String nom, String prenom, String ville) {
        this.id = id;
        Nom = nom;
        Prenom = prenom;
        Ville = ville;
    }
}
