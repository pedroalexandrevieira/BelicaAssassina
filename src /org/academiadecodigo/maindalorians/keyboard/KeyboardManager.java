package org.academiadecodigo.maindalorians.keyboard;

import org.academiadecodigo.simplegraphics.keyboard.Keyboard;
import org.academiadecodigo.simplegraphics.keyboard.KeyboardEvent;
import org.academiadecodigo.simplegraphics.keyboard.KeyboardHandler;

public class KeyboardManager implements KeyboardHandler {

    private KeyboardHandler activeHandler;

    public KeyboardManager(KeyboardHandler startingHandler){
        Keyboard kb = new Keyboard(this);

        setupKeyboard(kb);

        activeHandler = startingHandler;
    }

    private void setupKeyboard(Keyboard keyboard) {
        for (Key k: Key.values()) {
            KeyboardEvent event = new KeyboardEvent();
            event.setKey(k.keyNumber);
            event.setKeyboardEventType(k.type);
            keyboard.addEventListener(event);
        }
    }

    public void setActiveHandler(KeyboardHandler activeHandler) {
        this.activeHandler = activeHandler;
    }

    @Override
    public void keyPressed(KeyboardEvent keyboardEvent) {
        if(keyboardEvent.getKey() == KeyboardEvent.KEY_Q){
            System.exit(0);
        }
        activeHandler.keyPressed(keyboardEvent);
    }

    @Override
    public void keyReleased(KeyboardEvent keyboardEvent) {
        activeHandler.keyReleased(keyboardEvent);
    }
}
