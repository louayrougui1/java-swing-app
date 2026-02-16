package DataBase;

import java.sql.*;

public class Test {
    public static void main(String[] args) {
        try {
            Class.forName(Config.nomDriver);
        }
        catch (ClassNotFoundException cnfe){
            System.out.println("Erreur Driver"+cnfe.getMessage());
        }
        //Connection con=DataBaseConnection.makeConnection();
        //requete insertion


            String requet_sel= "select * from Etudiant";
            try{
                st = con.createStatement();
                ResultSet res = st.executeQuery(requet_sel);
                ResultSetMetaData rsmd=res.getMetaData();
                int nbcol = rsmd.getColumnCount();
                while(res.next()){
                    for(int i=0;i<nbcol;i++){
                        System.out.print(res.getObject(i+1)+" | ");
                    }
                    System.out.println();
                }

            }
            catch (SQLException se){
                System.out.println("Erreur de selection"+ se.getMessage());
            }

        }


    }
}