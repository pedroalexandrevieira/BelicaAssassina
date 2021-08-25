package org.academiadecodigo.maindalorians;

import org.academiadecodigo.simplegraphics.pictures.Picture;

public class Belica {
    private Game game;
    private Picture rect;
    private Picture ship;
    private final int ZERO = 0;
    private final int TWO = 2;
    private final int SPEED = 20;
    private final int XBORDER = 1185;
    private final int YBORDER = 720;

    public Belica(Picture rect, Game game) {
        this.rect = rect;
        this.game = game;
        this.ship = new Picture(ZERO, (int) (rect.getHeight() / TWO), "resources/blica2.png");
        ship.draw();
    }

    public void moveShipUp() {
        if (ship.getY() > ZERO) {
            ship.translate(ZERO, -SPEED);
        }
    }

    public void moveShipDown() {
        if (ship.getMaxY() < YBORDER) {
            ship.translate(ZERO, SPEED);

        }
    }

    public void moveShipRight() {
        if (ship.getMaxX() < XBORDER) {
            ship.translate(SPEED, ZERO);
        }
    }

    public void moveShipLeft() {
        if (ship.getX() > ZERO) {
            ship.translate(-SPEED, ZERO);
        }
    }

    public Picture getShip() {
        return ship;
    }
}
