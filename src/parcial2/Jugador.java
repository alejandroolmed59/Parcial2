/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package parcial2;

import AbstractFactory.AbstractFactory;
import AbstractFactory.FactoryProducer;
import Vehiculo.Vehiculo;
import Vehiculo.listaVehiculos;
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
import java.util.concurrent.ThreadLocalRandom;

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
    protected Map<Vehiculo, Integer> mapadeEspera_Vehiculo = new HashMap<>();
    protected ArrayList<Milicia> listaMiliciaJugador = new ArrayList<>();
    protected ArrayList<Vehiculo> listaVehiculoJugador = new ArrayList<>();

    public Jugador() {
        AbstractFactory factory;
        factory = FactoryProducer.getFactory("Raza");
        Scanner leer = new Scanner(System.in);
        System.out.println("Digite su nombre");
        nombre_jugador = leer.nextLine();
        int raza;
        while (true) {
            try {
                System.out.println("Eliga una raza");
                listaRazas lista = listaRazas.getInstance();
                lista.mostrar();
                raza = leer.nextInt();
                if (lista.getArray().size() >= raza) {
                    civilizacion = factory.getRaza(raza);
                    break;
                } else {
                    System.err.println("Ingrese una opcion valida");
                    leer.nextLine();
                }
            } catch (Exception e) {
                System.err.println("Ingrese un dato valido");
                leer.nextLine();
            }
        }
        civilizacion.Iniciar();
        centro_mando = new centro_Mando(civilizacion.oroBonus, civilizacion.piedraBonus, civilizacion.comidaBonus, civilizacion.vida_centroMandoBonus);
    }

    /**
     * Construye una edificacion para el jugador, se añade a su lista personal
     * de edificaciones
     *
     * @param fase Fase actual del juego pasar saber en cuantas fases se
     * terminar de construir dependiendo del tiempo de construccion
     */
    public void construir(int fase) {
        listaEdificaciones lista = listaEdificaciones.getInstance();
        lista.mostrar();
        Scanner leer = new Scanner(System.in);
        AbstractFactory factory;
        int opcion;
        factory = FactoryProducer.getFactory("Edificacion");
        try {
            opcion = leer.nextInt();
        } catch (Exception err) {
            System.err.println("Ingrese un dato valido");
            return;
        }
        if (lista.getArray().size() < opcion) {
            System.err.println("Ingrese una opcion valida");
            return;
        }
        Edificacion e = factory.getEdificacion(opcion);
        e.Iniciar();
        if (e.nombre == "Castillo" && centro_mando.numeroDeMejora < 3) {
            System.out.println("El castillo solo se puede construir en la edad de los Castillos como minimo!");
            return;
        }
        if (e.nombre == "\u001B[33m" + "MARAVILLA" + "\u001B[0m" && centro_mando.numeroDeMejora < 4) {
            System.out.println("La MARAVILLA solo se puede construir en la edad imperial!");
            return;
        }
        if (e.nombre == "Taller de maquinas de asedio" && centro_mando.numeroDeMejora < 2) {
            System.out.println("El Taller de maquinas de asedio solo se puede contruir en la edad Fedudal como minimo! ");
            return;
        }
        if (centro_mando.getComida_jugador() >= e.costo_comida && centro_mando.getOro_jugador() >= e.costo_oro && centro_mando.getPiedra_jugador() >= e.costo_piedra) {
            centro_mando.operar_Comida_jugador(-e.costo_comida);
            centro_mando.operar_Oro_jugador(-e.costo_oro);
            centro_mando.operar_Piedra_jugador(-e.costo_piedra);
            int tiempoNeto = e.cooldown - civilizacion.cooldownEdificacionesBonus;
            int espera = (tiempoNeto + fase);
            //listaEdificiosJugador.add(e);
            if (tiempoNeto == 0) {
                listaEdificiosJugador.add(e);
                System.out.println("Se ha terminado de construir " + e.nombre + " , " + nombre_jugador);
            } else {
                mapadeEspera.put(e, espera);
                System.out.println("Se empezo a construir la edificacion, estara lista dentro de " + tiempoNeto + " fase/s, es decir la fase " + (espera));
            }
        } else {
            System.out.println("Recursos insuficientes");
        }
    }

    /**
     * Genera los recursos para el cm y para las edificaciones, es llamado luego
     * en la clase Menu antes del inicio del turno del jugador
     */
    public void generar() {
        for (Edificacion e : listaEdificiosJugador) {
            centro_mando = e.generar(centro_mando);
            e.almacenar();
        }
    }

    /**
     * Recolectar los recursos manualmente(Solo puede recolectarse una vez por
     * turno)
     */
    public void recolectar() {
        if (centro_mando.getFlagRecolectar() == 0) {
            System.err.println("Solo se pueden recolectar recursos 1 vez por turno!!");
            return;
        }
        ArrayList<Edificacion> temp = new ArrayList<>();
        Scanner leer = new Scanner(System.in);
        int opcion = 0;
        int i = 1;
        for (Edificacion e : listaEdificiosJugador) {
            if (e.isRecolectable == true) {
                System.out.println(i + " " + e.nombre + " recursos almacenados: " + e.recurso + " " + e.descripcion_extra);
                temp.add(e);
                i++;
            }
        }
        if (temp.isEmpty()) {
            System.err.println("No tienes edificaciones que recolecten recursos");
            return;
        }
        try {
            opcion = leer.nextInt() - 1;
        } catch (Exception e) {
            System.err.println("Ingrese un dato valido");
            return;
        }
        if (temp.size() - 1 < opcion) {
            System.err.println("Ingrese una opcion valida");
            return;
        }
        centro_mando = temp.get(opcion).recolectar(centro_mando);
        System.out.println("Se transaladaron los recursos al centro de mando");

    }

    /**
     * Se puede alternar para crear un tipo de dato tipo vehiculo o milicia
     *
     * @param tipo Para diferenciar si se quiere una Milicia o Vehiculo
     * @param fase Fase actual para calcular el tiempo de espera de construccion
     * de los vehiculos
     */
    public void crear(String tipo, int fase) {
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
                System.out.println("Es necesario tener cuarteles para crear milicia");
                return;
            case "Vehiculo":
                for (Edificacion e : listaEdificiosJugador) {
                    if (e.nombre == "Taller de maquinas de asedio") {
                        listaVehiculos lista = listaVehiculos.getInstance();
                        lista.mostrar();
                        Scanner leer = new Scanner(System.in);
                        AbstractFactory factory = FactoryProducer.getFactory("Vehiculo");
                        int opcion = leer.nextInt();
                        if (lista.getArray().size() < opcion) {
                            System.err.println("Ingrese una opcion valida");
                            return;
                        }
                        Vehiculo v = factory.getVehiculo(opcion);
                        v.Iniciar();
                        int espera = (v.cooldown + fase);
                        if (centro_mando.getComida_jugador() >= v.costo_comida && centro_mando.getOro_jugador() >= v.costo_oro && centro_mando.getPiedra_jugador() >= v.costo_piedra) {
                            centro_mando.operar_Comida_jugador(-v.costo_comida);
                            centro_mando.operar_Oro_jugador(-v.costo_oro);
                            centro_mando.operar_Piedra_jugador(-v.costo_piedra);
                            if (v.cooldown == 0) {
                                listaVehiculoJugador.add(v);
                                return;
                            } else {
                                mapadeEspera_Vehiculo.put(v, espera);
                                System.out.println("Se empezo a construir el vehiculo, estara lista dentro de " + v.cooldown + " fase/s, es decir la fase " + (espera));
                                return;
                            }
                        }
                        System.out.println("No tienes suficientes recursos para crear esta unidad");
                        return;
                    }
                }
                System.out.println("Es necesario tener Taller de maquinas de asedio para crear vehiculos");
                return;
        }
    }

    /**
     * Mandar a atacar a Milicia o Vehiculos a los edificios enemigos, se
     * transladan de nuestro ArrayList al de ellos. Tambien puede ocurrir que
     * mueran en el intento de llegar.
     *
     * @param edificios_enemigos Donde se añadiran nuestros atacantes que antes
     * estaban en nuestro ArrayList
     */
    public void atacar(ArrayList<Edificacion> edificios_enemigos) {
        if (edificios_enemigos.isEmpty()) {
            System.out.println("El enemigo no posee edificaciones. Es buen momento para atacar" + "\u001B[1;31m" + " el centro de mando!!" + "\u001B[0m");
            return;
        }
        int i = 1;
        int opcion = 0;
        int opcion2 = 0;
        int opciontipo = 0;
        Scanner leer = new Scanner(System.in);
        Scanner leer2 = new Scanner(System.in);
        System.out.println("\u001B[1;31m" + "¡Edificaciones enemigas!" + "\u001B[0m");
        for (Edificacion e : edificios_enemigos) {
            System.out.println(i + " " + e.nombre + " " + e.vida);
            i++;
        }
        System.out.println("Cual atacara?");
        try {
            opcion = leer.nextInt() - 1;
        } catch (Exception e) {
            System.err.println("Ingrese un dato valido");
            return;
        }
        if (edificios_enemigos.size() - 1 < opcion) {
            System.err.println("Ingrese una opcion valida");
        }

        System.out.println("Que unidad mandaras al ataque?\n1.Milicia\n2.Vehiculo");
        try {
            opciontipo = leer.nextInt();
        } catch (Exception e) {
            System.err.println("Ingrese un tipo de dato valido");
            return;
        }
        switch (opciontipo) {
            case 1:
                System.out.println("A quienes mandaras al ataque?");
                mostrarMiliciaJugador();
                if (listaMiliciaJugador.size() != 0) {
                    try {
                        opcion2 = leer2.nextInt() - 1;
                    } catch (Exception e) {
                        System.err.println("Ingrese un tipo de dato valido");
                        return;
                    }
                    if (listaMiliciaJugador.size() - 1 < opcion2) {
                        System.err.println("Ingrese una opcion valida");
                        return;
                    }
                    if (listaMiliciaJugador.get(opcion2).getFlagAtaqueV2() == 0) {
                        int randomNum = ThreadLocalRandom.current().nextInt(0, 100 + 1);
                        //System.out.println(randomNum); BLESS RNG
                        if (randomNum <= civilizacion.bad_luck) {
                            System.out.println("\u001B[31m" + "Oh no! Has tenido mala suerte, tu milicia ha sido deborada por lobos salvajes en el camino :(" + "\u001B[0m");
                            listaMiliciaJugador.remove(opcion2);
                            return;
                        }
                        edificios_enemigos.get(opcion).atacantes.add(listaMiliciaJugador.get(opcion2));
                        listaMiliciaJugador.remove(opcion2);
                        System.out.println("Atacaras a " + edificios_enemigos.get(opcion).nombre + " en dos turnos " + " ,vida=" + edificios_enemigos.get(opcion).vida);
                    } else {
                        System.out.println("No puedes mandar a atacar a una unidad que entrenaste este turno!");
                    }
                    return;
                }
                System.out.println("No tienes milicia preparada para atacar!");
                return;
            case 2:
                System.out.println("Cual vehiculo mandaras al ataque?");
                mostrarVehiculosJugador();
                if (listaVehiculoJugador.size() != 0) {
                    try {
                        opcion2 = leer2.nextInt() - 1;
                    } catch (Exception e) {
                        System.err.println("Ingrese un tipo de dato valido");
                        return;
                    }
                    if (listaVehiculoJugador.size() - 1 < opcion2) {
                        System.err.println("Ingrese una opcion valida");
                        return;
                    }
                    if (listaVehiculoJugador.get(opcion2).getFlagAtaqueV2() == 0) {
                        int randomNum = ThreadLocalRandom.current().nextInt(0, 100 + 1);
                        //System.out.println(randomNum); BLESSS RNG
                        if (randomNum <= civilizacion.bad_luck) {
                            System.out.println("\u001B[31m" + "Oh no! Has tenido mala suerte, tu vehiculo ha explotado en el camino :(" + "\u001B[0m");
                            listaVehiculoJugador.remove(opcion2);
                            return;
                        }
                        edificios_enemigos.get(opcion).atacantes_Vehiculo.add(listaVehiculoJugador.get(opcion2));
                        listaVehiculoJugador.remove(opcion2);
                        System.out.println("Atacaras a " + edificios_enemigos.get(opcion).nombre + " en dos turnos " + " ,vida=" + edificios_enemigos.get(opcion).vida);
                    } else {
                        System.out.println("No puedes mandar a atacar a un vehiculo que creaste este turno!");
                    }
                    return;
                }
                System.out.println("No tienes vehiculos construidos para atacar!");
                return;
            default:
                System.out.println("Ingrese un tipo");
        }
    }

    /**
     * Se añadiaran nuestras tropas al centro de mando enemigo siempre y cuando
     * ya no existan otros edificios enemigos a nuestras tropas tambien les
     * puede suceder que nunca llegan por tener mala suerte :´0
     *
     * @param edificios_enemigos Para comprobar que ya no exista ninguno en pie
     * @param cm_enemigo Se añadiran a la lista de atacantes del centro de mando
     */
    public void atacarCentrodeMando(ArrayList<Edificacion> edificios_enemigos, centro_Mando cm_enemigo) {
        if (edificios_enemigos.isEmpty()) {
            int opcion;
            int opciontipo;
            Scanner leer = new Scanner(System.in);
            System.out.println("Que unidad mandaras al ataque?\n1.Milicia\n2.Vehiculo");
            try {
                opciontipo = leer.nextInt();
            } catch (Exception e) {
                System.out.println("Ingrese un tipo de dato valido");
                return;
            }
            switch (opciontipo) {
                case 1:
                    System.out.println("A quienes mandaras al ataque?");
                    mostrarMiliciaJugador();
                    if (listaMiliciaJugador.size() != 0) {
                        try {
                            opcion = leer.nextInt() - 1;
                        } catch (Exception e) {
                            System.err.println("Ingrese un tipo de dato valido");
                            return;
                        }
                        if (listaMiliciaJugador.size() - 1 < opcion) {
                            System.err.println("Ingrese una opcion valida");
                            return;
                        }
                        if (listaMiliciaJugador.get(opcion).getFlagAtaqueV2() == 0) {
                            int randomNum = ThreadLocalRandom.current().nextInt(0, 100 + 1);
                            //System.out.println(randomNum); BLESS RNG
                            if (randomNum <= civilizacion.bad_luck) {
                                System.out.println("\u001B[31m" + "Oh no! Has tenido mala suerte, tu milicia ha sido brutalmente destrozada por razones desconocidas:(" + "\u001B[0m");
                                listaVehiculoJugador.remove(opcion);
                                return;
                            }
                            cm_enemigo.atacantes.add(listaMiliciaJugador.get(opcion));
                            listaMiliciaJugador.remove(opcion);
                            System.out.println("Atacaras al centro de mando al principio de tu siguiente turno " + " ,vida=" + cm_enemigo.getVida());
                        } else {
                            System.out.println("No puedes mandar a atacar a una unidad que entrenaste este turno!");
                        }
                        return;
                    }
                    System.err.println("No tienes milicia preparada para atacar!");
                    return;
                case 2:
                    System.out.println("Cual vehiculo mandaras al ataque?");
                    mostrarVehiculosJugador();
                    if (listaVehiculoJugador.size() != 0) {
                        try {
                            opcion = leer.nextInt() - 1;
                        } catch (Exception e) {
                            System.err.println("Ingrese un tipo de dato valido");
                            return;
                        }
                        if (listaVehiculoJugador.size() - 1 < opcion) {
                            System.err.println("Ingrese una opcion valida");
                            return;
                        }
                        if (listaVehiculoJugador.get(opcion).getFlagAtaqueV2() == 0) {
                            int randomNum = ThreadLocalRandom.current().nextInt(0, 100 + 1);
                            //System.out.println(randomNum); BLESS RNG
                            if (randomNum <= civilizacion.bad_luck) {
                                System.out.println("\u001B[31m" + "Oh no! Has tenido mala suerte, tu vehiculo ha explotado en el camino :(" + "\u001B[0m");
                                listaVehiculoJugador.remove(opcion);
                                return;
                            }
                            cm_enemigo.atacantes_Vehiculo.add(listaVehiculoJugador.get(opcion));
                            listaVehiculoJugador.remove(opcion);
                            System.out.println("Atacaras al centro de mando al principio de tu siguiente turno " + " ,vida=" + cm_enemigo.getVida());
                        } else {
                            System.out.println("No puedes mandar a atacar a un vehiculo que creaste este turno!");
                        }
                        return;
                    } else {
                        System.err.println("No tienes vehiculos listos para atacar");
                    }
            }
        } else {
            System.out.println("\u001B[1;31m" + "NO puedes atacar al centro de mando si el enemigo tiene otras edificaciones!!" + "\u001B[0m");
        }
    }

    /**
     * Funcion para defender nuestras edificaciones de las tropas enemigas
     */
    public void defender() {
        int opcionEdificio = 0;
        int opcion = 0;
        int opcion1 = 0;
        int opciontipoAtacantes;
        int opciontipoAtacar;
        int i = 1;
        Scanner leer = new Scanner(System.in);
        Scanner leer2 = new Scanner(System.in);
        mostrarEdificiosJugador();
        if (listaEdificiosJugador.size() == 0) {
            System.err.println("No tienes ningun edificio :´(");
            return;
        }
        System.out.println("¿A que edificio mandara tropas a defender?");
        try {
            opcionEdificio = leer.nextInt() - 1;
        } catch (Exception e) {
            System.err.println("Ingrese un tipo de dato valido");
            return;
        }
        if (listaEdificiosJugador.size() - 1 < opcionEdificio) {
            System.err.println("Ingrese una opcion valida");
            return;
        }
        Edificacion e = listaEdificiosJugador.get(opcionEdificio);
        if (e.atacantes.isEmpty() && e.atacantes_Vehiculo.isEmpty()) {
            System.out.println("No estas siendo atacado");
            return;
        }
        System.out.println("A que tipo de unidad atacaras?\n1.Milicia\n2.Vehiculo");
        opciontipoAtacantes = leer.nextInt();
        switch (opciontipoAtacantes) {
            case 1:
                System.out.println("¿A que unidad atacara?");
                for (Milicia m : e.atacantes) {
                    System.out.println(i + " " + m.toString());
                    i++;
                }
                try {
                    opcion = leer.nextInt() - 1;
                } catch (Exception error) {
                    System.err.println("Seleccione un tipo de dato valido");
                    return;
                }
                if (e.atacantes.size() - 1 < opcion) {
                    System.err.println("Ingrese una opcion valida");
                    return;
                }
                break;
            case 2:
                System.out.println("¿A que vehicula atacara?");
                for (Vehiculo v : e.atacantes_Vehiculo) {
                    System.out.println(i + " " + v.toString());
                }
                try {
                    opcion = leer.nextInt() - 1;
                } catch (Exception err) {
                    System.err.println("Ingrese un tipo de dato valido");
                    return;
                }
                if (e.atacantes_Vehiculo.size() - 1 < opcion) {
                    System.err.println("Ingrese una opcion valida");
                    return;
                }
                break;
            default:
                System.err.println("Seleccione una opcion valida");
                return;
        }
        System.out.println("¿A que tipo de unidad mandaras al ataque?\n1.Milicia\n2.Vehiculo");
        try {
            opciontipoAtacar = leer.nextInt();
        } catch (Exception err) {
            System.err.println("Ingrese un tipo de dato valido");
            return;
        }
        switch (opciontipoAtacar) {
            case 1:
                System.out.println("¿A que unidad mandara?");
                mostrarMiliciaJugador();
                try {
                    opcion1 = leer2.nextInt() - 1;
                } catch (Exception err) {
                    System.out.println("Ingrese un tipo de dato valido");
                    return;
                }
                if (listaMiliciaJugador.size() - 1 < opcion1) {
                    System.out.println("Ingrese una opcion valida");
                    return;
                }
                if (listaMiliciaJugador.get(opcion1).getFlagDefensa() == 0) {
                    if (opciontipoAtacantes == 1) {
                        e.atacantes.get(opcion).operar_Vida(-listaMiliciaJugador.get(opcion1).getAtaque());
                        listaMiliciaJugador.get(opcion1).operar_Vida(-e.atacantes.get(opcion).getAtaque());
                        listaMiliciaJugador.get(opcion1).setFlagDefensa(1);
                        if (e.atacantes.get(opcion).getVida() <= 0) {
                            System.out.println("Has eliminado a la unidad enemiga");
                            e.atacantes.remove(opcion);
                        }
                        if (listaMiliciaJugador.get(opcion1).getVida() <= 0) {
                            System.out.println("Han eliminado a tu unidad");
                            listaMiliciaJugador.remove(opcion1);
                        }
                    } else if (opciontipoAtacantes == 2) {
                        e.atacantes_Vehiculo.get(opcion).operar_Vida(-listaMiliciaJugador.get(opcion1).getAtaque());
                        listaMiliciaJugador.get(opcion1).operar_Vida(-e.atacantes_Vehiculo.get(opcion).ataque);
                        listaMiliciaJugador.get(opcion1).setFlagDefensa(1);
                        if (e.atacantes_Vehiculo.get(opcion).getVida() <= 0) {
                            System.out.println("Has destruido al vehiculo enemigo");
                            e.atacantes_Vehiculo.remove(opcion);
                        }
                        if (listaMiliciaJugador.get(opcion1).getVida() <= 0) {
                            System.out.println("Han eliminado a tu unidad");
                            listaMiliciaJugador.remove(opcion1);
                        }
                    }
                } else {
                    System.out.println("\u001B[1;31m" + "Una unidad no puede ir a la batalla dos veces en el mismo turno o en el turno que es creada!!" + "\u001B[0m");
                    return;
                }
                break;
            case 2:
                System.out.println("¿A que vehicula mandara");
                mostrarVehiculosJugador();
                try {
                    opcion1 = leer2.nextInt() - 1;
                } catch (Exception err) {
                    System.err.println("Ingrese un tipo de dato valido");
                    return;
                }
                if (listaVehiculoJugador.size() - 1 < opcion1) {
                    System.err.println("Ingrese una opcion valida");
                    return;
                }
                if (listaVehiculoJugador.get(opcion1).getFlagDefensa() == 0) {
                    if (opciontipoAtacantes == 1) {
                        e.atacantes.get(opcion).operar_Vida(-listaVehiculoJugador.get(opcion1).getAtaque());
                        listaVehiculoJugador.get(opcion1).operar_Vida(-e.atacantes.get(opcion).getAtaque());
                        listaVehiculoJugador.get(opcion1).setFlagDefensa(1);
                        if (e.atacantes.get(opcion).getVida() <= 0) {
                            System.out.println("Has eliminado a la unidad enemiga");
                            e.atacantes.remove(opcion);
                        }
                        if (listaVehiculoJugador.get(opcion1).getVida() <= 0) {
                            System.out.println("Han destruido a tu vehiculo");
                            listaVehiculoJugador.remove(opcion1);
                        }
                    } else if (opciontipoAtacantes == 2) {
                        e.atacantes_Vehiculo.get(opcion).operar_Vida(-listaVehiculoJugador.get(opcion1).getAtaque());
                        listaVehiculoJugador.get(opcion1).operar_Vida(-e.atacantes_Vehiculo.get(opcion).getAtaque());
                        listaVehiculoJugador.get(opcion1).setFlagDefensa(1);
                        if (e.atacantes_Vehiculo.get(opcion).getVida() <= 0) {
                            System.out.println("Has destruido al vehiculo enemigo");
                            e.atacantes_Vehiculo.remove(opcion);
                        }
                        if (listaVehiculoJugador.get(opcion1).getVida() <= 0) {
                            System.out.println("Han destruido a tu vehiculo");
                            listaVehiculoJugador.remove(opcion1);
                        }
                    }
                } else {
                    System.out.println("\u001B[1;31m" + "Un vehiculo no puede ir a la batalla dos veces en el mismo turno o en el turno que es creado!!" + "\u001B[0m");
                    return;
                }
                break;

        }
    }

    /**
     * Defender nuestro centro de mando de las tropas enemigas
     */
    public void defenderCentrodeMando() {
        int opcion = 0;
        int opcion1 = 0;
        int opciontipoAtacantes = 0;
        int opciontipoAtacar = 0;
        int i = 1;
        Scanner leer = new Scanner(System.in);
        Scanner leer2 = new Scanner(System.in);
        if (centro_mando.atacantes.size() == 0 && centro_mando.atacantes_Vehiculo.size() == 0) {
            System.err.println("No estas siendo atacado");
            return;
        }
        System.out.println("A que tipo de unidad atacaras?\n1.Milicia\n2.Vehiculo");
        try {
            opciontipoAtacantes = leer.nextInt();
        } catch (Exception err) {
            System.err.println("Ingrese un tipo de dato valido");
            return;
        }

        switch (opciontipoAtacantes) {
            case 1:
                System.out.println("¿A que unidad atacara?");
                if (centro_mando.atacantes.size() == 0) {
                    System.err.println("No hay unidades atacando a esta edificacion");
                    return;
                }
                for (Milicia m : centro_mando.atacantes) {
                    System.out.println(i + " " + m.toString());
                    i++;
                }
                try {
                    opcion = leer.nextInt() - 1;
                } catch (Exception e) {
                    System.err.println("Ingrese un tipo de dato valido");
                    return;
                }
                if (centro_mando.atacantes.size() - 1 < opcion) {
                    System.err.println("Ingrese una opcion valida");
                    return;
                }
                break;
            case 2:
                System.out.println("¿A que vehicula atacara?");
                if (centro_mando.atacantes_Vehiculo.size() == 0) {
                    System.err.println("No hay unidades atacando a esta edificacion");
                    return;
                }
                for (Vehiculo v : centro_mando.atacantes_Vehiculo) {
                    System.out.println(i + " " + v.toString());
                }
                try {
                    opcion = leer.nextInt() - 1;
                } catch (Exception e) {
                    System.err.println("Ingrese un tipo de dato valido");
                    return;
                }
                if (centro_mando.atacantes_Vehiculo.size() - 1 < opcion) {
                    System.err.println("Ingrese una opcion valida");
                    return;
                }
                break;
            default:
                System.err.println("Ingrese un tipo de dato valido");
                return;
        }
        System.out.println("¿A que tipo de unidad mandaras al ataque?\n1.Milicia\n2.Vehiculo");
        try {
            opciontipoAtacar = leer.nextInt();
        } catch (Exception err) {
            System.err.println("Ingrese un tipo de dato valido");
            return;
        }
        switch (opciontipoAtacar) {
            case 1:
                System.out.println("¿A que unidad mandara?");
                mostrarMiliciaJugador();
                try {
                    opcion1 = leer2.nextInt() - 1;
                } catch (Exception err) {
                    System.err.println("Ingrese un tipo de dato valido");
                    return;
                }
                if (listaMiliciaJugador.size() - 1 < opcion1) {
                    System.err.println("Ingrese una opcion valida");
                    return;
                }
                if (listaMiliciaJugador.get(opcion1).getFlagDefensa() == 0) {
                    if (opciontipoAtacantes == 1) {
                        centro_mando.atacantes.get(opcion).operar_Vida(-listaMiliciaJugador.get(opcion1).getAtaque());
                        listaMiliciaJugador.get(opcion1).operar_Vida(-centro_mando.atacantes.get(opcion).getAtaque());
                        listaMiliciaJugador.get(opcion1).setFlagDefensa(1);
                        if (centro_mando.atacantes.get(opcion).getVida() <= 0) {
                            System.out.println("Has eliminado a la unidad enemiga");
                            centro_mando.atacantes.remove(opcion);
                        }
                        if (listaMiliciaJugador.get(opcion1).getVida() <= 0) {
                            System.out.println("Han eliminado a tu unidad");
                            listaMiliciaJugador.remove(opcion1);
                        }
                    } else if (opciontipoAtacantes == 2) {
                        centro_mando.atacantes_Vehiculo.get(opcion).operar_Vida(-listaMiliciaJugador.get(opcion1).getAtaque());
                        listaMiliciaJugador.get(opcion1).operar_Vida(-centro_mando.atacantes_Vehiculo.get(opcion).ataque);
                        listaMiliciaJugador.get(opcion1).setFlagDefensa(1);
                        if (centro_mando.atacantes_Vehiculo.get(opcion).getVida() <= 0) {
                            System.out.println("Has destruido al vehiculo enemigo");
                            centro_mando.atacantes_Vehiculo.remove(opcion);
                        }
                        if (listaMiliciaJugador.get(opcion1).getVida() <= 0) {
                            System.out.println("Han eliminado a tu unidad");
                            listaMiliciaJugador.remove(opcion1);
                        }
                    }
                } else {
                    System.out.println("\u001B[1;31m" + "Una unidad no puede ir a la batalla dos veces en el mismo turno o en el turno que es creada!!" + "\u001B[0m");
                    return;
                }
                break;
            case 2:
                System.out.println("¿A que vehicula mandara");
                mostrarVehiculosJugador();
                try {
                    opcion1 = leer2.nextInt() - 1;
                } catch (Exception err) {
                    System.err.println("Ingrese un tipo de dato valido");
                    return;
                }
                if (listaVehiculoJugador.size() - 1 < opcion1) {
                    System.err.println("Ingrese una opcion valida");
                    return;
                }
                if (listaVehiculoJugador.get(opcion1).getFlagDefensa() == 0) {
                    if (opciontipoAtacantes == 1) {
                        centro_mando.atacantes.get(opcion).operar_Vida(-listaVehiculoJugador.get(opcion1).getAtaque());
                        listaVehiculoJugador.get(opcion1).operar_Vida(-centro_mando.atacantes.get(opcion).getAtaque());
                        listaVehiculoJugador.get(opcion1).setFlagDefensa(1);
                        if (centro_mando.atacantes.get(opcion).getVida() <= 0) {
                            System.out.println("Has eliminado a la unidad enemiga");
                            centro_mando.atacantes.remove(opcion);
                        }
                        if (listaVehiculoJugador.get(opcion1).getVida() <= 0) {
                            System.out.println("Han destruido a tu vehiculo");
                            listaVehiculoJugador.remove(opcion1);
                        }
                    } else if (opciontipoAtacantes == 2) {
                        centro_mando.atacantes_Vehiculo.get(opcion).operar_Vida(-listaVehiculoJugador.get(opcion1).getAtaque());
                        listaVehiculoJugador.get(opcion1).operar_Vida(-centro_mando.atacantes_Vehiculo.get(opcion).getAtaque());
                        listaVehiculoJugador.get(opcion1).setFlagDefensa(1);
                        if (centro_mando.atacantes_Vehiculo.get(opcion).getVida() <= 0) {
                            System.out.println("Has destruido al vehiculo enemigo");
                            centro_mando.atacantes_Vehiculo.remove(opcion);
                        }
                        if (listaVehiculoJugador.get(opcion1).getVida() <= 0) {
                            System.out.println("Han destruido a tu vehiculo");
                            listaVehiculoJugador.remove(opcion1);
                        }
                    }
                } else {
                    System.out.println("\u001B[1;31m" + "Un vehiculo no puede ir a la batalla dos veces en el mismo turno o en el turno que es creado!!" + "\u001B[0m");
                    return;
                }
                break;
            default:
                System.err.println("Ingrese un tipo de dato valido");
                return;

        }

    }

    /**
     * Printear nuestros edificios
     */
    public void mostrarEdificiosJugador() {
        int i = 1;
        for (Edificacion e : listaEdificiosJugador) {
            System.out.println(i + " " + e.toString());
            i++;
        }
    }

    /**
     * Printear nuestros vehiculos
     */
    public void mostrarVehiculosJugador() {
        int i = 1;
        for (Vehiculo v : listaVehiculoJugador) {
            System.out.println(i + " " + v.toString());
            i++;
        }
    }

    /**
     * Printear nuestra Milicia
     */
    public void mostrarMiliciaJugador() {
        int i = 1;
        for (Milicia m : listaMiliciaJugador) {
            System.out.println(i + " " + m.toString());
            i++;
        }
    }

    /**
     * Funcion para comprobar que nuestras edificaciones no esten a 0 de salud,
     * de ser asi se eliminan de nuestro arrayList de edificaciones
     */
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
