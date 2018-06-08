/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edificacion;

/**
 *
 * @author Alejandro Olmedo <00097017@uca.edu.sv>
 */
public class centro_Mando{
    int numeroDeMejora=1;
    int vida=600;
    double max_oro=3000, max_piedra=5000, max_comida=10000;
    double oro_jugador=320, piedra_jugador=500, comida_jugador=6000;
    public centro_Mando(double oro, double piedra, double comida, int vida) {
        this.oro_jugador+= oro;
        this.piedra_jugador+= piedra;
        this.comida_jugador+= comida;
        this.vida += vida; 
    }
    
    public void mejorar() {
        switch(numeroDeMejora){
            case 1:
                if(comida_jugador>((max_comida*1.1)*0.25) && piedra_jugador>((max_piedra*1.1)*0.25)  && oro_jugador>((max_oro*1.1)*0.25)){
                    comida_jugador-=((max_comida*1.1)*0.25);
                    piedra_jugador-= ((max_piedra*1.1)*0.25);
                    oro_jugador-= ((max_oro*1.1)*0.25);
                    max_oro= (max_oro*1.1);
                    max_piedra= (max_piedra*1.1);
                    max_comida= (max_comida*1.1);
                    numeroDeMejora++;                    
                }
                else{
                    System.out.println("No tiene los suficientes recursos para avanzar a la edad feudal");
                }
                break;
            case 2:
                if(comida_jugador>((max_comida*1.3)*0.25) && piedra_jugador>((max_piedra*1.3)*0.25)  && oro_jugador>((max_oro*1.3)*0.25)){
                    comida_jugador-=((max_comida*1.3)*0.25);
                    piedra_jugador-= ((max_piedra*1.3)*0.25);
                    oro_jugador-= ((max_oro*1.3)*0.25);
                    max_oro= (max_oro*1.3);
                    max_piedra= (max_piedra*1.3);
                    max_comida= (max_comida*1.3);
                    numeroDeMejora++;
                }else{
                    System.out.println("No tiene los suficientes recursos para avanzar a la edad de los castillos");
                }
                break;
            case 3:
                if(comida_jugador>((max_comida*1.5)*0.25) && piedra_jugador>((max_piedra*1.5)*0.25)  && oro_jugador>((max_oro*1.5)*0.25)){
                    comida_jugador-=((max_comida*1.5)*0.25);
                    piedra_jugador-= ((max_piedra*1.5)*0.25);
                    oro_jugador-= ((max_oro*1.5)*0.25);
                    max_oro= (max_oro*1.5);
                    max_piedra= (max_piedra*1.5);
                    max_comida= (max_comida*1.5);
                    numeroDeMejora++;
                }else{
                    System.out.println("No tiene los suficientes recursos para avanzar a la edad imperial");
                }
                break;
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

    public int getVida() {
        return vida;
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
        return "centro_Mando{" + "vida=" + vida + ", max_oro=" + max_oro + ", max_piedra=" + max_piedra + ", max_comida=" + max_comida + "\noro_jugador=" + oro_jugador + ", piedra_jugador=" + piedra_jugador + ", comida_jugador=" + comida_jugador + '}'+"\n";
    }

}
