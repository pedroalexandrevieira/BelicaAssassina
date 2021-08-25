package org.academiadecodigo.maindalorians;

import org.academiadecodigo.simplegraphics.pictures.Picture;

public class Shots {
    private Game game;
    private Picture rect;
    private Picture ship;
    private Picture[] shots = new Picture[50];
    private final int ZERO = 0;
    private final int TWO = 2;
    private final int SPEED = 20;
    private final int XBORDER = 1185;

    public Shots(Picture rect, Picture ship, Game game) {
        this.rect = rect;
        this.ship = ship;
        this.game = game;
    }

    public void fireShot() {
        for (int i = ZERO; i < shots.length; i++) {
            if (shots[i] == null) {
                shots[i] = new Picture(ship.getMaxX(), (ship.getY() + (int) ((ship.getMaxY() - ship.getY()) / TWO)) - TWO, "resources/fireshot.png");
                if (ship.getMaxX() + shots[i].getWidth() < rect.getWidth()) {
                    shots[i].draw();
                    System.out.println(i + "boas ");
                    break;
                }
                shots[i] = null;
            }
        }
    }

    public void moveShot(int i) {
        if (shots[i].getMaxX() > XBORDER) {
            shots[i].delete();
            shots[i] = null;
            System.out.println(i);
            System.out.println(shots[i]);

        }
        if (shots[i] != null) {
            shots[i].translate(SPEED, ZERO);
        }
    }

    public Picture[] getShots() {
        return shots;
    }
}
