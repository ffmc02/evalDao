package org.gaetan.DAO;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class ClientDao extends connextion {
    public ClientDao(){

    }

public void Insert(Client cli){
        this.createConnection();

    PreparedStatement pstm;
    try {
    pstm = this.con.prepareStatement("INSERT INTO client(cli_Name, cli_Firstnamecli_City) VALUE (?, ? )");
        pstm.setString(1, cli.getNom());
        pstm.setString(2, cli.getNom());
        pstm.execute();
        pstm.close();

    } catch (SQLException throwables) {
        throwables.printStackTrace();
    }
    this.closeConnection();
}

public void Update(Client cli){
    this.createConnection();

    PreparedStatement pstm;
    try{
        pstm =this.con.prepareStatement("UPDATE client SET id= ?cli_Name= ?, cli_Firstname=?, cli_City=?");
        pstm.setInt(1, cli.getId());
        pstm.setString(2, cli.getNom());
        pstm.setString(3, cli.getPrenom());
        pstm.setString(4, cli.getVille());

        pstm.execute();
        pstm.close();
    } catch (Exception e) {
        e.printStackTrace();
    }
    this.closeConnection();
}

public void Find(int id) {}
public void List(){}
public List<Client> List{}

}