package common;

import common.enums.Color;

public class TurnManager {
    private final Color currentTurn;

    public TurnManager(Color currentTurn) {
        this.currentTurn = currentTurn;
    }

    public Color getCurrentTurn() {
        return currentTurn;
    }

    public Color getNextTurn(){
        return currentTurn == Color.BLACK ? Color.WHITE : Color.BLACK;
    }
}

