package common.validators;

import common.Board;
import common.Movement;

import java.util.List;

public class LimitedMoveValidator implements Validator {

    private final int limit;

    public LimitedMoveValidator(int limit){
        this.limit = limit;
    }

    @Override
    public boolean isValid(List<Board> board, Movement movement){
        int columnDiff = Math.abs (movement.getOrigin().column() - movement.getDestination().column());
        int rowDiff = Math.abs(movement.getOrigin().row() - movement.getDestination().row());
        return columnDiff <= limit && rowDiff <= limit;
    }
}
