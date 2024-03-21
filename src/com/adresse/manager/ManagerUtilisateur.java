package com.adresse.manager;

import com.adresse.db.Database;
import com.adresse.model.Utilisateur;

import java.sql.*;
import java.util.ArrayList;

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

    public static Utilisateur findByMail(Utilisateur user) {
        Utilisateur userRecup = new Utilisateur();
        try {
            //connexion à la bdd
            connexion.createStatement();
            //requête
            String req = "SELECT id, name, firstname, email, password FROM users WHERE email = ?";
            //préparer la requête
            PreparedStatement preparedStatement = connexion.prepareStatement(req);
            //binder le paramètre
            preparedStatement.setString(1, user.getEmail());
            //exécuter la requête
            ResultSet reponse = preparedStatement.executeQuery();
            //boucle pour parcourir le résultat
            while (reponse.next()) {
                if(reponse.getString(1) !=null){
                    //setter les nouvelles valeurs
                    userRecup.setId(reponse.getInt(1));
                    userRecup.setName(reponse.getString("name"));
                    userRecup.setFirstname(reponse.getString("firstname"));
                    userRecup.setEmail(reponse.getString("email"));
                    userRecup.setPassword(reponse.getString("password"));
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        //retourne l'objet Utilisateur
        return userRecup;
    }

    public static Utilisateur update(Utilisateur user) {
        Utilisateur userUpdate = new Utilisateur();
        try{
            Statement smt = connexion.createStatement();
            String req = "UPDATE users SET name = ?, firstname = ? WHERE email = ?";
            PreparedStatement preparedStatement = connexion.prepareStatement(req);
            preparedStatement.setString(1, user.getName());
            preparedStatement.setString(2, user.getFirstname());
            preparedStatement.setString(3, user.getEmail());
            int row = preparedStatement.executeUpdate();
            //test si la requête à été réalisé
            if(row > 0) {
                userUpdate.setName(user.getName());
                userUpdate.setFirstname(user.getFirstname());
                userUpdate.setEmail(user.getEmail());
                userUpdate.setPassword(user.getPassword());
                //on set id si il existe
                if(user.getId()!=0) {
                    userUpdate.setId(user.getId());
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return userUpdate;
    }

    public static ArrayList<Utilisateur> findAll(){
        ArrayList<Utilisateur> users = new ArrayList<>();
        try{
            Statement smt = connexion.createStatement();
            String req = "SELECT id, name, firstname, email, password FROM users";
            PreparedStatement preparedStatement = connexion.prepareStatement(req);
            ResultSet response = preparedStatement.executeQuery();
            while(response.next()) {
                Utilisateur user = new Utilisateur(response.getString("name"),
                        response.getString("firstname"), response.getString("email"), response.getString("password"));
                user.setId(response.getInt("id"));
                users.add(user);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return users;
    }

    public static boolean delete(Utilisateur utilisateur) throws SQLException {
        boolean statut = false;
        try {
            Statement smt = connexion.createStatement();
            String req = "DELETE FROM users WHERE email = ?";
            PreparedStatement preparedStatement = connexion.prepareStatement(req);
            preparedStatement.setString(1, utilisateur.getEmail());
            int row = preparedStatement.executeUpdate();
            if(row > 0) {
                statut = true;
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return statut;
    }
}
