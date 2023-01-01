package org.academiadecodigo.maindalorians.sprites;

import static org.academiadecodigo.maindalorians.utils.Configs.*;

public class Shot extends Sprite {


    public Shot() {
        super(SHOT_IMAGE_PATH, 0, 0);
    }

    public void moveToCoordinates(int x, int y) {
        int xDelta = x - representation.getX();
        int yDelta = y - representation.getY();
        representation.translate(xDelta, yDelta);
    }

    @Override
    public void move() {
        representation.translate(SHOT_SPEED, 0);
    }

    @Override
    public boolean isInvalidMovement() {
        return representation.getMaxX() + SHOT_SPEED >= GAME_WIDTH;
    }
}
