import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;

public class Driver {
	public static void main(String [] args) throws IOException {

		double[] coeff1 = {1, 2, 4, 7};
		int[] pow1 = {0, 1, 2, 3};
		Polynomial poly1 = new Polynomial(coeff1, pow1);

		double[] coeff2 = {2, 2};
		int[] pow2 = {2, 4};
		Polynomial poly2 = new Polynomial(coeff2, pow2);

		double[] coeff3 = {1, 3, 3, 4, 5, 1};
		int[] pow3 = {2, 3, 4, 5, 6, 9};
		Polynomial poly3 = new Polynomial(coeff3, pow3);


		System.out.println("Test Cases:");

		System.out.println("Test Evaluate:");
		System.out.println("Received: " +  poly1.evaluate(3));
		System.out.println("Expected: 232.0");
		System.out.println();

		System.out.println("Received: " +  poly2.evaluate(2));
		System.out.println("Expected: 40.0");
		System.out.println();

		System.out.println("Received: " +  poly3.evaluate(1));
		System.out.println("Expected: 17.0");
		System.out.println();

		System.out.println("Test HasRoot:");
		System.out.println("Received: " +  poly2.hasRoot(3));
		System.out.println("Expected: false");
		System.out.println();

		System.out.println("Received: " +  poly2.hasRoot(-1));
		System.out.println("Expected: false");
		System.out.println();

		System.out.println("Test Add:");

		Polynomial sum2 = poly1.add(poly2);
		System.out.println("Received: " +  Arrays.toString(sum2.coefficients) + "  " + Arrays.toString(sum2.exponents));
		System.out.println("Expected: [1.0, 2.0, 6.0, 7.0, 2.0]  [0, 1, 2, 3, 4]");
		System.out.println();

		Polynomial sum3 = poly1.add(poly3);
		System.out.println("Received: " +  Arrays.toString(sum3.coefficients) + "  " + Arrays.toString(sum3.exponents));
		System.out.println("Expected: [1.0, 2.0, 5.0, 10.0, 3.0, 4.0, 5.0, 1.0]  [0, 1, 2, 3, 4, 5, 6, 9]");
		System.out.println();

		System.out.println("Test Multiply:");
		Polynomial prod1 =  poly1.multiply(poly2);
		System.out.println("Received: " + Arrays.toString(prod1.coefficients) + "  " + Arrays.toString(prod1.exponents));
		System.out.println("Expected: [2.0, 4.0, 10.0, 18.0, 8.0, 14.0]  [2, 3, 4, 5, 6, 7]");
		System.out.println();

		Polynomial prod2 = poly2.multiply(poly1);
		System.out.println("Received: " +  Arrays.toString(prod2.coefficients) + "  " + Arrays.toString(prod2.exponents));
		System.out.println("Expected: [2.0, 4.0, 10.0, 18.0, 8.0, 14.0]  [2, 3, 4, 5, 6, 7]");
		System.out.println();

		Polynomial prod3 = poly1.multiply(poly3);
		System.out.println("Received: " +  Arrays.toString(prod3.coefficients) + "  " + Arrays.toString(prod3.exponents));
		System.out.println("Expected: [1.0, 5.0, 13.0, 29.0, 46.0, 47.0, 48.0, 36.0, 2.0, 4.0, 7.0]  [2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12]");
		System.out.println();

		System.out.println("Test Read from File:");
		File f = new File("poly.txt");
		Polynomial read = new Polynomial(f);
        System.out.println("Received: " +  Arrays.toString(read.coefficients) + "  " + Arrays.toString(read.exponents));
        System.out.println("Expected: [5.0, -3.0, 7.0]  [0, 2, 8]");
        System.out.println();
		File f2 = new File("poly2.txt");
		Polynomial read2 = new Polynomial(f2);
        System.out.println("Received: " +  Arrays.toString(read2.coefficients) + "  " + Arrays.toString(read2.exponents));
        System.out.println("Expected: [3.0, 5.0, -2.0, 7.0, -8.0]  [0, 1, 2, 3, 6]");
        System.out.println();

		System.out.println("Test Save To File:");
		poly1.saveToFile("output.txt");
		System.out.println("Successfully written to output.txt");
		System.out.println();
		System.out.println("Reading from output.txt");
		try (BufferedReader reader = new BufferedReader(new FileReader("output.txt"))) {
			String line;
			while ((line = reader.readLine()) != null) {
				System.out.println("Received: " + line);
			}
		} catch (IOException e) {
			System.err.println("Error reading the file: " + e.getMessage());
		}
		System.out.println("Expected: 1.0+2.0x1+4.0x2+7.0x3");
	}
}
