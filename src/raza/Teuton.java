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
public class Teuton extends Raza {
    @Override
    public void Iniciar() {
        this.oroBonus=1000;
        this.piedraBonus=1000;
        this.ataque=ataque-10;
        this.vida=vida+50;
        this.raza="Teuton";
        this.especialista="Caballero de la orden teutonica";
        this.bad_luck=0;
        this.ataque_especialista=180;
        this.vida_especialista=350;
        this.costo_oro_especialista=400;
        this.costo_comida_especialista=2000;
        this.costo_piedra_especialista=800;
        listaRazas lista= listaRazas.getInstance();
        lista.anniadir(this);
    }
}
