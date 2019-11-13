package back;

import java.util.List;
import java.util.stream.IntStream;

import back.expresiones.Constante;
import back.expresiones.Expresion;
import back.expresiones.Incognita;
import back.expresiones.operaciones.Multiplicacion;
import back.expresiones.operaciones.Resta;
import back.expresiones.operaciones.Suma;

public class MatrizNewtonGregory {
	
	private double [][] matrizTraspuesta;
	private int columnas;
	private int filas;
	
	public MatrizNewtonGregory(int columnas, int filas) {
	    
	    this.columnas = columnas;
	    this.filas = filas;
	    
	    matrizTraspuesta = new double [columnas][filas];
	}
	
	public double getValor(int columna, int fila) {
		
		return matrizTraspuesta[columna][fila];
	}

	public void setValor(int columna, int fila, double valor) {
		
		matrizTraspuesta[columna][fila] = valor;
	}
	
	public void inicializarCon(List<Punto> puntos) {
		
		IntStream
		  .range(0, puntos.size())
		  .forEach(indice -> {
			  
			  setValor(0, indice, puntos.get(indice).getX());
			  setValor(1, indice, puntos.get(indice).getY());
		  });
	}
	
	public void calcularCoeficientes() {
		
		for (int columna = 2; columna < columnas; columna++) {
			
			for (int fila = 0; fila <= filas - columna; fila++) {
				
				double valor = (getValor(columna - 1, fila + 1) - getValor(columna - 1, fila)) / (getValor(0, fila + columna - 1) - getValor(0, fila));
				
				setValor(columna, fila, valor);
			}
		}
	}
	
	public void mostrarMatriz() { //TODO: Esto lo tengo que convertir en el toString
		
		for (int fila = 0; fila < filas; fila++) {
			
			for (int columna = 0; columna < columnas; columna++) {
				
				System.out.print(matrizTraspuesta[columna][fila] + "\t");
			}
			
			System.out.println("\n");
		}
	}

	public Expresion calcularPolinomioProgresivo() {
		
		return IntStream
				.range(1, columnas)
				.mapToObj(columna -> {
					
					Expresion coeficiente = new Constante(getValor(columna, 0)); //TODO: Si este coeficiente es cero que hago?
				
					return IntStream
							.range(0, columna - 1) //TODO: testear con 2 por las dudas
							.mapToObj(fila -> (Expresion) new Resta(new Incognita(), new Constante(getValor(0, fila))))
							.reduce(coeficiente, (unaExpresion, otraExpresion) -> new Multiplicacion(unaExpresion, otraExpresion));
				})
				.reduce((unaExpresion, otraExpresion) -> new Suma(unaExpresion, otraExpresion))
				.orElseThrow(() -> new RuntimeException("No se puede calcular Newton Gregory sin puntos"));
	}

	public Expresion calcularPolinomioRegresivo() {
		// TODO: Implementar
		return null;
	}
}