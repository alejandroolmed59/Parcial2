/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package AbstractFactory;
import edificacion.Edificacion;
import raza.Raza;
/**
 *
 * @author Alejandro Olmedo <00097017@uca.edu.sv>
 */
public interface AbstractFactory {
    Raza getRaza(int tipo);
    Edificacion getEdificacion(int tipo);
}
