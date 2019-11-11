package back.expresiones.operaciones;

import back.expresiones.Expresion;

public abstract class Operacion implements Expresion {

	protected Expresion operandoIzquierdo;
	protected Expresion operandoDerecho;
	
	public Operacion(Expresion operandoIzquierdo, Expresion operandoDerecho) {

		this.operandoIzquierdo = operandoIzquierdo;
		this.operandoDerecho = operandoDerecho;
	}
	
	@Override
	public String toString() {
		
		return "(" + operandoIzquierdo.toString() + " " + getOperador() + " " + operandoDerecho.toString() + ")";
	}
	
	public abstract char getOperador();
}