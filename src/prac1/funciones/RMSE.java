/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package prac1.funciones;

/**
 *
 * @author cojou
 */
public class RMSE {

    public RMSE() {
    }
    
    public double compute(double[] real, double[] estimation) {
        int N = real.length;
        double score;
        double sum = 0;
        for (int i = 0; i < N; i++) {
            sum += Math.pow(real[i] - estimation[i], 2);
        }
        score = Math.sqrt(1.0 / N * sum);
        return score;
    }
    
}
