package checkers.validators;

import common.Board;
import common.Coordinate;
import common.Enums.Color;
import common.Movement;
import common.Piece;
import common.Validators.Validator;

import java.util.List;
import java.util.Map;

public class NoEnemyPiecesAtSight implements Validator {
    @Override
    public boolean isValid(List<Board> history, Movement movement) {
        Board currentBoard = history.get(history.size()-1);
        Color currentPlayer = currentBoard.getPieces().get(movement.getDestination()).getColor();
        for (Map.Entry<Coordinate, Piece> entry: currentBoard.getPieces().entrySet()) {
            Piece piece = entry.getValue();
            if (piece != null && piece.getColor() != currentPlayer) {
                return false; //mapeo el array de piezas y chequeo por cada una su existencia
            }
        }
        return true;
    }
}
