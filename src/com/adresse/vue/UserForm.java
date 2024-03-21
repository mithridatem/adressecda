package com.adresse.vue;

import javax.swing.*;
import java.awt.*;

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
    }
}
