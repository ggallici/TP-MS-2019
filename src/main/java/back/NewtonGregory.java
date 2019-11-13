package back;

import java.util.List;

import back.expresiones.Expresion;

public class NewtonGregory {
	
	private EstrategiaNewtonGregory estrategia;

	public NewtonGregory(EstrategiaNewtonGregory estrategia) {
		
		this.estrategia = estrategia;
	}
	
	public Expresion calcularPolinomioCon(List<Punto> puntos) {
		
		MatrizNewtonGregory matriz = calcularMatrizCon(puntos);
		
		return estrategia.calcularPolinomioCon(matriz);
	}
	
	public MatrizNewtonGregory calcularMatrizCon(List<Punto> puntos) {
		
		MatrizNewtonGregory matriz = new MatrizNewtonGregory(puntos.size() + 1, puntos.size());
		
		matriz.inicializarCon(puntos);
				
		matriz.calcularCoeficientes();
		
		return matriz;
	}
}