package org.gaetan.gui;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import org.gaetan.DAO.Client;
import org.gaetan.DAO.ClientDao;

import java.net.URL;
import java.util.ResourceBundle;

public class ClientControler implements Initializable {
    @FXML
    TableView<Client> ListeClient;
    @FXML
    TableColumn<Client, String> Name;
    @FXML
    TableColumn<Client, String> Firstname;
    @FXML
    TableColumn<Client, String> City;
    @FXML
    TableColumn<Client, Integer> id;
    @FXML
    Button AddClitent, updateClient, deleteClient, Validate, Return;
    @FXML
    VBox FormUser;
    @FXML
    TextField NameUser, FirstnameUser, CityUser;
    @FXML
    Label TitleForm;
int idSelectif= 0;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        id.setCellValueFactory(new PropertyValueFactory<>("id"));
        Firstname.setCellValueFactory(new PropertyValueFactory<>("Prenom"));
        Name.setCellValueFactory(new PropertyValueFactory<>("Nom"));
        City.setCellValueFactory(new PropertyValueFactory<>("ville"));
      tableLoad();
        }


    public void tableLoad() {
        ListeClient.getItems().clear();
        ListeClient.refresh();
        ClientDao clieentListe = new ClientDao();
        for (Client Cli : clieentListe.List()) {
            ListeClient.getItems().add(new Client(Cli.getId(), Cli.getNom(), Cli.getPrenom(), Cli.getVille()));
        }
    }
// specifique au clique sur l'ajout  (vide les texte filde et vérouille le tableau
public void AddformCLient(){
        NameUser.clear();
        FirstnameUser.clear();
        CityUser.clear();
        viewFormAddClient();
    ListeClient.setDisable(true);
    updateClient.setVisible(false);
    deleteClient.setVisible(false);
    updateClient.setDisable(true);
    deleteClient.setDisable(true);
    TitleForm.setText("Ajouter");
    activateForm();

}
// rend visible et déverouille le fourmulaire
    public void viewFormAddClient() {
        FormUser.setVisible(true);
        FormUser.setDisable(false);
    }
    //cache le formulaire et dévérouille le tableau

    public void hideFormClient() {
        FormUser.setVisible(false);
        FormUser.setDisable(true);
        ListeClient.setDisable(false);
        TitleForm.setText(" ");
        activateForm();
    }
    //clique sur une ligne du tableau -> transfere les donnée dans le formulaire et dévérouille les boutons update et delete
    public void SetForm(){
        NameUser.setText(ListeClient.getSelectionModel().getSelectedItem().getNom());
        FirstnameUser.setText(ListeClient.getSelectionModel().getSelectedItem().getPrenom());
        CityUser.setText(ListeClient.getSelectionModel().getSelectedItem().getVille());
        idSelectif=ListeClient.getSelectionModel().getSelectedItem().getId();
        updateClient.setVisible(true);
        deleteClient.setVisible(true);
        updateClient.setDisable(false);
        deleteClient.setDisable(false);

    }
    // spécifique au clique validé dans le bouton modifier
    public void updateFormClient(){
        viewFormAddClient();
        TitleForm.setText("Modifier");
        activateForm();
    }
    public void DeleteformClient(){
        viewFormAddClient();
        TitleForm.setText("Supprimer");
        NameUser.setDisable(true);
        FirstnameUser.setDisable(true);
        CityUser.setDisable(true);
    }
    public void activateForm(){
        NameUser.setDisable(false);
        FirstnameUser.setDisable(false);
        CityUser.setDisable(false);
    }
    public void ValidateForm(){
        ClientDao actionForm= new ClientDao();
        switch (TitleForm.getText()){
            case "Supprimer":
                actionForm.Delete(new Client(idSelectif));
                break;
            case "Modifier":
                actionForm.Update(new Client(idSelectif , NameUser.getText(), FirstnameUser.getText(), CityUser.getText()));
                break;
            case "Ajouter":
                actionForm.Insert(new Client(idSelectif , NameUser.getText(), FirstnameUser.getText(), CityUser.getText()));
                break;
            default:
                System.out.println("Une erreur est survenu veillez contacter l'administrateur ");


        }
        tableLoad();
        hideFormClient();

    }
}