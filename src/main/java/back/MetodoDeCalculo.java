package back;

import java.util.List;

import org.uqbar.commons.utils.Observable;

import back.expresiones.Expresion;

public interface MetodoDeCalculo {
	
	public abstract Expresion calcularPolinomioCon(List<Punto> puntos);

}
