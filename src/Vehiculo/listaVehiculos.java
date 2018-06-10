/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Vehiculo;

import java.util.ArrayList;

/**
 *
 * @author Alejandro Olmedo <00097017@uca.edu.sv>
 */
public class listaVehiculos {
    private static listaVehiculos Obj;
    private ArrayList<Vehiculo> listaVehiculos=null;
    
    private listaVehiculos(){
        listaVehiculos=new ArrayList<Vehiculo>();
    }

    public static listaVehiculos getInstance() {
        if (Obj == null) {
            Obj = new listaVehiculos();
        }
        return Obj;
    }
    public ArrayList<Vehiculo> getArray(){
        return this.listaVehiculos;
    }
    public void anniadir(Vehiculo v){
        for(Vehiculo Vehi: listaVehiculos){
            if(Vehi.nombre==v.nombre){
                return;
            }
        }
        listaVehiculos.add(v);
    }
    public void mostrar(){
        int i=1;
        System.out.println("-----Vehiculos disponibles-------");
        for(Vehiculo v: listaVehiculos){
            System.out.println(i+" "+v.nombre+" ataque: "+v.ataque+" vida: "+v.vida+". Costo: Oro="+v.costo_oro+" Piedra= "+v.costo_piedra+" Comida= "+v.costo_comida+" Tiempo de espera="+v.cooldown+" "+v.descripcion_extra);
            i++;
        }
    }
}
