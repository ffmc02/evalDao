package org.gaetan.gui;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import org.gaetan.DAO.Client;
import org.gaetan.DAO.ClientDao;
import org.kordamp.ikonli.fontawesome.FontAwesome;
import org.kordamp.ikonli.javafx.FontIcon;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.regex.Pattern;

public class ClientControler implements Initializable {
    public FontIcon iconeN;
    public FontIcon iconeF;
    public FontIcon iconeC;
    public Label CityV;
    public Label messageCity;

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
    Label TitleForm, messageName, messageFirstname, messagCity;
    int idSelectif = 0;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        id.setCellValueFactory(new PropertyValueFactory<>("id"));
        Firstname.setCellValueFactory(new PropertyValueFactory<>("Prenom"));
        Name.setCellValueFactory(new PropertyValueFactory<>("Nom"));
        City.setCellValueFactory(new PropertyValueFactory<>("ville"));
        tableLoad();
        Alert alStart = new Alert(Alert.AlertType.INFORMATION);
        alStart.setTitle("DOnnée a jours ");
        alStart.setContentText("Bienvenue sur votre service de gestion de clientele, votre tableau est à jours.");
        alStart.showAndWait();
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
    public void AddformCLient() {
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
        iconeF.setVisible(false);
        iconeC.setVisible(false);
        iconeN.setVisible(false);
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
    public void SetForm() {
        NameUser.setText(ListeClient.getSelectionModel().getSelectedItem().getNom());
        FirstnameUser.setText(ListeClient.getSelectionModel().getSelectedItem().getPrenom());
        CityUser.setText(ListeClient.getSelectionModel().getSelectedItem().getVille());
        idSelectif = ListeClient.getSelectionModel().getSelectedItem().getId();
        updateClient.setVisible(true);
        deleteClient.setVisible(true);
        updateClient.setDisable(false);
        deleteClient.setDisable(false);

    }

    // spécifique au clique validé dans le bouton modifier
    public void updateFormClient() {
        viewFormAddClient();
        TitleForm.setText("Modifier");
        activateForm();
    }

    public void DeleteformClient() {
        viewFormAddClient();
        TitleForm.setText("Supprimer");
        NameUser.setDisable(true);
        FirstnameUser.setDisable(true);
        CityUser.setDisable(true);
    }

    public void activateForm() {
        NameUser.setDisable(false);
        FirstnameUser.setDisable(false);
        CityUser.setDisable(false);
    }

    //verificaation des champs
    public boolean verifData() {

        //je met mes donnée rentré par l'utilisateur dans des variable
        String userName = NameUser.getText();
        String userFirstname = Firstname.getText();
        String userCity = CityUser.getText();
        //Pour le regex mettre deux \\
        //je nomme chaque boolean par rapport au varaible, je vérifie les regex en meme temps
        boolean N = Pattern.matches("^[A-Z][\\p{L}- ]*$", userName);
        boolean F = Pattern.matches("^[A-Z][\\p{L}- ]*$", userFirstname);
        boolean C = Pattern.matches("^[A-Z][\\p{L}- ]*$", userCity);
        if (N) {
            messageName.setVisible(false);
            iconeN.setIconCode(FontAwesome.CHECK_CIRCLE);
            iconeN.setIconColor(Color.GREENYELLOW);
            iconeN.setVisible(true);
            messageName.setText("");
        } else {
            iconeN.setIconCode(FontAwesome.CLOSE);
            iconeN.setIconColor(Color.RED);
            messageName.setText("Veuillez ne metre que des carractere Alphabétique et Commencé par une Majuscule Dans le Champs Non ");
            iconeN.setVisible(true);
        }
        if (F) {
            messageFirstname.setVisible(false);
            iconeF.setIconCode(FontAwesome.CHECK_CIRCLE);
            iconeF.setIconColor(Color.GREENYELLOW);
            iconeF.setVisible(true);
            messageFirstname.setText("");
        } else {
            iconeF.setIconCode(FontAwesome.CLOSE);
            iconeF.setIconColor(Color.RED);
            messageFirstname.setText("Veuillez ne metre que des carractere Alphabétique et Commencé par une  Majuscule dans le Champs Prénom");
            iconeF.setVisible(true);
        }
        if(C){
            messageCity.setVisible(false);
            iconeC.setIconCode(FontAwesome.CHECK_CIRCLE);
            iconeC.setIconColor(Color.GREENYELLOW);
            iconeC.setVisible(true);
            messageCity.setText("");
        } else {
            iconeC.setIconCode(FontAwesome.CLOSE);
            iconeC.setIconColor(Color.RED);
            messageCity.setText("Veuillez ne metre que des carractere Alphabétique et Commencé par une Majuscule Dans le Champs Ville ");
            iconeC.setVisible(true);
        }
        return N&F&C;
    }



    //validation suivant le texte
    public void ValidateForm() {
        ClientDao actionForm = new ClientDao();
        //si Titleform est egale a
        switch (TitleForm.getText()) {
            //supprimer alors
            case "Supprimer":
                //j'effectue l'action supprimer
                actionForm.Delete(new Client(idSelectif));
                Alert alDelete = new Alert(Alert.AlertType.WARNING);
                alDelete.setTitle("CLient supprimée");
                alDelete.setContentText("Votre client à été supprimier avec succées.");
                alDelete.showAndWait();
                break;
            //a
            case "Modifier":
                if(verifData()){
                // j'inserre mes dpnnée dans ma table
                actionForm.Update(new Client(idSelectif, NameUser.getText(), FirstnameUser.getText(), CityUser.getText()));

                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("AJOUT EFFECTUER");
                    alert.setContentText("Votre client à été Modifier avec succées.");
                    alert.showAndWait();
                }
                break;
            case "Ajouter":
               if(verifData()){
                   actionForm.Insert(new Client(idSelectif, NameUser.getText(), FirstnameUser.getText(), CityUser.getText()));
                   Alert alert = new Alert(Alert.AlertType.INFORMATION);
                   alert.setTitle("AJOUT EFFECTUER");
                   alert.setContentText("Votre client à été ajouté avec succées.");
                   alert.showAndWait();
               }

                break;
            default:
                Alert error = new Alert(Alert.AlertType.WARNING);
                error.setTitle("ERREUR GRAVE");
                error.setHeaderText("ERREUR SYSTEME:");
                error.setContentText("Une erreur est survenue veillez conctatez l'administrateur !");

                error.showAndWait();

        }
        tableLoad();
        hideFormClient();

    }

}