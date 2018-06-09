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
public class Granja extends Edificacion {
    @Override
    public void Iniciar(){
        this.vida=70;
        this.costo_comida=500;
        this.costo_oro=200;
        this.nombre="Granja";
        this.cooldown=0;
        listaEdificaciones lista = listaEdificaciones.getInstance();
        lista.anniadir(this);
    }
    public centro_Mando recolectar(centro_Mando cm){
        if(cm.comida_jugador+1000<cm.max_comida){
            cm.operar_Comida_jugador(1000);
            return cm;
        }
        System.out.println("Se a alcanzado el maximo de comida!!");   
        return cm;
    }
    
    @Override
    public Milicia crearSoldado(Raza raza){
        return null;
    }
}
