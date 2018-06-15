/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package parcial2;

import AbstractFactory.AbstractFactory;
import AbstractFactory.FactoryProducer;
import Vehiculo.Vehiculo;
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
        Raza japo = factory.getRaza(3);
        maya.Iniciar();
        teu.Iniciar();
        japo.Iniciar();
        factory = FactoryProducer.getFactory("Edificacion");
        Edificacion c = factory.getEdificacion(1);
        Edificacion g = factory.getEdificacion(2);
        Edificacion m = factory.getEdificacion(3);
        Edificacion p = factory.getEdificacion(4);
        Edificacion taller_vehiculos = factory.getEdificacion(5);
        Edificacion cas = factory.getEdificacion(6);
        Edificacion mar = factory.getEdificacion(7);
        factory = FactoryProducer.getFactory("Vehiculo");
        Vehiculo t = factory.getVehiculo(1);
        Vehiculo cannion = factory.getVehiculo(2);
        c.Iniciar();
        g.Iniciar();
        m.Iniciar();
        p.Iniciar();
        taller_vehiculos.Iniciar();
        cas.Iniciar();
        mar.Iniciar();
        t.Iniciar();
        cannion.Iniciar();
        System.out.println("\u001B[31m" + "----AGE OF EMPIRES 2: JAVA TEXT EDITION----" + "\u001B[0m");
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

    /**
     * Donde ocurre la ejecucion principal del juego, al principio de los turnos
     * de los jugadores se ataca, se construye en caso de ser posible y al final
     * de resetean las flags para defender y recolectar recursos. En caso de que
     * la flag de juego se setee a 1, significa que un jugador gano
     */
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

            resetFlagdeDefensa(listaPlayers.get(0));
            resetFlagdeDefensa(listaPlayers.get(1));
            fase++;
        }
    }

    /**
     * Printeo del menu principal
     */
    public void opciones() {
        System.out.println("");
        System.out.println("----- MENÚ -----");
        System.out.println("");
        System.out.println("1. Atacar");
        System.out.println("2. Defender Edificio");
        System.out.println("3. Entrenar a tu milicia");
        System.out.println("4. Construir vehiculos");
        System.out.println("5. Construir edificios");
        System.out.println("6. Ver milicia");
        System.out.println("7. Ver vehiculos");
        System.out.println("8. Ver edificios");
        System.out.println("9. Ver estado del centro de mando");
        System.out.println("10. Recolectar recursos");
        System.out.println("11. Avanzar de edad");
        System.out.println("12. Defender centro de mando");
        System.out.println("\u001B[35m" + "13. Atacar centro de mando" + "\u001B[0m");
        System.out.println("14. Pasar turno y generar recursos");
        System.out.println("");
    }

    /**
     * Se mandan a llamar todas las funciones principales del programa
     *
     * @param J Necesario para saber quien esta jugando
     * @param edificios_enemigos Se necesita para atacar al enemigo
     * @param cm_enemigo Se necesita para atacar al enemigo
     */
    public void menu(Jugador J, ArrayList<Edificacion> edificios_enemigos, centro_Mando cm_enemigo) {
        int opcion = 0;
        Scanner leer = new Scanner(System.in);
        while (opcion != 14) {
            opciones();
            try {
                opcion = leer.nextInt();
                switch (opcion) {
                    case 1:
                        J.atacar(edificios_enemigos);
                        break;
                    case 2:
                        J.defender();

                        break;
                    case 3:
                        J.crear("Milicia", fase);
                        break;
                    case 4:
                        J.crear("Vehiculo", fase);
                        break;
                    case 5:
                        J.construir(fase);
                        break;
                    case 6:
                        J.mostrarMiliciaJugador();
                        break;
                    case 7:
                        J.mostrarVehiculosJugador();
                        break;
                    case 8:
                        J.mostrarEdificiosJugador();
                        break;
                    case 9:
                        System.out.println(J.centro_mando.toString());
                        break;
                    case 10:
                        J.recolectar();
                        break;
                    case 11:
                        J.centro_mando.mejorar();
                        break;
                    case 12:
                        J.defenderCentrodeMando();
                        break;
                    case 13:
                        J.atacarCentrodeMando(edificios_enemigos, cm_enemigo);
                        break;
                    case 14:
                        J.generar();
                        break;
                }
            } catch (InputMismatchException e) {
                System.err.println("Por favor, Ingrese un número");
                leer.nextLine();
            }
        }
    }

    /**
     * Shuffle hace que el jugador que empieza sea aleatorio
     */
    public void turno() {
        listaPlayers.add(jugador1);
        listaPlayers.add(jugador2);
        Collections.shuffle(listaPlayers);
    }

    /**
     * En caso de que hayan Atacantes en las listas y que ya hayan pasado 2
     * turnos, se ataca
     *
     * @param j Para saber quien ataca
     * @param listaEdificiosJugador_enemigo
     * @param cm_enemigo
     */
    public void ataqueInicioTurno(Jugador j, ArrayList<Edificacion> listaEdificiosJugador_enemigo, centro_Mando cm_enemigo) {
        int flagHechiza = 0;
        for (Edificacion e : listaEdificiosJugador_enemigo) {
            if (e.atacantes.size() != 0) {
                for (Milicia m : e.atacantes) {
                    if (m.getFlagAtaque() == 0) {
                        flagHechiza = 1;
                        e.vida -= m.getAtaque();
                    } else {
                        m.setFlagAtaque(0);
                    }

                }
            }
            if (e.atacantes_Vehiculo.size() != 0) {
                for (Vehiculo v : e.atacantes_Vehiculo) {
                    if (v.getFlagAtaque() == 0) {
                        flagHechiza = 1;
                        e.vida -= v.ataque;
                    } else {
                        v.setFlagAtaque(0);
                    }
                }
            }
            if (flagHechiza == 1) {
                System.out.println("Tus unidades atacaran ahora! " + j.nombre_jugador);
            }
        }
        for (Milicia m : cm_enemigo.atacantes) {
            if (m.getFlagAtaque() == 0) {
                cm_enemigo.operar_Vida_jugador(-m.getAtaque());
            } else {
                m.setFlagAtaque(0);
            }

        }
        for (Vehiculo v : cm_enemigo.atacantes_Vehiculo) {
            if (v.getFlagAtaque() == 0) {
                cm_enemigo.operar_Vida_jugador(-v.ataque);
            } else {
                v.setFlagAtaque(0);
            }
        }
        if (cm_enemigo.getVida() <= 0) {
            flag_FindeJuego = 1;
        }
    }

    /**
     * Se ejecutara al principio de turno para resetear la flag de defensa de
     * las milicias y vehiculos y para la flag de recolectar del centro de mando
     *
     * @param j A cual jugador se le resetearan las flags
     */
    public void resetFlagdeDefensa(Jugador j) {
        j.centro_mando.setFlagRecolectar(1);
        for (Milicia m : j.listaMiliciaJugador) {
            m.setFlagDefensa(0);
            m.setFlagAtaqueV2(0);
        }
        for (Vehiculo v : j.listaVehiculoJugador) {
            v.setFlagDefensa(0);
            v.setFlagAtaqueV2(0);
        }
    }

    /**
     * En caso de que haya pasado ya las fases necesarias se crea la edificacion
     * correspondiente
     *
     * @param j Para saber que jugador se va a intentar construir
     */
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
        i = 0;
        for (Map.Entry<Vehiculo, Integer> entry : j.mapadeEspera_Vehiculo.entrySet()) {
            if (entry.getValue() == fase) {
                j.listaVehiculoJugador.add(entry.getKey());
                System.out.println("Se ha terminado de construir " + entry.getKey().nombre + " , " + j.nombre_jugador);
                i++;
            }
        }
    }
}
