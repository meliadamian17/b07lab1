/*
 * Damian Melia
 * 14/05/2023
 * CSCB07 Lab 1
 */

import java.lang.Math;

public class Polynomial {

    double[] coefficients;

    public Polynomial() {
        coefficients = new double[100];
        for (int i = 0; i < coefficients.length; i++) {
            coefficients[i] = 0;
        }
    }

    public Polynomial(double[] coefficients) {
        this.coefficients = coefficients;
    }

    public Polynomial add(Polynomial p) {
        Polynomial result = new Polynomial();

        int iters = Math.max(this.coefficients.length, p.coefficients.length);
        int min = Math.min(this.coefficients.length, p.coefficients.length);

        for (int i = 0; i < iters; i++) {
            if (i < min) {
                result.coefficients[i] = this.coefficients[i] + p.coefficients[i];
            } else if (this.coefficients.length > p.coefficients.length) {
                result.coefficients[i] = this.coefficients[i];
            } else {
                result.coefficients[i] = p.coefficients[i];
            }
        }

        return result;

    }

    public double evaluate(double input) {
        double result = 0;
        int degree = this.coefficients.length;

        for (int i = 0; i < degree; i++) {
            result += this.coefficients[i] * Math.pow(input, i);
        }
        return result;
    }

    public boolean hasRoot(double input) {
        return this.evaluate(input) == 0;
    }

}
