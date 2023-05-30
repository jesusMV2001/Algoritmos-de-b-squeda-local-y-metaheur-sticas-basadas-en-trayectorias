/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package prac1.funciones;

import prac1.Configurador;

/**
 *
 * @author cojou
 */
public class FuncionRastringin extends Evaluador{
    final private String nombre = "Rastringin";
    final private double rango_min = -51.12;
    final private double rango_max = 51.12;
    int d;
    
    public FuncionRastringin(Configurador config){
        d = config.getDimension();
    }
    
    @Override
    public double evaluar(double[] vector){
        double valor;
        double sum1 = 0.0;
        
        for (int i = 0; i < d; i++) {
            sum1 += Math.pow(vector[i], 2) - 10*Math.cos(2*Math.PI*vector[i]);
        }
        
        valor = 10*d + sum1 ;
        
        return valor;
    }
    
    @Override
    public double getRango_min() {
        return rango_min;
    }

    @Override
    public double getRango_max() {
        return rango_max;
    } 
    
    @Override
    public double[] getSolReal(){
        double[] v = new double[d];
        for (int i = 0; i < d; i++) {
            v[i] = 0;
        }
        return v;
    }

    @Override
    public String getNombre() {
        return nombre;
    }
    
}
