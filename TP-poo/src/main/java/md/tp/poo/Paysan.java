package md.tp.poo;

public class Paysan extends Personnage{
    
    //Méthodes
    
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
    
    public Paysan(String n, int pv, int dA, int pPar, int paAtt, int paPar, int dMax, Point2D p){
        super(n, pv, dA, pPar, paAtt, paPar,dMax, p);
    }
    /**
     * Constructeur par copie
     * @param p Paysan a copier
     **/
   
    public Paysan(Paysan p){
        super(p);
    }
    
    /**
     * Constructeur par defaut
     * 
     **/
    public Paysan(){
        super();
    }

}
