package front.vm;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.uqbar.commons.utils.Observable;

import back.EstrategiaNewtonGregory;
import back.Lagrange;
import back.MetodoDeCalculo;
import back.NewtonGregory;
import back.Punto;
import back.expresiones.Expresion;

@Observable
public class PrincipalVM {
	
	private double x;
	private double y;
	private List<Punto> puntos = new ArrayList<>();
	private Punto puntoSeleccionado;
	private boolean sonEquidistantesLosPuntos;
	private String stringComunicacion = "Calcule su primer polinomio";
	
	private List<MetodoInterpolacion> metodosInterpolacion = Arrays.asList(MetodoInterpolacion.values());
	private MetodoInterpolacion metodoInterpolacionSeleccionado;
	private List<EstrategiaNewtonGregory> estrategiasNewtonGregory = Arrays.asList(EstrategiaNewtonGregory.values());
	private EstrategiaNewtonGregory estrategiaNewtonGregorySeleccionada;
	private MetodoDeCalculo metodoAUsar;
	
	private Expresion polinomioCalculado;
	private String pasoIntermedio; //ESTO PODRIA SER STRING O ALGO POLIMORFICO ENTRE LA LISTA DE EXPRESIONES LI Y LA MATRIZ DE NG
	private int gradoDelPolinomio;
	private Boolean hayPolinomioCalculado = false;
	private Boolean esIgualAlPolinomioAnterior = null; //Null representa que todavia no hubo un primer polinomio calculado
	
	
	private double k;
	private double polinomioEvaluado;
	
	
	
	//METODOS POSTA

	public void agregarPunto() {
		
		puntos.add(new Punto(x, y));
		
		if(puntos.size() > 1)
			sonEquidistantesLosPuntos = Punto.sonEquiespaciados(puntos);
		
		if(hayPolinomioCalculado) {
			if(polinomioCalculado.evaluarEn(x) != y)  {			
				setStringComunicacion("El punto ingresado ha modificado el polinomio");
			} else {
				setStringComunicacion("El polinomio no ha sufrido cambios");
			}
		}
	}
	
	public void editarPunto() {
		
		puntoSeleccionado.editar(x,y);
	}
	
	public void borrarPunto() {
		
		puntos.remove(puntoSeleccionado);
	}
	
	public void definirMetodo() {
		
		if(metodoInterpolacionSeleccionado.equals("LAGRANGE")) {
			
			setMetodoAUsar(new Lagrange());
		}
		else if(metodoInterpolacionSeleccionado.equals("NEWTON_GREGORY")) {
			
			setMetodoAUsar(new NewtonGregory(estrategiaNewtonGregorySeleccionada));
		}
	}

	public void calcularPolinomio() {
		
		//polinomioCalculado = metodoInterpolacionSeleccionado.calcularPolinomioCon(puntos);
		
		//pasoIntermedio = metodoInterpolacionSeleccionado.calcularPasoIntermedioCon(puntos);
		
		//hacer algo para saber si el polinomio cambio o no cambio con respecto al calculo anterior actualizando la variable "esIgualAlPolinomioAnterior"
	
		hayPolinomioCalculado = true;
	}

	public void evaluarPolinomio() {
		
		polinomioEvaluado = polinomioCalculado.evaluarEn(k);
	}
	
	public void finalizar() {
		
		//ACA ABRIA QUE RESETEAR LOS PUNTOS Y EL POLINOMIO, POR LO QUE LEI EN LOS MAILS
		puntos.clear();
		stringComunicacion = "Calcule su primer polinomio";
	}




	
	
	
	
	
	public boolean getSonEquidistantesLosPuntos() {
		return sonEquidistantesLosPuntos;
	}
	
	
	public double getPolinomioEvaluado() {
		return polinomioEvaluado;
	}
	
	

	public EstrategiaNewtonGregory getEstrategiaNewtonGregorySeleccionada() {
		return estrategiaNewtonGregorySeleccionada;
	}



	public void setEstrategiaNewtonGregorySeleccionada(EstrategiaNewtonGregory estrategiaNewtonGregorySeleccionada) {
		this.estrategiaNewtonGregorySeleccionada = estrategiaNewtonGregorySeleccionada;
	}



	public List<EstrategiaNewtonGregory> getEstrategiasNewtonGregory() {
		return estrategiasNewtonGregory;
	}



	public void setEstrategiasNewtonGregory(List<EstrategiaNewtonGregory> estrategiasNewtonGregory) {
		this.estrategiasNewtonGregory = estrategiasNewtonGregory;
	}



	public Expresion getPolinomioCalculado() {
		return polinomioCalculado;
	}



	public void setPolinomioCalculado(Expresion polinomioCalculado) {
		this.polinomioCalculado = polinomioCalculado;
	}



	public Boolean getEsIgualAlPolinomioAnterior() {
		return esIgualAlPolinomioAnterior;
	}



	public void setEsIgualAlPolinomioAnterior(Boolean esIgualAlPolinomioAnterior) {
		this.esIgualAlPolinomioAnterior = esIgualAlPolinomioAnterior;
	}
	
	public double getK() {
		return k;
	}

	
	
	public double getX() {
		return x;
	}

	public void setX(double x) {
		this.x = x;
	}

	public double getY() {
		return y;
	}

	public void setY(double y) {
		this.y = y;
	}
	
	public List<Punto> getPuntos() {
		return puntos;
	}
	
	
	public MetodoInterpolacion getMetodoInterpolacionSeleccionado() {
		return metodoInterpolacionSeleccionado;
	}



	public void setMetodoInterpolacionSeleccionado(MetodoInterpolacion metodoInterpolacionSeleccionado) {
		this.metodoInterpolacionSeleccionado = metodoInterpolacionSeleccionado;
	}



	public void setPuntos(List<Punto> puntos) {
		this.puntos = puntos;
	}

	public String getStringComunicacion() {
		return stringComunicacion;
	}

	public void setStringComunicacion(String stringComunicacion) {
		this.stringComunicacion = stringComunicacion;
	}

	public List<MetodoInterpolacion> getMetodosInterpolacion() {
		return metodosInterpolacion;
	}

	public void setMetodosInterpolacion(List<MetodoInterpolacion> metodosInterpolacion) {
		this.metodosInterpolacion = metodosInterpolacion;
	}

	public MetodoDeCalculo getMetodoAUsar() {
		return metodoAUsar;
	}

	public void setMetodoAUsar(MetodoDeCalculo metodoAUsar) {
		this.metodoAUsar = metodoAUsar;
	}

	public int getGradoDelPolinomio() {
		return gradoDelPolinomio;
	}

	public void setGradoDelPolinomio(int gradoDelPolinomio) {
		this.gradoDelPolinomio = gradoDelPolinomio;
	}
}
