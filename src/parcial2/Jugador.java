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
import java.util.HashMap;
import java.util.Map;
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
    protected ArrayList<Edificacion> listaEdificiosJugador = new ArrayList<>();
    protected Map<Edificacion, Integer> mapadeEspera = new HashMap<>();
    protected ArrayList<Milicia> listaMiliciaJugador = new ArrayList<>();
    protected ArrayList<ArrayList<Integer>> listaDefensas = new ArrayList<>();

    public Jugador() {
        AbstractFactory factory;
        factory = FactoryProducer.getFactory("Raza");
        Scanner leer = new Scanner(System.in);
        System.out.println("Digite su nombre");
        nombre_jugador = leer.nextLine();
        System.out.println("-------Eliga una raza---------\n");
        listaRazas lista = listaRazas.getInstance();
        lista.mostrar();
        civilizacion = factory.getRaza(leer.nextInt());
        civilizacion.Iniciar();
        centro_mando = new centro_Mando(civilizacion.oroBonus, civilizacion.piedraBonus, civilizacion.comidaBonus, civilizacion.vida_centroMandoBonus);
    }

    public void construir(int fase) {
        listaEdificaciones lista = listaEdificaciones.getInstance();
        lista.mostrar();
        Scanner leer = new Scanner(System.in);
        AbstractFactory factory;
        factory = FactoryProducer.getFactory("Edificacion");
        Edificacion e = factory.getEdificacion(leer.nextInt());
        e.Iniciar();
        if(e.nombre=="Castillo" && centro_mando.numeroDeMejora<3){
            System.out.println("El castillo solo se puede construir en la edad de los Castillos!");
            return;
        }
        if(e.nombre=="\u001B[33m"+"MARAVILLA"+"\u001B[0m" && centro_mando.numeroDeMejora<4){
            System.out.println("La MARAVILLA solo se puede construir en la edad imperial!");
            return;
        }
        if (centro_mando.getComida_jugador() >= e.costo_comida && centro_mando.getOro_jugador() >= e.costo_oro && centro_mando.getPiedra_jugador() >= e.costo_piedra) {
            centro_mando.operar_Comida_jugador(-e.costo_comida);
            centro_mando.operar_Oro_jugador(-e.costo_oro);
            centro_mando.operar_Piedra_jugador(-e.costo_piedra);
            int espera= (e.cooldown+fase);
            //listaEdificiosJugador.add(e);
            if(espera==0){
                listaEdificiosJugador.add(e);
            }else{
            mapadeEspera.put(e, espera);
            System.out.println("Se empezo a construir la edificacion, estara lista dentro de "+e.cooldown+" fase/s, es decir la fase "+(espera));
            }
        } else {
            System.out.println("Recursos insuficientes");
        }
    }

    public void recolectar() {
        for (Edificacion e : listaEdificiosJugador) {
            centro_mando = e.recolectar(centro_mando);
        }
    }

    public void crear(String tipo) {
        switch (tipo) {
            case "Milicia":
                for (Edificacion e : listaEdificiosJugador) {
                    if (e.nombre == "Cuartel") {
                        Milicia m = e.crearSoldado(civilizacion);
                        if (m.getTipo() == "Soldado" && centro_mando.getComida_jugador() >= civilizacion.costo_comida_soldado && centro_mando.getOro_jugador() >= civilizacion.costo_oro_soldado) {
                            listaMiliciaJugador.add(m);
                            centro_mando.operar_Comida_jugador(-civilizacion.costo_comida_soldado);
                            centro_mando.operar_Oro_jugador(-civilizacion.costo_oro_soldado);
                        } else if (m.getTipo() == civilizacion.especialista && centro_mando.getComida_jugador() >= civilizacion.costo_comida_especialista && centro_mando.getOro_jugador() >= civilizacion.costo_oro_especialista && centro_mando.getPiedra_jugador() >= civilizacion.costo_piedra_especialista) {
                            for (Milicia mili : listaMiliciaJugador) {
                                if (mili.getTipo() == civilizacion.especialista) {
                                    System.out.println("NO puedes tener dos " + civilizacion.especialista + " a la vez!!!");
                                    return;
                                }
                            }
                            listaMiliciaJugador.add(m);
                            centro_mando.operar_Comida_jugador(-civilizacion.costo_comida_especialista);
                            centro_mando.operar_Oro_jugador(-civilizacion.costo_oro_especialista);
                            centro_mando.operar_Piedra_jugador(-civilizacion.costo_piedra_especialista);
                        } else {
                            System.out.println("No tiene recursos suficientes para crear esta unidad");
                        }
                        return;
                    }
                }
                System.out.println("No cuenta con la edificacion necesaria para hacer esta accion");
                return;

        }

    }

    public void atacar(ArrayList<Edificacion> edificios_enemigos) {
        if (edificios_enemigos.isEmpty()) {
            System.out.println("El enemigo no posee edificaciones. Es buen momento para atacar" + "\u001B[1;31m" + " el centro de mando!!" + "\u001B[0m");
            return;
        }
        int i = 1;
        int opcion = 0;
        int opcion2 = 0;
        Scanner leer = new Scanner(System.in);
        Scanner leer2 = new Scanner(System.in);
        System.out.println("\u001B[1;31m" + "¡Edificaciones enemigas!" + "\u001B[0m");
        for (Edificacion e : edificios_enemigos) {
            System.out.println(i + " " + e.nombre + " " + e.vida);
            i++;
        }
        System.out.println("Cual atacara?");
        opcion = leer.nextInt() - 1;
        System.out.println("A quienes mandaras al ataque?");
        mostrarMiliciaJugador();
        if (listaMiliciaJugador.size() != 0) {
            opcion2 = leer2.nextInt() - 1;
            //edificios_enemigos.get(opcion).vida-=listaMiliciaJugador.get(opcion2).getAtaque();
            edificios_enemigos.get(opcion).atacantes.add(listaMiliciaJugador.get(opcion2));
            listaMiliciaJugador.remove(opcion2);
            System.out.println("Atacaras a " + edificios_enemigos.get(opcion).nombre + " al principio de tu siguiente turno" + " ,vida=" + edificios_enemigos.get(opcion).vida);
            return;
        }
        System.out.println("No tienes milicia preparada para atacar!");
        return;
    }

    public void atacarCentrodeMando(ArrayList<Edificacion> edificios_enemigos, centro_Mando cm_enemigo) {
        if (edificios_enemigos.isEmpty()) {
            int opcion = 0;
            Scanner leer = new Scanner(System.in);
            System.out.println("A quienes mandaras al ataque?");
            mostrarMiliciaJugador();
            if (listaMiliciaJugador.size() != 0) {
                opcion = leer.nextInt() - 1;
                cm_enemigo.atacantes.add(listaMiliciaJugador.get(opcion));
                listaMiliciaJugador.remove(opcion);
                System.out.println("Atacaras al centro de mando al principio de tu siguiente turno " + " ,vida=" + cm_enemigo.getVida());
                return;
            }
            System.out.println("No tienes milicia preparada para atacar!");
            return;
        } else {
            System.out.println("\u001B[1;31m" + "NO puedes atacar al centro de mando si el enemigo tiene otras edificaciones!!" + "\u001B[0m");
        }
    }

    public void defender() {
        int opcion = 0;
        int opcion1 = 0;
        int opcion2 = 0;
        int i = 1;
        Scanner leer = new Scanner(System.in);
        Scanner leer2 = new Scanner(System.in);
        mostrarEdificiosJugador();
        System.out.println("¿A que edificio mandara tropas a defender?");
        opcion = leer.nextInt() - 1;
        if(listaEdificiosJugador.get(opcion).atacantes.isEmpty()){
            System.out.println("No estas siendo atacado");
            return;
        }
        System.out.println("¿A que unidad atacara");
        for (Milicia m : listaEdificiosJugador.get(opcion).atacantes) {
            System.out.println(i + " " + m.toString());
            i++;
        }
        opcion1 = leer.nextInt() - 1;
        System.out.println("¿A que unidad mandara?");
        mostrarMiliciaJugador();
        opcion2 = leer2.nextInt() - 1;
        if(listaMiliciaJugador.get(opcion2).getFlagAtaque()==0){
            listaEdificiosJugador.get(opcion).atacantes.get(opcion1).operar_Vida_milicia(-listaMiliciaJugador.get(opcion2).getAtaque());
            listaMiliciaJugador.get(opcion2).operar_Vida_milicia(-listaEdificiosJugador.get(opcion).atacantes.get(opcion1).getAtaque());
            listaMiliciaJugador.get(opcion2).setFlagAtaque(1);
        }else{
            System.out.println("\u001B[1;31m"+"Una unidad no puede ir a la batalla dos veces en el mismo turno!!"+"\u001B[0m");
        }
               
        if (listaEdificiosJugador.get(opcion).atacantes.get(opcion1).getVida() <= 0) {
            System.out.println("Has eliminado a la unidad enemiga");
            listaEdificiosJugador.get(opcion).atacantes.remove(opcion1);
        }
        if (listaMiliciaJugador.get(opcion2).getVida() <= 0) {
            System.out.println("Han eliminado a tu unidad");
            listaMiliciaJugador.remove(opcion2);
        }
    }
    public void defenderCentrodeMando(){
        int opcion = 0;
        int opcion1 = 0;
        int i = 1;
        Scanner leer = new Scanner(System.in);
        Scanner leer2 = new Scanner(System.in);
        System.out.println("¿A que unidad atacara?");
        for(Milicia m: centro_mando.atacantes){
            System.out.println(i + " " + m.toString());
            i++;
        }
        opcion= leer.nextInt()-1;
        System.out.println("¿A que unidad mandara?");
        mostrarMiliciaJugador();
        opcion1 = leer2.nextInt() - 1;
        if(listaMiliciaJugador.get(opcion1).getFlagAtaque()==0){
            centro_mando.atacantes.get(opcion).operar_Vida_milicia(-listaMiliciaJugador.get(opcion1).getAtaque());
            listaMiliciaJugador.get(opcion1).operar_Vida_milicia(-centro_mando.atacantes.get(opcion).getAtaque());
            listaMiliciaJugador.get(opcion1).setFlagAtaque(1);
        }else{
            System.out.println("\u001B[1;31m"+"Una unidad no puede ir a la batalla dos veces en el mismo turno!!"+"\u001B[0m");
        }
               
        if (centro_mando.atacantes.get(opcion).getVida() <= 0) {
            System.out.println("Has eliminado a la unidad enemiga");
            centro_mando.atacantes.remove(opcion);
        }
        if (listaMiliciaJugador.get(opcion1).getVida() <= 0) {
            System.out.println("Han eliminado a tu unidad");
            listaMiliciaJugador.remove(opcion1);
        }
    }

    public void mostrarEdificiosJugador() {
        int i = 1;
        for (Edificacion e : listaEdificiosJugador) {
            System.out.println(i + " " + e.nombre + " ,vida:" + e.vida + " atacantes=" + e.atacantes.toString());
            i++;
        }
    }

    public void mostrarMiliciaJugador() {
        int i = 1;
        for (Milicia m : listaMiliciaJugador) {
            System.out.println(i + " " + m.toString());
            i++;
        }
    }

    public void isAlive() {
        ArrayList<Edificacion> temp = new ArrayList();
        for (Edificacion e : listaEdificiosJugador) {
            if (e.vida <= 0) {
                System.out.println("Han destruido tu " + e.nombre + " " + nombre_jugador);
                temp.add(e);
            }
        }
        for (Edificacion e : temp) {
            listaEdificiosJugador.remove(e);
        }
    }
}
