package front.vistas;

import java.awt.Color;

import org.uqbar.arena.layout.ColumnLayout;
import org.uqbar.arena.layout.HorizontalLayout;
import org.uqbar.arena.widgets.Button;
import org.uqbar.arena.widgets.CheckBox;
import org.uqbar.arena.widgets.GroupPanel;
import org.uqbar.arena.widgets.KeyWordTextArea;
import org.uqbar.arena.widgets.Label;
import org.uqbar.arena.widgets.NumericField;
import org.uqbar.arena.widgets.Panel;
import org.uqbar.arena.widgets.RadioSelector;
import org.uqbar.arena.widgets.Selector;
import org.uqbar.arena.widgets.TextBox;
import org.uqbar.arena.widgets.tables.Column;
import org.uqbar.arena.widgets.tables.Table;
import org.uqbar.arena.windows.SimpleWindow;
import org.uqbar.arena.windows.WindowOwner;

import back.EstrategiaNewtonGregory;
import back.Lagrange;
import back.Punto;
import front.vm.MetodoInterpolacion;
import front.vm.PrincipalVM;

@SuppressWarnings("serial")
public class PrincipalWindow extends SimpleWindow<PrincipalVM>{
	
	public PrincipalWindow(WindowOwner parent) {
		super(parent, new PrincipalVM());
		// TODO Auto-generated constructor stub
	}

	@Override
	protected void createFormPanel(Panel panelPrincipal) {
		
		setTitle("Calculadora de polinomios interpolantes");
		
		//ALTA DE PUNTOS
		GroupPanel panelAltaPuntos = new GroupPanel(panelPrincipal);
		panelAltaPuntos.setTitle("Alta de puntos ");
		panelAltaPuntos.setLayout(new HorizontalLayout());
		new Label(panelAltaPuntos).setText("X = ").setWidth(50);
		new NumericField(panelAltaPuntos).setWidth(150).bindValueToProperty("x");
		new Label(panelAltaPuntos).setText("Y = ").setWidth(50);
		new NumericField(panelAltaPuntos).setWidth(150).bindValueToProperty("y");
		new Button(panelAltaPuntos).setCaption("Agregar punto").onClick(this::agregarPunto);
		
		//TABLA DE PUNTOS
		GroupPanel panelPuntos = new GroupPanel(panelPrincipal);
		panelPuntos.setTitle("Puntos ");
		Panel panelTablaPuntos = new Panel(panelPuntos);
		Table<Punto> tablaPuntos = new Table<>(panelTablaPuntos, Punto.class);
		tablaPuntos.setNumberVisibleRows(5);
		tablaPuntos.bindItemsToProperty("puntos");
		tablaPuntos.bindSelectionToProperty("puntoSeleccionado");
		new Column<Punto>(tablaPuntos).setTitle("X").setFixedSize(265).bindContentsToProperty("x");
		new Column<Punto>(tablaPuntos).setTitle("Y").setFixedSize(265).bindContentsToProperty("y");
		//EDITAR Y BORRAR PUNTOS, SI NO LO PUEDO METER EN UNA COLUMNA, QUE SEAN BOTONES COMUNES
		Panel panelEdicion = new Panel(panelPuntos);
		panelEdicion.setLayout(new HorizontalLayout());
		//new Button(panelEdicion).setCaption("Editar punto").onClick(this::editarPunto);
		new Button(panelEdicion).setCaption("Borrar punto").onClick(this::borrarPunto);
		//new Column<Punto>(tablaPuntos).setFixedSize(50);
		//new Column<Punto>(tablaPuntos).setFixedSize(50);
		Panel panelSonPuntosEquidistantes = new Panel(panelPuntos);
		panelSonPuntosEquidistantes.setLayout(new HorizontalLayout());
		new Label(panelSonPuntosEquidistantes).setText("¿Son puntos equiespaciados?: ");
		new Label(panelSonPuntosEquidistantes).bindValueToProperty("sonEquidistantesLosPuntos");
		
		//CALCULO DEL POLINOMIO
		GroupPanel panelPolinomio = new GroupPanel(panelPrincipal);
		panelPolinomio.setTitle("Metodos de calculo ");
		Selector<MetodoInterpolacion> selectorMetodo = new Selector<MetodoInterpolacion>(panelPolinomio);
		selectorMetodo.bindItemsToProperty("metodosInterpolacion");
		selectorMetodo.bindValueToProperty("metodoInterpolacionSeleccionado");
		selectorMetodo.onSelection(this::definirMetodo);
		selectorMetodo.allowNull(false);
		Selector<EstrategiaNewtonGregory> radioSelector = new RadioSelector<EstrategiaNewtonGregory>(panelPolinomio);
		radioSelector.bindItemsToProperty("estrategiasNewtonGregory");
		radioSelector.bindValueToProperty("estrategiaNewtonGregorySeleccionada");
		radioSelector.allowNull(false);
		new Button(panelPolinomio).setCaption("Calcular").onClick(this::calcularPolinomio);
		new Label(panelPolinomio).bindValueToProperty("stringComunicacion");
		new Label(panelPolinomio).bindValueToProperty("polinomioCalculado");
		new Label(panelPolinomio).bindValueToProperty("esIgualAlPolinomioAnterior");
		GroupPanel panelPasos = new GroupPanel(panelPolinomio);
		panelPasos.setWidth(400);
		panelPasos.setTitle("Pasos");
		new Label(panelPasos).setWidth(380).bindValueToProperty("pasoIntermedio");
		Panel panelGrado = new Panel(panelPolinomio);
		panelGrado.setLayout(new HorizontalLayout());
		new Label(panelGrado).setText("Grado del polinomio: ");
		new Label(panelGrado).bindValueToProperty("gradoDelPolinomio");
		
		//EVALUACION DEL POLINOMIO
		GroupPanel panelEvaluacion = new GroupPanel(panelPrincipal);
		panelEvaluacion.setTitle("Evaluacion ");
		panelEvaluacion.setLayout(new HorizontalLayout());
		new Label(panelEvaluacion).setText("Evaluar en K = ");
		new NumericField(panelEvaluacion).bindValueToProperty("k");
		new Button(panelEvaluacion).setCaption("Evaluar").onClick(this::evaluarPolinomio);
		new Label(panelEvaluacion).setText("EL POLINOMIO EVALUADO EN K DA: ");
		new Label(panelEvaluacion).bindValueToProperty("polinomioEvaluado");
		new Button(panelEvaluacion).setCaption("Limpiar campos").onClick(this::finalizar);
	}
	
	@Override
	protected void addActions(Panel actionsPanel) { }
	
	public void agregarPunto() {
		getModelObject().agregarPunto();
	}
	
	public void editarPunto() {
		
		getModelObject().editarPunto();
	}
	public void borrarPunto() {
		
		getModelObject().borrarPunto();
	}
	
	public void calcularPolinomio() {
		
		getModelObject().calcularPolinomio();
	}
	
	public void evaluarPolinomio() {
		
		getModelObject().evaluarPolinomio();
	}
	
	public void finalizar() {
		
		getModelObject().finalizar();
	}
	
	public void definirMetodo() {
		getModelObject().definirMetodo();
	}
}