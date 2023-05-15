/*
 * Damian Melia
 * 14/05/2023
 * CSCB07 Lab 1
 */

import java.lang.Math;

public class Polynomial {

    public double[] coefficients;

    public Polynomial() {
        coefficients = new double[] { 0 };
    }

    public Polynomial(double[] coefficients) {
        this.coefficients = coefficients;
    }

    public Polynomial add(Polynomial p) {
        Polynomial result = new Polynomial();

        if (this.coefficients.length > p.coefficients.length) {
            result.coefficients = this.coefficients;
            for (int i = 0; i < p.coefficients.length; i++) {
                result.coefficients[i] += p.coefficients[i];
            }

        }

        else {
            result.coefficients = p.coefficients;
            for (int i = 0; i < this.coefficients.length; i++) {
                result.coefficients[i] += this.coefficients[i];
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