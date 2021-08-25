package org.academiadecodigo.maindalorians;

import org.academiadecodigo.simplegraphics.graphics.Color;
import org.academiadecodigo.simplegraphics.graphics.Text;
import org.academiadecodigo.simplegraphics.keyboard.Keyboard;
import org.academiadecodigo.simplegraphics.keyboard.KeyboardEvent;
import org.academiadecodigo.simplegraphics.keyboard.KeyboardEventType;
import org.academiadecodigo.simplegraphics.pictures.Picture;

public class Game {
    private static Handler handler;
    private Start start;
    private Mamonas mamona;
    private Shots shot;
    private Belica ship;
    private Picture rect;
    private boolean gameOver = false;
    private boolean resetGame = false;
    private boolean screen = false;
    private int scoreCounter;
    private int highScore;
    private final int ZERO = 0;
    private final int BORDER = 10;
    private final int THREAD_SLEEP = 70;

    public Game() {}

    public void init() {
        gameSetup();
        handler = new Handler(this, ship);
        Keyboard keyboard = new Keyboard(handler);
        KeyboardEvent spacePressed = new KeyboardEvent();
        KeyboardEvent upArrowPress = new KeyboardEvent();
        KeyboardEvent downArrowPress = new KeyboardEvent();
        KeyboardEvent leftArrowPress = new KeyboardEvent();
        KeyboardEvent rightArrowPress = new KeyboardEvent();
        KeyboardEvent enterKeyPress = new KeyboardEvent();
        spacePressed.setKey(KeyboardEvent.KEY_SPACE);
        spacePressed.setKeyboardEventType(KeyboardEventType.KEY_PRESSED);
        upArrowPress.setKey(KeyboardEvent.KEY_UP);
        upArrowPress.setKeyboardEventType(KeyboardEventType.KEY_PRESSED);
        downArrowPress.setKey(KeyboardEvent.KEY_DOWN);
        downArrowPress.setKeyboardEventType(KeyboardEventType.KEY_PRESSED);
        leftArrowPress.setKey(KeyboardEvent.KEY_LEFT);
        leftArrowPress.setKeyboardEventType(KeyboardEventType.KEY_PRESSED);
        rightArrowPress.setKey(KeyboardEvent.KEY_RIGHT);
        rightArrowPress.setKeyboardEventType(KeyboardEventType.KEY_PRESSED);
        enterKeyPress.setKey(KeyboardEvent.KEY_ENTER);
        enterKeyPress.setKeyboardEventType(KeyboardEventType.KEY_PRESSED);
        keyboard.addEventListener(spacePressed);
        keyboard.addEventListener(upArrowPress);
        keyboard.addEventListener(downArrowPress);
        keyboard.addEventListener(leftArrowPress);
        keyboard.addEventListener(rightArrowPress);
        keyboard.addEventListener(enterKeyPress);
        start();
    }

    public void gameSetup() {
        scoreCounter = ZERO;
        setGameOver(false);
        rect = new Picture(ZERO, ZERO, "resources/background.png");
        rect.draw();
        ship = new Belica(rect, this);
        shot = new Shots(rect, ship.getShip(), this);
        mamona = new Mamonas(rect, ship.getShip(), this);
        mamona.createMamona();
        mamona.createMamona();
    }

    public void start() {
        while (!gameOver) {
            if (shot.getShots() != null) {
                for (int i = ZERO; i < shot.getShots().length; i++) {
                    if (shot.getShots()[i] != null && shot.getShots()[i].getMaxX() < rect.getWidth()) {
                        shot.moveShot(i);
                        collisionDetector(i);
                    }
                }
            }
            if (mamona.getMamonas() != null) {
                for (int i = ZERO; i < mamona.getMamonas().length; i++) {
                    if (mamona.getMamonas()[i] != null) {
                        mamona.moveMamonas(i);
                        collisionDetector(i);
                    }
                }
            }
            try {
                Thread.sleep(THREAD_SLEEP);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        gameOver();
    }

    public void gameOver() {
        int x = ZERO;
        while (!resetGame) {
            Picture overGame = new Picture(ZERO, ZERO, "resources/background.png");
            Picture gameOver = new Picture(ZERO, ZERO, "resources/gameover.png");
            Text score = new Text(ZERO, ZERO, ""+scoreCounter);
            if (x == ZERO) {
                overGame.draw();
                gameOver.draw();
                score.setColor(Color.GREEN);
                score.draw();
                x++;
            }
        }
        restart();
    }

    public void startScreen() {
        start = new Start(this);
        while (!screen) {
            start.initScreen();
        }
    }

    public void restart() {
        handler = null;
        setResetGame(false);
        init();
    }

    public void setResetGame(boolean resetGame) {
        this.resetGame = resetGame;
    }

    public boolean isGameOver() {
        return gameOver;
    }

    public void setGameOver(boolean gameOver) {
        this.gameOver = gameOver;
    }

    public void fireShot() {
        shot.fireShot();
    }

    public void collisionDetector(int i) {
        for (int j = 0; j < mamona.getMamonas().length; j++) {
            if (shot.getShots()[i] != null && mamona.getMamonas()[j] != null) {
                if (mamona.getMamonas()[j].getMaxY() - BORDER > shot.getShots()[i].getY() && mamona.getMamonas()[j].getY() + BORDER < shot.getShots()[i].getMaxY()
                        && mamona.getMamonas()[j].getX() + BORDER < shot.getShots()[i].getMaxX() && mamona.getMamonas()[j].getMaxX() - BORDER > shot.getShots()[i].getX()) {
                    shot.getShots()[i].delete();
                    shot.getShots()[i] = null;
                    mamona.getMamonas()[j].delete();
                    mamona.getMamonas()[j] = null;
                    scoreCounter++;
                    mamona.createMamona();
                    if ((Math.random() * (BORDER*BORDER)) > THREAD_SLEEP) {
                        mamona.createMamona();
                    }
                    break;
                }
            }
            if (mamona.getMamonas()[j] != null) {

                if (mamona.getMamonas()[j].getMaxY() - BORDER > ship.getShip().getY() && mamona.getMamonas()[j].getY() + BORDER < ship.getShip().getMaxY()
                        && mamona.getMamonas()[j].getX() + BORDER < ship.getShip().getMaxX() && mamona.getMamonas()[j].getMaxX() - BORDER > ship.getShip().getX()) {
                    System.out.println("boas");
                    setGameOver(true);
                }
            }
        }
    }

    public void setScreen(boolean screen) {
        this.screen = screen;
    }
}


