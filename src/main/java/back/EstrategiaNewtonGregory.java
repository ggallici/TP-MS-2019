package back;

public enum EstrategiaNewtonGregory {

	PROGRESIVO {
		
		@Override
		public int getFilaCoeficiente(int filas, int columnaActual) {
			
			return 0;
		}
		
		@Override
		public int getFilaXi(int filas, int filaActual) {
			
			return filaActual;
		}
	}
	
	,
	
	REGRESIVO {
		
		@Override
		public int getFilaCoeficiente(int filas, int columnaActual) {
			
			return filas - columnaActual;
		}
		
		@Override
		public int getFilaXi(int filas, int filaActual) {
			
			return filas - filaActual - 1;
		}
	}
	
	;
	
	public abstract int getFilaCoeficiente(int filas, int columnaActual);
	
	public abstract int getFilaXi(int filas, int filaActual);
}