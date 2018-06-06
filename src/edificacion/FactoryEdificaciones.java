/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edificacion;
import AbstractFactory.AbstractFactory;
import raza.Raza;
/**
 *
 * @author Alejandro Olmedo <00097017@uca.edu.sv>
 */
public class FactoryEdificaciones implements AbstractFactory {
    @Override
    public Edificacion getEdificacion(String tipo){
        switch(tipo){
            case "Mina":
                return new Mina_oro();
            case "Granja":
                return new Granja();
            case "Piedreria":
                return new Piedreria();
            case "Cuartel":
                return new Cuartel();
        }
        return null;
    }
    @Override
    public Raza getRaza(String tipo){
        return null;
    }
}
