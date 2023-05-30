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
public class FuncionAckley extends Evaluador{
    final private String nombre = "Ackley";
    final private double rango_min = -32768;
    final private double rango_max = 32768;
    final private double a = 20;
    final private double b = 0.2;
    final private double c = 2 * Math.PI;
    int d;
    
    public FuncionAckley(Configurador config){
        d = config.getDimension();
    }
    
    @Override
    public double evaluar(double[] vector){
        double valor;
        double sum1 = 0.0;
        double sum2 = 0.0;
        
        for (int i = 0; i < d; i++) {
            sum1 += Math.pow(vector[i], 2);
            sum2 += Math.cos(c * vector[i]);
        }
        
        valor = -a * Math.exp(-b * Math.sqrt((1.0/d)*sum1)) - Math.exp( (1.0/d)*sum2 ) + a + Math.exp(1.0) ;
        
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
