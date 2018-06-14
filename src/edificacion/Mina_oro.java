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
public class Mina_oro extends Edificacion{
    @Override
    public void Iniciar(){
        this.vida=100;
        this.nombre="Mina de oro";
        this.descripcion_extra="\u001B[34m"+"se deben recolectar recursos: oro"+"\u001B[0m";
        this.isRecolectable= true;
        this.capacidad_recursos_max=1000;
        this.recurso=0;
        this.costo_comida=500;
        this.costo_piedra=350;
        this.cooldown=2;
        listaEdificaciones lista = listaEdificaciones.getInstance();
        lista.anniadir(this);
    }
       @Override
    public void almacenar(){
        if(this.recurso+300>this.capacidad_recursos_max){
            System.out.println("Se ha alcanzado el maximo de recursos en "+nombre);
            return;
        }
        recurso+=300;
    }
    @Override
    public centro_Mando recolectar(centro_Mando cm){
        if(cm.oro_jugador+recurso<cm.max_oro){
            cm.operar_Oro_jugador(recurso);
            cm.flagRecolectar=0;
            return cm;
        }
        System.out.println("Se ha alcanzado el maximo de Oro!!");
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
