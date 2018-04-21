package Controleur;

import Connexion.Connexion;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.control.Label;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.ComboBox;

/**
 *
 * @author Nicolas
 */


public class ReportingController implements Initializable {
    
    @FXML
    private Label text;
    
    @FXML
    private PieChart piechart;
    
    @FXML
    private ComboBox comb;

    
    private int TYPE_GRAPH=0;
    private ObservableList<String> choix = FXCollections.observableArrayList("Specialité", "Service", "Lits", "Salaire","Chambres"); 
  
    
        final CategoryAxis xAxis = new CategoryAxis();
    final NumberAxis yAxis = new NumberAxis();
    
    @FXML
private ObservableList<PieChart.Data> pieChartData;
    private ObservableList<XYChart.Series<String,Number>> barChartData;
    private BarChart<String,Number> barchart=new BarChart<String,Number>(xAxis,yAxis);

    private Connection conn;
    private Connexion connect;

    /**
     * Constructeur
     */
    public ReportingController() {
    }
    
    
    /**
     * Methode d'initialisation du controleur
     * @param url
     * @param rb 
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       
             //initialisation Ã©lÃ©ments de la vue 
        comb.setItems(choix);
        comb.setValue(choix.get(0));
        pieChartData = FXCollections.observableArrayList(); 
        
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
     * Methode de gestion des evenement sur la comboBox
     */
    @FXML
    public void combAction()
    {
         TYPE_GRAPH = comb.getSelectionModel().getSelectedIndex() + 1;
         pieChartData.clear();
         

         switch(TYPE_GRAPH)
         {
            case 1:
                piechart.setVisible(true);
                barchart.setVisible(false);
                pieChartData = connect.queryPie("SELECT specialite, COUNT(numero) FROM docteur GROUP BY specialite");
                piechart.setTitle("Nombre de medecins par spÃ©cialitÃ©s");
                piechart.setData(pieChartData);
            break;
            case 2:
                piechart.setVisible(true);
                barchart.setVisible(false);
                pieChartData = connect.queryPie("SELECT code_service, COUNT(no_malade) FROM hospitalisation GROUP BY code_service");
                piechart.setTitle("Nombre de malade par service");
                piechart.setData(pieChartData);
                break;
            case 3:
                piechart.setVisible(true);
                barchart.setVisible(false);
                pieChartData = connect.queryPie("SELECT code_service, SUM(nb_lits) FROM `chambre` GROUP BY code_service");
                piechart.setTitle("Nombre de lit par service");
                piechart.setData(pieChartData);
            break;
            case 4:
                piechart.setVisible(true);
                barchart.setVisible(false);
                pieChartData = connect.queryPie("SELECT code_service, FLOOR(AVG(salaire)) FROM infirmier GROUP BY code_service");
                piechart.setTitle("Salaire moyen par service");
                piechart.setData(pieChartData);
            break;
            case 5:
                piechart.setVisible(false);
                barchart.setVisible(true);
                barChartData = queryBar("SELECT code_service, COUNT(no_chambre) FROM hospitalisation GROUP BY code_service", "Nombre de chambre par service");
                barchart.setData(barChartData);
                
                break;
        }

        }
    
       /**
     * Methode qui execute la requete et stock les donnÃ©es dans un ObservableList
     * @param q requete Ã  la base de donnÃ©e
     * @param label titre du graphique
     * @return ObservableList pour BarChart
     */
    public ObservableList<XYChart.Series<String,Number>> queryBar(String q, String label)
    {   
        
        ObservableList<XYChart.Series<String,Number>> items = FXCollections.observableArrayList(); 
        XYChart.Series serie =new XYChart.Series();
                        try {
                        ResultSet result = conn
                                .createStatement(
                                        ResultSet.TYPE_SCROLL_INSENSITIVE,
                                        ResultSet.CONCUR_UPDATABLE
                                ).executeQuery(
                                        q   
                                );
                        ResultSetMetaData resultMeta= result.getMetaData();
                         
                        while (!result.isLast()) {
                           result.next();
                           serie.getData().add(new XYChart.Data(result.getString(1),Integer.parseInt(result.getString(2)) ));
                        }
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
        serie.setName(label);
        items.add(serie);
        return items;
    }   

         
   
}
    
    
    
   
    
  
    

