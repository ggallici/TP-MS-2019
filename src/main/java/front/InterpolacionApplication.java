package front;

import org.uqbar.arena.Application;
import org.uqbar.arena.windows.Window;

import front.vistas.PrincipalWindow;

public class InterpolacionApplication extends Application {

	public static void main(String[] args) throws Exception {		
		
		new InterpolacionApplication().start();
	}

	@Override
	protected Window<?> createMainWindow() {
		
		return new PrincipalWindow(this);
	}
}
