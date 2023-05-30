/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package prac1.algoritmos;

import java.util.ArrayList;
import prac1.TablaDatos;
import prac1.funciones.Evaluador;

/**
 *
 * @author Xjesu
 */
public abstract class Busqueda {
    
    public Busqueda() {
    }
    
    public abstract double[] busqueda(Evaluador exe, long semilla, TablaDatos datos);
    
    public abstract ArrayList<double[]> getSoluciones();
    
    public abstract void limpiaBusqueda();
}
