package back;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.uqbar.commons.utils.Observable;

@Observable
public class Punto {
	
	private double x;
	private double y;
	
	public Punto(double x, double y) {
		
		this.x = x;
		this.y = y;
	}
	
	public double getX() {
		
		return x;
	}
	
	public double getY() {
		
		return y;
	}
	
	public double getDistanciaA(Punto otroPunto) {
		
		return Math.abs(x - otroPunto.getX());
	}
	
	public static boolean sonEquiespaciados(List<Punto> puntos) {
		
		//TODO: Refactorizar con un fold y sin listas intermedias?
		
		if (puntos.size() < 2)
			throw new RuntimeException("Para calcular una distancia se necesitan 2 puntos como mínimo");
		
		List<Punto> puntosOrdenados = puntos
				.stream()
				.sorted(Comparator.comparing(Punto::getX))
				.collect(Collectors.toList());
		
		return IntStream
				.range(0, puntosOrdenados.size() - 1)
				.mapToObj(indice -> {
				
					Punto punto = puntosOrdenados.get(indice);
					
					Punto puntoSiguiente = puntosOrdenados.get(indice + 1);
					
					return punto.getDistanciaA(puntoSiguiente);
				})
				.distinct().count() == 1;
	}
}