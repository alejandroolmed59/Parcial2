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
public class Taller_vehiculos extends Edificacion{
    @Override
    public void Iniciar(){
        this.vida=250;
        this.nombre="Taller de maquinas de asedio";
        this.descripcion_extra="\u001B[1;31m"+"Exclusivo de la edad feudal"+"\u001B[0m";
        this.costo_comida=900;
        this.costo_piedra=400;
        this.costo_oro=200;
        this.cooldown=2;
        listaEdificaciones lista = listaEdificaciones.getInstance();
        lista.anniadir(this);
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
    public centro_Mando generar(centro_Mando cm){
        return cm;
    }
        @Override
    public void almacenar(){
    }
}
