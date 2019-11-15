package back;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import back.expresiones.Constante;
import back.expresiones.Expresion;
import back.expresiones.Incognita;
import back.expresiones.operaciones.Division;
import back.expresiones.operaciones.Multiplicacion;
import back.expresiones.operaciones.Resta;
import back.expresiones.operaciones.Suma;

public class Lagrange implements MetodoDeCalculo{

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
				  .orElseThrow(() -> new RuntimeException("No se puede calcular Lagrange sin puntos"));
	}

	public List<Expresion> calcularLisCon(List<Punto> puntos) {
		
		return puntos
				.stream()
				.map(punto -> calcularLiDe(punto, puntos))
				.collect(Collectors.toList());
	}
	
	private Expresion calcularLiDe(Punto unPunto, List<Punto> puntos) {
		
		return puntos
				.stream()
				.filter(punto -> punto != unPunto)
				.map(punto -> (Expresion) new Constante(punto.getX()))
				.reduce((unaConstante, otraConstante) -> {
					
					Expresion operandoIzquierdo = new Resta(new Incognita(), unaConstante);
					
					Expresion operandoDerecho = new Resta(new Incognita(), otraConstante);
					
					return new Multiplicacion(operandoIzquierdo, operandoDerecho);
				})
				.orElseThrow(() -> new RuntimeException("No se puede calcular Lagrange sin puntos"));
	}
}
