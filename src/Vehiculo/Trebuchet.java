/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Vehiculo;

/**
 *
 * @author Alejandro Olmedo <00097017@uca.edu.sv>
 */
public class Trebuchet extends Vehiculo {
    @Override
    public void Iniciar(){
        this.ataque=300;
        this.vida=300;
        this.costo_comida=1000;
        this.costo_oro=500;
        this.costo_piedra=1000;
        this.nombre="Trebuchet";
        this.descripcion_extra="\u001B[1;31m"+"Exclusivo de la edad feudal"+"\u001B[0m";
        this.cooldown=2;
        listaVehiculos lista = listaVehiculos.getInstance();
        lista.anniadir(this);
    }
}
