package DataBase;

import java.sql.*;

public class EtudiantImplementation implements EtudiantDAO {
    Connection con;

    public EtudiantImplementation() {
        con = DataBaseConnection.makeConnection();
    }

    @Override
    public int insertEtudiant(int cin, String nom, String prenom, double moyenne) {
        String requete_insertion = "insert into Etudiant values ("+cin+",'"+nom+"','"+prenom+"',"+moyenne+")";
        Statement st = null;
        if (con != null) {
            try {
                st = con.createStatement();
                int a = st.executeUpdate(requete_insertion);
                if (a > 0) {
                    System.out.println("ta777iite");
                }
                return a;
            } catch (SQLException se) {
                System.out.println("Erreur d'insertion: " + se.getMessage());

            }
        }
        return 0;
    }

    @Override
    public ResultSet selectEtudiant(String requeteSelection) {
        Statement st = null;
        try {
            st = con.createStatement();
            ResultSet res = st.executeQuery(requeteSelection);
            return res;

        } catch (SQLException se) {
            System.out.println("Erreur de selection: " + se.getMessage());
        }
        return null;
    }
    @Override
    public int deleteEudiant(int cin) {
        String requete_delete = "DELETE FROM Etudiant WHERE cin="+cin;
        Statement st = null;
        if (con != null) {
            try {
                st = con.createStatement();
                int a = st.executeUpdate(requete_delete);
                if (a > 0) {
                    System.out.println("fasa5 3liya");
                }
                return a;
            } catch (SQLException se) {
                System.out.println("Erreur deleting: " + se.getMessage());

            }
        }
        return 0;
    }

    @Override
    public int modifyEudiant(int cin, String nom, String prenom, double moyenne) {
        String requete_update = "Update Etudiant set nom='"+nom+"',prenom='"+prenom+"',moyenne="+moyenne+" WHERE cin="+cin;
        Statement st = null;
        if (con != null) {
            try {
                st = con.createStatement();
                int a = st.executeUpdate(requete_update);
                if (a > 0) {
                    System.out.println("badil badil fisa3");
                }
                return a;
            } catch (SQLException se) {
                System.out.println("Erreur updating: " + se.getMessage());

            }
        }
        return 0;
    }

    @Override
    public void afficherResultSet(ResultSet rs) {
        try {
            while (rs != null && rs.next()) {
                System.out.println(rs.getInt(1) + " | " + rs.getString(2) + " | " + rs.getString(3) + " | " + rs.getDouble(4));
            }
        } catch (SQLException se) {
            System.out.println("Erreur lecture ResultSet: " + se.getMessage());
        }
    }

}
