package javachess.validators;

import common.Board;
import common.Coordinate;
import common.Movement;
import common.Piece;
import common.validators.Validator;

import java.util.List;
import java.util.Map;

public class JustMovingAroundValidator implements Validator {

    @Override
    public boolean isValid(List<Board> board, Movement movement){
        Map<Coordinate, Piece> pieces = board.get(board.size()- 1).getPieces();
        return !pieces.containsKey(movement.getDestination());
    }
}
//si simplemente es valido moverme a una posicion vacia en el board