package back;

import java.util.List;

import back.expresiones.Expresion;

public class NewtonGregory {

	public Expresion calcularPolinomioCon(List<Punto> puntos) {
		
		List<Punto> puntosOrdenados = null;
		
		List<Expresion> diferenciasDivididas = calcularDiferenciasDivididasCon(puntosOrdenados);
	}

	public List<Expresion> calcularDiferenciasDivididasCon(List<Punto> puntos) {
		
		List<Double> xis = null;
		List<Double> yis = null;
		
		
		return null;
	}
	
	public double calcularOrden1(Punto punto0, Punto punto1) {
		
		return ( punto1.getY() - punto0.getY() ) / ( punto1.getX() - punto0.getX() );
	}
	
	public double calcularOrden2(Punto punto0, Punto punto1, Punto punto2) {
		
		return ( calcularOrden1(punto1, punto2) - calcularOrden1(punto0, punto1) ) / ( punto2.getX() - punto0.getX() );
	}
}
