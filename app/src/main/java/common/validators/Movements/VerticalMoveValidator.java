package common.validators.Movements;

import common.Board;
import common.Coordinate;
import common.Movement;
import common.Piece;
import common.validators.Validator;

import java.util.List;
import java.util.Map;

public class VerticalMoveValidator implements Validator {

    private final boolean forward;

    public VerticalMoveValidator(boolean forward){
        this.forward = forward;
    }

    @Override
    public boolean isValid(List<Board> board, Movement movement){
        Board currentBoard = board.get(board.size()- 1);
        Map<Coordinate, Piece> pieces = currentBoard.getPieces();
        if (movement.getOrigin().column() != movement.getDestination().column()) return false;
        if (forward && movement.getOrigin().row() >= movement.getDestination().row()) return false;
        int direction = (forward) ? 1 : -1;
        Coordinate origin = new Coordinate(movement.getOrigin().column(), movement.getOrigin().row() + direction);
        while (!origin.equals(movement.getDestination())){
            if (pieces.containsKey(origin)) return false;
            if (origin.row() < 1 || origin.row() > currentBoard.getRows()) return false;
            origin = new Coordinate(origin.column(), origin.row() + direction);
        }
        return true;
    }
}
