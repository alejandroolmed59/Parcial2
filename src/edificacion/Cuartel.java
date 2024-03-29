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
        this.cooldown=2;
        listaEdificaciones lista = listaEdificaciones.getInstance();
        lista.anniadir(this);
    }
    @Override
    public centro_Mando generar(centro_Mando cm){
        return cm;
    }
    @Override
    public Milicia crearSoldado(Raza raza){
        System.out.println("Ingrese el tipo de milicia(Soldado o Especialista)");
        System.out.println("Costo de Soldado.  Comida: "+raza.costo_comida_soldado+" Oro: "+raza.costo_oro_soldado);
        System.out.println("Costo de "+raza.especialista+".  Comida: "+raza.costo_comida_especialista+" Oro: "+raza.costo_oro_especialista+" Piedra: "+raza.costo_piedra_especialista);
        Scanner leer = new Scanner(System.in);
        while(true){
            switch(leer.next()){
                case "Soldado":
                    return new Milicia("Soldado",raza.ataque, raza.vida);
                case "Especialista":
                    return new Milicia(raza.especialista,raza.ataque_especialista, raza.vida_especialista);
            }
            System.err.println("Debe ingresar Soldado o Especialista");
        }
    }
    
    @Override
    public centro_Mando recolectar(centro_Mando cm){
        return cm;
    }
        @Override
    public void almacenar(){
    }
}
