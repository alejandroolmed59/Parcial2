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
public class Cannion extends Vehiculo{
    @Override
    public void Iniciar(){
        this.ataque=100;
        this.vida=450;
        this.costo_comida=750;
        this.costo_oro=300;
        this.costo_piedra=700;
        this.nombre="Cañon";
        this.descripcion_extra="\u001B[1;31m"+"Exclusivo de la edad feudal"+"\u001B[0m";
        this.cooldown=1;
        listaVehiculos lista = listaVehiculos.getInstance();
        lista.anniadir(this);
    }
}
