package org.gaetan.DAO;
import java.sql.Connection;
import java.sql.SQLException;

import static java.sql.DriverManager.getConnection;

public class connextion {
    //attention a bien ajouté ?serverTimezone=UTC aprés la DDB
    String url = "jdbc:mysql://localhost:3306/hotel?serverTimezone=UTC";
    Connection con;
//* methode de connexion 
    public void createConnection(){
        try {
            con = getConnection(url,"root","");
            System.out.println("ça marche ");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
    //*methode de deconnexion
    public boolean closeConnection() {
        boolean flag;
        try {
            con.close();
            flag = true;

        } catch (SQLException e) {
            flag = false;
            System.out.println("con n'est pas fermée");
        }
        return flag;
    }

}



