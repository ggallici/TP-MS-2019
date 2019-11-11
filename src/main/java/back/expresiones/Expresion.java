package back.expresiones;

public interface Expresion {

	public abstract Double evaluarEn(double unValor);
	
	public abstract Expresion reducir();
	
	public abstract String toString();
}