/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package prac1.algoritmos;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;
import prac1.Configurador;
import prac1.TablaDatos;
import prac1.funciones.Evaluador;

/**
 *
 * @author Xjesu
 */
public class BusquedaTabu extends Busqueda {
    Configurador config;
    ArrayList<double []> soluciones;
    private LinkedList<double []> listaTabu;
    private LinkedList<ArrayList<Integer>> movPosTabu;
    double [] mejorSolGlobal;
    int [][] memoriaLargoPlazo;

    public BusquedaTabu(Configurador config) {
        this.config = config;
        listaTabu = new LinkedList<>();
        movPosTabu = new LinkedList<>();
        soluciones = new ArrayList<>();
        mejorSolGlobal = new double[config.getDimension()];
        memoriaLargoPlazo = new int[config.getDimension()][config.getDimension()];
    }
    
    
    @Override
    public double[] busqueda(Evaluador exe, long semilla, TablaDatos datos){
        Integer d = config.getDimension();
        Integer maxIteraciones = config.getIteraciones();
        Integer tenenciaTabu = config.getTenenciaTabu();
        double rangoSup = 1 + config.getDiffRango();
        double rangoInf = 1 - config.getDiffRango();
        double rangoTabuSup = 1 + config.getRangoTabu();
        double rangoTabuInf = 1 - config.getRangoTabu();
        double[] v = new double[d];
        int rangoReinicializacion =(int) (maxIteraciones*config.getIntervaloReinicializacion());
        
        Random aleatorio = new Random(semilla);
        ArrayList<Integer> movTabu = new ArrayList<>();
        
        //se crea solucion incial de forma aleatoria
        for (int i = 0; i < d; i++) {
            v[i] = aleatorio.nextDouble(exe.getRango_max() - exe.getRango_min()) + exe.getRango_min();
        }
        listaTabu.add(v);
        soluciones.add(v);
        System.arraycopy(v, 0, mejorSolGlobal, 0, d);
        
        
        int mov = 0, contReinicio=0;
        while(mov < maxIteraciones){
            double[] mejorVecino = new double[d];
            boolean esTabu = true;
            boolean primerVecinoNoTabu = false;
            int numVecinos = aleatorio.nextInt(config.getNumVecinosMin(), config.getNumVecinosMax() + 1); // Al igual que el BLK
            // Generamos todos los vecinos
            for (int i = 0; i < numVecinos; i++) {
                double[] vecinoActual = new double[d];
                esTabu = true;
                movTabu = new ArrayList<>();
                // Rellena de datos el vecino
                for (int j = 0; j < d; j++) {
                    if(aleatorio.nextDouble() < config.getProbCambio()){
                        //se crea el aleatorio entre un rango de +-10% del valor acutual(v[j])
                        vecinoActual[j] = obtenerValorRangoAleatorio(exe, v[j], rangoInf, rangoSup, aleatorio);
                        
                        // Una vez hecho el cambio comprobamos si es tabú
                        
                        for (double[] e : listaTabu) {
                            if(Math.abs(vecinoActual[j]) > Math.abs(e[j]) * rangoTabuSup || Math.abs(vecinoActual[j]) < Math.abs(e[j]) * rangoTabuInf)
                                esTabu = false;
                        }
                                
                        movTabu.add(j);
                    }
                    else vecinoActual[j] = v[j];
                }
                if(!esTabu && !primerVecinoNoTabu){
                    primerVecinoNoTabu = true;
                    System.arraycopy(vecinoActual, 0, mejorVecino, 0, d);
                }
                // Escogemos el mejor vecino
                if(!esTabu && exe.evaluar(vecinoActual) < exe.evaluar(mejorVecino) && !coincidenCambios(movTabu)){
                    System.arraycopy(vecinoActual, 0, mejorVecino, 0, d);
                }
            }
            if(!esTabu){
                contReinicio=0;
                agregarSolucionMemoriaLargoPlazo(exe,v);
                System.arraycopy(mejorVecino, 0, v, 0, d);
                listaTabu.add(v);
                movPosTabu.add(movTabu);
                soluciones.add(mejorVecino);
                if(listaTabu.size() == tenenciaTabu) listaTabu.pop();
                if(movPosTabu.size() == tenenciaTabu) movPosTabu.pop();
                if(exe.evaluar(v) < exe.evaluar(mejorSolGlobal)) 
                    System.arraycopy(v, 0, mejorSolGlobal, 0, d);
            }
            contReinicio++;
            mov++;
            if(contReinicio==rangoReinicializacion){
                //Diversificacion
                if(aleatorio.nextDouble() < config.getOscilacionEstrategica())
                    System.arraycopy(diversificacion(exe, aleatorio), 0, v, 0, 0);
                else//Intensificacion
                    System.arraycopy(intensificacion(exe, aleatorio), 0, v, 0, 0);
            }
        }
        
        return mejorSolGlobal;
    }   
    
    private double[] diversificacion(Evaluador exe, Random aleatorio){
        double[] v = new double[config.getDimension()];
        double rango = ( Math.abs(exe.getRango_min()) + Math.abs(exe.getRango_max())) / config.getDimension();
        
        for (int i = 0; i < config.getDimension(); i++) {
            int menosVisitado=memoriaLargoPlazo[i][0], pos=0;
            for (int j = 1; j < config.getDimension(); j++) {
                if(memoriaLargoPlazo[i][j] < menosVisitado) pos=j;
            }
            v[i] = aleatorio.nextDouble( rango*pos-exe.getRango_min(), rango*(pos+1)-exe.getRango_min() );
        }
        return v;
    }
    
    private double[] intensificacion(Evaluador exe, Random aleatorio){
        double[] v = new double[config.getDimension()];
        double rango = ( Math.abs(exe.getRango_min()) + Math.abs(exe.getRango_max())) / config.getDimension();
        
        for (int i = 0; i < config.getDimension(); i++) {
            int masVisitado=memoriaLargoPlazo[i][0], pos=0;
            for (int j = 1; j < config.getDimension(); j++) {
                if(memoriaLargoPlazo[i][j] > masVisitado) pos=j;
            }
            v[i] = aleatorio.nextDouble( rango*pos-exe.getRango_min(), rango*(pos+1)-exe.getRango_min() );
        }
        return v;
    }
    
    private double obtenerValorRangoAleatorio(Evaluador exe, double valor, double rangoInf, double rangoSup, Random aleatorio){
        if (valor >= 0) {
            double originRandom = Math.max(exe.getRango_min(), valor * rangoInf);
            double boundRandom = Math.min(exe.getRango_max(), valor * rangoSup);
            return aleatorio.nextDouble(originRandom, boundRandom);
        } else {
            double originRandom = Math.max(exe.getRango_min(), valor * rangoSup);
            double boundRandom = Math.min(exe.getRango_max(), valor * rangoInf);
            return aleatorio.nextDouble(originRandom, boundRandom);
        }
    }
    
    private void agregarSolucionMemoriaLargoPlazo(Evaluador exe, double[] solucion){
        double rango = ( Math.abs(exe.getRango_min()) + Math.abs(exe.getRango_max())) / config.getDimension();
        for (int i = 0; i < solucion.length; i++) {
            double posicion = (solucion[i] + Math.abs(exe.getRango_min())) / rango;
            memoriaLargoPlazo[i][(int)posicion]++;
        }
    }
    
    private boolean coincidenCambios(ArrayList<Integer> vecino){
        for (int i = 0; i < movPosTabu.size(); i++) {
            if(vecino.equals(movPosTabu.get(i))) return true;            
        }
        return false;
    }
    
    @Override
    public ArrayList<double[]> getSoluciones() {
        return soluciones;
    }
    
    @Override
    public void limpiaBusqueda(){
        memoriaLargoPlazo = new int[config.getDimension()][config.getDimension()];
        soluciones.clear();
        listaTabu.clear();
        movPosTabu.clear();
    }
}
