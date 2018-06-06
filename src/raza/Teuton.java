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
        this.comidaBonus=1000;
        this.ataque=ataque+20;
        this.vida=vida;
        this.raza="Teuton";
        this.especialista="Caballero de la orden teutonica";
        this.ataque_especialista=200;
        this.vida_especialista=300;
        listaRazas lista= listaRazas.getInstance();
        lista.anniadir(this);
    }
    @Override
    public void Atacar_Fortaleza(){
        System.out.println("Bru");
    }
}
