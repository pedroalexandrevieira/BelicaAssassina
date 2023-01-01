package org.academiadecodigo.maindalorians.sprites;

import org.academiadecodigo.simplegraphics.pictures.Picture;

public abstract class Sprite {

    protected Picture representation;

    public Sprite(String representationPath, int x, int y) {
        representation = new Picture(x, y, representationPath);
    }

    public abstract void move();

    public abstract boolean isInvalidMovement();

    public void show() {
        representation.draw();
    }

    public void hide() {
        representation.delete();
    }

    public boolean isColldingWith(Sprite sprite) {
        return sprite.representation.getY() <= representation.getMaxY()
                && sprite.representation.getX() <= representation.getMaxX()
                && sprite.representation.getMaxX() >= representation.getX()
                && sprite.representation.getMaxY() >= representation.getY();
    }

}
