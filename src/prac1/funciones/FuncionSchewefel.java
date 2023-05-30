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
public class FuncionSchewefel extends Evaluador{
    final private String nombre = "Schewefel";
    final private double rango_min = -5000;
    final private double rango_max = 5000;
    int d;

    public FuncionSchewefel(Configurador config) {
        d = config.getDimension();
    }
    
    @Override
    public double evaluar(double[] vector){
        double valor;
        double sum = 0.0;
        
        for (int i = 0; i < d; i++) {
            sum += vector[i]*Math.sin(Math.sqrt(Math.abs(vector[i])));
        }
        
        valor = 418.9829*d -sum ;
        
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
            v[i] = 420.9687;
        }
        return v;
    }

    @Override
    public String getNombre() {
        return nombre;
    }
    
}
