/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edificacion;

import java.util.Scanner;
import raza.Milicia;
import raza.Raza;

/**
 *
 * @author Alejandro Olmedo <00097017@uca.edu.sv>
 */
public class Cuartel extends Edificacion{
    @Override
    public void Iniciar(){
        this.vida=200;
        this.nombre="Cuartel";
        this.costo_comida=500;
        this.costo_oro=150;
        listaEdificaciones lista = listaEdificaciones.getInstance();
        lista.anniadir(this);
    }
    @Override
    public int recolectar(){
        return 0;
    }
    @Override
    public Milicia crearSoldado(Raza raza){
        System.out.println("Ingrese el tipo de milicia(Soldado o Especialista)");
        Scanner leer = new Scanner(System.in);
        switch(leer.next()){
            case "Soldado":
                return new Milicia(raza.ataque, raza.vida);
            case "Especialista":
                return new Milicia(raza.ataque_especialista, raza.vida_especialista);
        }
        return null;
    }
}
