package checkers.validators;

import common.Board;
import common.Coordinate;
import common.enums.Color;
import common.Movement;
import common.Piece;
import common.validators.Validator;

import java.util.List;
import java.util.Map;

public class GameOverValidator implements Validator {


    @Override
    public boolean isValid(List<Board> history, Movement movement) {
        Color currentPlayer = getCurrentPlayer(history, movement.getOrigin());
        Board currentBoard = history.get(history.size() - 1);
        Map<Coordinate, Piece> pieces = currentBoard.getPieces();

        for(int row = 1; row < currentBoard.getRows(); row++){
            for(int column = 1; column < currentBoard.getColumns(); column++){
                Coordinate currentDestination = new Coordinate(column, row);
                for(Map.Entry<Coordinate, Piece> piece : pieces.entrySet()){
                    Movement move = new Movement(piece.getKey(), currentDestination);
                    if (piece.getValue().getColor() == currentPlayer && piece.getValue().getValidator().isValid(history, move)){
                        return false; //busca un movimiento valido en el tablero para el currentPlayer
                    }
                }
            }
        }
        return true;
    }

    private Color getCurrentPlayer(List<Board> history, Coordinate origin) {
        Board previousBoard = history.get(history.size() - 2    );
        Map<Coordinate, Piece> pieces = previousBoard.getPieces();
        return pieces.get(origin).getColor();
    } //busca el color del jugador actual usando el origen del previous board
}
