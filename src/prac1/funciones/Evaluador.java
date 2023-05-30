/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package prac1.funciones;

/**
 *
 * @author jesus
 */
public abstract class Evaluador {

    public Evaluador() {
    }
    
    public abstract double evaluar(double[] vector);
    
    public abstract double getRango_min();

    public abstract double getRango_max();
    
    public abstract double[] getSolReal();
    
    public abstract String getNombre();
    
}
