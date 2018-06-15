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

    public int ataque = 0, vida = 0, cooldown = 0, flagDefensa = 1, flagAtaque = 1, flagAtaqueV2 = 1, id = Vehiculo.this.hashCode();

    /**
     * La flagAtaqueV2 se usara para corroborar que en ese mismo turno no fue
     * creada y asi poderla añadirla a la lista de atacantes de la edificacione
     * enemiga, 
     * flagAtaque es para corrobarar que hayan pasado dos turnos desde
     * que se añadio para empezar a hacer daño a la edificacion enemiga
     *
     * @return
     */
    public int getFlagAtaqueV2() {
        return flagAtaqueV2;
    }

    public void setFlagAtaqueV2(int flagAtaqueV2) {
        this.flagAtaqueV2 = flagAtaqueV2;
    }
    public String nombre = "", descripcion_extra = "";
    public double costo_oro = 0, costo_piedra = 0, costo_comida = 0;

    abstract public void Iniciar();

    @Override
    public String toString() {
        return "{ " + nombre + " id= " + id + ", ataque=" + ataque + ", vida=" + vida + '}';
    }

    public int getAtaque() {
        return ataque;
    }

    public int getVida() {
        return vida;
    }

    public int getFlagDefensa() {
        return flagDefensa;
    }

    public void operar_Vida(int vida) {
        this.vida += vida;
    }

    public int getFlagAtaque() {
        return flagAtaque;
    }

    public void setFlagAtaque(int flagAtaque) {
        this.flagAtaque = flagAtaque;
    }

    public void setFlagDefensa(int flagDefensa) {
        this.flagDefensa = flagDefensa;
    }

}
