package front.vistas;

import java.awt.Color;

import org.apache.commons.collections15.Transformer;
import org.uqbar.arena.bindings.ValueTransformer;
import org.uqbar.arena.layout.ColumnLayout;
import org.uqbar.arena.layout.HorizontalLayout;
import org.uqbar.arena.layout.VerticalLayout;
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
		
		Panel panelEdicion = new Panel(panelPuntos);
		panelEdicion.setLayout(new HorizontalLayout());
		//new Button(panelEdicion).setCaption("Editar punto").onClick(this::editarPunto);
		new Button(panelEdicion).setCaption("Borrar punto").onClick(this::borrarPunto);
		Panel panelSonPuntosEquidistantes = new Panel(panelPuntos);
		panelSonPuntosEquidistantes.setLayout(new HorizontalLayout());
		new Label(panelSonPuntosEquidistantes).setText("Son puntos equiespaciados?: ");
		new Label(panelSonPuntosEquidistantes).bindValueToProperty("sonEquidistantesLosPuntos").setTransformer(new ValueTransformer<Boolean, String>() {

			@Override
			public Boolean viewToModel(String valueFromView) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public String modelToView(Boolean valueFromModel) {
				
				return valueFromModel.booleanValue() ? "Si" : "No";
			}

			@Override
			public Class<Boolean> getModelType() {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public Class<String> getViewType() {
				// TODO Auto-generated method stub
				return null;
			}});;
		
		//CALCULO DEL POLINOMIO
		GroupPanel panelPolinomio = new GroupPanel(panelPrincipal);
		panelPolinomio.setTitle("Metodos de calculo ");
		Selector<MetodoInterpolacion> selectorMetodo = new Selector<MetodoInterpolacion>(panelPolinomio);
		selectorMetodo.bindItemsToProperty("metodosInterpolacion");
		selectorMetodo.bindValueToProperty("metodoInterpolacionSeleccionado");
		selectorMetodo.allowNull(false);
		Selector<EstrategiaNewtonGregory> radioSelector = new RadioSelector<EstrategiaNewtonGregory>(panelPolinomio);
		radioSelector.bindItemsToProperty("estrategiasNewtonGregory");
		radioSelector.bindValueToProperty("estrategiaNewtonGregorySeleccionada");
		radioSelector.allowNull(false);
		new Button(panelPolinomio).setCaption("Calcular").onClick(this::calcularPolinomio);
		new Label(panelPolinomio).bindValueToProperty("polinomioCalculado");
		GroupPanel panelPasos = new GroupPanel(panelPolinomio);
		panelPasos.setWidth(400);
		panelPasos.setTitle("Pasos");
		new Label(panelPasos).setWidth(380).bindValueToProperty("pasoIntermedio");
		Panel panelGrado = new Panel(panelPolinomio);
		panelGrado.setLayout(new HorizontalLayout());
		new Label(panelGrado).setText("Grado del polinomio: ");
		new Label(panelGrado).bindValueToProperty("gradoDelPolinomio");
		new Label(panelPolinomio).bindValueToProperty("stringComunicacion");
		
		//EVALUACION DEL POLINOMIO
		GroupPanel panelEvaluacion = new GroupPanel(panelPrincipal);
		panelEvaluacion.setTitle("Evaluacion ");
		panelEvaluacion.setLayout(new VerticalLayout());
		Panel acciones = new Panel(panelEvaluacion);
		acciones.setLayout(new HorizontalLayout());
		new Label(acciones).setText("Evaluar en K = ");
		new NumericField(acciones).bindValueToProperty("k");
		new Button(acciones).setCaption("Evaluar").onClick(this::evaluarPolinomio);
		new Label(acciones).setText("Resultado de evaluar el polinomio: ");
		new Label(acciones).bindValueToProperty("polinomioEvaluado");
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