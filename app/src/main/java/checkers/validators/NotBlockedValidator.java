package checkers.validators;


import common.Board;
import common.Movement;
import common.validators.Validator;

import java.util.List;

public class NotBlockedValidator implements Validator {

    @Override
    public boolean isValid(List<Board> history, Movement movement) {
        Board currentBoard = history.get(history.size() - 1);
        return !currentBoard.getPieces().containsKey(movement.getDestination());
    }
}
