package common.validators.Movements;

import common.Board;
import common.Coordinate;
import common.Movement;
import common.Piece;
import common.validators.Validator;

import java.util.List;
import java.util.Map;

public class HorizontalMoveValidator implements Validator {

    private final boolean right;

    public HorizontalMoveValidator(boolean right) {
        this.right = right;
    }

    @Override
    public boolean isValid(List<Board> board, Movement movement) {
        Board currentBoard = board.get(board.size()- 1); //ultimo board
        Map<Coordinate, Piece> pieces = currentBoard.getPieces();
        if (movement.getOrigin().row() != movement.getDestination().row()) return false;
        if (right && movement.getOrigin().column() >= movement.getDestination().column()) return false;
        int direction = (right) ? 1 : -1;
        Coordinate origin = new Coordinate(movement.getOrigin().column() + direction, movement.getOrigin().row());
        while (!origin.equals(movement.getDestination())){
            if (pieces.containsKey(origin)) return false;
            if (origin.column() < 1 || origin.column() > currentBoard.getColumns()) return false;
            origin = new Coordinate(origin.column() + direction, origin.row());
        }
        return true;
    }

}
