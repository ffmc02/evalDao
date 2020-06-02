package org.gaetan;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

import static java.sql.DriverManager.getConnection;
public class App extends Application {

    public static void main(String[] args) {
        launch(args);
    }


        @Override
        public void start(Stage stage) throws IOException {
            Scene scene = new Scene(FXMLLoader.load(getClass().getResource("/org/gaetan/gui/client.fxml")), 1000, 600);
            stage.setScene(scene);
            stage.setResizable(false);
            stage.show();
    }
}
