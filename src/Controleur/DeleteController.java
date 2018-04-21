package Controleur;

import Connexion.Connexion;
import Connexion.DAOFactory;
import Modele.Chambre;
import Modele.Docteur;
import Modele.Employe;
import Modele.Hospitalisation;
import Modele.Infirmier;
import Modele.Malade;
import Modele.Service;
import Modele.Soigne;
import java.net.URL;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;

/**
 * FXML Controller class
 *
 * @author user
 */
public class DeleteController implements Initializable {

    
    @FXML
    private Label labId1, labId2;
    @FXML
    private ComboBox Classe, Id1, Id2;
    @FXML
    private Button ok;

    private Connection conn;
    private Connexion connect;
    // 1 : Chambre 2: Docteur 3:Employe 4:Hospitalisation 5:Infirmier 6:Malade 7:Service 8:Soigne
    private int TYPE_CLASSE;

    private ObservableList<String> classe = FXCollections.observableArrayList("Chambre", "Docteur", "Employe",
            "Hospitalisation", "Infirmier", "Malade", "Service", "Soigne");
    private ObservableList<String> id1 = FXCollections.observableArrayList();
    private ObservableList<String> id2 = FXCollections.observableArrayList();

   

        private interactLabels l =new interactLabels();
        
        /**
         * Constructeur
         */
         public DeleteController() {
    }
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        initClasse();
      
        Id2.setVisible(false);
        labId2.setVisible(false);
        Id1.setVisible(false);
        labId1.setVisible(false);
        ok.setVisible(false);

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
     * On récupère la connexion
     * @param connect 
     */
     public void setConnexion(Connexion connect) {

        this.connect = connect;
        this.conn=connect.getConn();

    }

    /**
     * Lorsque l'on a sélectionné la classe voulue
     * on set les ID
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
                    id2.add(Integer.toString(ch1.getIdChambre()));

                }
                //on définie le nom des label des id
                labId1.setText("Service");
                labId2.setText("N' chambre");

                //on ajoute les listes dans les combobox
                Id1.setItems(id1);
                Id2.setItems(id2);
                Id2.setValue(id2.get(0));
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
        Id1.setValue(id1.get(0));
ok.setVisible(true);

    }

     /**
     * Si on appuie sur le bouton OK
     * On verifie que la saisie et correcte et on supprime l'entrée
     * */
   @FXML
    public void handleOk() {
        
               
        String id, idbis;
        
        //on recupere les id selectionnés
        System.out.println(Id1.getSelectionModel().getSelectedItem().toString());
        id = Id1.getSelectionModel().getSelectedItem().toString();
       
        //en fonction de la classe de l'entité
        switch (TYPE_CLASSE) {
            case 1:

                idbis = Id2.getSelectionModel().getSelectedItem().toString();
                Chambre ch = (Chambre) DAOFactory.getChambreDAO(conn).find(id, Integer.parseInt(idbis));
                if (ch != null) {
                      DAOFactory.getChambreDAO(conn).delete(ch);
                    
                }
                break;
            case 2:
               Docteur doc= ((Docteur) DAOFactory.getDocteurDAO(conn).find(Integer.parseInt(id)));
                DAOFactory.getDocteurDAO(conn).delete(doc);
                
                break;
            case 3:
                Employe emp= ((Employe) DAOFactory.getEmployeDAO(conn).find(Integer.parseInt(id)));
                DAOFactory.getEmployeDAO(conn).delete(emp);
                break;
            case 4:
                Hospitalisation hospi =((Hospitalisation) DAOFactory.getHospitalisationDAO(conn).find(Integer.parseInt(id))); //cast en int
                DAOFactory.getHospitalisationDAO(conn).delete(hospi);
                break;
            case 5:
               Infirmier inf = ((Infirmier) DAOFactory.getInfirmierDAO(conn).find(Integer.parseInt(id)));
               DAOFactory.getInfirmierDAO(conn).delete(inf);
                break;
            case 6:
                Malade mal = ((Malade) DAOFactory.getMaladeDAO(conn).find(Integer.parseInt(id)));
                DAOFactory.getMaladeDAO(conn).delete(mal);
                break;
            case 7:
                 Service  serv= ((Service) DAOFactory.getServiceDAO(conn).find(id));
                 DAOFactory.getServiceDAO(conn).delete(serv);
                break;
            case 8:
                idbis = Id2.getSelectionModel().getSelectedItem().toString();
                Soigne soigne = (Soigne) DAOFactory.getSoigneDAO(conn).find(Integer.parseInt(id), Integer.parseInt(idbis));
                if (soigne != null) {
                    DAOFactory.getSoigneDAO(conn).delete(soigne);
                }
                break;
        }
      handleClasseAction();
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
