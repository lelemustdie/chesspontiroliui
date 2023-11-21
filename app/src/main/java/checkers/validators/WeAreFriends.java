package checkers.validators;

import common.Board;
import common.Coordinate;
import common.Movement;
import common.Piece;
import common.validators.Validator;

import java.util.List;
import java.util.Map;

public class WeAreFriends implements Validator {


    @Override
    public boolean isValid(List<Board> history, Movement movement) {
        Board currentBoard = history.get(history.size() - 1);
        Map<Coordinate, Piece> pieces = currentBoard.getPieces();
        int directionColumn = (movement.getOrigin().column() < movement.getDestination().column()) ? 1 : -1;
        int directionRow = (movement.getOrigin().row() < movement.getDestination().row()) ? 1 : -1;
        Coordinate PreviousCoordinate = new Coordinate(movement.getDestination().column() - directionColumn, movement.getDestination().row() - directionRow);
        if (pieces.containsKey(PreviousCoordinate)) {
            Piece piece = pieces.get(PreviousCoordinate);
            return !piece.getColor().equals(pieces.get(movement.getOrigin()).getColor());
        }
        return true;
    }
}
