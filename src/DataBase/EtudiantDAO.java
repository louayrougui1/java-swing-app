package DataBase;

import Model.Profile;

import java.sql.ResultSet;
import java.util.List;

public interface EtudiantDAO {
    int insertEtudiant(int cin,String nom, String prenom,double moyenne);
    ResultSet selectEtudiant(String requeteSelection);

    int deleteEudiant(int cin);
    int modifyEudiant(int cin,String nom, String prenom,double moyenne);
    void afficherResultSet(ResultSet rs);
}
