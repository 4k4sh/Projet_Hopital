package Mod�le;

/**
 *
 * @author Akram, Thomas, Killian
 */

/**
 * 
 * Classe objet permettant de faire le lien avec la table Malade
 * Les attributs ont été mis en place par rapport à la description de la base de données
 */

public class Malade {
    
    /*
    Variable de la classe Malade
    */
    
    private int numero; //(clé primaire)
    private String nom; //(clé candidate)
    private String prenom; //(clé candidate)
    private String tel; //(clé candidate)
    private String adresse;
    private String mutuelle;

    /*Constructeur par defaut*/
    
    public Malade() {
    }
    
    /*Constructeur d'initialisation*/
    
    public Malade(int numero, String nom, String prenom, String tel, String adresse, String mutuelle) {
        this.numero = numero;
        this.nom = nom;
        this.prenom = prenom;
        this.tel = tel;
        this.adresse = adresse;
        this.mutuelle = mutuelle;
    }

   

    @Override
    public String toString() {
        return "Malade{" + "numero=" + numero + ", nom=" + nom + ", prenom=" + prenom + ", tel=" + tel + ", adresse=" + adresse + ", mutuelle=" + mutuelle + '}';
    }

    
     /**
     * Getter et setter
     */
    
    /**
     * @return the numMalade
     */
    public int getIdMalade() {
        return numero;
    }

    /**
     * @param numMalade the numMalade to set
     */
    public void setIdMalade(int numero) {
        this.numero = numero;
    }

    /**
     * @return the nom
     */
    public String getNom() {
        return nom;
    }

    /**
     * @param nom the nom to set
     */
    public void setNom(String nom) {
        this.nom = nom;
    }

    /**
     * @return the prenom
     */
    public String getPrenom() {
        return prenom;
    }

    /**
     * @param prenom the prenom to set
     */
    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    /**
     * @return the tel
     */
    public String getTel() {
        return tel;
    }

    /**
     * @param tel the tel to set
     */
    public void setTel(String tel) {
        this.tel = tel;
    }

    /**
     * @return the adresse
     */
    public String getAdresse() {
        return adresse;
    }

    /**
     * @param adresse the adresse to set
     */
    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    /**
     * @return the mutuelle
     */
    public String getMutuelle() {
        return mutuelle;
    }

    /**
     * @param mutuelle the mutuelle to set
     */
    public void setMutuelle(String mutuelle) {
        this.mutuelle = mutuelle;
    }
    
    
    
}
