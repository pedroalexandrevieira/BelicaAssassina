package org.academiadecodigo.maindalorians.sprites;

import static org.academiadecodigo.maindalorians.utils.Configs.*;


public class Enemy extends Sprite {

    private Direction currentDirection;

    public Enemy() {
        //TODO choose how to handle y
        super(ENEMY_IMAGE_PATH, ENEMY_STARTING_X,40);
        currentDirection = Direction.getRandom();
    }

    @Override
    public void move() {
        if(isInvalidMovement()){
            currentDirection = currentDirection.getOpposite();
        }
        representation.translate(currentDirection.xDelta, currentDirection.yDelta);
    }

    @Override
    public boolean isInvalidMovement() {
        return currentDirection == Direction.DIAGONAL_UP && representation.getY() - ENEMY_SPEED <= 0
                || currentDirection == Direction.DIAGONAL_DOWN && representation.getMaxY() + ENEMY_SPEED >= GAME_HEIGHT;
    }

    public boolean isTouchingLeftEdge(){
        return representation.getX() <= 0;
    }

    public void resetPos(){
        int xDelta = ENEMY_STARTING_X - representation.getX();
        representation.translate(xDelta, 0);
    }

    private enum Direction{
        DIAGONAL_UP(-ENEMY_SPEED, -ENEMY_SPEED),
        DIAGONAL_DOWN(-ENEMY_SPEED, ENEMY_SPEED);

        private final int xDelta;
        private final int yDelta;

        Direction(int xDelta, int yDelta) {
            this.xDelta = xDelta;
            this.yDelta = yDelta;
        }

        public static Direction getRandom(){
            return values()[(int)(Math.random() * values().length)];
        }

        public Direction getOpposite(){
            switch (this){
                case DIAGONAL_UP:
                    return DIAGONAL_DOWN;
                case DIAGONAL_DOWN:
                    return DIAGONAL_UP;
                default:
                    //not the most correct one
                    throw new UnsupportedOperationException();
            }
        }
    }
}
