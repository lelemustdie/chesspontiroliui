package javachess.validators.checknmate;

import common.Board;
import common.Coordinate;
import common.enums.Color;
import common.Movement;
import common.Piece;
import common.validators.Validator;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class CheckValidator implements Validator {

        @Override

        public boolean isValid(List<Board> history, Movement movement) { //valida q el otro jugador no tenga un movimiento hacia mi rey, en ataque
            Board currentBoard = history.get(history.size()- 1);
            Map<Coordinate, Piece> pieces = currentBoard.getPieces();
            Piece movedPiece = pieces.get(movement.getDestination());

            Coordinate kingsCoordinate = currentBoard.getKingsCoordinate(movedPiece.getColor());
            Color  enemyColor = movedPiece.getColor() == Color.WHITE ? Color.BLACK : Color.WHITE;

            for(Map.Entry<Coordinate, Piece> piece : pieces.entrySet()){
                Movement movementToKing = new Movement(piece.getKey(), kingsCoordinate);
                if(piece.getValue().getColor() == enemyColor &&
                        piece.getValue().getValidator().isValid(new ArrayList<>(List.of(currentBoard)), movementToKing))
                    return false;
            }

            return true;
        }
    }


