package Vue;

import Connexion.Connexion;
import Controleur.ConnexionController;
import Controleur.MainController;
import java.io.IOException;
import java.sql.Connection;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 *Main de l'application avec le chargement des fichiers FXML
 * de l'interface
 * @author user
 */
public class MainApp extends Application {

    private Stage primaryStage;
    private Connection conn;
    MainController controllermain = new MainController();
    //ConnexionController controllerconnex;

    /**
     * Methode héritée de Application pour lancer l'interface FXML
     * @param primaryStage
     * @throws Exception 
     */
    @Override
    public void start(Stage primaryStage) throws Exception {
        this.primaryStage = primaryStage;
        this.primaryStage.setTitle("BASE DE L'HOPITAL ARCIDALE");
        showConnexion();
    }
/**
 * Affiche le fichier FXML Connection.fxml
 */
    public void showConnexion() {
        try {

            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("Connexion.fxml"));
            Parent page = (AnchorPane) loader.load();
            Scene scene = new Scene(page);

            primaryStage.setScene(scene);
            primaryStage.show();

            ConnexionController controller = loader.getController();
            controller.setMainApp(this);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
/**
 * Affiche le fichier FXML pour MainController.fxml
 * @param conn 
 */
    public void showMainApp(Connexion conn) {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("Main.fxml"));
            AnchorPane page = (AnchorPane) loader.load();
            Scene scene = new Scene(page);
            primaryStage.setScene(scene);
            primaryStage.show();
           

            controllermain = loader.getController();
            controllermain.setMainApp(this);
            controllermain.setConnexion(conn); // I want to send the variable conn to the others

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Lance l'application
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

}
