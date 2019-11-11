package back;

import java.util.Arrays;
import java.util.List;

import back.expresiones.Expresion;

public class TestPedorro {
	
	private static int indice = 0;

	public static void main(String[] args) {
		
		Punto punto0 = new Punto(1, 5);
		Punto punto1 = new Punto(2, 8);
		Punto punto2 = new Punto(5, 10);
		
		List<Punto> puntos = Arrays.asList(punto0, punto1, punto2);
		
		Lagrange lagrange = new Lagrange();
		
		List<Expresion> lis = lagrange.calcularLisCon(puntos);
		
		lis.forEach( li ->  {
			
			System.out.println("------------------------------------------------");
			System.out.println("l" + indice + " sin evaluar: " + li);
			System.out.println("l" + indice + " evaluado en el punto: " + li.evaluarEn(puntos.get(indice).getX()));
			System.out.println("------------------------------------------------");
			
			indice++;
		});
		
		Expresion polinomio = lagrange.calcularPolinomioCon(puntos);
		
		System.out.println(polinomio.toString());
		
		//double resultado = polinomio.evaluarEn(4);

	}

}
