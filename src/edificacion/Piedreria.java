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
public class Piedreria extends Edificacion{
    @Override
    public void Iniciar(){
        this.vida=80;
        this.nombre="Piedreria";
        this.descripcion_extra="\u001B[34m"+ "se deben recolectar recursos: piedra"+"\u001B[0m";
        this.isRecolectable=true;
        this.costo_piedra=100;
        this.costo_oro=100;
        this.capacidad_recursos_max=1600;
        this.recurso=0;
        this.cooldown=1;
        listaEdificaciones lista = listaEdificaciones.getInstance();
        lista.anniadir(this);
    }
    @Override
    public void almacenar(){
        if(this.recurso+500>this.capacidad_recursos_max){
            System.out.println("Se ha alcanzado el maximo de recursos en "+nombre);
            return;
        }
        recurso+=500;
    }
    public centro_Mando recolectar(centro_Mando cm){
        if(cm.piedra_jugador+recurso<cm.max_piedra){
            cm.operar_Piedra_jugador(recurso);
            recurso=0;
            cm.flagRecolectar=0;
            return cm;
        }
        System.out.println("Se ha alcanzado el maximo de piedra!!");
        return cm;
    }
    @Override
    public Milicia crearSoldado(Raza raza){
        return null;
    }
    @Override
    public centro_Mando generar(centro_Mando cm){
        return cm;
    }
}
