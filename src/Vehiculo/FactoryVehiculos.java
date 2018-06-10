/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Vehiculo;

import AbstractFactory.AbstractFactory;
import edificacion.Edificacion;
import raza.Raza;

/**
 *
 * @author Alejandro Olmedo <00097017@uca.edu.sv>
 */
public class FactoryVehiculos implements AbstractFactory{
    @Override
    public Vehiculo getVehiculo(int tipo){
        switch(tipo){
            case 1:
                return new Trebuchet();
            case 2:
                return new Cannion();
        }
        return null;
    }
    @Override
    public Raza getRaza(int tipo){
        return null;
    }
    @Override
    public Edificacion getEdificacion(int tipo){
        return null;
    }
}
