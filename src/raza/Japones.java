/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package raza;

/**
 *
 * @author Alejandro Olmedo <00097017@uca.edu.sv>
 */
public class Japones extends Raza{
    @Override
    public void Iniciar(){
        this.oroBonus=600;
        this.piedraBonus=100;
        this.ataque=ataque+50;
        this.vida=vida-30;
        this.raza="Japones";
        this.especialista="Ninja Samurai Hoja Dorada";
        this.ataque_especialista=400;
        this.vida_especialista=50;
        this.costo_comida_especialista=0;
        this.costo_piedra_especialista=500;
        this.costo_oro_especialista=1000;
        listaRazas lista= listaRazas.getInstance();
        lista.anniadir(this);
    }
}
