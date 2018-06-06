/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package parcial2;
import AbstractFactory.AbstractFactory;
import AbstractFactory.FactoryProducer;
import edificacion.Edificacion;
import edificacion.centro_Mando;
import edificacion.listaEdificaciones;
import java.util.Scanner;
import java.util.ArrayList;
import raza.Raza;
import raza.Milicia;
import raza.listaRazas;

/**
 *
 * @author Alejandro Olmedo <00097017@uca.edu.sv>
 */
public class Jugador {
    public String nombre_jugador;
    protected Raza civilizacion;
    protected centro_Mando centro_mando;
    protected ArrayList<Edificacion> listaEdificiosJugador= new ArrayList<>();
    protected ArrayList<Milicia> listaMiliciaJugador= new ArrayList<>();

    public Jugador() {
        AbstractFactory factory;
        factory = FactoryProducer.getFactory("Raza");
        Scanner leer = new Scanner(System.in);
        System.out.println("Eliga una raza");
        listaRazas lista= listaRazas.getInstance();
        lista.mostrar();
        civilizacion= factory.getRaza(leer.next());
        civilizacion.Iniciar();
        System.out.println(civilizacion.especialista);
        centro_mando= new centro_Mando(civilizacion.oroBonus, civilizacion.piedraBonus, civilizacion.comidaBonus, civilizacion.vida_centroMandoBonus);
    }
    public void construir(){
        listaEdificaciones lista = listaEdificaciones.getInstance();
        lista.mostrar();
        Scanner leer = new Scanner(System.in);
        AbstractFactory factory;
        factory = FactoryProducer.getFactory("Edificacion");
        Edificacion e= factory.getEdificacion(leer.next());
        e.Iniciar();
        if(centro_mando.getComida_jugador()>=e.costo_comida && centro_mando.getOro_jugador()>=e.costo_oro && centro_mando.getPiedra_jugador()>= e.costo_piedra){
            centro_mando.operar_Comida_jugador(-e.costo_comida);
            centro_mando.operar_Oro_jugador(-e.costo_oro);
            centro_mando.operar_Piedra_jugador(-e.costo_piedra);
            listaEdificiosJugador.add(e);
            System.out.println("Se empezo a construir la edificacion");
        }else{
            System.out.println("Recursos insuficientes");
        }
    }
    public void recolectar(){
        for(Edificacion e: listaEdificiosJugador){
            centro_mando= e.recolectar(centro_mando);
        }
    }
    public void crear(String tipo){
        switch(tipo){
            case "Milicia":
                for(Edificacion e: listaEdificiosJugador){
                    if(e.nombre=="Cuartel"){
                        Milicia m=e.crearSoldado(civilizacion);
                        listaMiliciaJugador.add(m);
                        return;
                    }
                }
                System.out.println("No cuenta con la edificacion necesaria para hacer esta accion");
                return;
                
        }
                
    }
    public void mostrarEdificiosJugador(){
        int i=1;
        for(Edificacion e: listaEdificiosJugador){
            System.out.println(i+""+e.nombre);
            i++;
        }
    }
    public void mostrarMiliciaJugador(){
        int i=1;
        for(Milicia m: listaMiliciaJugador){
            System.out.println(i+""+m.toString());
            i++;
        }
    }
 
}
