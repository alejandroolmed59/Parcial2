/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edificacion;

import Vehiculo.Vehiculo;
import java.util.ArrayList;
import raza.Milicia;
import raza.Raza;

/**
 *
 * @author Alejandro Olmedo <00097017@uca.edu.sv>
 */
public abstract class Edificacion {
    public ArrayList<Milicia> atacantes= new ArrayList();
    public ArrayList<Vehiculo> atacantes_Vehiculo= new ArrayList();
    public int vida=0, cooldown=0;
    public boolean isRecolectable=false;
    public String nombre="", descripcion_extra="";
    public double costo_oro=0, costo_piedra=0, costo_comida=0,capacidad_recursos_max, recurso;
    abstract public void Iniciar(); //Se inicializa la edificacion y se setean las stats
    abstract public centro_Mando recolectar(centro_Mando cm); //Recolecta manual 1 vez por turno
    abstract public centro_Mando generar(centro_Mando cm); //Se utilizara para modificar automaticamente al final del turno los valores de los recursos en el CENTRO DE MANDO
    abstract public Milicia crearSoldado(Raza raza); //Se usa en los cuarteles para crear milicia
    abstract public void almacenar(); //Se utilizara para modificar automaticamente al final del turno los valores de los recursos en LAS EDIFICACIONES

    @Override
    public String toString() {
        if(isRecolectable==true){
            return nombre + " ,vida:" + vida +" recursos dentro: "+recurso +" Milicia atacantes=" + atacantes.toString()+" Vehiculos atacantes= "+atacantes_Vehiculo.toString();
        }
        return nombre + " ,vida:" + vida +" Milicia atacantes=" + atacantes.toString()+" Vehiculos atacantes= "+atacantes_Vehiculo.toString();   
    }
}
        
