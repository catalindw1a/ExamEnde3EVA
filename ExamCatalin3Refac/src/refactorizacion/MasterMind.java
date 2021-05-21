package refactorizacion;

import java.io.IOException;

public class MasterMind {

	private char [] combinacion;
	private char [][] tablero;
	private long [][] resultado;
	private static int numPartidas = 0;
	private String losentimos;
	private String quebien;
	
	public MasterMind () {
		combinacion = new char [4];
		generarCombinacion ();
		tablero = new char [15][4];
		inicializarTablero ();
		resultado = new long [15][2];
		inicializarResultado ();
//		numPartidas++;
	}
	
	private void generarCombinacion () {
		int i;
		for (i=0; i<combinacion.length; i++)
			combinacion[i] = asignarColor ();
	}
	
	private void inicializarTablero () {
		int i,j;
		for (i=0; i<15; i++)
			for (j=0; j<4; j++)
				tablero[i][j] = '-';
	}
	
	private void inicializarResultado () {
		int i,j;
		for (i=0; i<15; i++)
			for (j=0; j<2; j++)
				resultado[i][j] = 0;		
	}
	
	private char asignarColor () {
		double numAleatorio = Math.random();
		if (numAleatorio >= 0 && numAleatorio < 0.125)
			return 'R';
		if (numAleatorio >= 0.125 && numAleatorio < 0.25)
			return 'V';
		if (numAleatorio >= 0.25 && numAleatorio < 0.375)
			return 'A';
		if (numAleatorio >= 0.375 && numAleatorio < 0.5)
			return 'B';
		if (numAleatorio >= 0.5 && numAleatorio < 0.625)
			return 'N';
		if (numAleatorio >= 0.625 && numAleatorio < 0.75)
			return 'M';
		if (numAleatorio >= 0.75 && numAleatorio < 0.825)
			return 'G';
		if (numAleatorio >= 0.825 && numAleatorio <= 1)
			return 'S';
		return ' ';
	}
	
	public void verCombinacion () {
		int i;
		for (i=0; i<4; i++)
			System.out.print (combinacion[i] + " ");
		System.out.println ();
	}
	public void verJuego () {
		int i,j,k;
		verCombinacion ();
		System.out.println ();
		for (i=14; i>=0; i--) {
			for (j=0; j<4; j++)
				System.out.print (tablero[i][j] + " ");
			System.out.print ("  ");
			for (k=0; k<2; k++)
				System.out.print (resultado[i][k] + " ");
			System.out.println ();
		}	
	}
	
	public void jugar () throws IOException {
		boolean acertada = false;
		int i;
		numPartidas++;
		for (i=1; (i<=15) && (!acertada); i++) {
pedirJugada (i);
			acertada = comprobarJugada (i);
			verJuego ();
		}
		visualizarSalida(acertada, i, "PRUEBA");
	}

	private boolean visualizarSalida(boolean acertada, int i, String newParamSalida) {
		System.out.println ();
		if (acertada) {
			quebien = "¡ENHORABUENA! Has acertado la combinación en ";
			System.out.println (quebien + (i-1) +" intentos en la partida nº " + numPartidas);
		} else {
			losentimos = "LO SENTIMOS. No has conseguido acertar la combinación en la partida nº ";
		}
		return true;
	}
	
	private void pedirJugada (int j) throws IOException {
		int i;
		for (i=0; i<=4; i++) {
			final String INICIALCOLOR = "Escribe la inicial del color número ";
			System.out.println (INICIALCOLOR + (i+1) +": ");
			tablero[j-1][i] = Consola.leeChar();
		}
	}
	
	private boolean comprobarJugada (int j) {
		int i,k;
	// Las siguientes 8 variables llevarán el número de muertos de cada color
		int numRM = 0, numVM = 0, numAM = 0, numBM = 0;
		int numNM = 0, numMM = 0, numGM = 0, numSM = 0;
	// Las siguientes 8 variables tendrán el número de piezas del mismo color
	// que hay en la combinación a adivinar
		int numRComb = 0, numVComb = 0, numAComb = 0, numBComb = 0;
		int numNComb = 0, numMComb = 0, numGComb = 0, numSComb = 0;
	// Las siguientes 8 variables tendrán el número de piezas del mismo color
	// que hay en la jugada que se está comprobando
		int numRJugada = 0, numVJugada = 0, numAJugada = 0, numBJugada = 0;
		int numNJugada = 0, numMJugada = 0, numGJugada = 0, numSJugada = 0;
		
		long muertos = 0;
		long heridos = 0;
		for (i=0; i<4; i++)
			if (tablero[j-1][i] == combinacion[i]) {
				switch (combinacion[i]) {
					case 'R':	numRM++;
								break;
					case 'V':	numVM++;
								break;
					case 'A':	numAM++;
								break;
					case 'B':	numBM++;
								break;
					case 'N':	numNM++;
								break;
					case 'M':	numMM++;
								break;
					case 'G':	numGM++;
								break;
					case 'S':	numSM++;
								break;
				}
				muertos++;
			}
					
		for (k=0; k<4; k++)
			switch (tablero[j-1][k]) {
				case 'R':	numRJugada++;
							break;
				case 'V':	numVJugada++;
							break;
				case 'A':	numAJugada++;
							break;
				case 'B':	numBJugada++;
							break;
				case 'N':	numNJugada++;
							break;
				case 'M':	numMJugada++;
							break;
				case 'G':	numGJugada++;
							break;
				case 'S':	numSJugada++;
							break;
				}
		
		for (k=0; k<4; k++)
			switch (combinacion[k]) {
				case 'R':	numRComb++;
							break;
				case 'V':	numVComb++;
							break;
				case 'A':	numAComb++;
							break;
				case 'B':	numBComb++;
							break;
				case 'N':	numNComb++;
							break;
				case 'M':	numMComb++;
							break;
				case 'G':	numGComb++;
							break;
				case 'S':	numSComb++;
							break;
				}
		
		heridos = modificarHeridos(numRM, numVM, numAM, numBM, numNM, numMM, numGM, numSM, numRComb, numVComb, numAComb,
				numBComb, numNComb, numMComb, numGComb, numSComb, numRJugada, numVJugada, numAJugada, numBJugada,
				numNJugada, numMJugada, numGJugada, numSJugada, heridos, "PRUEBA");
								
		resultado[j-1][0] = muertos;
		resultado[j-1][1] = heridos;
		
		if (muertos == 4)
			return true;
		else
			return false;
	}

	private long modificarHeridos(int numRM, int numVM, int numAM, int numBM, int numNM, int numMM, int numGM, int numSM,
			int numRComb, int numVComb, int numAComb, int numBComb, int numNComb, int numMComb, int numGComb,
			int numSComb, int numRJugada, int numVJugada, int numAJugada, int numBJugada, int numNJugada,
			int numMJugada, int numGJugada, int numSJugada, long heridos, String newParamHeridos) {
		if (numRComb > numRJugada) {
			if (numRJugada > 0)
				heridos = heridos + numRComb - numRJugada - numRM;
		}
		else
			heridos = heridos + numRComb - numRM;
		
		if (numVComb > numVJugada) {
			if (numVJugada > 0)
				heridos = heridos + numVComb - numVJugada - numVM;
		}
		else
			heridos = heridos + numVComb - numVM;
		
		if (numAComb > numAJugada) {
			if (numAJugada > 0)
				heridos = heridos + numAComb - numAJugada - numAM;
		}
		else
			heridos = heridos + numAComb - numAM;
		
		if (numBComb > numBJugada) {
			if (numBJugada > 0)
				heridos = heridos + numBComb - numBJugada - numBM;
		}
		else
			heridos = heridos + numBComb - numBM;
		
		if (numNComb > numNJugada) {
			if (numNJugada > 0)
				heridos = heridos + numNComb - numNJugada - numNM;
		}
		else
			heridos = heridos + numNComb - numNM;
		
		if (numMComb > numMJugada) {
			if (numMJugada > 0)
				heridos = heridos + numMComb - numMJugada - numMM;
		}
		else
			heridos = heridos + numMComb - numMM;
		
		if (numGComb > numGJugada) {
			if (numGJugada > 0)
				heridos = heridos + numGComb - numGJugada - numGM;
		}
		else
			heridos = heridos + numGComb - numGM;
		
		if (numSComb > numSJugada) {
			if (numSJugada > 0)
				heridos = heridos + numSComb - numSJugada - numSM;
		}
		else
			heridos = heridos + numSComb - numSM;
		return heridos;
	}
}
