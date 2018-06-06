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
    int ataque,  vida;
    public Milicia(int ataque, int vida){
        this.ataque=ataque;
        this.vida=vida;
    }

    @Override
    public String toString() {
        return "Milicia{" + "ataque=" + ataque + ", vida=" + vida + '}';
    }
    
}
