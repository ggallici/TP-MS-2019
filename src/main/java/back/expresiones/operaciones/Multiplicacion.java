package back.expresiones.operaciones;

import back.expresiones.Expresion;

public class Multiplicacion extends Operacion {
	
	public Multiplicacion(Expresion operandoIzquierdo, Expresion operandoDerecho) {
		
		super(operandoIzquierdo, operandoDerecho);
	}
	
	@Override
	public Double evaluarEn(double unValor) {
		
		return operandoIzquierdo.evaluarEn(unValor) * operandoDerecho.evaluarEn(unValor);
	}
	
	@Override
	public Expresion reducir() {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public char getOperador() {
		
		return '*';
	}
}