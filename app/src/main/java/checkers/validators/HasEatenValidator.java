package checkers.validators;


import common.Board;
import common.Coordinate;
import common.Movement;
import common.Piece;
import common.Validators.Validator;

import java.util.List;
import java.util.Map;

public class HasEatenValidator implements Validator {


    @Override
    public boolean isValid(List<Board> history, Movement movement) {
        Board currentBoard = history.get(history.size() - 1);
        Map<Coordinate, Piece> pieces = currentBoard.getPieces();
        Coordinate PreviousCoordinate = getEatenCoordinate(pieces.get(movement.getOrigin()), movement);
        return pieces.containsKey(PreviousCoordinate);
    }

    public Coordinate getEatenCoordinate(Piece piece, Movement movement){ //saca la cord de la pieza comida en el mov actual
        int directionColumn = (movement.getOrigin().column() < movement.getDestination().column()) ? 1 : -1;
        int directionRow = (movement.getOrigin().row() < movement.getDestination().row()) ? 1 : -1;
        return new Coordinate(movement.getDestination().column() - directionColumn, movement.getDestination().row() - directionRow);
    }
}
