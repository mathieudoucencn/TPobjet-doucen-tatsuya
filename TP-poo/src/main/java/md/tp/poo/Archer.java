package md.tp.poo;

import java.util.Random;

public class Archer extends Personnage implements Combattant {
    
    private int nbFleches;
    
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
     * @param nbf Nombre de fleches
     **/
    
    public Archer(String n, int pv, int dA, int pPar, int paAtt, int paPar, int dMax, Point2D p, int nbf){
        super(n,pv,dA,pPar,paAtt,paPar,dMax,p);
        nbFleches = nbf;
    }
    
   
     /**
     * Constructeur par copie
     * @param a Archer a copier
     **/
    
    public Archer(Archer a){
        super(a);
        nbFleches = a.nbFleches;  
    }
    
    /**
     * Constructeur par defaut
     * 
     **/
    public Archer(){
        super();
    }

    public int getNbFleches() {
        return nbFleches;
    }

    public void setNbFleches(int nbFleches) {
        this.nbFleches = nbFleches;
    }
    
    @Override
    public void combattre(Creature c){
        
        Random alea = new Random();
        double distance = this.pos.distance(c.getPos());
        
        if(distance <= 1){
            super.combattre(c);
        }
        else if (distance < this.distAttMax){
            
            if(this.getNbFleches() > 0){
                nbFleches --;
                
                int randAtt = alea.nextInt(100) + 1;
            
                if(randAtt <= this.pageAtt){

                    int randDef = alea.nextInt(100) + 1;
                    int damage;
                    if(randDef >  c.getPagePar()){
                        damage = this.degAtt;     
                    }
                    else{
                        damage = this.degAtt - c.getPtPar();
                    }

                c.setPtVie(c.getPtVie()- damage);
                System.out.println(this.nom + "--->" + c.getNom() + " : " + damage + "damage ");
                }
                else{
                    System.out.println(this.nom + "--->" + c.getNom() + " : missed shooting ");
                }
            }
            else{
                System.out.println(this.nom + "--->" + c.getNom() + " : no arrows to shoot");
            }
           
        } 
        else{
            System.out.println(this.nom + "--->" + c.getNom() + " : too far to attack");      
        }
        
        
    
    }
    
}
