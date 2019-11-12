package back.expresiones.operaciones;

import back.expresiones.Expresion;

public class Suma extends Operacion {
	
	public Suma(Expresion operandoIzquierdo, Expresion operandoDerecho) {
		
		super(operandoIzquierdo, operandoDerecho);
	}
	
	@Override
	public Double evaluarEn(double unValor) {
		
		return operandoIzquierdo.evaluarEn(unValor) + operandoDerecho.evaluarEn(unValor);
	}
	
	@Override
	public String toString() {
		
		return operandoIzquierdo.toString() + " " + getOperador() + " " + operandoDerecho.toString();
	}
	
	@Override
	public Expresion reducir() {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public char getOperador() {
		
		return '+';
	}
}