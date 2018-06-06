/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package raza;
import AbstractFactory.AbstractFactory;
import edificacion.Edificacion;
/**
 *
 * @author Alejandro Olmedo <00097017@uca.edu.sv>
 */
public class FactoryRaza implements AbstractFactory {
    @Override
    public Raza getRaza(String tipo){
        switch(tipo){
            case "Teuton":
                return new Teuton();
            case "Maya":
                return new Maya();
    }
        return null;
    }
    @Override
    public Edificacion getEdificacion(String tipo){
        return null;
    }
}
