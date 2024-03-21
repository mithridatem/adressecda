package com.adresse.model;

public class Utilisateur {
    /*-------------------------------------------
                    Attributs
    -------------------------------------------*/
    private int id;
    private String name;
    private String firstname;
    private String email;
    private String password;

    /*-------------------------------------------
                    Constructeurs
    -------------------------------------------*/
    public Utilisateur(){}
    public Utilisateur(String name, String firstname, String email, String password) {
        this.name = name;
        this.firstname = firstname;
        this.email = email;
        this.password = password;
    }

    /*-------------------------------------------
                    Getters et Setters
    -------------------------------------------*/

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    /*-------------------------------------------
                    Méthodes
    -------------------------------------------*/

    @Override
    public String toString() {
        return "Utilisateur{" +
                "name='" + name + '\'' +
                ", firstname='" + firstname + '\'' +
                '}';
    }
}
