package Controleur;

import Connexion.DAOFactory;
import Modele.Chambre;
import Modele.Docteur;
import Modele.Employe;
import Modele.Hospitalisation;
import Modele.Infirmier;
import Modele.Malade;
import Modele.Service;
import Modele.Soigne;
import java.sql.Connection;
import java.util.ArrayList;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

/**
 *
 * @author user
 */
public class interactLabels {
    
     /**
      * Constructeur
      */
    public interactLabels() {

    }
        

    //GET<T> pour instancier un object en le remplissant grace aux labels
    /**
     * On remplit une chambre grace aux textFields 
     * @param txt1
     * @param txt2
     * @param txt3
     * @param txt4
     * @param txt5
     * @param Id1
     * @param Id2
     * @param Txtco1
     * @param Txtco2
     * @param Txtco3
     * @param conn
     * @return 
     */
    public Chambre getChambre( String txt1, String txt2, String txt3, String txt4, String txt5, String Id1, String Id2, String Txtco1, String Txtco2, String Txtco3, Connection conn) {
        Chambre ch = null;
        String lit = txt2; String id=Id2;
        if(isOkChambre(lit,id)){
            
            ch=new Chambre();
        ch.setServ((Service) DAOFactory.getServiceDAO(conn).find(Id1));
        // System.out.print(Id1.getSelectionModel().getSelectedItem().toString());
        ch.setIdChambre(Integer.parseInt(Id2));
        ch.setInf((Infirmier) DAOFactory.getInfirmierDAO(conn).find(Integer.parseInt(Txtco1)));
        // ch.setInf((Infirmier) DAOFactory.getInfirmierDAO(conn).find(Integer.parseInt(txt1.getText())));
        ch.setNbLits(Integer.parseInt(lit));
        }
        return ch;
    }
/**
 * On  verifie si la saisie est bonne
 * @param lit
 * @param id
 * @return 
 */
    public boolean isOkChambre(String lit,String id){
        String error ="";
       
         if(lit.length()==0){
        error+="Erreurr id chanbre";
        }
        else{
           try{ Integer.parseInt(lit);}
        catch(NumberFormatException e){
        error+="Erreur id chambre veuillez saisir un entier";
        }}
        
        if(lit.length()==0){
        error+="Erreurr nblit";
        }
        else{
           try{ Integer.parseInt(lit);}
        catch(NumberFormatException e){
        error+="Erreur nblit veuillez saisir un entier";
        }}
        
        return msgErreur(error);   
       
    }
    /**
     * On remplit une docteur grace aux textFields 
     * @param txt1
     * @param txt2
     * @param txt3
     * @param txt4
     * @param txt5
     * @param Id1
     * @param Id2
     * @param Txtco1
     * @param Txtco2
     * @param Txtco3
     * @param conn
     * @return 
     */
    public Docteur getDocteur( String txt1, String txt2, String txt3, String txt4, String txt5, String Id1, String Id2, String Txtco1, String Txtco2, String Txtco3, Connection conn) {
        Docteur doc = new Docteur();
        
        doc.setEmp((Employe) DAOFactory.getEmployeDAO(conn).find(Integer.parseInt(Id1)));
        doc.setSpe(Txtco1);
        return doc;

    }
    
        
/**
     * On remplit un employe grace aux textFields 
     * @param txt1
     * @param txt2
     * @param txt3
     * @param txt4
     * @param txt5
     * @param Id1
     * @param Id2
     * @param Txtco1
     * @param Txtco2
     * @param Txtco3
     * @param conn
     * @return 
     */
    public Employe getEmploye(String txt1, String txt2, String txt3, String txt4, String txt5, String Id1, String Id2, String Txtco1, String Txtco2, String Txtco3, Connection conn) {
String nom=txt1; String prenom = txt2; String tel =txt3; String adresse=txt4;
        Employe emp = null;
                if(isOkEmploye(nom,prenom,tel,adresse)){
                    emp=new Employe();
        System.out.print(Id1);
        
        emp.setIdEmploye(Integer.parseInt(Id1));
        emp.setNom(nom);
        emp.setPrenom(prenom);
        emp.setTel(tel);
        emp.setAdresse(adresse);
                }
        return emp;
    }
    
    /**
     * On  verifie si la saisie est bonne
     * @param nom
     * @param prenom
     * @param tel
     * @param adresse
     * @return 
     */
    public boolean isOkEmploye(String nom, String prenom, String tel, String adresse){
    String error="";
    if(nom.length()==0){error+="nom ";}
    if(prenom.length()==0){error+="prenom ";}
    if(tel.length()==0){error+="tel ";}
    if(adresse.length()==0){error+="adresse ";}
    return msgErreur(error);
    
    }

    /**
     * On remplit une hospitalisation grace aux textFields 
     * @param txt1
     * @param txt2
     * @param txt3
     * @param txt4
     * @param txt5
     * @param Id1
     * @param Id2
     * @param Txtco1
     * @param Txtco2
     * @param Txtco3
     * @param conn
     * @return 
     */
    public Hospitalisation getHospitalisation( String txt1, String txt2, String txt3, String txt4, String txt5, String Id1, String Id2, String Txtco1, String Txtco2, String Txtco3, Connection conn) {
        String lit= txt3;
        Hospitalisation hospi = null;
       
        if(isOkHospi(lit)){
            hospi=new Hospitalisation();
        hospi.setMalade((Malade) DAOFactory.getMaladeDAO(conn).find(Integer.parseInt(Id1)));
        hospi.setServ((Service) DAOFactory.getServiceDAO(conn).find(Txtco1));
        hospi.setChambre((Chambre) DAOFactory.getChambreDAO(conn).find(hospi.getServ().getIdService(), Integer.parseInt(Txtco2)));
        hospi.setLit(Integer.parseInt(lit));}
        return hospi;
    }
    /**
     * On  verifie si la saisie est bonne
     * @param lit
     * @return 
     */
    public boolean isOkHospi(String lit){
        String error ="";
       
        if(lit.length()==0){
        error+="Erreurr nblit";
        }
        else{
           try{ Integer.parseInt(lit);}
        catch(NumberFormatException e){
        error+="Erreur lit : Saisir un entier ";
        }}
        
        return msgErreur(error);   
       
    }

/**
     * On remplit un infirmier grace aux textFields 
     * @param txt1
     * @param txt2
     * @param txt3
     * @param txt4
     * @param txt5
     * @param Id1
     * @param Id2
     * @param Txtco1
     * @param Txtco2
     * @param Txtco3
     * @param conn
     * @return 
     */
    public Infirmier getInfirmier(String txt1, String txt2, String txt3, String txt4, String txt5, String Id1, String Id2, String Txtco1, String Txtco2, String Txtco3, Connection conn) {
        Infirmier inf = null;
        String sal = txt3;
                if(isOkInf(sal)){
                    inf = new Infirmier();
        inf.setEmp((Employe) DAOFactory.getEmployeDAO(conn)
                .find(Integer.parseInt(Id1)));
        inf.setServ((Service) DAOFactory.getServiceDAO(conn).find(Txtco1));
//inf.setServ((Service) DAOFactory.getServiceDAO(conn).find(txt1.getText()));
        inf.setRotation(Txtco2);
        inf.setSalaire(Double.parseDouble(sal));
                }
        return inf;

    }
    /**
     * On  verifie si la saisie est bonne
     * @param sal
     * @return 
     */
    public boolean isOkInf(String sal){
    String error="";
    if(sal.length()==0){
        error+="salaire ";
    }
    else{
    try{
    Double.parseDouble(sal);
    }
    catch(NumberFormatException e){
        error+="salaire : entrer un nombre a virgule";
    }
    }
   return msgErreur(error);
    }
    
/**
     * On remplit un malade grace aux textFields 
     * @param txt1
     * @param txt2
     * @param txt3
     * @param txt4
     * @param txt5
     * @param Id1
     * @param Id2
     * @param Txtco1
     * @param Txtco2
     * @param Txtco3
     * @param conn
     * @return 
     */
    public Malade getMalade( String txt1, String txt2, String txt3, String txt4, String txt5, String Id1, String Id2, String Txtco1, String Txtco2, String Txtco3, Connection conn) {
        Malade mal = null;
        String nom=txt1; String prenom = txt2; String tel =txt3; String adresse=txt4; String mutuelle=txt5;
        
         System.out.println("   "+Id1);
         if(isOkMalade(nom,prenom,tel,adresse,mutuelle)){
             mal=new Malade();
        mal.setIdMalade(Integer.parseInt(Id1));
        mal.setNom(nom);
        mal.setPrenom(prenom);
        mal.setTel(tel);
        mal.setAdresse(adresse);
        mal.setMutuelle(mutuelle);
    }
        return mal;

    }
    
    /**
     * On  verifie si la saisie est bonne
     * @param nom
     * @param prenom
     * @param tel
     * @param adresse
     * @param mutuelle
     * @return 
     */
    public boolean isOkMalade(String nom, String prenom, String tel, String adresse, String mutuelle){
    String error="";
    if(nom.length()==0){error+="nom ";}
    if(prenom.length()==0){error+="prenom ";}
    if(tel.length()==0){error+="tel";}
    if(adresse.length()==0){error+="adresse ";}
    if(mutuelle.length()==0){error+="mutuelle ";}
    
    return msgErreur(error);
    
    }

    /**
     * On remplit un service grace aux textFields 
     * @param txt1
     * @param txt2
     * @param txt3
     * @param txt4
     * @param txt5
     * @param Id1
     * @param Id2
     * @param Txtco1
     * @param Txtco2
     * @param Txtco3
     * @param conn
     * @return 
     */
    public Service getService( String txt1, String txt2, String txt3, String txt4, String txt5, String Id1, String Id2, String Txtco1, String Txtco2, String Txtco3, Connection conn) {
        Service serv = null;
        String batiment = txt2; String nom = txt1; String id = Id1;
        if(isOkService(nom,batiment,id,conn)){
            serv= new Service();
        serv.setIdService(id);
        serv.setNomService(nom);
        serv.setBatiment(batiment.charAt(0));
        serv.setDirecteur((Docteur) DAOFactory.getDocteurDAO(conn)
                .find(Integer.parseInt(Txtco3)));
        }
        return serv;

    }
    /***
     * On  verifie si la saisie est bonne
     * @param nom
     * @param batiment
     * @param id
     * @param conn
     * @return 
     */
    public boolean isOkService(String nom, String batiment, String id, Connection conn){
    String erreur="";
    ArrayList<Service>serv=null;
    serv= DAOFactory.getServiceDAO(conn).getAll();
    for(Service serv1 : serv){
    if(serv1.getIdService().equals(id)){
        erreur+=" le service existe déja ";
    }
    }
    if(id.length()!=3){erreur+="id service (saisir 3 lettres)";}
    if(nom.length()==0){erreur+="nom ";}
    if(batiment.length()!=1){erreur+="batiment (1 seule lettre) ";}
    
    return msgErreur(erreur);
    }

    /**
     * On remplit un soigne grace aux textFields 
     * @param txt1
     * @param txt2
     * @param txt3
     * @param txt4
     * @param txt5
     * @param Id1
     * @param Id2
     * @param Txtco1
     * @param Txtco2
     * @param Txtco3
     * @param conn
     * @return 
     */
    public Soigne getSoigne( String txt1, String txt2, String txt3, String txt4, String txt5, String Id1, String Id2, String Txtco1, String Txtco2, String Txtco3, Connection conn) {
        Soigne soigne = null;
        String jour=txt1; String heure=txt2;
        if(isOkSoigne(jour,heure)){
            soigne= new Soigne();
        soigne.setIdDocteur((Docteur) DAOFactory.getDocteurDAO(conn)
                .find(Integer.parseInt(Id1)));
        soigne.setIdMalade((Malade) DAOFactory.getMaladeDAO(conn)
                .find(Integer.parseInt(Id2)));

        soigne.setJour(jour);
        soigne.setHeure(heure);
        }
        return soigne;

    }
    
    /**
     * On  verifie si la saisie est bonne
     * @param jour
     * @param heure
     * @return 
     */
    public boolean isOkSoigne(String jour, String heure){
    String erreur="";
    String jourj = jour.substring(0,1);
    String mois= jour.substring(3,4);
    String annee= jour.substring(6,9);
    String tiret1 = jour.substring(2);
    String tiret2 = jour.substring(5);
    if(jour.length()==10)
    {
         try{ int i=Integer.parseInt(jourj);
        int j=Integer.parseInt(annee);
         int k=Integer.parseInt(annee);
           if(i<=1||i>30||j<0||j>31||k<2015)
           {
               erreur+="jour mal saisi (jour: 01->31, mois : 1->12, annee >2015) ";
           }
           if(!tiret1.contains("-")||tiret2.contains("-")){
           erreur+="saisir les tirets entre les chiffres ";
           }
           }
        catch(NumberFormatException e){
        erreur+="Erreur date veuillez suivre les consigne de saisie ";
        }
    }
    else
    {
        erreur+="Erreur date veuillez suivre les consigne de saisie ";
    }
     if(heure.length()==0){
        erreur+="heure ";
        }
        else{
           try{ int i=Integer.parseInt(heure);
           if(i<=0||i>24)
           {
               erreur+="heure comprise en 1 et 24";
           }
           }
        catch(NumberFormatException e){
        erreur+="Erreur nblit veuillez saisir un entier";
        }}
        
        return msgErreur(erreur);
    }
    /**
     * On affiche un message d'erreur personnalisé sur les champs mal
     * remplis
     * @param error
     * @return 
     */
    public boolean msgErreur(String error){
    if (error.length() == 0) {
            return true;
        } else {
            // Show the error message.
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Entrée invalide");
            alert.setHeaderText("Corriger les entrées invalides suivantes:");
            alert.setContentText(error);

            alert.showAndWait();

            return false;}
    }
    
}