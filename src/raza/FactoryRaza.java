/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package raza;
import AbstractFactory.AbstractFactory;
import Vehiculo.Vehiculo;
import edificacion.Edificacion;
/**
 *
 * @author Alejandro Olmedo <00097017@uca.edu.sv>
 */
public class FactoryRaza implements AbstractFactory {
    @Override
    public Raza getRaza(int tipo){
        switch(tipo){
            case 1:
                return new Maya();
            case 2:
                return new Teuton();  
            case 3:
                return new Japones();
    }
        return null;
    }
    @Override
    public Edificacion getEdificacion(int tipo){
        return null;
    }
    @Override
    public Vehiculo getVehiculo(int tipo){
        return null;
    }
}
