package Controleur;

import Connexion.Connexion;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.util.Callback;

/**
 * FXML Controller class
 *
 * @author Ciboulette
 */
public class RechercheController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @FXML
    private Label lab1, lab2, lab3;

    @FXML
    private TextField txt1, txt2, txt3;

    @FXML
    private ComboBox Classe;

    @FXML
    private Button rechercher;

    @FXML
    private TableView<ArrayList> tableau = new TableView<ArrayList>();

    private ArrayList<ArrayList<String>> lignes = new ArrayList<ArrayList<String>>();
    private boolean text1Ok = false, text2Ok = false, text3Ok = false;
    private int nbTextOk = 0;
    private Connection conn;
    private Connexion connect;
    // 1 : Chambre 2: Docteur 3:Employe 4:Hospitalisation 5:Infirmier 6:Malade 7:Service 8:Soigne
    private int TYPE_CLASSE;
    private ObservableList<String> classe = FXCollections.observableArrayList("chambre", "docteur", "employe",
            "hospitalisation", "infirmier", "malade", "service", "soigne");
/**
 * Constructeur
 */
    public RechercheController() {
    }

    /**
 * Set la MainApp
 * @param mainApp 
 */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        initClasse();

    }
 /**
     * On récupère la connexion
     * @param connect 
     */
    public void setConnexion(Connexion connect) {

        this.connect = connect;
        this.conn = connect.getConn();

    }
/**
     * On initilalise la combox des classes
     */
    public void initClasse() {
        setInvisible();
        Classe.setItems(classe);
        Classe.setValue(classe.get(0));
        rechercher.setVisible(false);
    }

    /**
     * Lorsque l'on a sélectionné la classe voulue
     */
    @FXML
    public void handleClasseAction() {
// on recupere le type de classe avec la combobox en fonction de l'index de l'element selectionnÃ©
        TYPE_CLASSE = Classe.getSelectionModel().getSelectedIndex() + 1;
        setInvisible();
        //System.out.println(TYPE_CLASSE);
        switch (TYPE_CLASSE) {
            case 1:
                setChambre();
                break;

            case 2:
                setDocteur();
                break;

            case 3:
                setEmploye();
                break;

            case 4:
                setHospitalisation();
                break;

            case 5:
                setInfirmier();
                break;

            case 6:
                setMalade();
                break;

            case 7:
                setService();
                break;

            case 8:
                setSoigne();
                break;

        }
        //on rend visible le bouton ok
        rechercher.setVisible(true);

    }

    /**
     * méthode du bouton recherche
     */
    public void handleRechercher() {
        String classe = Classe.getSelectionModel().getSelectedItem().toString();
        ResultSet result = null;

        nbTextOk = 0;
        text1Ok = text2Ok = text3Ok = false;

        if (txt1.getText().length() != 0) {
            text1Ok = true;
            nbTextOk++;
        }

        if (txt2.getText().length() != 0) {
            text2Ok = true;
            nbTextOk++;
        }

        if (txt3.getText().length() != 0) {
            text3Ok = true;
            nbTextOk++;
        }

        switch (nbTextOk) {
            case 0:
                result = connect.requete0critere(classe);

                break;

            case 1:
                if (text1Ok) {
                    result = connect.requete1critere(classe, lab1.getText(), txt1.getText());
                }

                if (text2Ok) {
                    result = connect.requete1critere(classe, lab2.getText(), txt2.getText());
                    System.out.print("dfd");
                }

                if (text3Ok) {
                    result = connect.requete1critere(classe, lab3.getText(), txt3.getText());
                }

                break;

            case 2:
                if (text1Ok && text3Ok) {
                    result = connect.requete2critere(classe, lab1.getText(), lab3.getText(), txt1.getText(), txt3.getText());
                }

                if (text1Ok && text2Ok) {
                    result = connect.requete2critere(classe, lab1.getText(), lab2.getText(), txt1.getText(), txt2.getText());
                }

                if (text2Ok && text3Ok) {
                    result = connect.requete2critere(classe, lab2.getText(), lab3.getText(), txt2.getText(), txt3.getText());
                }
                break;

            case 3:
                if (text1Ok && text2Ok && text3Ok) {
                    result = connect.requete3critere(classe, lab1.getText(), lab2.getText(), lab3.getText(), txt1.getText(), txt2.getText(), txt3.getText());
                }
                break;

        }
       if (result!=null){
        try {
            if (result.next()) {
                result.beforeFirst();
                tableau.setVisible(true);
                afficherResult(result);
           } else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Probleme recherche");
                alert.setHeaderText("La recherche n'a rien retourné");
                alert.setContentText("Désolé.");
                
                alert.showAndWait();
            }
        } catch (SQLException ex) {
            Logger.getLogger(RechercheController.class.getName()).log(Level.SEVERE, null, ex);
        }}
        
        else{
          Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Probleme recherche");
                alert.setHeaderText("Vous avez mal saisi vos crit`eres");
                alert.setContentText("Attention.");
                
                alert.showAndWait();
        }
       
      
    }


/**
     * Set les Label et les TextFields de la chambre
     * @param ch 
     */
public void setChambre() {

        lab1.setText("code_service");
        lab2.setText("no_chambre");
        lab3.setText("nb_lits");

        setVisibleLabel(3);
    }

 /**
     * Set les Label et les TextFields du docteur
     * @param doc 
     */
    public void setDocteur() {
        lab1.setText("numero");
        lab2.setText("specialite");

        setVisibleLabel(2);

    }

     /**
     * Set les Label et les TextFields de l'employé
     * @param emp
     */
    public void setEmploye() {
        lab1.setText("numero");
        lab2.setText("nom");
        lab3.setText("prenom");

        setVisibleLabel(3);

    }

     /**
     * Set les Label et les TextFields de l'Hospitalisation
     * @param hospi
     */
    public void setHospitalisation() {
        lab1.setText("no_malade");
        lab2.setText("code_service");
        lab3.setText("no_chambre");

        setVisibleLabel(3);

    }
/**
     * Set les Label et les TextFields de l'infirmier
     * @param inf
     */
    public void setInfirmier() {
        lab1.setText("numero");
        lab2.setText("code_service");
        lab3.setText("rotation");

        setVisibleLabel(3);

    }
/**
     * Set les Label et les TextFields du malade
     * @param mal
     */
    public void setMalade() {
        lab1.setText("nom");
        lab2.setText("prenom");
        lab3.setText("mutuelle");

        setVisibleLabel(3);

    }
 /**
     * Set les Label et les TextFields du Service
     * @param serv
     */
    public void setService() {
        lab1.setText("nom");
        lab2.setText("batiment");
        lab3.setText("directeur");

        setVisibleLabel(3);

    }

    
     /**
     * Set les Label et les TextFields de soigne
     * @param soigne
     */
    public void setSoigne() {
        lab1.setText("no_docteur");
        lab2.setText("no_malade");

        setVisibleLabel(2);
    }
    
/**
     * 
    //on affiche le nombre de label correspondant au nombre
     * @param i 
     */
    public void setVisibleLabel(int i) {
        lab1.setVisible(true);
        txt1.setVisible(true);
        if (i >= 2) {
            lab2.setVisible(true);
            txt2.setVisible(true);
            if (i >= 3) {
                lab3.setVisible(true);
                txt3.setVisible(true);

            }
        }
    }
    
/**
 * //on cache tout les label et les textes
 */
    public void setInvisible() {

        lab1.setVisible(false);
        txt1.setVisible(false);
        lab2.setVisible(false);
        txt2.setVisible(false);
        lab3.setVisible(false);
        txt3.setVisible(false);
        rechercher.setVisible(false);
        tableau.setVisible(false);
    }

  
    /**
     * Affichage du tableau de données
     * @param result 
     */
    public void afficherResult(ResultSet result) {
        //TableView<ArrayList> tableau_bis = new TableView<ArrayList>();
        try {

            ArrayList<TableColumn> tab = new ArrayList();
            ResultSetMetaData meta = result.getMetaData();
            ObservableList<ArrayList> data = FXCollections.observableArrayList();

            for (int i = 1; i <= meta.getColumnCount(); i++) {
                tab.add(new TableColumn(meta.getColumnName(i)));

            }
            tableau.getColumns().clear();
            for (int i = 0; i < meta.getColumnCount(); i++) {
                final int colNo = i;
                tab.get(i).setCellValueFactory(new Callback<CellDataFeatures<ArrayList, String>, ObservableValue<String>>() {
                    @Override
        public ObservableValue<String> call(CellDataFeatures<ArrayList, String> p) {
                        return new SimpleStringProperty((String) p.getValue().get(colNo));
                    }

                });
                tableau.getColumns().add(tab.get(colNo));

            }

            lignes.clear();
            while (result.next()) {
                ArrayList<String> ligne = new ArrayList();
                ligne.clear();

                for (int j = 1; j <= meta.getColumnCount(); j++) {
                    ligne.add((result.getObject(j).toString()));

                    // System.out.println(result.getObject(j).toString());
                    //System.out.println("");
                    if (j == meta.getColumnCount()) {
                        setListe(ligne);
                    }
                }
                for (String ligne2 : ligne) {
                    //System.out.print(ligne2);
                    //System.out.println("");
                }
            }

            for (ArrayList<String> lignes1 : lignes) {
                for (String ligne2 : lignes1) {
                    //System.out.print(ligne2);

                }

            }
            // 
            data.addAll(lignes);
            //}

            tableau.setItems(data);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Set la liste d'array list contenant le resultat de la requete
     * @param ligne 
     */
     public void setListe(ArrayList<String> ligne) {
        lignes.add(ligne);
    }


}
