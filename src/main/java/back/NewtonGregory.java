package back;

public class NewtonGregory {
	
	public static void main(String[] args) {
		double[] xi = {1, 3, 4, 5, 7};
		double[] fxi = {1, 3, 13, 37, 151};
		
		NewtonGregory newtonGregory = new NewtonGregory();
		double[][] tabla = newtonGregory.evaluar(xi, fxi);
		String polinomioProgresivo = newtonGregory.generarPolinomioProgresivo(tabla, xi);
		System.out.println();
		System.out.println("Polinomio Pogresivo:");
		System.out.println(polinomioProgresivo);
		System.out.println();
		String polinomioRegresivo = newtonGregory.generarPolinomioRegresivo(tabla, xi);
		System.out.println("Polinomio Regresivo");
		System.out.println(polinomioRegresivo);
	}

	public String generarPolinomioRegresivo(double[][] tabla, double[] xi) {
		int n = xi.length - 1;
		int indiceX = n - 1;
		String polinomio = Double.toString(tabla[n][0]) + " + ";
		boolean coeficienteAgregado = false;
		
		for (int i = 1; i<n + 1; i++) {

			for (int j = n; j > indiceX; j--) {
				if (!coeficienteAgregado) {
					polinomio += Double.toString(tabla[indiceX][i]);
					coeficienteAgregado = true;
				}
				
				if (xi[j] < 0) {
					polinomio += "*" + "(x - (" + Double.toString(xi[j]) + "))";
				} else {
					polinomio += "*" + "(x - " + Double.toString(xi[j]) + ")";
				}
			}
			
			indiceX--;
			coeficienteAgregado = false;
			if (indiceX >= 0) {
				polinomio += " + ";
			}
		}
		
		return polinomio;
	}
	
	public String generarPolinomioProgresivo(double[][] tabla, double[] xi) {
		String polinomio = Double.toString(tabla[0][0]) + " + ";
		int n = xi.length;
		int indiceX = 1;
		
		for (int i = 1; i<n; i++) {
			for (int j = 0; j < indiceX; j++) {
				if (j == 0) {
					polinomio += Double.toString(tabla[0][i]);
				}
				
				if (xi[j] < 0) {
					polinomio += "*" + "(x - (" + Double.toString(xi[j]) + "))";
				} else {
					polinomio += "*" + "(x - " + Double.toString(xi[j]) + ")";
				}
			}
			
			indiceX++;
			if (indiceX != n) {
				polinomio += " + ";
			}
		}
		
		return polinomio;
	}
	
	public double[][] evaluar(double[] xi, double[] fxi) {

		int n = xi.length;
		double[][] T = new double[n][n];

		System.out.println("--------------------------------------------------");
		System.out.println("------------Interpolacion de Newton---------------");
		System.out.println("--------------------------------------------------");

		for (int i = 0; i < n; i++ ) {
			T[i][0] = fxi[i];
		}
		
		for (int i = 0; i < n - 1; i++) {

			T[i][1] = (fxi[i + 1] - fxi[i]) / (xi[i + 1] - xi[i]);

		}

		for (int j = 2; j<n; j++) {

			for (int i = 0; i <(n-j); i++) {
				T[i][j] = (T[i+1][j-1] - T[i][j-1]) / (xi[i+j] - xi[i]);

			}
		}

		return T;
	}
}
