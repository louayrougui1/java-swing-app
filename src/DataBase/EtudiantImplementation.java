package DataBase;

import java.sql.*;

public class EtudiantImplementation implements EtudiantDAO {
    Connection con;

    EtudiantImplementation() {
        con = DataBaseConnection.makeConnection();
    }

    @Override
    public int insertEtudiant(int cin, String nom, String prenom, double moyenne) {
        String requete_insertion = "insert into Etudiant values (12345,'Salem','karim',10.83)";
        Statement st = null;
        if (con != null) {
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
            /*
            ResultSetMetaData rsmd=res.getMetaData();
            int nbcol = rsmd.getColumnCount();
            while(res.next()){
                for(int i=0;i<nbcol;i++){
                    System.out.print(res.getObject(i+1)+" | ");
                }
                System.out.println();
            }
            */
        } catch (SQLException se) {
            System.out.println("Erreur de selection" + se.getMessage());
        }
        return null;
    }
    /*
    * @Override
    public List<Profile> selectEtudiant(String query) {
        List<Profile> Profiles = new ArrayList<>();
        //String query = "SELECT * FROM Profile";

        try (PreparedStatement ps = con.prepareStatement(query);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                Profile p = new Profile(rs.getString("nom"), rs.getString("prenom"), rs.getString("Pseudo"));
                Profiles.add(p);
            }
        } catch (SQLException se) {
            System.out.println("Erreur de selection: " + se.getMessage());
        }

        return Profiles;
    }*/

    @Override
    public int deleteEudiant(int cin) {
        String requete_delete = "DELETE FROM Etudiant WHERE cin="+cin;
        Statement st = null;
        if (con != null) {
            try {
                st = con.createStatement();
                int a = st.executeUpdate(requete_delete);
                if (a > 0) {
                    System.out.println("Done, Deleted !!!");
                }
                return a;
            } catch (SQLException se) {
                System.out.println("Erreur deleting" + se.getMessage());

            }
        }
        return 0;
    }

    @Override
    public int modifyEudiant(int cin, String nom, String prenom, double moyenne) {
        String requete_update = "Update Etudiant set nom='"+nom+"',prenom='"+prenom+"',moyenne='"+Double.toString(moyenne)+"' WHERE cin='"+Integer.toString(cin)+"'";
        System.out.println(requete_update);
        Statement st = null;
        if (con != null) {
            try {
                st = con.createStatement();
                int a = st.executeUpdate(requete_update);
                if (a > 0) {
                    System.out.println("Done, updated !!!");
                }
                return a;
            } catch (SQLException se) {
                System.out.println("Erreur updating" + se.getMessage());

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
