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
public class Maravilla extends Edificacion {
    @Override
    public void Iniciar(){
        this.vida=20000000;
        this.costo_comida=20000;
        this.costo_oro=6000;
        this.costo_piedra=10000;
        this.nombre="\u001B[33m"+"MARAVILLA"+"\u001B[0m";
        this.descripcion_extra="\u001B[1;31m"+"Exclusivo de la edad imperial- Concede la victoria!"+"\u001B[0m";
        this.cooldown=5;
        listaEdificaciones lista = listaEdificaciones.getInstance();
        lista.anniadir(this);
    }
    public centro_Mando recolectar(centro_Mando cm){
        if(cm.comida_jugador+1000<cm.max_comida){
            cm.operar_Comida_jugador(500);
        }else{
            System.out.println("Se a alcanzado el maximo de comida!!");
        }
        if(cm.oro_jugador+1000<cm.max_oro){
            cm.operar_Oro_jugador(500);
        }else{
            System.out.println("Se a alcanzado el maximo de cro!!");
        }
        if(cm.piedra_jugador+1000<cm.max_piedra){
            cm.operar_Piedra_jugador(1000);
        }else{
            System.out.println("Se a alcanzado el maximo de piedra!!");
        }  
        return cm;
    }
    
    @Override
    public Milicia crearSoldado(Raza raza){
        return null;
    }
    
}

