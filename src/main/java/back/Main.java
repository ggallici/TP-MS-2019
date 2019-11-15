package back;

import java.util.Arrays;
import java.util.List;

import back.expresiones.Expresion;

public class Main {

	public static void main(String[] args) {
		
		//NEWTON GREGORY
		Punto punto0 = new Punto(1,1);
		Punto punto1 = new Punto(3,3);
		Punto punto2 = new Punto(4,13);
		Punto punto3 = new Punto(5,37);
		Punto punto4 = new Punto(7,151);
		
		List<Punto> puntos = Arrays.asList(punto0, punto1, punto2, punto3, punto4);
		
		NewtonGregory ngProgresivo = new NewtonGregory(EstrategiaNewtonGregory.PROGRESIVO);
		
		Expresion polinomioProgresivo = ngProgresivo.calcularPolinomioCon(puntos);
		
		System.out.println(polinomioProgresivo.toString());
		
		System.out.println();
		
		NewtonGregory ngRegresivo = new NewtonGregory(EstrategiaNewtonGregory.REGRESIVO);
		
		Expresion polinomioRegresivo = ngRegresivo.calcularPolinomioCon(puntos);
		
		System.out.println(polinomioRegresivo.toString());
		
		
		System.out.println();
		System.out.println();
		
		
		//LAGRANGE
		Punto punto5 = new Punto(1,5);
		Punto punto6 = new Punto(2,8);
		Punto punto7 = new Punto(5,10);
		
		List<Punto> otrosPuntos = Arrays.asList(punto5, punto6, punto7);
		
		Lagrange lagrange = new Lagrange();
		
		Expresion polinomioLagrange = lagrange.calcularPolinomioCon(otrosPuntos);
		
		System.out.println(polinomioLagrange.toString());
		
		
		
		
		
		System.out.println("------------------------------------------------ PASO INTERMEDIO---------------------");
		
		System.out.println(ngRegresivo.calcularMatrizCon(puntos).toString());
		
		
		
		System.out.println("---------------------------------------------------------------ORDENNNNNNNNNNNNNNN---------------------");
		System.out.println();
		
		System.out.println(ngProgresivo.calcularMatrizCon(puntos).getOrdenDelPolinomioSegun(EstrategiaNewtonGregory.PROGRESIVO));
		System.out.println(ngProgresivo.calcularMatrizCon(puntos).getOrdenDelPolinomioSegun(EstrategiaNewtonGregory.REGRESIVO));
	}

}
