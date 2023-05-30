/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package prac1;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import prac1.algoritmos.*;

/**
 *
 * @author jesus
 */
public class Configurador {
    private ArrayList<String> archivos;
    private ArrayList<String> algoritmos;
    private ArrayList<String> entornosVNS;
    private ArrayList<Long> semillas;
    private Integer dimension, iteraciones;
    private double probCambio, diffRango, rangoTabu,intervaloReinicializacion,oscilacionEstrategica;
    private int numDecimales, numVecinos, numVecinosMin, numVecinosMax, tenenciaTabu;
    
    public Configurador(String ruta){
        algoritmos = new ArrayList<>();
        archivos = new ArrayList<>();
        semillas = new ArrayList<>();
        entornosVNS = new ArrayList<>();
        String linea;
        FileReader f =null;
        try{
            f = new FileReader(ruta);
            BufferedReader b = new BufferedReader(f);
            while( (linea=b.readLine()) != null){
                String[] split = linea.split("=");
                switch(split[0]){
                    case "Archivos":
                        String[] v= split[1].split(" ");
                        for (int i = 0; i < v.length; i++) 
                            archivos.add(v[i]);
                        break;
                    case "Semillas":
                        String[] vsemillas= split[1].split(" ");
                        for (int i = 0; i < vsemillas.length; i++) 
                            semillas.add(Long.parseLong(vsemillas[i]));
                        break;
                    case "Algoritmos":
                        String[] valgoritmos= split[1].split(" ");
                        for (int i = 0; i < valgoritmos.length; i++) 
                            algoritmos.add(valgoritmos[i]);
                        break;
                    case "Dimension":
                        dimension = Integer.parseInt(split[1]);
                        break;
                    case "Iteraciones":
                        iteraciones = Integer.parseInt(split[1]);
                        break;
                    case "ProbCambio":
                        probCambio = (double)Integer.parseInt(split[1]) / 100;
                        break;
                    case "DiffRango":
                        diffRango = (double)Integer.parseInt(split[1]) / 100;
                        break;
                    case "RangoTabu":
                        rangoTabu = (double)Integer.parseInt(split[1]) / 100;
                        break;
                    case "NumDecimales":
                        numDecimales = Integer.parseInt(split[1]);
                        break;
                    case "NumVecinos":
                        numVecinos = Integer.parseInt(split[1]);
                        break;
                    case "NumVecinosMin":
                        numVecinosMin = Integer.parseInt(split[1]);
                        break;
                    case "NumVecinosMax":
                        numVecinosMax = Integer.parseInt(split[1]);
                        break;
                    case "TenenciaTabu":
                        tenenciaTabu = Integer.parseInt(split[1]);
                        break;
                    case "IntervaloReinicializacion":
                        intervaloReinicializacion = (double)Integer.parseInt(split[1]) / 100;
                        break;
                    case "OscilacionEstrategica":
                        oscilacionEstrategica = (double)Integer.parseInt(split[1]) / 100;
                        break;
                    case "EntornosVNS":
                        String[] vc= split[1].split(" ");
                        for (int i = 0; i < vc.length; i++) 
                            entornosVNS.add(vc[i]);
                        break;
                }
            }
            
        }catch(IOException e){
            System.out.println(e);
        }
    }
    
    public ArrayList<Busqueda> obtenerBusquedas(){
        ArrayList<Busqueda> busquedas = new ArrayList<>();
        for (int i = 0; i < algoritmos.size(); i++) {
            switch (algoritmos.get(i)) {
                case "bl3":
                    busquedas.add(new BusquedaLocal(this, true));
                    break;
                case "blk":
                    busquedas.add(new BusquedaLocal(this, false));
                    break;
                case "btabu":
                    busquedas.add(new BusquedaTabu(this));
                    break;
                case "vns":
                    busquedas.add(new BusquedaVNS(this));
                    break;
            }
        }
        return busquedas;
    }

    public ArrayList<String> getArchivos() {
        return archivos;
    }

    public ArrayList<String> getAlgoritmos() {
        return algoritmos;
    }

    public ArrayList<Long> getSemillas() {
        return semillas;
    }

    public Integer getDimension() {
        return dimension;
    }
    
    public Integer getIteraciones() {
        return iteraciones;
    }

    public double getProbCambio() {
        return probCambio;
    }

    public double getDiffRango() {
        return diffRango;
    }

    public double getRangoTabu() {
        return rangoTabu;
    }

    public int getNumDecimales() {
        return numDecimales;
    }

    public int getNumVecinos() {
        return numVecinos;
    }

    public int getNumVecinosMin() {
        return numVecinosMin;
    }

    public int getNumVecinosMax() {
        return numVecinosMax;
    }

    public int getTenenciaTabu() {
        return tenenciaTabu;
    }

    public double getIntervaloReinicializacion() {
        return intervaloReinicializacion;
    }

    public double getOscilacionEstrategica() {
        return oscilacionEstrategica;
    }

    public ArrayList<String> getEntornosVNS() {
        return entornosVNS;
    }
    
}
