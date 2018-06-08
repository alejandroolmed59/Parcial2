/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package raza;

import java.util.ArrayList;

/**
 *
 * @author Alejandro Olmedo <00097017@uca.edu.sv>
 */
public class listaRazas {
    private static listaRazas Obj;
    private ArrayList<Raza> listaRaza=null;
    
    private listaRazas(){
        listaRaza=new ArrayList<Raza>();
    }

    public static listaRazas getInstance() {
        if (Obj == null) {
            Obj = new listaRazas();
        }
        return Obj;
    }
    public ArrayList<Raza> getArray(){
        return this.listaRaza;
    }
    public void anniadir(Raza r){
        for(Raza Raz: listaRaza){
            if(Raz.raza==r.raza){
                return;
            }
        }
        listaRaza.add(r);
    }
    public void mostrar(){
        int i=1;
        System.out.println("-----Razas disponibles-------");
        for(Raza r: listaRaza){
            System.out.println(i+" "+r.raza);
            i++;
        }
    }
}
