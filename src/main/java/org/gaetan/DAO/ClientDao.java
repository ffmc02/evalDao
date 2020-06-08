package org.gaetan.DAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class ClientDao extends connextion {
    public ClientDao() {

    }

    //methode pour inserer
    public void Insert(Client cli) {
        //appel a la fonction de connection
        this.createConnection();
//requette préparer  (preparedstament)
        PreparedStatement pstm;
        //try
        try {
            pstm = this.con.prepareStatement("INSERT INTO client(cli_Name, cli_Firstname, cli_City) VALUE (?, ? ,?)");
            pstm.setString(1, cli.getNom());
            pstm.setString(2, cli.getPrenom());
            pstm.setString(3, cli.getVille());
            pstm.execute();
            pstm.close();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        this.closeConnection();
    }

    public void Update(Client cli) {
        this.createConnection();

        PreparedStatement pstm;
        try {
            pstm = this.con.prepareStatement("UPDATE client SET  cli_Name= ?, cli_Firstname=?, cli_City=? WHERE id=?");
            pstm.setInt(4, cli.getId());
            pstm.setString(1, cli.getNom());
            pstm.setString(2, cli.getPrenom());
            pstm.setString(3, cli.getVille());

            pstm.execute();
            pstm.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        this.closeConnection();
    }

    public void Find(int id) {
    }

    public List<Client> List() {
        List<Client> listeClient = new ArrayList();
        this.createConnection();

        try {
            Statement stm = con.createStatement();
            ResultSet res = stm.executeQuery("SELECT * FROM client");
            while (res.next()) {
                int id = res.getInt("id");
                String nom = res.getString("cli_Name");
                String prenom = res.getString("cli_Firstname");
                String ville = res.getString("cli_City");
                Client c = new Client(id, nom, prenom, ville);
                listeClient.add(c);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        this.closeConnection();
        return listeClient;
    }

    public void Delete(Client cli) {
        this.createConnection();

        PreparedStatement pstm;
        try {
            pstm = this.con.prepareStatement("DELETE FROM client WHERE id=?");
            pstm.setInt(1, cli.getId());
            pstm.execute();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }

}