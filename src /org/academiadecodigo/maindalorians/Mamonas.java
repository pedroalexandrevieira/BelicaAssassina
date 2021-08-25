package org.academiadecodigo.maindalorians;

import org.academiadecodigo.simplegraphics.pictures.Picture;

import java.util.Arrays;

public class Mamonas {
    private Game game;
    private Picture[] mamona = new Picture[50];
    private Picture rect;
    private Picture ship;
    private String[] direction = new String[50];
    private int[] counter = new int[50];
    private final int BORDER = 10;
    private final int SPEED = 15;
    private final int ZERO = 0;
    private final int YBORDER = 720;
    private final int XSPAWNBORDER = 160;
    private final int YSPAWNBORDER = 170;


    public Mamonas(Picture rect, Picture ship, Game game) {
        this.rect = rect;
        this.ship = ship;
        this.game = game;
        Arrays.fill(counter, BORDER);
    }

    public void createMamona() {

        for (int i = ZERO; i < mamona.length; i++) {
            if (mamona[i] == null) {
                mamona[i] = new Picture((int) (Math.random() +
                        (rect.getWidth() - XSPAWNBORDER)), (int) (Math.random() * (rect.getHeight() - YSPAWNBORDER)), "resources/boobies1.png");
                mamona[i].draw();
                break;
            }
        }
    }

    public Picture[] getMamonas() {
        return mamona;
    }

    public void moveMamonas(int i) {
        if (mamona[i].getX() < BORDER) {
            game.setGameOver(true);
            counter[i] = BORDER;
        } else if (mamona[i].getY() < BORDER) {
            mamona[i].translate(ZERO, SPEED);
            counter[i] = BORDER;
        } else if (mamona[i].getMaxY() > YBORDER) {
            mamona[i].translate(ZERO, -SPEED);
            counter[i] = BORDER;
        }
        if (counter[i] < BORDER) {
            if (direction[i] == "up") {
                mamona[i].translate(ZERO, SPEED);
                counter[i]++;
            } else if (direction[i] == "down") {
                mamona[i].translate(ZERO, -SPEED);
                counter[i]++;
            } else if (direction[i] == "left") {
                mamona[i].translate(-SPEED, ZERO);
                counter[i]++;
            }
        }
        if (counter[i] == BORDER) {
            int random = (int) (Math.random() * (BORDER * BORDER));

            if (random > (YSPAWNBORDER - (BORDER * BORDER))) {
                mamona[i].translate(ZERO, BORDER);
                direction[i] = "up";
                System.out.println("up");
                System.out.println("ez bugzito");
                counter[i] = ZERO;

            } else if (SPEED + SPEED + BORDER < random) {
                mamona[i].translate(ZERO, -BORDER);
                direction[i] = "down";
                System.out.println("down");
                System.out.println("ez bugzit");
                counter[i] = ZERO;

            } else {
                mamona[i].translate(-BORDER, ZERO);
                direction[i] = "left";
                System.out.println("left");
                System.out.println("ez bugzito");
                counter[i] = ZERO;

            }
        }
    }
}