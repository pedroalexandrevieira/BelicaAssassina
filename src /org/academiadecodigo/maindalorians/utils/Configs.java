package org.academiadecodigo.maindalorians.utils;

public class Configs {
    //gameStart-phase related
    public static final String GAME_START_BACKGROUND = "resources/splash.png";

    //game-phase related
    public static final int GAME_WIDTH = 1200;
    public static final int GAME_HEIGHT = 750;
    public static final int STARTING_ENEMIES = 2;
    public static final int ENEMY_MULTIPLIER = 2;
    public static final int INTERVAL_60FPS = 1000 / 60;
    public static final String GAME_BACKGROUND = "resources/background.png";

    //gameOver-phase related
    public static final String GAME_OVER_BACKGROUND = "resources/gameOver.png";

    //Enemy related
    public static final String ENEMY_IMAGE_PATH = "resources/boobies.png";
    public static final int ENEMY_STARTING_X = Configs.GAME_WIDTH - 148;
    public static final int ENEMY_SPEED = 3;

    //Player related
    public static final String PLAYER_IMAGE_PATH = "resources/blica.png";
    public static final int PLAYER_SPEED = 10;

    //Shot related
    public static final String SHOT_IMAGE_PATH = "resources/fireShot.png";
    public static final int SHOT_SPEED = 10;

    //EnemyPool related
    public static final int ENEMY_POOL_STARTING_SIZE = 30;

    //ShotPool related
    public static final int SHOT_POOL_STARTING_SIZE = 20;
}
