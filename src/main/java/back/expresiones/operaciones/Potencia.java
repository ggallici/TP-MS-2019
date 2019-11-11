package back.expresiones.operaciones;

import back.expresiones.Expresion;

public class Potencia extends Operacion {
	
	public Potencia(Expresion operandoIzquierdo, Expresion operandoDerecho) {
		
		super(operandoIzquierdo, operandoDerecho);
	}
	
	@Override
	public Double evaluarEn(double unValor) {
		
		return Math.pow(operandoIzquierdo.evaluarEn(unValor), operandoDerecho.evaluarEn(unValor));
	}
	
	@Override
	public Expresion reducir() {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public char getOperador() {
		
		return '^';
	}
}