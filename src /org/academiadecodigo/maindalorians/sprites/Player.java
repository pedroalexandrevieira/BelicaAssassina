package org.academiadecodigo.maindalorians.sprites;

import org.academiadecodigo.maindalorians.utils.ShotPool;

import static org.academiadecodigo.maindalorians.utils.Configs.*;


public class Player extends Sprite {

    private boolean shooting;
    private Direction currentDirection;

    public Player() {
        super(PLAYER_IMAGE_PATH, 0, 40);
        shooting = false;
        currentDirection = Direction.STILL;
    }

    public Shot createShot() {
        return ShotPool.pollAtCoordinates(representation.getMaxX(), representation.getY());
    }

    public void shoot() {
        shooting = true;
    }

    public void stopShooting() {
        shooting = false;
    }

    public void stopMoving() {
        currentDirection = Direction.STILL;
    }

    public void goUp() {
        currentDirection = Direction.UP;
    }

    public void goDown() {
        currentDirection = Direction.DOWN;
    }

    public boolean isShooting() {
        return shooting;
    }

    @Override
    public void move() {
        if(!isInvalidMovement()){
            representation.translate(currentDirection.xDelta, currentDirection.yDelta);
        }
    }

    @Override
    public boolean isInvalidMovement() {
        return currentDirection == Direction.UP && representation.getY() - PLAYER_SPEED <=  0
                || currentDirection == Direction.DOWN && representation.getMaxY() + PLAYER_SPEED >= GAME_HEIGHT;
    }

    protected enum Direction {
        STILL(0,0),
        UP(0, -PLAYER_SPEED),
        DOWN(0, PLAYER_SPEED);

        private int xDelta;
        private int yDelta;

        Direction(int xDelta, int yDelta) {
            this.xDelta = xDelta;
            this.yDelta = yDelta;
        }

    }
}
