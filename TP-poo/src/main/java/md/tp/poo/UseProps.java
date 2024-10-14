/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package md.tp.poo;

import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Equipe Pedagogique Objet
 */
public class UseProps {
    private static String serverAddress;
    
    private static void loadProps() {
        try {
            // Get ressource file
            // Should be in src/main/resources/ ... app package // propertyFileName.properties
            ResourceBundle parametre = ResourceBundle.getBundle(UseProps.class.getPackage().getName() + ".props");
            
            // USE parameters
            serverAddress = parametre.getString("server");
        } catch (Exception ex) {
            Logger.getLogger(UseProps.class.getName()).log(Level.SEVERE, null, ex);
        }
    }


    public static void main(String[] args) {
        loadProps();
        System.out.println("serverAddress is " + serverAddress);
    }
}
