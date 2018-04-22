package Controleur;

import Connexion.Connexion;
import Connexion.DAOFactory;
import Mod�le.*;
import Vue.MainApp;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *Pour le module d'ajout
 * @author user
 */
public class AjoutController implements Initializable {

    @FXML
    private Label lab1, lab2, lab3, lab4, lab5;
    @FXML
    private Label labId1, labId2;
    @FXML
    private TextField txt1, txt2, txt3, txt4, txt5;
    @FXML
    private ComboBox Classe, Id1, Id2, Txtco1, Txtco2, Txtco3;
    @FXML
    private Button ok;
    @FXML
    private TextArea servicetxt;

    //connexion
    private Connection conn;
    private Connexion connect;
    // 1 : Chambre 2: Docteur 3:Employe 4:Hospitalisation 5:Infirmier 6:Malade 7:Service 8:Soigne
    private int TYPE_CLASSE;

    //combobox du choix de la classe
    private ObservableList<String> classe = FXCollections.observableArrayList("Chambre", "Docteur", "Employe",
            "Hospitalisation", "Infirmier", "Malade", "Service", "Soigne");
    private ObservableList<String> id1 = FXCollections.observableArrayList();
    private ObservableList<String> id2 = FXCollections.observableArrayList();
    private ObservableList<String> id3 = FXCollections.observableArrayList();

    //combobox des attributs de la classe
    private ObservableList<String> txtco1 = FXCollections.observableArrayList();

    private ObservableList<String> txtco2 = FXCollections.observableArrayList();

    private ObservableList<String> txtco3 = FXCollections.observableArrayList();

    //classe permettant de verifier si les labels sont bien remplis et de les recuperer pour creer un nouvel objet du modele
    private interactLabels l = new interactLabels();

    
    private boolean okvalid = true;

    /**
     * Constructeur
     */
    public AjoutController() {

    }

   /**
    * On récupère la connexion
    * @param connect 
    */
    public void setConnexion(Connexion connect) {

        this.connect = connect;
        this.conn=connect.getConn();

    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

       
        initClasse();
        setInvisible();
        Id2.setVisible(false);
        labId2.setVisible(false);
        Id1.setVisible(false);
        labId1.setVisible(false);

        // TODO
    }

    /**
     * On initilalise la combox des classes
     */
    public void initClasse() {
        Classe.setItems(classe);
        Classe.setValue(classe.get(0));

    }

    /**
     * Lorsque l'on a sélectionné la classe voulue
     */
    @FXML
    public void handleClasseAction() {
// on recupere le type de classe avec la combobox en fonction de l'index de l'element selectionné
        TYPE_CLASSE = Classe.getSelectionModel().getSelectedIndex() + 1;
        // vidage des ObservableList
        id1.clear();
        id2.clear();
        id3.clear();

        okvalid = true;

        Id1.setEditable(false);
        Id2.setEditable(false);

        Id2.setVisible(false);
        labId2.setVisible(false);
        setInvisible();

        // en fonction de la classe on rempli les comboxBox des ID correspondants a la classe
        switch (TYPE_CLASSE) {
            case 1:
                ArrayList<Service> serv;
                //on récupère tout les entités de chambre
                serv = DAOFactory.getServiceDAO(conn).getAll();
                //on parcoure l'arraylist et on ajoute les id a l'observablelist
                for (Service serv1 : serv) {
                    // si l'id n'a pas déja ete ajoutée
                    if (!id1.contains(serv1.getIdService())) {
                        // on ajoute l'id a la liste
                        id1.add(serv1.getIdService());
                    }

                }

                //on définie le nom des label des id
                labId1.setText("Service");
                labId2.setText("N' chambre");

                //on ajoute les listes dans les combobox
                Id1.setItems(id1);
                Id1.setValue(id1.get(0));
                setId2();
                setVisibleId(true);

                break;
            case 2:

                ArrayList<Employe> emp4;
                ArrayList<Docteur> doc3;
                emp4 = DAOFactory.getEmployeDAO(conn).getAll();
                doc3 = DAOFactory.getDocteurDAO(conn).getAll();
                for (Docteur doc1 : doc3) {
                    id2.add(Integer.toString(doc1.getEmp().getIdEmploye()));
                }
                ArrayList<Infirmier> inf2;
                inf2 = DAOFactory.getInfirmierDAO(conn).getAll();
                for (Infirmier inf1 : inf2) {
                    id3.add(Integer.toString(inf1.getEmp().getIdEmploye()));
                }
                for (Employe emp2 : emp4) {
                    if (!(id2.contains(Integer.toString(emp2.getIdEmploye())))) {
                        if (!(id3.contains(Integer.toString(emp2.getIdEmploye())))) {
                            id1.add(Integer.toString(emp2.getIdEmploye()));
                        }
                    }
                }
                if (id1.isEmpty()) {
                    okvalid = false;
                    Id1.setVisible(false);
                    labId1.setVisible(false);

                    // Show the error message.
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Impossible d'ajouter un docteur");
                    alert.setHeaderText("Tout les employés ont déja une fonction");
                    alert.setContentText("Veuillez créer un nouvel employé");

                    alert.showAndWait();
                } else {
                    labId1.setText("Numero");
                    Id1.setItems(id1);
                }
                break;
            case 3:
                id1.add(Integer.toString(setNumero("employe", "numero", "")));
                labId1.setText("Numero");
                Id1.setItems(id1);
                setVisibleId(false);
                break;
            case 4:
                ArrayList<Malade> mal1;
                mal1 = DAOFactory.getMaladeDAO(conn).getAll();
                for (Malade mal2 : mal1) {
                    id1.add(Integer.toString(mal2.getIdMalade()));
                }
                labId1.setText("Numero");
                Id1.setItems(id1);
                setVisibleId(false);
                break;
            case 5:
                ArrayList<Employe> emp;
                ArrayList<Docteur> doc2;
                emp = DAOFactory.getEmployeDAO(conn).getAll();
                doc2 = DAOFactory.getDocteurDAO(conn).getAll();
                for (Docteur doc1 : doc2) {
                    id2.add(Integer.toString(doc1.getEmp().getIdEmploye()));
                }
                ArrayList<Infirmier> inf;
                inf = DAOFactory.getInfirmierDAO(conn).getAll();
                for (Infirmier inf1 : inf) {
                    id3.add(Integer.toString(inf1.getEmp().getIdEmploye()));
                }
                for (Employe emp1 : emp) {
                    if (!(id2.contains(Integer.toString(emp1.getIdEmploye())))) {
                        if (!(id3.contains(Integer.toString(emp1.getIdEmploye())))) {
                            id1.add(Integer.toString(emp1.getIdEmploye()));
                        }
                    }
                }

                // si tout les employés ont une fonction
                if (id1.isEmpty()) {
                    okvalid = false;
                    Id1.setVisible(false);
                    labId1.setVisible(false);

                    // Show the error message.
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Impossible d'ajouter un infirmier");
                    alert.setHeaderText("Tout les employés ont déja une fonction");
                    alert.setContentText("Veuillez créer un nouvel employé");

                    alert.showAndWait();
                } else {
                    labId1.setText("Numero");
                    Id1.setItems(id1);
                }
                break;
            case 6:
                id1.add(Integer.toString(setNumero("malade", "numero", "")));
                labId1.setText("numéro");
                Id1.setItems(id1);
                setVisibleId(false);
                break;
            case 7:

                labId1.setText("Id Service (3lettres)");
                Id1.getItems().clear();
                Id1.setEditable(true);

                setVisibleId(false);
                break;
            case 8:
                ArrayList<Soigne> soigne;
                soigne = DAOFactory.getSoigneDAO(conn).getAll();
                for (Soigne Soigne1 : soigne) {
                    if (!id1.contains(Integer.toString(Soigne1.getIdDocteur().getEmp().getIdEmploye()))) {
                        id1.add(Integer.toString(Soigne1.getIdDocteur().getEmp().getIdEmploye()));
                    }
                    if (!id2.contains(Integer.toString(Soigne1.getIdMalade().getIdMalade()))) {
                        id2.add(Integer.toString(Soigne1.getIdMalade().getIdMalade()));
                    }
                }

                labId1.setText("Num Docteur");
                labId2.setText("Num Malade");
                Id1.setItems(id1);
                Id2.setItems(id2);
                Id2.setValue(id2.get(0));
                setVisibleId(true);
                break;

        }

        if ((TYPE_CLASSE != 7) && (okvalid)) {
            Id1.setValue(id1.get(0));
           
        }
        if (okvalid) {
            afficheLabel();
        }

    }

  /**
   * On affiche les labels correspondants a la classe selectionnée
   */
    public void afficheLabel() {

        
        setInvisible();
        //en fonction de la classe de l'entité
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
         //  Id1.setDisable(true);           
        // Id2.setDisable(true);

        //on rend visible le bouton ok
        ok.setVisible(true);
    }

     /**
     * Si on appuie sur le bouton OK
     * On verifie que la saisie et correcte et on met à jour l'entrée
     * */
    @FXML
    public void handleOk() {
        // on regarde le type de classe
      okvalid=true;

        switch (TYPE_CLASSE) {
            case 1:
                Chambre ch = l.getChambre(txt1.getText(), txt2.getText(), txt3.getText(), txt4.getText(), txt5.getText(),
                        Id1.getSelectionModel().getSelectedItem().toString(),  Id2.getSelectionModel().getSelectedItem().toString(),
                        Txtco1.getSelectionModel().getSelectedItem().toString(), null, null, conn);
                if (ch != null) {
                    DAOFactory.getChambreDAO(conn).create(ch);
                } else {
                    okvalid = false;
                }
                break;
            case 2:
                Docteur doc = l.getDocteur(txt1.getText(), txt2.getText(), txt3.getText(), txt4.getText(), txt5.getText(),
                        Id1.getSelectionModel().getSelectedItem().toString(), null,
                        Txtco1.getSelectionModel().getSelectedItem().toString(), null,null, conn);
                DAOFactory.getDocteurDAO(conn).create(doc);
                break;
            case 3:
                Employe emp = l.getEmploye(txt1.getText(), txt2.getText(), txt3.getText(), txt4.getText(), txt5.getText(),
                        Id1.getSelectionModel().getSelectedItem().toString(), null,null,null,null, conn);
                if (emp != null) {
                    DAOFactory.getEmployeDAO(conn).create(emp);
                } else {
                    okvalid = false;
                }
                break;
            case 4:
                
                Hospitalisation hospi = l.getHospitalisation(null,null, txt3.getText(), null, null,
                        Id1.getSelectionModel().getSelectedItem().toString(), null,
                        Txtco1.getSelectionModel().getSelectedItem().toString(), Txtco2.getSelectionModel().getSelectedItem().toString(),
                      null, conn);
                if (hospi != null) {
                    DAOFactory.getHospitalisationDAO(conn).create(hospi);
                } else {
                    okvalid = false;
                }
                break;
            case 5:
                Infirmier inf = l.getInfirmier(txt1.getText(), txt2.getText(), txt3.getText(), txt4.getText(), txt5.getText(),
                        Id1.getSelectionModel().getSelectedItem().toString(), null,
                        Txtco1.getSelectionModel().getSelectedItem().toString(), Txtco2.getSelectionModel().getSelectedItem().toString(),null, conn);
                if (inf != null) {
                    DAOFactory.getInfirmierDAO(conn).create(inf);
                } else {
                    okvalid = false;
                }
                break;
            case 6:

                Malade mal = l.getMalade(txt1.getText(), txt2.getText(), txt3.getText(), txt4.getText(), txt5.getText(),
                        Id1.getSelectionModel().getSelectedItem().toString(), null,null,null,null, conn);
                if (mal != null) {
                    DAOFactory.getMaladeDAO(conn).create(mal);
                } else {
                    okvalid = false;
                }
                break;
            case 7:
                Service serv = l.getService(txt1.getText(), txt2.getText(), txt3.getText(), txt4.getText(), txt5.getText(),
                        Id1.getSelectionModel().getSelectedItem().toString(), null,null,null,
                        Txtco3.getSelectionModel().getSelectedItem().toString(), conn);
                if (serv != null) {
                    DAOFactory.getServiceDAO(conn).create(serv);
                } else {
                    okvalid = false;
                }
                break;
            case 8:
                Soigne soigne = l.getSoigne(txt1.getText(), txt2.getText(), txt3.getText(), txt4.getText(), txt5.getText(),
                        Id1.getSelectionModel().getSelectedItem().toString(), Id2.getSelectionModel().getSelectedItem().toString(),
                        null,null,null, conn);
                if (soigne != null) {
                    DAOFactory.getSoigneDAO(conn).create(soigne);
                } else {
                    okvalid = false;
                }
                break;

        }

        if (okvalid) {
            handleClasseAction();
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Succès Ajout");
            alert.setHeaderText("Votre nouvelle entrée a bien été ajoutée");
            alert.setContentText("Vous pouvez continuer.");

            alert.showAndWait();
        }
        
        
    }

    // SET<T> pour initialiser les labels en fonction du type 
    /**
     * Ajoute la deuxième ID en fonction de la premiere ID
     */
    public void setId2() {
        if ((TYPE_CLASSE ==1)&&(!Id1.getItems().isEmpty())) {
            id2.clear();
        
            int id = setNumero("chambre", "no_chambre", Id1.getSelectionModel().getSelectedItem().toString());
            if(id-1==0){
        Id2.setEditable(true);
        servicetxt.setVisible(true);
        } 
            else{
            id2.add(Integer.toString(id));
            Id2.setItems(id2);
            Id2.setValue(id2.get(0));
            }
       }
        
    }

    /**
     * Set les Label et les TextFields de la chambre
     * @param ch 
     */
    public void setChambre() {
        txtco1.clear();

        lab1.setText("Surveillant");
        lab2.setText("Nombre lits");
        ArrayList<Infirmier> inf;
        inf = DAOFactory.getInfirmierDAO(conn).getAll();
        for (Infirmier inf1 : inf) {
            txtco1.add(Integer.toString(inf1.getEmp().getIdEmploye()));
        }
        Txtco1.setItems(txtco1);
        Txtco1.setValue(txtco1.get(0));

        //txt1.setText(Integer.toString(ch.getInf().getEmp().getIdEmploye()));
        txt2.setText("");

        setVisibleLabel(2);

        txt1.setVisible(false);
        Txtco1.setVisible(true);

    }

     /**
     * Set les Label et les TextFields du docteur
     * @param doc 
     */
    public void setDocteur() {
        lab1.setText("Spécialité");

        ArrayList<Docteur> doc2;
        doc2 = DAOFactory.getDocteurDAO(conn).getAll();
        for (Docteur doc3 : doc2) {
            if (!txtco1.contains(doc3.getSpe())) {
                txtco1.add((doc3.getSpe()));
            }
        }
        Txtco1.setItems(txtco1);
        Txtco1.setValue(txtco1.get(0));
        //txt1.setText(doc.getSpe());
        setVisibleLabel(1);

        txt1.setVisible(false);
        Txtco1.setVisible(true);

    }

      /**
     * Set les Label et les TextFields de l'employé
     * @param emp
     */
    public void setEmploye() {
        lab1.setText("Nom");
        lab2.setText("Prenom");
        lab3.setText("Telephone");
        lab4.setText("Adresse");

        txt1.setText("");
        txt2.setText("");
        txt3.setText("");
        txt4.setText("");

        setVisibleLabel(4);

    }

    /**
     * Set les Label et les TextFields de l'Hospitalisation
     * @param hospi
     */
    public void setHospitalisation() {
        txtco1.clear();
        txtco2.clear();

        lab1.setText("Code Service");
        lab2.setText("Num Chambre");
        lab3.setText("Lit");

        ArrayList<Service> serv;
        serv = DAOFactory.getServiceDAO(conn).getAll();
        for (Service serv1 : serv) {
            if (!txtco1.contains(serv1.getIdService())) {
                txtco1.add(serv1.getIdService());
            }
        }

        ArrayList<Chambre> ch;
        //on récupère tout les entités de chambre
        ch = DAOFactory.getChambreDAO(conn).getAll();
        //on parcoure l'arraylist et on ajoute les id a l'observablelist
        for (Chambre ch1 : ch) {
            txtco2.add(Integer.toString(ch1.getIdChambre()));

        }

        //on ajoute les listes dans les combobox
        Txtco1.setItems(txtco1);
        Txtco2.setItems(txtco2);
        Txtco1.setValue(txtco1.get(0));
        Txtco2.setValue(txtco2.get(0));

        //  txt1.setText(hospi.getServ().getIdService());
        //txt2.setText(Integer.toString(hospi.getChambre().getIdChambre()));
        txt3.setText("");

        setVisibleLabel(3);
        txt1.setVisible(false);
        Txtco1.setVisible(true);
        txt2.setVisible(false);
        Txtco2.setVisible(true);

    }

    /**
     * Set les Label et les TextFields de l'infirmier
     * @param inf
     */
    public void setInfirmier() {
        txtco1.clear();
        txtco2.clear();

        lab1.setText("Code Service");
        lab2.setText("Rotation");
        lab3.setText("Salaire");

        ArrayList<Service> serv;
        serv = DAOFactory.getServiceDAO(conn).getAll();
        for (Service serv1 : serv) {
            if (!txtco1.contains(serv1.getIdService())) {
                txtco1.add(serv1.getIdService());
            }
        }
        Txtco1.setItems(txtco1);
        Txtco1.setValue(txtco1.get(0));

        // txt1.setText(inf.getServ().getIdService());
        txtco2.addAll("JOUR", "NUIT");
        Txtco2.setItems(txtco2);
        Txtco2.setValue(txtco2.get(0));
        //txt2.setText(inf.getRotation());
        txt3.setText("");

        setVisibleLabel(3);
        txt1.setVisible(false);
        Txtco1.setVisible(true);
        txt1.setVisible(false);
        Txtco2.setVisible(true);
        txt2.setVisible(false);

    }

    /**
     * Set les Label et les TextFields du malade
     * @param mal
     */
    public void setMalade() {
        lab1.setText("Nom");
        lab2.setText("Prenom");
        lab3.setText("Telephone");
        lab4.setText("Adresse");
        lab5.setText("Mutuelle");

        txt1.setText("");
        txt2.setText("");
        txt3.setText("");
        txt4.setText("");
        txt5.setText("");

        setVisibleLabel(5);

    }

     /**
     * Set les Label et les TextFields du Service
     * @param serv
     */
    public void setService() {
        txtco3.clear();
        
        servicetxt.setVisible(true);

        lab1.setText("Nom");
        lab2.setText("Batiment");
        lab3.setText("Directeur");

        txt1.setText("");
        txt2.setText("");

        ArrayList<Docteur> doc;
        doc = DAOFactory.getDocteurDAO(conn).getAll();
        for (Docteur doc1 : doc) {
            txtco3.add(Integer.toString(doc1.getEmp().getIdEmploye()));
        }
        Txtco3.setItems(txtco3);
        Txtco3.setValue(txtco3.get(0));
        //txt3.setText(Integer.toString(serv.getDirecteur().getEmp().getIdEmploye()));

        setVisibleLabel(3);

        txt3.setVisible(false);
        Txtco3.setVisible(true);

    }

     /**
     * Set les Label et les TextFields de soigne
     * @param soigne
     */
    public void setSoigne() {
        lab1.setText("Jour (jj-mm-aaaa)");
        lab2.setText("Heure (1 à 24) ");

        txt1.setText("");
        txt2.setText("");

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
                if (i >= 4) {
                    lab4.setVisible(true);
                    txt4.setVisible(true);
                    if (i >= 5) {
                        lab5.setVisible(true);
                        txt5.setVisible(true);
                    }
                }
            }
        }
    }

/**
 * //on cache tout les label et les textes
 */
    public void setInvisible() {
        //  Id1.setEditable(false);
        servicetxt.setVisible(false);
        servicetxt.setEditable(false);
        lab1.setVisible(false);
        txt1.setVisible(false);
        lab2.setVisible(false);
        txt2.setVisible(false);
        lab3.setVisible(false);
        txt3.setVisible(false);
        lab4.setVisible(false);
        txt4.setVisible(false);
        lab5.setVisible(false);
        txt5.setVisible(false);
        ok.setVisible(false);
        Txtco1.setVisible(false);
        Txtco2.setVisible(false);
        Txtco3.setVisible(false);
    }

    /**
     * On affiche le nombre d'ID spécifié
     * @param i 
     */
    public void setVisibleId(boolean i) {

        Id1.setVisible(true);
        labId1.setVisible(true);
        if (i) {
            Id2.setVisible(true);
            labId2.setVisible(true);
        }
    }

    /**
     * Permet de donner un ID automatique a partir de la valeur
     * maximale des ID de la table
     * @param classe
     * @param id
     * @param code
     * @return 
     */
    public int setNumero(String classe, String id, String code) {

        int ID = 0;
        ResultSet result = null;

        try {
            switch (TYPE_CLASSE) {
                case 1:
                    result = conn
                            .createStatement(
                                    ResultSet.TYPE_SCROLL_INSENSITIVE,
                                    ResultSet.CONCUR_UPDATABLE).executeQuery("SELECT MAX(" + id + ") as id FROM " + classe + " WHERE code_service='" + code + "'");
                    break;
                case 3:
                case 6:
                    result = conn
                            .createStatement(
                                    ResultSet.TYPE_SCROLL_INSENSITIVE,
                                    ResultSet.CONCUR_UPDATABLE).executeQuery("SELECT MAX(" + id + ") as id FROM " + classe);
                    break;

            }
            if (result.first()) {
                ID = result.getInt("id") + 1;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return ID;
    }

}
