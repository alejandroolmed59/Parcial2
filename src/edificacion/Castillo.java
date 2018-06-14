/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edificacion;

import raza.Milicia;
import raza.Raza;

/**
 *
 * @author Alejandro Olmedo <00097017@uca.edu.sv>
 */
public class Castillo extends Edificacion {
    @Override
    public void Iniciar(){
        this.vida=1000;
        this.costo_comida=7000;
        this.costo_oro=1500;
        this.costo_piedra=2500;
        this.nombre="Castillo";
        this.descripcion_extra="\u001B[1;31m"+"Exclusivo de la edad de los castillos, "+"\u001B[0m"+"\u001B[34m"+" genera recursos:comida, piedra, oro"+ "\u001B[0m";
        this.cooldown=4;
        listaEdificaciones lista = listaEdificaciones.getInstance();
        lista.anniadir(this);
    }
    public centro_Mando generar(centro_Mando cm){
        if(cm.comida_jugador+5000<cm.max_comida){
            cm.operar_Comida_jugador(5000);
        }else{
            System.out.println("Se a alcanzado el maximo de comida!!");
        }
        if(cm.oro_jugador+2000<cm.max_oro){
            cm.operar_Oro_jugador(3000);
        }else{
            System.out.println("Se a alcanzado el maximo de cro!!");
        }
        if(cm.piedra_jugador+3000<cm.max_piedra){
            cm.operar_Piedra_jugador(10000);
        }else{
            System.out.println("Se a alcanzado el maximo de piedra!!");
        }  
        return cm;
    }
    @Override
    public centro_Mando recolectar(centro_Mando cm){
        return cm;
    }
    
    @Override
    public Milicia crearSoldado(Raza raza){
        return null;
    }
    @Override
    public void almacenar(){
    }
}
