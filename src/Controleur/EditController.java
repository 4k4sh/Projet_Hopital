package Controleur;

import Connexion.DAOFactory;
import Connexion.Connexion;
import Mod�le.*;
import java.net.URL;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author user
 */
public class EditController implements Initializable {

    @FXML
    private Label lab1, lab2, lab3, lab4, lab5;
    @FXML
    private Label labId1, labId2;
    @FXML
    private TextField txt1, txt2, txt3, txt4, txt5;
    @FXML
    private ComboBox Classe, Id1, Id2, Txtco1, Txtco2, Txtco3;
    @FXML
    private Button validID, ok;

    private Connection conn;
    private Connexion connect;
    
    private boolean okvalid=true;
    // 1 : Chambre 2: Docteur 3:Employe 4:Hospitalisation 5:Infirmier 6:Malade 7:Service 8:Soigne
    private int TYPE_CLASSE;

    private ObservableList<String> classe = FXCollections.observableArrayList("Chambre", "Docteur", "Employe",
            "Hospitalisation", "Infirmier", "Malade", "Service", "Soigne");
    private ObservableList<String> id1 = FXCollections.observableArrayList();
    private ObservableList<String> id2 = FXCollections.observableArrayList();

    //futur test a voir..
    private ObservableList<String> txtco1 = FXCollections.observableArrayList();

    private ObservableList<String> txtco2 = FXCollections.observableArrayList();

    private ObservableList<String> txtco3 = FXCollections.observableArrayList();

    private interactLabels l = new interactLabels();

    /**
     * Constructeur
     */
    public EditController() {
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
        validID.setVisible(false);

        // TODO
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
     * On initilalise la combox 
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

        Id2.setVisible(false);
        labId2.setVisible(false);
        validID.setVisible(true);
        setInvisible();

        System.out.println(TYPE_CLASSE);
        // en fonction de la classe on rempli les comboxBox des ID correspondants a la classe
        switch (TYPE_CLASSE) {
            case 1:
                ArrayList<Chambre> ch;
                //on récupère tout les entités de chambre
                ch = DAOFactory.getChambreDAO(conn).getAll();
                //on parcoure l'arraylist et on ajoute les id a l'observablelist
                for (Chambre ch1 : ch) {
                    // si l'id n'a pas déja ete ajoutée
                    if (!id1.contains(ch1.getServ().getIdService())) {
                        // on ajoute l'id a la liste
                        id1.add(ch1.getServ().getIdService());
                    }
                    //id2.add(Integer.toString(ch1.getIdChambre()));

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

                ArrayList<Docteur> doc;
                doc = DAOFactory.getDocteurDAO(conn).getAll();
                for (Docteur doc1 : doc) {
                    id1.add(Integer.toString(doc1.getEmp().getIdEmploye()));
                }
                labId1.setText("Numero");
                Id1.setItems(id1);
                setVisibleId(false);
                break;
            case 3:
                ArrayList<Employe> emp;
                emp = DAOFactory.getEmployeDAO(conn).getAll();
                for (Employe emp1 : emp) {
                    id1.add(Integer.toString(emp1.getIdEmploye()));
                }
                labId1.setText("Numero");
                Id1.setItems(id1);
                setVisibleId(false);
                break;
            case 4:
                ArrayList<Hospitalisation> hospi;
                hospi = DAOFactory.getHospitalisationDAO(conn).getAll();
                for (Hospitalisation hospi1 : hospi) {
                    id1.add(Integer.toString(hospi1.getMalade().getIdMalade()));
                }
                labId1.setText("Numero");
                Id1.setItems(id1);
                setVisibleId(false);
                break;
            case 5:
                ArrayList<Infirmier> inf;
                inf = DAOFactory.getInfirmierDAO(conn).getAll();
                for (Infirmier inf1 : inf) {
                    id1.add(Integer.toString(inf1.getEmp().getIdEmploye()));
                }
                labId1.setText("Numero");
                Id1.setItems(id1);
                setVisibleId(false);
                break;
            case 6:
                ArrayList<Malade> mal;
                mal = DAOFactory.getMaladeDAO(conn).getAll();
                for (Malade mal1 : mal) {
                    id1.add(Integer.toString(mal1.getIdMalade()));
                }
                labId1.setText("numéro");
                Id1.setItems(id1);
                setVisibleId(false);
                break;
            case 7:
                ArrayList<Service> serv;
                serv = DAOFactory.getServiceDAO(conn).getAll();
                for (Service serv1 : serv) {
                    if (!id1.contains(serv1.getIdService())) {
                        id1.add(serv1.getIdService());
                    }
                }
                labId1.setText("Id Service");
                Id1.setItems(id1);

                setVisibleId(false);
                break;
            case 8:
                ArrayList<Soigne> soigne;
                soigne = DAOFactory.getSoigneDAO(conn).getAll();
                for (Soigne Soigne1 : soigne) {
                    if (!id1.contains(Integer.toString(Soigne1.getIdDocteur().getEmp().getIdEmploye()))) {
                        id1.add(Integer.toString(Soigne1.getIdDocteur().getEmp().getIdEmploye()));
                    }
                  
                }

                labId1.setText("Num Docteur");
                labId2.setText("Num Malade");
                Id1.setItems(id1);
                 Id1.setValue(id1.get(0));
               setId2();
                setVisibleId(true);
                break;

        }
        Id1.setValue(id1.get(0));

        afficheLabel();

    }

    /**
     *  //si on appuie sur le bouton valider id (on peut afficher les labels correspondants)
     */
    @FXML
    public void handleValidID() {
        afficheLabel();
    }

    /**
     * Permet de remplir correctement les labels en fonction de la classe saisie
     */
    public void afficheLabel() {

        //il faudra blinder pour verifier que les 2 id ensemble correspondent
        String id, idbis;

        //on recupere les id selectionnés
        //System.out.println(Id1.getSelectionModel().getSelectedItem().toString());
        id = Id1.getSelectionModel().getSelectedItem().toString();
        setInvisible();
        //en fonction de la classe de l'entité
        switch (TYPE_CLASSE) {
            case 1:

                idbis = Id2.getSelectionModel().getSelectedItem().toString();
                Chambre ch = (Chambre) DAOFactory.getChambreDAO(conn).find(id, Integer.parseInt(idbis));
                if (ch != null) {
                    setChambre(ch);
                }
                break;
            case 2:
                setDocteur((Docteur) DAOFactory.getDocteurDAO(conn).find(Integer.parseInt(id)));
                break;
            case 3:
                setEmploye((Employe) DAOFactory.getEmployeDAO(conn).find(Integer.parseInt(id)));
                break;
            case 4:
                setHospitalisation((Hospitalisation) DAOFactory.getHospitalisationDAO(conn).find(Integer.parseInt(id)));
                break;
            case 5:
                setInfirmier((Infirmier) DAOFactory.getInfirmierDAO(conn).find(Integer.parseInt(id)));
                break;
            case 6:
                setMalade((Malade) DAOFactory.getMaladeDAO(conn).find(Integer.parseInt(id)));
                break;
            case 7:
                setService((Service) DAOFactory.getServiceDAO(conn).find(id));
                break;
            case 8:
                idbis = Id2.getSelectionModel().getSelectedItem().toString();
                Soigne soigne = (Soigne) DAOFactory.getSoigneDAO(conn).find(Integer.parseInt(id), Integer.parseInt(idbis));
                if (soigne != null) {
                    setSoigne(soigne);
                }
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
                Chambre ch =  l.getChambre(txt1.getText(), txt2.getText(), txt3.getText(), txt4.getText(), txt5.getText(),
                        Id1.getSelectionModel().getSelectedItem().toString(), null,
                        Txtco1.getSelectionModel().getSelectedItem().toString(), null, null, conn);
                if (ch != null) {
                    DAOFactory.getChambreDAO(conn).update(ch);
                }
                 else{okvalid=false;}
                break;
            case 2:
                Docteur doc = l.getDocteur(txt1.getText(), txt2.getText(), txt3.getText(), txt4.getText(), txt5.getText(),
                        Id1.getSelectionModel().getSelectedItem().toString(), null,
                        Txtco1.getSelectionModel().getSelectedItem().toString(), null,null, conn);
                DAOFactory.getDocteurDAO(conn).update(doc);
                break;
            case 3:
                Employe emp =  l.getEmploye(txt1.getText(), txt2.getText(), txt3.getText(), txt4.getText(), txt5.getText(),
                        Id1.getSelectionModel().getSelectedItem().toString(), null,null,null,null, conn);
                if (emp != null) {
                    DAOFactory.getEmployeDAO(conn).update(emp);
                }
                 else{okvalid=false;}
                break;
            case 4:
                Hospitalisation hospi =  l.getHospitalisation(txt1.getText(), txt2.getText(), txt3.getText(), txt4.getText(), txt5.getText(),
                        Id1.getSelectionModel().getSelectedItem().toString(), null,
                        Txtco1.getSelectionModel().getSelectedItem().toString(), Txtco2.getSelectionModel().getSelectedItem().toString(),
                      null, conn);
                if (hospi != null) {
                    DAOFactory.getHospitalisationDAO(conn).update(hospi);
                }
                 else{okvalid=false;}
                break;
            case 5:
                Infirmier inf = l.getInfirmier(txt1.getText(), txt2.getText(), txt3.getText(), txt4.getText(), txt5.getText(),
                        Id1.getSelectionModel().getSelectedItem().toString(), null,
                        Txtco1.getSelectionModel().getSelectedItem().toString(), Txtco2.getSelectionModel().getSelectedItem().toString(),null, conn);
                if (inf != null) {
                    DAOFactory.getInfirmierDAO(conn).update(inf);
                }
                 else{okvalid=false;}
                break;
            case 6:

                Malade mal =  l.getMalade(txt1.getText(), txt2.getText(), txt3.getText(), txt4.getText(), txt5.getText(),
                        Id1.getSelectionModel().getSelectedItem().toString(), null,null,null,null, conn);
                if (mal != null) {
                    DAOFactory.getMaladeDAO(conn).update(mal);
                }
                 else{okvalid=false;}
                break;
            case 7:
                Service serv = l.getService(txt1.getText(), txt2.getText(), txt3.getText(), txt4.getText(), txt5.getText(),
                        Id1.getSelectionModel().getSelectedItem().toString(), null,null,null,
                        Txtco3.getSelectionModel().getSelectedItem().toString(), conn);
                if (serv != null) {
                    DAOFactory.getServiceDAO(conn).update(serv);
                }
                 else{okvalid=false;}
                break;
            case 8:
                Soigne soigne = l.getSoigne(txt1.getText(), txt2.getText(), txt3.getText(), txt4.getText(), txt5.getText(),
                        Id1.getSelectionModel().getSelectedItem().toString(), Id2.getSelectionModel().getSelectedItem().toString(),
                        null,null,null, conn);
                if (soigne != null) {
                    DAOFactory.getSoigneDAO(conn).update(soigne);
                }
                else{okvalid=false;}
                break;

        }
        
         if (okvalid) {
            handleClasseAction();
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Succès Modification");
            alert.setHeaderText("Votre entrée a bien étémodifiée");
            alert.setContentText("Vous pouvez continuer.");

            alert.showAndWait();
        }
         
         
    }

     // SET<T> pour initialiser les labels en fonction du type 
    
    /**
     * Initialise l'ID2 en fonction de la premiere ID
     * Valade pour Soigne et Chambre
     */
    public void setId2() {
       if((TYPE_CLASSE==1)&&( !Id1.getItems().isEmpty())){
        id2.clear();
        ArrayList<Chambre> ch;
        System.out.println(Id1.getSelectionModel().getSelectedItem().toString());
        //on récupère tout les entités de chambre
        ch = DAOFactory.getChambreDAO(conn).getAll();
        for (Chambre ch1 : ch) {
            // si l'id n'a pas déja ete ajoutée
            if ((ch1.getServ().getIdService().equals(Id1.getSelectionModel().getSelectedItem().toString()))) {
                // on ajoute l'id a la liste
                id2.add(Integer.toString(ch1.getIdChambre()));
            }

        }
        Id2.setItems(id2);
                Id2.setValue(id2.get(0));
        }
        
         if((TYPE_CLASSE==8)&&(!Id1.getItems().isEmpty())){
        id2.clear();
        ArrayList<Soigne> soigne;
        //on récupère tout les entités de chambre
        soigne = DAOFactory.getSoigneDAO(conn).getAll();
        for (Soigne soigne1 : soigne) {
            // si l'id n'a pas déja ete ajoutée
            if ((soigne1.getIdDocteur().getEmp().getIdEmploye()==Integer.parseInt(Id1.getSelectionModel().getSelectedItem().toString()))) {
                // on ajoute l'id a la liste
                id2.add(Integer.toString(soigne1.getIdMalade().getIdMalade()));
            }

        }
        Id2.setItems(id2);
                Id2.setValue(id2.get(0));
        }
    }

    /**
     * Set les Label et les TextFields de la chambre
     * @param ch 
     */
    public void setChambre(Chambre ch) {
        txtco1.clear();

        lab1.setText("Surveillant");
        lab2.setText("Nombre lits");
        ArrayList<Infirmier> inf;
        inf = DAOFactory.getInfirmierDAO(conn).getAll();
        for (Infirmier inf1 : inf) {
            txtco1.add(Integer.toString(inf1.getEmp().getIdEmploye()));
        }
        Txtco1.setItems(txtco1);
        Txtco1.setValue(Integer.toString(ch.getInf().getEmp().getIdEmploye()));

        //txt1.setText(Integer.toString(ch.getInf().getEmp().getIdEmploye()));
        txt2.setText(Integer.toString(ch.getNbLits()));

        setVisibleLabel(2);

        txt1.setVisible(false);
        Txtco1.setVisible(true);

    }

    /**
     * Set les Label et les TextFields du docteur
     * @param doc 
     */
    public void setDocteur(Docteur doc) {
        lab1.setText("Spécialité");

        ArrayList<Docteur> doc2;
        doc2 = DAOFactory.getDocteurDAO(conn).getAll();
        for (Docteur doc3 : doc2) {
            if (!txtco1.contains(doc3.getSpe())) {
                txtco1.add((doc3.getSpe()));
            }
        }
        Txtco1.setItems(txtco1);
        Txtco1.setValue(doc.getSpe());
        //txt1.setText(doc.getSpe());
        setVisibleLabel(1);

        txt1.setVisible(false);
        Txtco1.setVisible(true);

    }

    /**
     * Set les Label et les TextFields de l'employé
     * @param emp
     */
    public void setEmploye(Employe emp) {
        lab1.setText("Nom");
        lab2.setText("Prenom");
        lab3.setText("Telephone");
        lab4.setText("Adresse");

        txt1.setText(emp.getNom());
        txt2.setText(emp.getPrenom());
        txt3.setText(emp.getTel());
        txt4.setText(emp.getAdresse());

        setVisibleLabel(4);

    }
    
/**
     * Set les Label et les TextFields de l'Hospitalisation
     * @param hospi
     */
    public void setHospitalisation(Hospitalisation hospi) {
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
        Txtco1.setValue(hospi.getServ().getIdService());
        Txtco2.setValue(Integer.toString(hospi.getChambre().getIdChambre()));

        txt3.setText(Integer.toString(hospi.getLit()));

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
    public void setInfirmier(Infirmier inf) {
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
        Txtco1.setValue(inf.getServ().getIdService());

       
        txtco2.addAll("JOUR", "NUIT");
        Txtco2.setItems(txtco2);
        Txtco2.setValue((inf.getRotation()));
        
        txt3.setText(Double.toString(inf.getSalaire()));

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
    public void setMalade(Malade mal) {
        lab1.setText("Nom");
        lab2.setText("Prenom");
        lab3.setText("Telephone");
        lab4.setText("Adresse");
        lab5.setText("Mutuelle");

        txt1.setText(mal.getNom());
        txt2.setText(mal.getPrenom());
        txt3.setText(mal.getTel());
        txt4.setText(mal.getAdresse());
        txt5.setText(mal.getMutuelle());

        setVisibleLabel(5);

    }

    /**
     * Set les Label et les TextFields du Service
     * @param serv
     */
    public void setService(Service serv) {
        txtco3.clear();

        lab1.setText("Nom");
        lab2.setText("Batiment");
        lab3.setText("Directeur");

        txt1.setText(serv.getNomService());
        txt2.setText(Character.toString(serv.getBatiment()));

        ArrayList<Docteur> doc;
        doc = DAOFactory.getDocteurDAO(conn).getAll();
        for (Docteur doc1 : doc) {
            txtco3.add(Integer.toString(doc1.getEmp().getIdEmploye()));
        }
        Txtco3.setItems(txtco3);
        Txtco3.setValue(serv.getDirecteur().getEmp().getIdEmploye());
        //txt3.setText(Integer.toString(serv.getDirecteur().getEmp().getIdEmploye()));

        setVisibleLabel(3);

        txt3.setVisible(false);
        Txtco3.setVisible(true);

    }

    /**
     * Set les Label et les TextFields de soigne
     * @param soigne
     */
    public void setSoigne(Soigne soigne) {
        lab1.setText("Jour (jj-mm-aaaa)");
        lab2.setText("Heure (1 à 24) ");

        txt1.setText(soigne.getJour());
        txt2.setText(soigne.getHeure());

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

}
