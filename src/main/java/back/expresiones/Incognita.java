package back.expresiones;

public class Incognita implements Expresion {

	@Override
	public Double evaluarEn(double unValor) {
		
		return unValor;
	}
	
	@Override
	public Expresion reducir() {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public String toString() {
		
		return "X";
	}
}