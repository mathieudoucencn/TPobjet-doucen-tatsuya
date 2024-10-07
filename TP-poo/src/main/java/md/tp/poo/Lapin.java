package md.tp.poo;

public class Lapin extends Monstre{
    
    /**
     * Constructeur avec paramètres
     * @param n Nom de la créature
     * @param pv Points de vie
     * @param dA Dégâts d'attaque
     * @param pPar Points de parade
     * @param paAtt Pourcentage d'attaque
     * @param paPar Pourcentage de parade
     * @param dMax Distance maximale d'attaque
     * @param p Position
     */
    
    public Lapin(String n,int pv, int dA, int pPar, int paAtt, int paPar, Point2D p){
        super(n, pv, dA, pPar, paAtt, paPar, p);
    }
    
     /**
     * Constructeur par copie
     * @param l Lapin a copier
     **/
  
    public Lapin(Lapin l){
        super(l);
    }
    
    /**
     * Constructeur par defaut
     * 
     **/
    public Lapin(){
        super();
    }

}
