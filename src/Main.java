import com.adresse.model.ManagerUtilisateur;
import com.adresse.model.Utilisateur;

import java.sql.SQLException;
import java.util.Objects;

public class Main {
    public static void main(String[] args) throws SQLException {
        Utilisateur user = new Utilisateur("test", "test", "test@test.com", "1234");
        //test si le compte exist
        if(Objects.equals(user.getEmail(), ManagerUtilisateur.findByMail(user).getEmail())){
            System.out.println("Le compte existe déja");
        }
        else{
            //ajouter le compte en BDD
            System.out.println(ManagerUtilisateur.create(user));
        }
    }
}
