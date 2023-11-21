package javachess.validators;

import common.Board;
import common.Coordinate;
import common.Movement;
import common.Piece;
import common.validators.Validator;

import java.util.List;
import java.util.Map;

public class EatingValidator implements Validator {

    @Override
    public boolean isValid(List<Board> board, Movement movement){
        Map<Coordinate, Piece> pieces = board.get(board.size()- 1).getPieces();
        if (movement.getOrigin().equals(movement.getDestination())) return false; //same place
        if (!pieces.containsKey(movement.getOrigin())) return false; //checks origin piece
        if (!pieces.containsKey(movement.getDestination())) return false; //same destination
        return pieces.get(movement.getOrigin()).getColor() !=
                pieces.get(movement.getDestination()).getColor(); //dif color
    }
}
