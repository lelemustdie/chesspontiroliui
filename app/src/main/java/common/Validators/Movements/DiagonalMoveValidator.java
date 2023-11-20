package common.Validators.Movements;

import common.Board;
import common.Coordinate;
import common.Movement;
import common.Piece;
import common.Validators.Validator;

import java.util.List;
import java.util.Map;

public class DiagonalMoveValidator implements Validator {

    private final boolean forward;

    public DiagonalMoveValidator(boolean forward){
        this.forward = forward;
    }

    @Override
    public boolean isValid(List<Board> board, Movement movement){
        Board currentBoard = board.get(board.size()- 1);
        Map<Coordinate, Piece> pieces = currentBoard.getPieces();
        if (movement.getOrigin().column() == movement.getDestination().column() || movement.getOrigin().row() == movement.getDestination().row()) return false;
        int directionX = (movement.getOrigin().column() < movement.getDestination().column()) ? 1 : -1;
        int directionY = (movement.getOrigin().row() < movement.getDestination().row()) ? 1 : -1;
        if (forward && directionY == -1) return false;
        Coordinate origin = new Coordinate(movement.getOrigin().column() + directionX, movement.getOrigin().row() + directionY);
        while (!origin.equals(movement.getDestination())) {
            if (pieces.containsKey(origin)) return false;
            if (origin.column() < 1 || origin.column() > currentBoard.getColumns() || origin.row() < 1 || origin.row() > currentBoard.getRows()) return false;
            origin = new Coordinate(origin.column() + directionX, origin.row() + directionY);
        }
        return true;
    }
}
