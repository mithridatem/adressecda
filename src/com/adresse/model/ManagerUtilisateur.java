package com.adresse.model;

import java.sql.*;
public class ManagerUtilisateur {
    private static final Connection connexion = Database.getConnexion();
    public static Utilisateur create(Utilisateur user) throws SQLException {
        //créer un objet Utilisateur
        Utilisateur userAdd = new Utilisateur();
        //try la requête
        try {
            //connecter à la bdd
            Statement smt = connexion.createStatement();
            //préparation de la requête
            String req = "INSERT INTO users(name,firstname,email,password) VALUE(?,?,?,?)";
            PreparedStatement preparedStatement = connexion.prepareStatement(req);
            //binder les paramètres
            preparedStatement.setString(1, user.getName());
            preparedStatement.setString(2, user.getFirstname());
            preparedStatement.setString(3, user.getEmail());
            preparedStatement.setString(4, user.getPassword());
            //exécuter la requête
            int row = preparedStatement.executeUpdate();
            //tester si la requête à réussi
            if(row > 0) {
                userAdd.setName(user.getName());
                userAdd.setFirstname(user.getFirstname());
                userAdd.setEmail(user.getEmail());
                userAdd.setPassword(user.getPassword());
            }
            //recupérer l'enregistrement
        }
        //lever l'erreur SQL
        catch (SQLException e){
            e.printStackTrace();
        }
        //retourne un objet utilisateur complet
        return userAdd;
    }

    public static Utilisateur findByMail(Utilisateur user) throws SQLException{
        Utilisateur userRecup = new Utilisateur();
        try {
            //connexion à la bdd
            Statement smt = connexion.createStatement();
            //requête
            String req = "SELECT id, name, firstname, password FROM users WHERE email = ?";
            //préparer la requête
            PreparedStatement preparedStatement = connexion.prepareStatement(req);
            //binder le paramètre
            preparedStatement.setString(1, user.getEmail());
            //exécuter la requête
            ResultSet reponse = preparedStatement.executeQuery();

            while (reponse.next()) {
                //setter le resultat de la réponse dans userRecup
                userRecup.setId(reponse.getInt("id"));
                userRecup.setName(reponse.getString(2));
                userRecup.setFirstname(reponse.getString(3));
                userRecup.setEmail(reponse.getString(4));
                userRecup.setPassword(reponse.getString(5));
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return userRecup;
    }
}
