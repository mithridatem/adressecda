package com.adresse.vue;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class UserForm extends JDialog{
    private JLabel jlName;
    private JTextField tfName;
    private JLabel jlFirstname;
    private JTextField tfFirstname;
    private JLabel jlEmail;
    private JTextField tfEmail;
    private JLabel jlPassword;
    private JPasswordField pfPassword;
    private JButton btValid;
    private JButton btCancel;
    private JPanel jpMain;

    public UserForm(JDialog parent){
        super(parent);
        setTitle("Ajouter un compte utilisateur");
        setContentPane(jpMain);
        setMaximumSize(new Dimension(800,600));
        setMinimumSize(new Dimension(800,600));
        setVisible(true);
        //écouteur événement bt valider
        btValid.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //récupérer le contenu du nom
                String name = tfName.getText();
                String password = String.valueOf(pfPassword.getPassword());
                System.out.println(name);
            }
        });
        //ecouteur événement bt annuler
        btCancel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("on à cliqué sur le bouton annulé");
            }
        });
    }
}
