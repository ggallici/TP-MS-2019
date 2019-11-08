package front.vistas;

import org.uqbar.arena.widgets.Panel;
import org.uqbar.arena.windows.SimpleWindow;
import org.uqbar.arena.windows.WindowOwner;

import front.vm.PrincipalVM;

@SuppressWarnings("serial")
public class PrincipalWindow extends SimpleWindow<PrincipalVM>{
	
	public PrincipalWindow(WindowOwner parent) {
		super(parent, new PrincipalVM());
		// TODO Auto-generated constructor stub
	}

	@Override
	protected void addActions(Panel actionsPanel) {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void createFormPanel(Panel mainPanel) {
		// TODO Auto-generated method stub
		
	}
}