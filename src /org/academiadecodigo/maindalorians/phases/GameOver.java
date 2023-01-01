package org.academiadecodigo.maindalorians.phases;

import org.academiadecodigo.simplegraphics.keyboard.KeyboardEvent;
import org.academiadecodigo.simplegraphics.keyboard.KeyboardHandler;
import org.academiadecodigo.simplegraphics.pictures.Picture;

import static org.academiadecodigo.maindalorians.utils.Configs.GAME_OVER_BACKGROUND;

public class GameOver implements KeyboardHandler {

    private final Picture background;
    private volatile boolean idling;

    public GameOver() {
        background = new Picture(0,0, GAME_OVER_BACKGROUND);
        idling = true;
    }

    public void execute(){
        background.draw();
        while (idling){
        }
        cleanup();
    }

    private void cleanup() {
        background.delete();
        idling=true;
    }

    @Override
    public void keyPressed(KeyboardEvent event) {
        switch (event.getKey()){
            case KeyboardEvent.KEY_ENTER:
                idling = false;
                break;
        }
    }

    @Override
    public void keyReleased(KeyboardEvent event) {

    }
}
