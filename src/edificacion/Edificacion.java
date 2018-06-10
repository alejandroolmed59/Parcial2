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
    public String nombre="", descripcion_extra="";
    public double costo_oro=0, costo_piedra=0, costo_comida=0;
    abstract public void Iniciar();
    abstract public centro_Mando recolectar(centro_Mando cm);
    abstract public Milicia crearSoldado(Raza raza);

    @Override
    public String toString() {
        return nombre + " ,vida:" + vida + " Milicia atacantes=" + atacantes.toString()+" Vehiculos atacantes= "+atacantes_Vehiculo.toString();
    }
    
}
