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
public abstract class Vehiculo {

    public int ataque = 0, vida = 0, cooldown = 0, flagAtaque=1, id = Vehiculo.this.hashCode();
    public String nombre = "", descripcion_extra = "";
    public double costo_oro = 0, costo_piedra = 0, costo_comida = 0;

    abstract public void Iniciar();

    @Override
    public String toString() {
        return "{ "+nombre + " id= " + id + ", ataque=" + ataque + ", vida=" + vida + '}';
    }

    public int getAtaque() {
        return ataque;
    }

    public int getVida() {
        return vida;
    }

    public int getFlagAtaque() {
        return flagAtaque;
    }
    public void operar_Vida(int vida){
        this.vida+= vida;
    }

    public void setFlagAtaque(int flagAtaque) {
        this.flagAtaque = flagAtaque;
    }

}
