package org.academiadecodigo.maindalorians.keyboard;

import org.academiadecodigo.simplegraphics.keyboard.KeyboardEvent;
import org.academiadecodigo.simplegraphics.keyboard.KeyboardEventType;

public enum Key {
    Q_PRESS(KeyboardEventType.KEY_PRESSED, KeyboardEvent.KEY_Q),
    SPACE_PRESS(KeyboardEventType.KEY_PRESSED, KeyboardEvent.KEY_SPACE),
    ENTER_PRESS(KeyboardEventType.KEY_PRESSED, KeyboardEvent.KEY_ENTER),
    UP_PRESS(KeyboardEventType.KEY_PRESSED, KeyboardEvent.KEY_UP),
    UP_RELEASE(KeyboardEventType.KEY_RELEASED, KeyboardEvent.KEY_UP),
    DOWN_PRESS(KeyboardEventType.KEY_PRESSED, KeyboardEvent.KEY_DOWN),
    DOWN_RELEASE(KeyboardEventType.KEY_RELEASED, KeyboardEvent.KEY_DOWN);

    protected final KeyboardEventType type;
    protected final int keyNumber;

    Key(KeyboardEventType type, int keyNumber) {
        this.type = type;
        this.keyNumber = keyNumber;
    }
}
