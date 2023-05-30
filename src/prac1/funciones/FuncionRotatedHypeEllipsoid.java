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
public class FuncionRotatedHypeEllipsoid extends Evaluador{
    final private String nombre = "RotatedHypeEllipsoid";
    final private double rango_min = -65536;
    final private double rango_max = 65536;
    int d;

    public FuncionRotatedHypeEllipsoid(Configurador config) {
        d = config.getDimension();
    }
    
    @Override
    public double evaluar(double[] vector){
        double sum = 0.0;
        
        for (int i = 0; i < d; i++) {
            for (int j = 0; j <= i; j++) {
                sum += Math.pow(vector[i], 2);                
            }
        }
        
        return sum ;
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
