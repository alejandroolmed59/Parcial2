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
public class Maya extends Raza{
    @Override
    public void Iniciar() {
        this.oroBonus=300;
        this.piedraBonus=300;
        this.comidaBonus=300;
        this.vida_centroMandoBonus=200;
        this.ataque=ataque;
        this.vida=vida+20;
        this.raza="Maya";
        this.especialista="Arquero aguila";
        this.ataque_especialista=200;
        this.vida_especialista=150;
        this.costo_comida_especialista=1700;
        this.costo_piedra_especialista=500;
        this.costo_oro_especialista=500;
        listaRazas lista= listaRazas.getInstance();
        lista.anniadir(this);
    }
    @Override
    public void Atacar_Fortaleza(){
        System.out.println("Bashg!");
    }

}
