/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package AbstractFactory;
import Vehiculo.FactoryVehiculos;
import edificacion.FactoryEdificaciones;
import raza.FactoryRaza;
/**
 *
 * @author Alejandro Olmedo <00097017@uca.edu.sv>
 */
public class FactoryProducer {
    /**
     * Devuelve un tipo de factory
     * @param type Nombre de la factory que queremos
     * @return Factory a conveniencia
     */
    public static AbstractFactory getFactory(String type){
        switch (type){
            case "Raza":
                return new FactoryRaza();
            case "Edificacion":
                return new FactoryEdificaciones();
            case "Vehiculo":
                return new FactoryVehiculos();
        }
        return null;
    }
}
