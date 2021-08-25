package org.academiadecodigo.maindalorians;

import org.academiadecodigo.simplegraphics.keyboard.Keyboard;
import org.academiadecodigo.simplegraphics.keyboard.KeyboardEvent;
import org.academiadecodigo.simplegraphics.keyboard.KeyboardEventType;
import org.academiadecodigo.simplegraphics.keyboard.KeyboardHandler;
import org.academiadecodigo.simplegraphics.pictures.Picture;

public class Start implements KeyboardHandler {
    private Game game;
    private static Start handler;
    private final int ZERO = 0;
    private Picture startGame = new Picture(ZERO, ZERO, "resources/background.png");
    private Picture gameStart = new Picture(310, 80, "resources/splash.png");

    public Start(Game game) {
        this.game = game;
    }

    public void initScreen() {

        handler = new Start(game);

        Keyboard keyboard = new Keyboard(handler);
        KeyboardEvent enterKeyPress = new KeyboardEvent();
        enterKeyPress.setKey(KeyboardEvent.KEY_ENTER);
        enterKeyPress.setKeyboardEventType(KeyboardEventType.KEY_PRESSED);
        keyboard.addEventListener(enterKeyPress);

        startGame.draw();
        gameStart.draw();
    }

    @Override
    public void keyPressed(KeyboardEvent event) {

        if (event.getKey() == KeyboardEvent.KEY_ENTER) {
            game.setScreen(true);


        }
    }

    @Override
    public void keyReleased(KeyboardEvent keyboardEvent) {

    }


}
