package javachess.Validators;

import common.Board;
import common.Coordinate;
import common.Movement;
import common.Piece;
import common.Validators.Validator;

import java.util.List;
import java.util.Map;

public class WeAreFriendsValidator implements Validator {

    @Override
    public boolean isValid(List<Board> board, Movement movement){
        Map<Coordinate, Piece> pieces = board.get(board.size()- 1).getPieces();
        if(!pieces.containsKey(movement.getDestination())) return true;
        return pieces.get(movement.getOrigin()).getColor() != pieces.get(movement.getDestination()).getColor();
    }
}
