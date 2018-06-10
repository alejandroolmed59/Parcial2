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
import java.util.Map;
import java.util.Scanner;
import raza.Milicia;
import raza.Raza;

/**
 *
 * @author Alejandro Olmedo <00097017@uca.edu.sv>
 */
public class Juego {

    int flag_FindeJuego = 0;
    ArrayList<Jugador> listaPlayers = new ArrayList<>();
    private static Juego Obj;
    private Jugador jugador1;
    private Jugador jugador2;
    private int fase = 0;

    private Juego() {
        AbstractFactory factory;
        factory = FactoryProducer.getFactory("Raza");
        Raza maya = factory.getRaza(1);
        Raza teu = factory.getRaza(2);
        maya.Iniciar();
        teu.Iniciar();
        factory = FactoryProducer.getFactory("Edificacion");
        Edificacion c = factory.getEdificacion(1);
        Edificacion g = factory.getEdificacion(2);
        Edificacion m = factory.getEdificacion(3);
        Edificacion p = factory.getEdificacion(4);
        Edificacion cas = factory.getEdificacion(5);
        Edificacion mar = factory.getEdificacion(6);
        c.Iniciar();
        g.Iniciar();
        m.Iniciar();
        p.Iniciar();
        cas.Iniciar();
        mar.Iniciar();
        jugador1 = new Jugador();
        System.out.println("");
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
        System.out.println("Empezaras tu " + listaPlayers.get(0).nombre_jugador);
        while (true) {
            System.out.println("\u001B[32m" + "Inicia la fase " + fase + "\u001B[0m");

            System.out.println("Turno de " + "\u001B[31m" + listaPlayers.get(0).nombre_jugador + "\u001B[0m" + "!");
            construir(listaPlayers.get(0));
            ataqueInicioTurno(listaPlayers.get(0), listaPlayers.get(1).listaEdificiosJugador, listaPlayers.get(1).centro_mando);
            listaPlayers.get(1).isAlive();
            if (flag_FindeJuego == 1) {
                System.out.println("\u001B[1;34m" + listaPlayers.get(0).nombre_jugador + " HA GANADO LA PARTIDA" + "\u001B[0m");
                return;
            }
            menu(listaPlayers.get(0), listaPlayers.get(1).listaEdificiosJugador, listaPlayers.get(1).centro_mando);

            System.out.println("Turno de " + "\u001B[34m" + listaPlayers.get(1).nombre_jugador + "\u001B[0m" + "!");
            construir(listaPlayers.get(1));
            ataqueInicioTurno(listaPlayers.get(1), listaPlayers.get(0).listaEdificiosJugador, listaPlayers.get(0).centro_mando);
            listaPlayers.get(0).isAlive();
            if (flag_FindeJuego == 1) {
                System.out.println("\u001B[1;34m" + listaPlayers.get(1).nombre_jugador + " HA GANADO LA PARTIDA" + "\u001B[0m");
                return;
            }
            menu(listaPlayers.get(1), listaPlayers.get(0).listaEdificiosJugador, listaPlayers.get(0).centro_mando);
           
            resetFlagdeAtaque(listaPlayers.get(0));
            resetFlagdeAtaque(listaPlayers.get(1));
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
        System.out.println("11. Defender centro de mando");
        System.out.println("\u001B[35m" + "12. Atacar centro de mando" + "\u001B[0m");
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
                        J.construir(fase);
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
                        J.defenderCentrodeMando();
                        break;
                    case 12:
                        J.atacarCentrodeMando(edificios_enemigos, cm_enemigo);
                        break;
                }
            } catch (InputMismatchException e) {
                System.err.println("Por favor, Ingrese un número");
                leer.nextLine();
            }
        }
    }

    public void turno() {
        listaPlayers.add(jugador1);
        listaPlayers.add(jugador2);
        Collections.shuffle(listaPlayers);
    }

    public void ataqueInicioTurno(Jugador j, ArrayList<Edificacion> listaEdificiosJugador, centro_Mando cm) {
        int flagHechiza1=0;
        int flagHechiza2=0;
        for (Edificacion e : listaEdificiosJugador) {
            if (e.atacantes.size() != 0) {
                if(flagHechiza1==0){
                    System.out.println("Tus unidades "+j.nombre_jugador+" atacaran a las edificaciones enemigas ahora!");
                    flagHechiza1=1;
                }
                for (Milicia m : e.atacantes) {
                    e.vida -= m.getAtaque();
                }
            }
        }
        for (Milicia m : cm.atacantes) {
            if(flagHechiza2==0){
                System.out.println("Tus unidades "+j.nombre_jugador+" atacaran al centro de mando enemigo ahora!");
                flagHechiza2=1;
            }
            cm.operar_Vida_jugador(-m.getAtaque());
            if (cm.getVida() <= 0) {
                flag_FindeJuego = 1;
            }
        }
    }

    public void resetFlagdeAtaque(Jugador j) {
        for (Milicia m : j.listaMiliciaJugador) {
            m.setFlagAtaque(0);
        }
    }

    public void construir(Jugador j) {
        int i = 0;
        for (Map.Entry<Edificacion, Integer> entry : j.mapadeEspera.entrySet()) {
            if (entry.getValue() == fase) {
                j.listaEdificiosJugador.add(entry.getKey());
                System.out.println("Se ha terminado de construir " + entry.getKey().nombre + " , " + j.nombre_jugador);
                if (entry.getKey().nombre == "\u001B[33m" + "MARAVILLA" + "\u001B[0m") {
                    flag_FindeJuego = 1;
                    return;
                }
                i++;
            }
        }
    }
}
