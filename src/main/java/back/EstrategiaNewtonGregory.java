package back;

import back.expresiones.Expresion;

public enum EstrategiaNewtonGregory {

	PROGRESIVO {
		
		@Override
		public Expresion calcularPolinomioCon(MatrizNewtonGregory matriz) {
			
			return matriz.calcularPolinomioProgresivo();
		}
	}
	
	,
	
	REGRESIVO {
		
		@Override
		public Expresion calcularPolinomioCon(MatrizNewtonGregory matriz) {
			
			return matriz.calcularPolinomioRegresivo();
		}
	}
	
	;
	
	public abstract Expresion calcularPolinomioCon(MatrizNewtonGregory matriz);
}