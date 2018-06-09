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
import java.util.ArrayList;
import java.util.Collections;
import java.util.InputMismatchException;
import java.util.Scanner;
import raza.Milicia;
import raza.Raza;

/**
 *
 * @author Alejandro Olmedo <00097017@uca.edu.sv>
 */
public class Juego {
    int flag_FindeJuego=0;
    ArrayList<Jugador> listaPlayers = new ArrayList<>();    
    private static Juego Obj;
    private Jugador jugador1;
    private Jugador jugador2;
    private int fase = 0;

    private Juego() {
        AbstractFactory factory;
        factory = FactoryProducer.getFactory("Raza");
        Raza maya = factory.getRaza("Teuton");
        Raza teu = factory.getRaza("Maya");
        teu.Iniciar();
        maya.Iniciar();
        factory = FactoryProducer.getFactory("Edificacion");
        Edificacion c = factory.getEdificacion("Cuartel");
        Edificacion g = factory.getEdificacion("Granja");
        Edificacion m = factory.getEdificacion("Mina");
        Edificacion p = factory.getEdificacion("Piedreria");
        c.Iniciar();
        g.Iniciar();
        m.Iniciar();
        p.Iniciar();
        jugador1 = new Jugador();
        jugador2 = new Jugador();
    }

    public static Juego getInstance() {
        if (Obj == null) {
            Obj = new Juego();
        }
        return Obj;
    }

    public void Jugar() {
        turno();
        System.out.println("Empezaras tu "+listaPlayers.get(0).nombre_jugador);
        while (true) {
            System.out.println("\u001B[32m"+"Inicia la fase "+fase+"\u001B[0m");
            System.out.println("Turno de "+"\u001B[31m"+listaPlayers.get(0).nombre_jugador+"\u001B[0m"+"!");
            menu(listaPlayers.get(0), listaPlayers.get(1).listaEdificiosJugador, listaPlayers.get(1).centro_mando);
            System.out.println("Turno de "+"\u001B[34m"+listaPlayers.get(1).nombre_jugador+"\u001B[0m"+"!");
            menu(listaPlayers.get(1), listaPlayers.get(0).listaEdificiosJugador, listaPlayers.get(0).centro_mando);
            
            System.out.println("\n"+"\u001B[1;31m"+"Las unidades atacaran ahora!"+"\u001B[0m");
            ataqueFinaldeFase(listaPlayers.get(0).listaEdificiosJugador, listaPlayers.get(0).centro_mando);
            if(flag_FindeJuego==1){
                System.out.println("\u001B[1;34m"+listaPlayers.get(1).nombre_jugador+" HA GANADO LA PARTIDA"+"\u001B[0m");
                return;
            }
            ataqueFinaldeFase(listaPlayers.get(1).listaEdificiosJugador, listaPlayers.get(1).centro_mando);
            if(flag_FindeJuego==1){
                System.out.println("\u001B[1;31m"+listaPlayers.get(0).nombre_jugador+" HA GANADO LA PARTIDA"+"\u001B[0m");
                return;
            }
            
            listaPlayers.get(0).isAlive();
            listaPlayers.get(1).isAlive();            
            fase++;
        }
    }

    public void opciones() {
        System.out.println("");
        System.out.println("----- MENÚ -----");
        System.out.println("");
        System.out.println("1. Atacar");
        System.out.println("2. Avanzar de edad(mejorar Centro de mando)");
        System.out.println("3. Entrenar a tu milicia");
        System.out.println("4. Construir edificios");
        System.out.println("5. Construir vehiculos");
        System.out.println("6. Ver milicia");
        System.out.println("7. Ver edificios");
        System.out.println("8. Ver estado de centro de mando");
        System.out.println("9. Pasar turno y recolectar materiales");
        System.out.println("10. Defender edificio");
        System.out.println("\u001B[35m"+"11. Atacar centro de mando"+"\u001B[0m");
        System.out.println("");
    }

    public void menu(Jugador J, ArrayList<Edificacion> edificios_enemigos, centro_Mando cm_enemigo) {
        int opcion = 0;
        Scanner leer = new Scanner(System.in);
        while (opcion != 9) {
            opciones();
            try {
                opcion = leer.nextInt();
                switch (opcion) {
                    case 1:
                        J.atacar(edificios_enemigos);
                        break;
                    case 2:
                        J.centro_mando.mejorar();
                        break;
                    case 3:
                        J.crear("Milicia");
                        break;
                    case 4:
                        J.construir();
                        break;
                    case 5:
                        //J.crear("Vehiculo"); FALTA ANNIADIR
                        break;
                    case 6:
                        J.mostrarMiliciaJugador();
                        break;
                    case 7:
                        J.mostrarEdificiosJugador();
                        break;
                    case 8:
                        System.out.println(J.centro_mando.toString());
                        break;
                    case 9:
                        J.recolectar();
                        break;
                    case 10:
                        J.defender();
                        break;
                    case 11:
                        J.atacarCentrodeMando(edificios_enemigos, cm_enemigo);
                        break;
                }
            } catch (InputMismatchException e) {
                System.err.println("Por favor, Ingrese un número");
                leer.nextLine();
            }
        }
    }
    public void turno(){
        listaPlayers.add(jugador1);
        listaPlayers.add(jugador2);
        Collections.shuffle(listaPlayers);
    }
    public void ataqueFinaldeFase(ArrayList<Edificacion> listaEdificiosJugador, centro_Mando cm){
        for(Edificacion e: listaEdificiosJugador){
            if(e.atacantes.size()!=0){
                for(Milicia m: e.atacantes){
                    e.vida-=m.getAtaque();
                }
            }
        }
        for(Milicia m: cm.atacantes){
            cm.operar_Vida_jugador(-m.getAtaque());
            if (cm.getVida()<=0){
                flag_FindeJuego=1;
            }
        }
    }
}
