package front.vm;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.uqbar.commons.model.UserException;
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
	private Boolean sonEquidistantesLosPuntos;
	
	private List<MetodoInterpolacion> metodosInterpolacion = Arrays.asList(MetodoInterpolacion.values());
	private MetodoInterpolacion metodoInterpolacionSeleccionado = MetodoInterpolacion.LAGRANGE;
	private List<EstrategiaNewtonGregory> estrategiasNewtonGregory = Arrays.asList(EstrategiaNewtonGregory.values());
	private EstrategiaNewtonGregory estrategiaNewtonGregorySeleccionada = EstrategiaNewtonGregory.PROGRESIVO;
	private MetodoDeCalculo metodoAUsar = new Lagrange();
	
	private String stringComunicacion = "Calcule su primer polinomio";
	private Expresion polinomioCalculado;
	private String pasoIntermedio;
	private int gradoDelPolinomio;
	private boolean hayPolinomioCalculado;
	
	private double k;
	private double polinomioEvaluado;
	
	
	
	//METODOS POSTA

	public void agregarPunto() {
		
		puntos.add(new Punto(x, y));
		
		if(puntos.size() > 1)
			sonEquidistantesLosPuntos = Punto.sonEquiespaciados(puntos);
	}
	
	public void editarPunto() {
		
		puntoSeleccionado.editar(x,y);
	}
	
	public void borrarPunto() {
		
		puntos.remove(puntoSeleccionado);
	
		if(puntos.size() > 1)
			sonEquidistantesLosPuntos = Punto.sonEquiespaciados(puntos);
	}
	
	public void definirMetodo() {
		
		if(metodoInterpolacionSeleccionado == MetodoInterpolacion.LAGRANGE) {
			
			setMetodoAUsar(new Lagrange());
		}
		else if(metodoInterpolacionSeleccionado == MetodoInterpolacion.NEWTON_GREGORY) {
			
			setMetodoAUsar(new NewtonGregory(estrategiaNewtonGregorySeleccionada));
		}
	}

	public void calcularPolinomio() {
		
		definirMetodo();
		
		if(puntos.size() < 2)
			throw new UserException("Para calcular necesita al menos 2 puntos");
		
		if(hayPolinomioCalculado) {
			if(polinomioCalculado.evaluarEn(x) != y)  {			
				setStringComunicacion("El punto ingresado ha modificado el polinomio");
			} else {
				setStringComunicacion("El polinomio no ha sufrido cambios");
			}
		}
		else {
			setStringComunicacion("Ahora, pruebe ingresando nuevos puntos y recalculando para ver si el polinomio se modifica");
		}
		
		polinomioCalculado = metodoAUsar.calcularPolinomioCon(puntos);
		
		pasoIntermedio = metodoAUsar.calcularPasoIntermedioCon(puntos);
	
		gradoDelPolinomio = new NewtonGregory(EstrategiaNewtonGregory.PROGRESIVO).calcularGrado(puntos);
		
		hayPolinomioCalculado = true;
	}

	public void evaluarPolinomio() {
		
		polinomioEvaluado = polinomioCalculado.evaluarEn(k);
	}
	
	public void finalizar() {
		
		//ACA ABRIA QUE RESETEAR LOS PUNTOS Y EL POLINOMIO, POR LO QUE LEI EN LOS MAILS
		puntos.clear();
		stringComunicacion = "Calcule su primer polinomio";
		polinomioCalculado = null;
		gradoDelPolinomio = 0;
		x = 0;
		y = 0;
		k = 0;
		polinomioEvaluado = 0;
		pasoIntermedio = "";
		hayPolinomioCalculado = false;
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
	
	public double getK() {
		return k;
	}
	
	public void setK(double k) {
		this.k = k;
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
	
	public Punto getPuntoSeleccionado() {
		return puntoSeleccionado;
	}
	
	public void setPuntoSeleccionado(Punto puntoSeleccionado) {
		this.puntoSeleccionado = puntoSeleccionado;
	}

	public String getPasoIntermedio() {
		return pasoIntermedio;
	}

	public void setPasoIntermedio(String pasoIntermedio) {
		this.pasoIntermedio = pasoIntermedio;
	}

}
