package back;

import java.util.List;

import back.expresiones.Constante;
import back.expresiones.Expresion;
import back.expresiones.Incognita;
import back.expresiones.operaciones.Multiplicacion;
import back.expresiones.operaciones.Resta;

public class Punto {
	
	private double x;
	private double y;
	
	public Punto(double x, double y) {
		
		this.x = x;
		this.y = y;
	}

	public double getX() {
		
		return x;
	}

	public double getY() {
		
		return y;
	}
	
	public Expresion calcularLiCon(List<Punto> puntos) {
		
		return puntos
				.stream()
				.filter(punto -> punto != this)
				.map(punto -> (Expresion) new Constante(punto.getX()))
				.reduce((unaConstante, otraConstante) -> {
					
					Expresion operandoIzquierdo = new Resta(new Incognita(), unaConstante);
					
					Expresion operandoDerecho = new Resta(new Incognita(), otraConstante);
					
					return new Multiplicacion(operandoIzquierdo, operandoDerecho);
				})
				.orElse(new Constante(0));
	}
}
