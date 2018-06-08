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
        this.vida=70;
        this.nombre="Mina de oro";
        this.costo_oro=150;
        this.costo_piedra=50;
        listaEdificaciones lista = listaEdificaciones.getInstance();
        lista.anniadir(this);
    }
    @Override
    public centro_Mando recolectar(centro_Mando cm){
        if(cm.oro_jugador+300<cm.max_oro){
            cm.operar_Oro_jugador(300);
            return cm;
        }
        System.out.println("Se ha alcanzado el maximo de Oro!!");
        return cm;
    }
    @Override
    public Milicia crearSoldado(Raza raza){
        return null;
    }
}
