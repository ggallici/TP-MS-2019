package back.expresiones;

public class Constante implements Expresion {

	private double valor;
	
	public Constante(double valor) {
		
		this.valor = valor;
	}
	
	@Override
	public Double evaluarEn(double unValor) {

		return valor;
	}
	
	@Override
	public Expresion reducir() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String toString() {
		
		return valor < 0 ? "(" + String.valueOf(valor) + ")" : String.valueOf(valor);
	}
}