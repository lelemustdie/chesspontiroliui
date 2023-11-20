package javachess.Validators;


import common.Board;
import common.Movement;
import common.Validators.Validator;

import java.util.List;

public class FirstToEatValidator implements Validator {

    @Override
    public boolean isValid(List<Board> history, Movement movement) {
        Board previousBoard = history.get(history.size() - 2);
        Board currentBoard = history.get(history.size() - 1);
        return previousBoard.getPieces().size() > currentBoard.getPieces().size();
    }
}