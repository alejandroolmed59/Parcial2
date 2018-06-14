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
        this.comidaBonus=600;
        this.vida_centroMandoBonus=-100;
        this.cooldownEdificacionesBonus=1;
        this.ataque=ataque+80;
        this.vida=vida-30;
        this.raza="Japones";
        this.especialista="Ninja Samurai Asiático Japones Hoja Dorada";
        this.bad_luck=10;
        this.ataque_especialista=400;
        this.vida_especialista=50;
        this.costo_comida_especialista=500;
        this.costo_piedra_especialista=200;
        this.costo_oro_especialista=1000;
        listaRazas lista= listaRazas.getInstance();
        lista.anniadir(this);
    }
}