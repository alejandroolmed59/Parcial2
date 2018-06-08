/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package parcial2;

import AbstractFactory.AbstractFactory;
import AbstractFactory.FactoryProducer;
import edificacion.Edificacion;
import java.util.InputMismatchException;
import java.util.Scanner;
import raza.Raza;

/**
 *
 * @author Alejandro Olmedo <00097017@uca.edu.sv>
 */
public class Juego {

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
        while (true) {
            System.out.println("Turno de "+jugador1.nombre_jugador);
            menu(jugador1);
            System.out.println("Turno de "+jugador2.nombre_jugador);
            menu(jugador2);
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
        System.out.println("9. Pasar turno y recolertar materiales");
        System.out.println("");
    }

    public void menu(Jugador J) {
        int opcion = 0;
        Scanner leer = new Scanner(System.in);
        while (opcion != 9) {
            opciones();
            try {
                opcion = leer.nextInt();
                switch (opcion) {
                    case 1:
                        J.civilizacion.Atacar_Fortaleza();
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
                }
            } catch (InputMismatchException e) {
                System.err.println("Por favor, Ingrese un número");
                leer.nextLine();
            }
        }
    }
}
