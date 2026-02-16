package DataBase;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class EtudiantImplementation implements EtudiantDAO{
    Connection con;
    EtudiantImplementation(){
        con=DataBaseConnection.makeConnection();
    }
    @Override
    public int insertEudiant(int cin, String nom, String prenom, double moyenne) {
        String requete_insertion = "insert into Etudiant values (12345,'Salem','karim',10.83)";
        Statement st = null;
        if (con!=null)
        {
            try {
                st = con.createStatement();
                int a = st.executeUpdate(requete_insertion);
                if (a > 0) {
                    System.out.println("Done, inserted !!!");
                }
                return a;
            } catch (SQLException se) {
                System.out.println("Erreur dinsertion" + se.getMessage());

            }
            return 0;
    }

    @Override
    public int deleteEudiant(int cin) {
        return 0;
    }

    @Override
    public int modifyEudiant(int cin, String nom, String prenom, double moyenne) {
        return 0;
    }

    @Override
    public ResultSet selectEudiant(String requeteSelection) {
        return null;
    }

    @Override
    public void afficherResultSet(ResultSet rs) {

    }
}
