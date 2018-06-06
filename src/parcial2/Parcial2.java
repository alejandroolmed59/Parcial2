/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package parcial2;
import AbstractFactory.AbstractFactory;
import AbstractFactory.FactoryProducer;
import edificacion.Edificacion;
import raza.FactoryRaza;
import raza.Milicia;
import raza.Raza;
import edificacion.centro_Mando;
import raza.listaRazas;
/**
 *
 * @author Alejandro Olmedo <00097017@uca.edu.sv>
 */
public class Parcial2 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        AbstractFactory factory;
        factory = FactoryProducer.getFactory("Raza");
        Raza maya= factory.getRaza("Teuton");
        Raza teu= factory.getRaza("Maya");
        teu.Iniciar();
        maya.Iniciar();
        factory=FactoryProducer.getFactory("Edificacion");
        Edificacion c= factory.getEdificacion("Cuartel");
        Edificacion g= factory.getEdificacion("Granja");
        Edificacion m= factory.getEdificacion("Mina");
        Edificacion p= factory.getEdificacion("Piedreria");
        c.Iniciar();
        g.Iniciar();
        m.Iniciar();
        p.Iniciar();
        Jugador jugador1= new Jugador();        
        System.out.println(jugador1.civilizacion.raza);
        System.out.println(jugador1.centro_mando.toString());
        jugador1.construir();
        jugador1.recolectar();
        
        //jugador1.crear("Milicia");
        System.out.println(jugador1.centro_mando.toString());
        jugador1.construir();  
        jugador1.recolectar();  
        System.out.println(jugador1.centro_mando.toString());
        jugador1.mostrarEdificiosJugador();
    }
    
}
