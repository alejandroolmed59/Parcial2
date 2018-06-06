/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package AbstractFactory;
import edificacion.FactoryEdificaciones;
import raza.FactoryRaza;
/**
 *
 * @author Alejandro Olmedo <00097017@uca.edu.sv>
 */
public class FactoryProducer {
    public static AbstractFactory getFactory(String type){
        switch (type){
            case "Raza":
                return new FactoryRaza();
            case "Edificacion":
                return new FactoryEdificaciones();
        }
        return null;
    }
}
