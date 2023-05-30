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
public class FuncionMICHALEWICZ extends Evaluador{
    final private String nombre = "Michalewicz";
    final private double rango_min = 0;
    final private double rango_max = 2 * Math.PI;
    int d,m;
    
    public FuncionMICHALEWICZ(Configurador config){
        d = config.getDimension();
        m=10;
    }
    
    @Override
    public double evaluar(double[] vector){
        double sum = 0.0;
        
        for (int i = 0; i < d; i++) {
            sum += Math.sin(vector[i]) * Math.pow( Math.sin( ((i+1)*Math.pow(vector[i],2))/Math.PI) ,2*m) ;
        }
        
        return -sum;
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
    public double[] getSolReal() {
        double[] v = {2.202906, 1.570796, 1.284992, 1.923058, 1.720470, 1.570796, 1.454414, 1.756087, 1.655717, 1.570796};
        return v;
    }

    @Override
    public String getNombre() {
        return nombre;
    }
    
}
