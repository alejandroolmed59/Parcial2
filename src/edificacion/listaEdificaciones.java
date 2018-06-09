/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edificacion;

import java.util.ArrayList;


/**
 *
 * @author Alejandro Olmedo <00097017@uca.edu.sv>
 */
public class listaEdificaciones {
    private static listaEdificaciones Obj;
    private ArrayList<Edificacion> listaEdificaciones=null;
    
    private listaEdificaciones(){
        listaEdificaciones=new ArrayList<Edificacion>();
    }

    public static listaEdificaciones getInstance() {
        if (Obj == null) {
            Obj = new listaEdificaciones();
        }
        return Obj;
    }
    public ArrayList<Edificacion> getArray(){
        return this.listaEdificaciones;
    }
    public void anniadir(Edificacion e){
        for(Edificacion Edi: listaEdificaciones){
            if(Edi.nombre==e.nombre){
                return;
            }
        }
        listaEdificaciones.add(e);
    }
    public void mostrar(){
        int i=1;
        System.out.println("-----Edificios disponibles-------");
        for(Edificacion e: listaEdificaciones){
            System.out.println(i+" "+e.nombre+" vida: "+e.vida+". Costo: Oro="+e.costo_oro+" Piedra= "+e.costo_piedra+" Comida= "+e.costo_comida+" Tiempo de espera="+e.cooldown+" "+e.edad_minima);
            i++;
        }
    }
}
