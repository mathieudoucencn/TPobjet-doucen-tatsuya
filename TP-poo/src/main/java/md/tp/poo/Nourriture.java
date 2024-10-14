/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package md.tp.poo;

/**
 * Classe qui représente un objet Nourriture qui peut donner des bonus ou malus.
 */
public class Nourriture extends Objet implements Utilisable {

    private int effet;    // Bonus/Malus à appliquer
    private int duree;    // Durée de l'effet
    private String caract;  // Caractéristique affectée (ex: "degAtt", "ptPar")

    /**
     * Constructeur avec paramètres.
     *
     * @param n Nom du type de nourriture (ex: "Eau").
     * @param p Position de la nourriture.
     * @param effet Valeur de l'effet (positif pour bonus, négatif pour malus).
     * @param duree Nombre de tours pour lesquels l'effet est actif.
     * @param caract La caractéristique modifiée (ex: degAtt, ptPar).
     */
    public Nourriture(String n, Point2D p, int effet, int duree, String caract) {
        super(n, p);
        this.effet = effet;
        this.duree = duree;
        this.caract = caract;
    }

    /**
     * Constructeur par copie.
     *
     * @param n Nourriture à copier.
     */
    public Nourriture(Nourriture n) {
        super(n);  // コピーコンストラクタ
        this.effet = n.effet;
        this.duree = n.duree;
        this.caract = n.caract;
    }

    /**
     * Constructeur par défaut.
     *
     * @param n
     */
    public Nourriture(String n) {
        super(n);
    }

    // Getters and Setters
    public int getEffet() {
        return effet;
    }

    public void setEffet(int effet) {
        this.effet = effet;
    }

    public int getDuree() {
        return duree;
    }

    public void setDuree(int duree) {
        this.duree = duree;
    }

    public String getCaract() {
        return caract;
    }

    public void setCaract(String caract) {
        this.caract = caract;
    }

    /**
     * Utilisation de la Nourriture par un joueur. Applique l'effet sur une
     * caractéristique pendant une durée définie.
     *
     * @param p personnage qui utilise la nourriture.
     */
    @Override
    public void utiliser(Personnage p) {
        System.out.println(p.getNom() + " utilise la nourriture et gagne un effet sur " + this.caract
                + " de " + this.effet + " pour " + this.duree + " tours.");
    }

    @Override
    public String toString() {
        return "Nourriture [effet=" + effet + ", duree=" + duree + ", caract=" + caract + "]";
    }

}


