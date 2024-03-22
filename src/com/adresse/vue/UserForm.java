package com.adresse.vue;

import com.adresse.manager.ManagerUtilisateur;
import com.adresse.model.Utilisateur;
import org.springframework.security.crypto.bcrypt.BCrypt;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import static com.adresse.env.Regex.REGEX_MAIL;
import static com.adresse.env.Regex.REGEX_PASSWORD;


public class UserForm extends JDialog {
    private JPanel jpMain;
    private JTextField tfName;
    private JLabel jlName;
    private JTextField tfFirstname;
    private JLabel jlFirstname;
    private JTextField tfEmail;
    private JLabel jlEmail;
    private JPasswordField pfPassword;
    private JLabel jlPassword;
    private JPasswordField pfPasswordVerif;
    private JLabel jlVerifPassword;
    private JButton btValid;
    private JButton btCancel;

    public UserForm(JDialog parent) {
        super(parent);
        setTitle("Ajouter un compte utilisateur");
        setContentPane(jpMain);
        setMaximumSize(new Dimension(800, 600));
        setMinimumSize(new Dimension(800, 600));
        setVisible(true);
        btValid.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    createUser();
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }

            }
        });
        btCancel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
    }

    public void createUser() throws SQLException {
        //récupérer le contenu des 5 champs de texte
        String name = tfName.getText();
        String firstname = tfFirstname.getText();
        String email = tfEmail.getText();
        String password = String.valueOf(pfPassword.getPassword());
        String passwordVerif = String.valueOf(pfPasswordVerif.getPassword());
        //test si les 5 champs sont bien remplis
        if (!name.isEmpty() && !firstname.isEmpty() && !email.isEmpty() && !password.isEmpty() && !passwordVerif.isEmpty()) {
            //test si les passwords correspondent
            if (password.equals(passwordVerif)) {

                //test si le password n'est pas valide (match pas le regex)
                if (!password.matches(REGEX_PASSWORD)) {
                    JOptionPane.showMessageDialog(null,
                            "Le mot de passe est invalide",
                            "Erreur",
                            JOptionPane.ERROR_MESSAGE);
                    return;
                }
                //test si le mail n'est pas valide (match pas le regex)
                if (!email.matches(REGEX_MAIL)) {
                    JOptionPane.showMessageDialog(null,
                            "Le mail est invalide",
                            "Erreur",
                            JOptionPane.ERROR_MESSAGE);
                    return;
                }
                //hash du mot de passe
                password = BCrypt.hashpw(password, BCrypt.gensalt());
                //création d'un objet Utilisateur
                Utilisateur user = new Utilisateur(name, firstname, email, password);

                //test si le compte n'existe pas
                if (ManagerUtilisateur.findByMail(user).getId() == 0) {
                    //ajouter le compte
                    ManagerUtilisateur.create(user);
                    //afficher le message compte à bien été ajouté
                    JOptionPane.showMessageDialog(null,
                            "le compte a été ajouté",
                            "Valide",
                            JOptionPane.INFORMATION_MESSAGE);
                }
                //test si le compte existe
                else {
                    JOptionPane.showMessageDialog(null,
                            "le existe déja",
                            "Valide",
                            JOptionPane.ERROR_MESSAGE);
                }


            } else {
                JOptionPane.showMessageDialog(null,
                        "Les mots de passe ne correspondent pas",
                        "Erreur",
                        JOptionPane.ERROR_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(null,
                    "Veuillez remplir tous les champs du formulaire",
                    "Erreur",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    public void cancelUser() {
        //vider les champs
        tfName.setText("");
        tfFirstname.setText("");
        tfEmail.setText("");
        pfPassword.setText("");
        pfPasswordVerif.setText("");
        //afficher un message pour indiquer que les champs sont vidés
        JOptionPane.showMessageDialog(null,
                "Formulaire reset",
                "Reset",
                JOptionPane.INFORMATION_MESSAGE);
        dispose();
    }
}

