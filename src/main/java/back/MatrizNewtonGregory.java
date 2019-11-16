package back;

import java.util.List;
import java.util.stream.Collectors;
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
	
	public MatrizNewtonGregory(List<Punto> puntos) {
	    
		if(puntos.size() == 0)
			throw new RuntimeException("No se puede calcular Newton Gregory sin puntos");
		
	    this.columnas = puntos.size() + 1;
	    this.filas = puntos.size();
	    
	    matrizTraspuesta = new double [columnas][filas];
	    
	    inicializarCon(puntos);
	    
	    calcularCoeficientes();
	}
	
	private double getValor(int columna, int fila) {
		
		return matrizTraspuesta[columna][fila];
	}
	
	private void setValor(int columna, int fila, double valor) {
		
		matrizTraspuesta[columna][fila] = valor;
	}
	
	private void inicializarCon(List<Punto> puntos) {
		
		IntStream
			.range(0, puntos.size())
			.forEach(indice -> {
				
				setValor(0, indice, puntos.get(indice).getX());
				setValor(1, indice, puntos.get(indice).getY());
			});
	}
	
	private void calcularCoeficientes() {
		
		IntStream
			.range(2, columnas)
			.forEach(columna -> {

				IntStream
					.range(0, filas - columna + 1)
					.forEach(fila -> {
						
						double valor = (getValor(columna - 1, fila + 1) - getValor(columna - 1, fila)) / (getValor(0, fila + columna - 1) - getValor(0, fila));
						
						setValor(columna, fila, valor);
					});
			});
	}
	
	public Expresion calcularPolinomioCon(EstrategiaNewtonGregory estrategia) {
		
		return IntStream
				.range(1, columnas)
				.mapToObj(columna -> {
					
					Expresion coeficiente = new Constante(getValor(columna, estrategia.getFilaCoeficiente(filas, columna)));
				
					return IntStream
							.range(0, columna - 1)
							.mapToObj(fila -> (Expresion) new Resta(new Incognita(), new Constante(getValor(0, estrategia.getFilaXi(filas, fila)))))
							.reduce(coeficiente, (unaExpresion, otraExpresion) -> new Multiplicacion(unaExpresion, otraExpresion));
				})
				.reduce((unaExpresion, otraExpresion) -> new Suma(unaExpresion, otraExpresion))
				.get();
	}
	
	public String toString() {
		
		String cabecera = "X\tY";
		
		for (int i = 2; i < columnas; i++) {
			
			cabecera = cabecera + "\tO" + (i - 1);
		}
		
		cabecera = cabecera + "\n\n";
		
		return cabecera + IntStream
				.range(0, filas)
				.mapToObj(fila -> {
					
					return IntStream
							.range(0, columnas)
							.mapToObj(columna -> getValor(columna, fila)  + "\t")
							.reduce("", String::concat);
				})
				.reduce((unaFila, otraFila) -> unaFila + "\n\n" + otraFila)
				.get();
	}
	
	public int getOrdenDelPolinomioSegun(EstrategiaNewtonGregory estrategia) {
		
		//TODO: Refactorizar delegando esta responsabilidad a las expresiones
		
		List<Double> coeficientes = IntStream
				.range(1, columnas)
				.mapToDouble(columna -> getValor(columna, estrategia.getFilaCoeficiente(filas, columna)))
				.boxed()
				.collect(Collectors.toList());
		
		for (int i = coeficientes.size() - 1; i < coeficientes.size(); i--) {
			
			if(coeficientes.get(i) != 0)
				return i;
		}
		
		return 0;
	}
}