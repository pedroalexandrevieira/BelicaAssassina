package org.academiadecodigo.maindalorians;

import org.academiadecodigo.maindalorians.keyboard.KeyboardManager;
import org.academiadecodigo.maindalorians.phases.Game;
import org.academiadecodigo.maindalorians.phases.GameOver;
import org.academiadecodigo.maindalorians.phases.Start;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
public class BelicaAssassinaGameApplication {

	public static void main(String[] args) {

		Start startPhase = new Start();
		Game game = new Game();
		GameOver gameOver = new GameOver();

		KeyboardManager keyboardManager = new KeyboardManager(startPhase);

		startPhase.execute();

		while(true){
			keyboardManager.setActiveHandler(game);
			game.execute();
			keyboardManager.setActiveHandler(gameOver);
			gameOver.execute();
		}
	}
}
