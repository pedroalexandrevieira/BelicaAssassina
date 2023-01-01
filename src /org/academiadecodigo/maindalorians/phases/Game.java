package org.academiadecodigo.maindalorians.phases;

import org.academiadecodigo.maindalorians.sprites.Enemy;
import org.academiadecodigo.maindalorians.sprites.Player;
import org.academiadecodigo.maindalorians.sprites.Shot;
import org.academiadecodigo.maindalorians.utils.EnemyPool;
import org.academiadecodigo.maindalorians.utils.ShotPool;
import org.academiadecodigo.simplegraphics.keyboard.KeyboardEvent;
import org.academiadecodigo.simplegraphics.keyboard.KeyboardHandler;
import org.academiadecodigo.simplegraphics.pictures.Picture;

import java.util.Iterator;
import java.util.LinkedList;

import static org.academiadecodigo.maindalorians.utils.Configs.*;


public class Game implements KeyboardHandler {

    private final Picture background;
    private final Player player;
    private final EnemyPool enemyPool;
    private final LinkedList<Enemy> enemies;
    private final LinkedList<Shot> shots;
    private boolean gameOver;

    public Game() {
        background = new Picture(0, 0, GAME_BACKGROUND);
        player = new Player();
        enemyPool = new EnemyPool();
        enemies = new LinkedList<>();
        shots = new LinkedList<>();
        gameOver = false;
    }

    public void execute() {
        init();
        while (!gameOver) {
            delay();
            playerAction();
            moveShots();
            moveEnemies();
        }
        cleanup();
    }

    private void init() {
        gameOver = false;
        background.draw();
        player.show();
        for (int i = 0; i < STARTING_ENEMIES; i++) {
            enemies.add(enemyPool.poll());
        }
    }

    private void delay() {
        try {
            Thread.sleep(INTERVAL_60FPS);
        } catch (InterruptedException e) {
            e.printStackTrace();
            System.exit(1);
        }
    }

    private void playerAction() {
        player.move();
        if (player.isShooting()) {
            shots.add(player.createShot());
            player.stopShooting();
        }
    }

    private void moveShots() {
        int removedEnemies = 0;
        Iterator<Shot> shotIterator = shots.iterator();
        while (shotIterator.hasNext()) {
            Shot s = shotIterator.next();
            s.move();
            if(s.isInvalidMovement()){
                shotIterator.remove();
                ShotPool.offer(s);
                break;
            }
            Iterator<Enemy> enemyIterator = enemies.iterator();
            while (enemyIterator.hasNext()) {
                Enemy e = enemyIterator.next();
                if (s.isColldingWith(e)) {
                    enemyIterator.remove();
                    shotIterator.remove();
                    enemyPool.offer(e);
                    ShotPool.offer(s);
                    removedEnemies++;
                    break;
                }
            }
        }
        for (int i = 0; i < removedEnemies * ENEMY_MULTIPLIER; i++) {
            enemies.add(enemyPool.poll());
        }
    }

    private void moveEnemies() {
        for (Enemy e : enemies) {
            e.move();
            if (e.isColldingWith(player) || e.isTouchingLeftEdge()) {
                gameOver = true;
                break;
            }
        }
    }

    private void cleanup() {
        background.delete();
        player.hide();
        player.stopMoving();
        for (Enemy e : enemies) {
            enemyPool.offer(e);
        }
        for (Shot s: shots) {
            ShotPool.offer(s);
        }
        enemies.clear();
        shots.clear();
    }

    @Override
    public void keyPressed(KeyboardEvent event) {
        switch (event.getKey()) {
            case KeyboardEvent.KEY_UP:
                player.goUp();
                break;
            case KeyboardEvent.KEY_DOWN:
                player.goDown();
                break;
            case KeyboardEvent.KEY_SPACE:
                player.shoot();
        }
    }

    @Override
    public void keyReleased(KeyboardEvent event) {
        switch (event.getKey()) {
            case KeyboardEvent.KEY_UP:
            case KeyboardEvent.KEY_DOWN:
                player.stopMoving();
                break;
        }
    }
}
