package uo.lozana.cueto.miguel.minesweeper.session;


import uo.mp.minesweeper.util.log.SimpleLogger;

import uo.lozana.cueto.miguel.minesweeper.game.Game;
import uo.lozana.cueto.miguel.minesweeper.game.GameInteractor;
import uo.lozana.cueto.miguel.minesweeper.game.board.Board;
import uo.lozana.cueto.miguel.minesweeper.ranking.GameRanking;
import uo.lozana.cueto.miguel.minesweeper.ranking.GameRankingEntry;
import uo.mp.util.check.ArgumentChecks;

public class GameSession {
	private static final String INVALID_OPTION_MESSAGE = "Invalid option";
	
	private GameInteractor gameInteractor;
	private SessionInteractor sesion;
	private SimpleLogger logger;
	private GameRanking ranking;
	private String name; 
	
	public GameSession() {
		// Nothing here by design
	}
	
	public GameSession(GameInteractor gameInteractor, SessionInteractor sesion, SimpleLogger logger, GameRanking ranking) {
		setGameInteractor(gameInteractor);
		setSessionInteractor(sesion);
		setLogger(logger);
		setGameRanking(ranking);
	}
	
	/*===========================================
	 * 				RUN
	 * ========================================
	 */
	
	
	public void run() {
	    try {
	    	this.name = sesion.askUserName(); 
            mainLoop();
            sesion.showGoodBye();
	    } catch (RuntimeException e) {       
	        logger.log(e);                   
	        sesion.showFatalErrorMessage("Error interno irrecuperable.");
	    }
	}
	/*===========================================
	 * 				SETERS
	 * ========================================
	 */
	public void setGameInteractor(GameInteractor interactor) {
		ArgumentChecks.isNotNull(interactor,"The Game Interactor mus not be null");
		this.gameInteractor = interactor;
	}
	public void setSessionInteractor(SessionInteractor interactor) {
		ArgumentChecks.isNotNull(interactor,"The Sesion Interactor mus not be null");
		this.sesion = interactor;
	}
	
	public void setLogger(SimpleLogger logger) {
		ArgumentChecks.isNotNull(logger,"The Sesion logger mus not be null");
		this.logger = logger;
	}
	public void setGameRanking(GameRanking ranking) {
		ArgumentChecks.isNotNull(ranking,"The Sesion logger mus not be null");
		this.ranking = ranking;
	}


	/*===========================================
	 * 				PRIVATE HELPING 
	 * ========================================
	 */
	
	
	
	private void mainLoop() {
	    int option;
	    while ((option = sesion.askNextOption()) != 0) { 
	        try {
	            handleOption(option); 
	        } catch (GameException e) {
	        	sesion.showErrorMessage(e.getMessage());
	        }
	    }
	}
	

	private void handleOption(int option) throws GameException {
		switch (option) { 
		case 1 -> playGame();
		case 2 -> sesion.showRanking(ranking.getAllEntries());
		case 3 -> sesion.showPersonalRanking(ranking.getEntriesForUsername(this.name));
		default -> throw new GameException(INVALID_OPTION_MESSAGE);
		};
		
	}
	
	
	
	private void playGame() {
		
		GameLevel  level = sesion.askGameLevel();
	
		Board board = switch (level) {
	    case  FACIL -> new Board(9,9,12);
	    case MEDIANO-> new Board(16,16,15);
	    case DIFICIL-> new Board(30,16,20);
	    default -> throw new RuntimeException("Nivel no reconocido");
		}; 
		Game game = new Game(board);
		game.setInteractor(gameInteractor);
		game.play();
		if(sesion.doYouWantToRegisterYourScore() && game.hasWon()) {
			long duration = game.getDuration();
			ranking.append(new GameRankingEntry(this.name, level, duration, game.hasWon()));
		}
	} 
	
	
	
}
//FALTA:
//*DOCCUEMTNAR
/*test
 *e xceptiones
 *En GameRankingEntry (Errores Críticos):

Constructor vacío: El constructor no asigna los parámetros (userName, level, duration, hasWon) a los atributos de la clase; el objeto se crea sin datos.

Atributo de fecha: No se almacena la fecha en un atributo en el constructor. El método getDate() genera una fecha nueva (now()) cada vez que se llama, en lugar de devolver el momento en que terminó la partida.

Error de formato en toString: El String.format tiene un error de concordancia entre los marcadores %s y los argumentos pasados (falta el argumento para el nombre de usuario tras el literal "User:").

En GameSession:

Restricción de líneas: El método run() excede las 7 líneas de código permitidas (tiene 9 líneas de lógica).

Ubicación de GameException: Está definida como clase interna; debe ser una clase independiente en su propio archivo.

En ConsoleSessionInteractor (Interacción):

Método incompleto: doYouWantToRegisterYourScore() siempre devuelve false y no pregunta al usuario.

Formato de Ranking: Los métodos showRanking y showPersonalRanking no usan formato tabular (columnas alineadas); solo imprimen el toString.

Privacidad en Ranking: showPersonalRanking no oculta la columna del nombre del usuario (debería omitirla al ser el usuario de la sesión).

Validación de askNextOption: El rango de opciones (0 a 3) no coincide con las 4 acciones requeridas (Jugar, Ranking Total, Ranking Propio, Salir).

Entrada de Nivel: askGameLevel espera que el usuario escriba exactamente "FACIL", "MEDIANO" o "DIFICIL" en lugar de usar un sistema de selección numérica o más robusto, y lanza RuntimeException si falla.

Prompts ausentes: askUserName no muestra un mensaje en pantalla solicitando el nombre antes de leer la entrada.

Otros:

Documentación y Test: Falta la documentación Javadoc y los escenarios de prueba unitaria para GameRanking mencionados en las instrucciones.

Diagramas: Faltan los archivos PNG en la carpeta /uml.
 *
 *
 * 
 * 
 * 
 * 
 * 
 * 
 * 
 * 
 * 
 * /
 */