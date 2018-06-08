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
        this.costo_piedra=100;
        this.costo_oro=100;
        listaEdificaciones lista = listaEdificaciones.getInstance();
        lista.anniadir(this);
    }
    public centro_Mando recolectar(centro_Mando cm){
        if(cm.piedra_jugador+500<cm.max_piedra){
            cm.operar_Piedra_jugador(500);       
            return cm;
        }
        System.out.println("Se ha alcanzado el maximo de Piedra!!");
        return cm;
    }
    @Override
    public Milicia crearSoldado(Raza raza){
        return null;
    }
}
