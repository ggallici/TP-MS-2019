package back;

import java.util.List;

import back.expresiones.Expresion;

public class NewtonGregory implements MetodoDeCalculo {
	
	private EstrategiaNewtonGregory estrategia;

	public NewtonGregory(EstrategiaNewtonGregory estrategia) {
		
		this.estrategia = estrategia;
	}
	
	public Expresion calcularPolinomioCon(List<Punto> puntos) {
		
		MatrizNewtonGregory matriz = calcularMatrizCon(puntos);
		
		return matriz.calcularPolinomioCon(estrategia);
	}
	
	public MatrizNewtonGregory calcularMatrizCon(List<Punto> puntos) {
		
		return new MatrizNewtonGregory(puntos);
	}
	
	public int calcularGrado(List<Punto> puntos) { 		 		
		return this.calcularMatrizCon(puntos).getOrdenDelPolinomioSegun(estrategia); 	
	}

	@Override
	public String calcularPasoIntermedioCon(List<Punto> puntos) {
		
		return this.calcularMatrizCon(puntos).toString();
	}
}