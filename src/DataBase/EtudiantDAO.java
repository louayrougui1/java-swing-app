package DataBase;

import java.sql.ResultSet;

public interface EtudiantDAO {
    int insertEudiant(int cin,String nom, String prenom,double moyenne);
    int deleteEudiant(int cin);
    int modifyEudiant(int cin,String nom, String prenom,double moyenne);
    ResultSet selectEudiant(String requeteSelection);
    void afficherResultSet(ResultSet rs);
}
