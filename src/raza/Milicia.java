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
public class Milicia {
    String tipo;
    int ataque,  vida, id, flagAtaque=1;

    public int getFlagAtaque() {
        return flagAtaque;
    }

    public void setFlagAtaque(int flagAtaque) {
        this.flagAtaque = flagAtaque;
    }
    
    public Milicia(String tipo, int ataque, int vida){
        this.tipo=tipo;
        this.ataque=ataque;
        this.vida=vida;
        id= Milicia.this.hashCode();
    }
    public void operar_Vida(int vida){
        this.vida+= vida;
    }
    public String getTipo() {
        return tipo;
    }

    public int getAtaque() {
        return ataque;
    }

    public int getVida() {
        return vida;
    }

    public int getId() {
        return id;
    }

    @Override
    public String toString() {
        return "Milicia{" + "id=" + id + " , tipo="+tipo+" , ataque=" + ataque + ", vida=" + vida + '}';
    }
    
}
