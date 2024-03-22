package com.adresse.vue;

import com.adresse.model.Utilisateur;
import org.springframework.security.crypto.bcrypt.BCrypt;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class UserForm extends JDialog{
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

            }
        });
        btCancel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
    }
    public void createUser() {
        //récupérer le contenu des 5 champs de texte
        String name = tfName.getText();
        String firstname = tfFirstname.getText();
        String email = tfEmail.getText();
        String password = String.valueOf(pfPassword.getPassword());
        String verifPassword = String.valueOf(pfPasswordVerif.getPassword());
        //test si les 5 champs sont bien remplis
        if (!name.isEmpty() && !firstname.isEmpty() && !email.isEmpty()) {
            //test si les passwords correspondent
            if (password.equals(verifPassword)) {
                password = BCrypt.hashpw(password, BCrypt.gensalt());
                Utilisateur user = new Utilisateur(name, firstname, email, password);
                JOptionPane.showMessageDialog(null,
                        "le compte a été ajouté",
                        "Valide",
                        JOptionPane.INFORMATION_MESSAGE);
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
