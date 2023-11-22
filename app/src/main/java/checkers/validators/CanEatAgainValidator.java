package checkers.validators;

import common.Board;
import common.Coordinate;
import common.Movement;
import common.Piece;
import common.validators.Validator;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class CanEatAgainValidator implements Validator {

    private final Validator eatValidator;

    public CanEatAgainValidator(Validator eatValidator) {
        this.eatValidator = eatValidator;
    }


    @Override
    public boolean isValid(List<Board> history, Movement movement) {
        Board currentBoard = getCurrentBoard(history);
        Map<Coordinate, Piece> pieces = currentBoard.getPieces();
        Coordinate pieceOrigin = movement.getDestination();
        Piece piece = pieces.get(pieceOrigin);
        List<Coordinate> possibleMoves = getPossibleMoves(pieceOrigin);
                for(Coordinate coordinate: possibleMoves){
                Movement eatMovement = new Movement(pieceOrigin, coordinate);
                if (eatValidator.isValid(history, eatMovement) && piece.getValidator().isValid(history, eatMovement)) return true;

        }
        return false;
    }

    private Board getCurrentBoard(List<Board> history) {
        Board currentBoard = history.get(history.size() - 1);
        return currentBoard;
    }

    private List<Coordinate> getPossibleMoves(Coordinate current){
        List<Coordinate> possibleMoves = new ArrayList<>();
        possibleMoves.add(new Coordinate(current.column() + 2, current.row() + 2));
        possibleMoves.add(new Coordinate(current.column() - 2, current.row() + 2));
        possibleMoves.add(new Coordinate(current.column() + 2, current.row() - 2));
        possibleMoves.add(new Coordinate(current.column() - 2, current.row() - 2));
        return possibleMoves;
    }

}
