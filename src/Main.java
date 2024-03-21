import at.favre.lib.crypto.bcrypt.BCrypt;
import com.adresse.manager.ManagerUtilisateur;
import com.adresse.model.Utilisateur;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Objects;

public class Main {
    public static void main(String[] args) throws SQLException {

        Utilisateur user = new Utilisateur("test", "test", "test@test.com", "1234");
        //test si le compte existe

        ManagerUtilisateur.findByMail(user);
        System.out.println(user.getId());
        if(Objects.equals(user.getEmail(), ManagerUtilisateur.findByMail(user).getEmail())){
            System.out.println("Le compte existe déja");
        }
        else{
            //ajouter le compte en BDD
            System.out.println(ManagerUtilisateur.create(user));
        }

        //update de l'utilisateur
        Utilisateur user2 = ManagerUtilisateur.findByMail(user);
        user2.setFirstname("nouveau");
        user2.setName("nouveau");
        ManagerUtilisateur.update(user2);
        //affichage de la liste des utilisateurs
        ArrayList<Utilisateur> liste = ManagerUtilisateur.findAll();
        for (int i = 0; i < liste.size(); i++) {
            System.out.println(liste.get(i));
        }
        if(ManagerUtilisateur.delete(user)){
            System.out.println("Le compte à été supprimé");
        }
        else{
            System.out.println("Le compte n'a pas été supprimé");
        }

    }
}
