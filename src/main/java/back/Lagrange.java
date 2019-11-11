package back;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import back.expresiones.Constante;
import back.expresiones.Expresion;
import back.expresiones.operaciones.Division;
import back.expresiones.operaciones.Multiplicacion;
import back.expresiones.operaciones.Suma;

public class Lagrange {

	public Expresion calcularPolinomioCon(List<Punto> puntos) {
		
		List<Expresion> lis = calcularLisCon(puntos);
		
		return IntStream
				  .range(0, Math.min(puntos.size(), lis.size()))
				  .mapToObj(indice -> {
					  
					  Expresion li = lis.get(indice);
					  
					  double xi = puntos.get(indice).getX();
					  
					  double yi = puntos.get(indice).getY();
					  
					  double liEvaluadoEnXi = li.evaluarEn(xi);
					  
					  return (Expresion) new Multiplicacion(new Division(new Constante(yi), new Constante(liEvaluadoEnXi)), li);
				  })
				  .reduce((unaExpresion, otraExpresion) -> new Suma(unaExpresion, otraExpresion))
				  .orElse(new Constante(0));
	}

	public List<Expresion> calcularLisCon(List<Punto> puntos) {
		
		return puntos
				.stream()
				.map(punto -> punto.calcularLiCon(puntos))
				.collect(Collectors.toList());
	}
}
