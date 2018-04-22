package Controleur;

import Connexion.Connexion;
import java.awt.AWTException;
import Vue.MainApp;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;

/**
 * FXML Controller class
 *
 * @author user
 */
public class ConnexionController implements Initializable {

    @FXML
    private Label lab1, lab2, lab3, lab4, lab5, lab6;
    @FXML
    private TextField txt1, txt2, txt4, txt5;
    @FXML
    private PasswordField txt3, txt6;

    ToggleGroup group = new ToggleGroup();
    @FXML
    RadioButton on = new RadioButton("on"), off = new RadioButton("off");
    @FXML
    Button btnconnect = new Button();

    private Connexion maconnexion = null;
    private MainApp mainApp;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO

        //initialisation des radio button
        on.setToggleGroup(group);
        off.setToggleGroup(group);
        off.setSelected(true);
        isSelectedOff();
    }
    
/**
 * On remet tous les champs à 0
 */
    public void reset() {
        txt1.setText("");
        txt2.setText("");
        txt3.setText("");
        txt4.setText("");
        txt5.setText("");
        txt6.setText("");

    }

    /**
     * Affiche les labels correspondant a la connexion en ligne
     */
    @FXML
    public void isSelectedOn() {
        reset();
        off.setSelected(false);
        //  off.
        lab4.setVisible(true);
        txt4.setVisible(true);
        lab5.setVisible(true);
        txt5.setVisible(true);
        lab6.setVisible(true);
        txt6.setVisible(true);
    }

     /**
     * Affiche les labels correspondant a la connexion locale
     */
    @FXML
    public void isSelectedOff() {
        reset();
        on.setSelected(false);

        lab4.setVisible(false);
        txt4.setVisible(false);
        lab5.setVisible(false);
        txt5.setVisible(false);
        lab6.setVisible(false);
        txt6.setVisible(false);

    }

    /**
     * Si presse le bouton connect 
     * on essaie de créer une connexion avec les attribut entrés
     * si cela marche on change de fenetre pour passer sur l'interface principale de l'application
     */
    public void handleConnect() {
        boolean ok=false;
        if (group.getSelectedToggle() == on) {
            if (txt1.getText().length() != 0 || txt2.getText().length() != 0 || txt3.getText().length() != 0) {

                try {
                    try {
                    // tentative de connexion si les 4 attributs sont remplis
                        //maconnexion = new Connexion(nameECETexte.getText(), passwdECEString,
                        // loginBDDTexte.getText(), passwdBDDString);

                        maconnexion = new Connexion(txt1.getText(), txt2.getText(), txt3.getText(), txt4.getText(), txt5.getText(), txt6.getText());
                        ok=true;
                      //  mainApp.showMainApp(maconnexion.getConn());

                    } catch (ClassNotFoundException cnfe) {
                        //System.out.println("Connexion echouee : probleme de classe");
                       msgErreur();
                    }
                } catch (SQLException e) {
                   // System.out.println("Connexion echouee : probleme SQL");
                   msgErreur();
                }
            }
        } else {
            if (txt1.getText().length() != 0 || txt2.getText().length() != 0 || txt3.getText().length() != 0 || txt4.getText().length()!=0||txt5.getText().length()!=0||txt6.getText().length()!=0) {
             
                try {
                    try {

                        maconnexion = new Connexion(txt1.getText(), txt2.getText(), txt3.getText());
                        ok=true;
                        
                    } catch (ClassNotFoundException cnfe) {
                       
                        msgErreur();
                        //cnfe.printStackTrace();
                    }
                } catch (SQLException e) {
                    msgErreur();
                    //e.printStackTrace();
                }
            }
        }
        
        if(ok){
            //on affiche l'interface
        mainApp.showMainApp(maconnexion);
        }
    }
/**
 * Set la MainApp
 * @param mainApp 
 */
    public void setMainApp(MainApp mainApp) {
        this.mainApp = mainApp;

    }
    /**
     * Affiche un msg d'erreur si les informations de connection
     * sont incorrectes
     */
     public void msgErreur(){
   
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Entrée invalide");
            alert.setHeaderText("Corriger les entrées invalides.");
            alert.setContentText("Les informations entrées sont erronnées");

            alert.showAndWait();
   

}
}