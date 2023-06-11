/*
 * Damian Melia
 * 14/05/2023
 * CSCB07 Lab 1
 */

import java.lang.Math;
import java.io.*;
import java.util.*;

public class Polynomial {

    public double[] coefficients;
    public int[] exponents;

    public Polynomial() {
        coefficients = new double[] { 0 };
        exponents = new int[] { 0 };

    }

    public Polynomial(File poly) throws IOException{
        // the file will only have one line with no whitepsaces which contains a polynomial in the form 5-3x2+7x8 which corresponds to 5-3x^2+7x^8. The 
        // coefficients array will be [5, -3, 7] and the exponents array will be [0, 2, 8]

        // read the file
        // split the string into an array of strings
        // loop through the array of strings
        // if the string contains an x, then split the string into two strings, one with the coefficient and one with the exponent
        // if the string does not contain an x, then the coefficient is the string and the exponent is 0
        // convert the strings to doubles and ints
        // add the doubles and ints to the coefficients and exponents arrays
        // return the polynomial

        //read the file
        BufferedReader br = new BufferedReader(new FileReader(poly));
    
        String line = br.readLine();
        br.close();

        //split the string at each + or -
        String[] split;
        split = line.split("(?=[+-])");

        double[] newCoefficients = new double[split.length];
        int[] newExponents = new int[split.length];



        //loop through the array of strings
        for (int i = 0; i < split.length; i++) {
            //if the string contains an x, then split the string into two strings, one with the coefficient and one with the exponent
            if(split[i].contains("x")){
                String[] split2 = split[i].split("x");
                //if the string does not contain an x, then the coefficient is the string and the exponent is 0
                if(split2[0].equals("")){
                    newCoefficients[i] = 1;
                } else if(split2[0].equals("-")){
                    newCoefficients[i] = -1;
                } else {
                    newCoefficients[i] = Double.parseDouble(split2[0]);
                }
                newExponents[i] = Integer.parseInt(split2[1]);
            } else {
                //if the string does not contain an x, then the coefficient is the string and the exponent is 0
                newCoefficients[i] = Double.parseDouble(split[i]);
                newExponents[i] = 0;
            }
        }

        //add the doubles and ints to the coefficients and exponents arrays
        this.coefficients = newCoefficients;
        this.exponents = newExponents;

    }

    public Polynomial(double[] coefficients, int[] exponents) {
        this.coefficients = coefficients;
        this.exponents = exponents;
    }

    public Polynomial add(Polynomial p) {
        Polynomial result = new Polynomial();
        int newLength = this.coefficients.length + p.coefficients.length;
        double[] newCoefficients = new double[newLength];
        int[] newExponents = new int[newLength];

        int i = 0, j= 0, k = 0;

        while(i < coefficients.length && j < p.coefficients.length){
            if(this.exponents[i] > p.exponents[j]){
                newCoefficients[k] = p.coefficients[i];
                newExponents[k] = p.exponents[i];
                j++;
            }
            else if(this.exponents[i] < p.exponents[j]){
                newCoefficients[k] = this.coefficients[i];
                newExponents[k] = this.exponents[i];
                i++;
            }
            else{
                newCoefficients[k] = this.coefficients[i] + p.coefficients[j];
                newExponents[k] = this.exponents[i];
                i++;
                j++;
            }
            k++;
        }

        while(i < coefficients.length){
            newCoefficients[k] = this.coefficients[i];
            newExponents[k] = this.exponents[i];
            i++;
            k++;
        }

        while(j < p.coefficients.length){
            newCoefficients[k] = p.coefficients[j];
            newExponents[k] = p.exponents[j];
            j++;
            k++;
        }



        result = this.cleanPolynomial(newCoefficients, newExponents, newLength);
        return result;
    }

    public Polynomial multiply(Polynomial p){
        Polynomial result = new Polynomial();
        int selfExpoLength = this.exponents.length;
        int pExpoLength = p.exponents.length;
        int minExpo = Integer.MAX_VALUE;
        int maxExpo = Integer.MIN_VALUE;

        for(int expo : this.exponents){
            for(int j=0; j<pExpoLength; j++){
                int newExpo = expo + p.exponents[j];
                if(newExpo < minExpo){
                    minExpo = newExpo;
                }
                if(newExpo > maxExpo){
                    maxExpo = newExpo;
                }
            }
        }

        int newExpoLength = maxExpo - minExpo + 1;
        double[] newCoefficients = new double[newExpoLength];
        int[] newExponents = new int[newExpoLength];

        for(int i=0; i< selfExpoLength; i++){
            for(int j=0; j<pExpoLength; j++){
                int newExpo = this.exponents[i] + p.exponents[j];
                int index = newExpo - minExpo;
                newCoefficients[index] += (this.coefficients[i] * p.coefficients[j]);
                newExponents[index] = newExpo;
            }
        }

        result = this.cleanPolynomial(newCoefficients, newExponents, newExpoLength);


        return result;
    }

    public double evaluate(double input) {
        double result = 0;
        for (int i = 0; i < this.coefficients.length; i++) {
            result += this.coefficients[i] * Math.pow(input, this.exponents[i]);
        }
        return result;
    }

    public boolean hasRoot(double input) {
        return this.evaluate(input) == 0;
    }

    public void saveToFile(String filename) throws IOException{
        
        BufferedWriter polysave = new BufferedWriter(new FileWriter(filename));

        boolean first = true;

        for(int i=0; i < this.coefficients.length; i++){
            if(this.coefficients[i] != 0){
                if(!first){
                    if(this.coefficients[i] > 0){
                        polysave.write("+");
                    }
                    else{
                        polysave.write("-");
                    }
                } else {
                    first = false;
                }
                polysave.write(Double.toString(Math.abs(this.coefficients[i])));
                if(this.exponents[i] > 0){
                    polysave.write("x" + Integer.toString(this.exponents[i]));

                }
            }
        }

        
        polysave.close();
    }

    public Polynomial cleanPolynomial(double[] coefficients, int[] exponents, int length){

        int accLength =0;

        double[] newCoefficients = new double[length];
        int[] newExponents = new int[length];

        for(int i=0; i < length; i++){
            if(coefficients[i] != 0){
                newCoefficients[accLength] = coefficients[i];
                newExponents[accLength] = exponents[i];
                accLength++;
            }
        }

        newCoefficients =  Arrays.copyOf(newCoefficients, accLength);
        newExponents =  Arrays.copyOf(newExponents, accLength);


        Polynomial result = new Polynomial(newCoefficients, newExponents);

        return result;
    }


}