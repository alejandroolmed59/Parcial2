/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edificacion;
import AbstractFactory.AbstractFactory;
import Vehiculo.Vehiculo;
import raza.Raza;
/**
 *
 * @author Alejandro Olmedo <00097017@uca.edu.sv>
 */
public class FactoryEdificaciones implements AbstractFactory {
    @Override
    public Edificacion getEdificacion(int tipo){
        switch(tipo){
            case 1:
                return new Cuartel();
            case 2:
                return new Granja();
            case 3:
                return new Mina_oro();   
            case 4:
                return new Piedreria();
            case 5:
                return new Taller_vehiculos();
            case 6:
                return new Castillo();
            case 7:
                return new Maravilla();
        }
        return null;
    }
    @Override
    public Raza getRaza(int tipo){
        return null;
    }
    @Override
    public Vehiculo getVehiculo(int tipo){
        return null;
    }
}
