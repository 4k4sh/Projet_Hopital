package Controleur;

import Connexion.Connexion;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.Initializable;
import Vue.MainApp;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

/**
 *
 * @author user
 */
public class MainController implements Initializable {
   
    
    private MainApp mainApp;
    private Button retour;
    protected Connexion conn;
    
    @FXML
   AjoutController AjoutController= new AjoutController();
    @FXML
   EditController EditController = new EditController();
    @FXML
    DeleteController DeleteController = new DeleteController();
    @FXML
    RechercheController RechercheController = new RechercheController();
    @FXML
    ReportingController ReportingController = new ReportingController();
      
    /**
     * Constructeur
     */
   public MainController() {
    }
    
   /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
                
        // TODO
    }    
    
        /**
 * Set la MainApp
 * @param mainApp 
 */
     public void setMainApp(MainApp mainApp) {
        this.mainApp = mainApp;


    }
     /**
      * Set la connexion et l'envoie à tout ses fxml importés
      * @param conn 
      */
     public void setConnexion(Connexion conn){
     
     this.conn=conn;
    
     AjoutController.setConnexion(this.conn);
     EditController.setConnexion(this.conn);
     DeleteController.setConnexion(this.conn);
     RechercheController.setConnexion(this.conn);
     ReportingController.setConnexion(this.conn);
     
         }
     /**
      * On revient sur la fenetre de connexion
      */
     public void handleRetour(){
         
         mainApp.showConnexion();
       
     }
     
   
}
