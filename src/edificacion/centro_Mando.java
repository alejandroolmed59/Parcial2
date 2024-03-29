/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edificacion;

import Vehiculo.Vehiculo;
import java.util.ArrayList;
import java.util.Scanner;
import raza.Milicia;

/**
 *
 * @author Alejandro Olmedo <00097017@uca.edu.sv>
 */
public class centro_Mando{
    public ArrayList<Milicia> atacantes= new ArrayList();
    public ArrayList<Vehiculo> atacantes_Vehiculo= new ArrayList();
    public int numeroDeMejora=1;
    int vida=600,  flagRecolectar=1;
    double max_oro=3000, max_piedra=5000, max_comida=10000;
    double oro_jugador=300, piedra_jugador=500, comida_jugador=1000;
    public centro_Mando(double oro, double piedra, double comida, int vida) {
        this.oro_jugador+= oro;
        this.piedra_jugador+= piedra;
        this.comida_jugador+= comida;
        this.vida += vida; 
    }
    /**
     * Funcion para poder avanzar por las edades y desbloquear nuevas edificaciones y ampliar la capacidad maxima
     */
    public void mejorar() {
        switch(numeroDeMejora){
            case 1:
                System.out.println("Avanzar a la edad feudal consumira. Comida: "+((max_comida*1.1)*0.25)+" piedra: "+((max_piedra*1.1)*0.25)+" Oro: "+((max_oro*1.1)*0.25));
                
                if(!confirmar()){
                    return;
                }
                if(comida_jugador>((max_comida*1.1)*0.25) && piedra_jugador>((max_piedra*1.1)*0.25)  && oro_jugador>((max_oro*1.1)*0.25)){
                    comida_jugador-=((max_comida*1.1)*0.25);
                    piedra_jugador-= ((max_piedra*1.1)*0.25);
                    oro_jugador-= ((max_oro*1.1)*0.25);
                    max_oro= (max_oro*1.1);
                    max_piedra= (max_piedra*1.1);
                    max_comida= (max_comida*1.1);
                    numeroDeMejora++;
                    System.out.println("\u001B[1;34m"+"Has avanzado a la edad Feudal!!. Tu centro de mando se ha mejorado"+"\u001B[0m");
                }
                else{
                    System.out.println("No tiene los suficientes recursos para avanzar a la edad feudal");
                }
                break;
            case 2:
                System.out.println("Avanzar a la edad de los castillos consumira. Comida: "+((max_comida*1.3)*0.25)+" Piedra: "+((max_piedra*1.3)*0.25)+" Oro: "+((max_oro*1.3)*0.25));
                if(!confirmar()){return;}
                if(comida_jugador>((max_comida*1.3)*0.25) && piedra_jugador>((max_piedra*1.3)*0.25)  && oro_jugador>((max_oro*1.3)*0.25)){
                    comida_jugador-=((max_comida*1.3)*0.25);
                    piedra_jugador-= ((max_piedra*1.3)*0.25);
                    oro_jugador-= ((max_oro*1.3)*0.25);
                    max_oro= (max_oro*1.3);
                    max_piedra= (max_piedra*1.3);
                    max_comida= (max_comida*1.3);
                    numeroDeMejora++;
                    System.out.println("\u001B[1;34m"+"Has avanzado a la edad de los castillos!!. Tu centro de mando se ha mejorado"+"\u001B[0m");
                }else{
                    System.out.println("No tiene los suficientes recursos para avanzar a la edad de los castillos");
                }
                break;
            case 3:       
                System.out.println("Avanzar a la edad imperial consumira. Comida: "+((max_comida*1.5)*0.25)+" Piedra: "+((max_piedra*1.5)*0.25)+" Oro: "+((max_oro*1.5)*0.25));
                if(!confirmar()){return;}
                if(comida_jugador>((max_comida*1.5)*0.25) && piedra_jugador>((max_piedra*1.5)*0.25)  && oro_jugador>((max_oro*1.5)*0.25)){
                    comida_jugador-=((max_comida*1.5)*0.25);
                    piedra_jugador-= ((max_piedra*1.5)*0.25);
                    oro_jugador-= ((max_oro*1.5)*0.25);
                    max_oro= (max_oro*1.5);
                    max_piedra= (max_piedra*1.5);
                    max_comida= (max_comida*1.5);
                    numeroDeMejora++;
                    System.out.println("\u001B[1;34m"+"Has avanzado a la edad imperial!!. Tu centro de mando se ha mejorado al maximo"+"\u001B[0m");
                }else{
                    System.out.println("No tiene los suficientes recursos para avanzar a la edad imperial");
                }
                break;
            case 4:
                System.out.println("\u001B[1;31m"+" Ya estas en la edad imperial, no puedes avanzar en el tiempo!"+"\u001B[0m");
                break;
        }
        
    }
    /**
     * Confirmar que se quiere avanzar de edad y descontar los recursos
     * @return true si asi se quiere, false de lo contrario
     */
    private boolean confirmar(){
        System.out.println("1. Para confirmar\n0.Volver");
        Scanner leer = new Scanner(System.in);
        int opcion;
        while(true){
            try{
                opcion=leer.nextInt();
                break;
            }catch(Exception err){
                System.out.println("Ingrese un tipo de dato valido");
                leer.nextLine();
            }
        }
        if(opcion==1){
            return true;
        }else{
            return false;
        }
    }

    public void operar_Oro_jugador(double precio) {
        this.oro_jugador += precio;
    }

    public void operar_Piedra_jugador(double precio) {
        this.piedra_jugador += precio;
    }

    public void operar_Comida_jugador(double precio) {
        this.comida_jugador += precio;
    }
    public void operar_Vida_jugador(double vida) {
        this.vida += vida;
    }

    public int getVida() {
        return vida;
    }

    public int getFlagRecolectar() {
        return flagRecolectar;
    }

    public void setFlagRecolectar(int flagRecolectar) {
        this.flagRecolectar = flagRecolectar;
    }
    

    public double getOro_jugador() {
        return oro_jugador;
    }

    public double getPiedra_jugador() {
        return piedra_jugador;
    }

    public double getComida_jugador() {
        return comida_jugador;
    }

    @Override
    public String toString() {
        return "Centro de Mando " + " vida:" + vida + "\n   Oro: "+oro_jugador+"/"+max_oro+"\n  Piedra: "+piedra_jugador+"/"+max_piedra+
                "\n Comida: "+comida_jugador+"/"+max_comida+"\nMilicia atacantes: "+atacantes+"\nVehiculos atacantes: "+atacantes_Vehiculo+"\n";
    }

}
