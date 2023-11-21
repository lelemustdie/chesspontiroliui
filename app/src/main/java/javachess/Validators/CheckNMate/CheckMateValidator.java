package javachess.Validators.CheckNMate;

import common.Board;
import common.Coordinate;
import common.Enums.Color;
import common.Movement;
import common.Piece;
import common.Validators.Validator;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class CheckMateValidator implements Validator {
    private final Validator checkValidator;

    public CheckMateValidator(Validator checkValidator) {
        this.checkValidator = checkValidator;
    }

    @Override
    public boolean isValid(List<Board> history, Movement movement) {
        Board previousBoard = history.get(history.size() - 2);
        Color currentPlayer = determineCurrentPlayer(previousBoard, movement);
        Board currentBoard = history.get(history.size() - 1);
        Map<Coordinate, Piece> pieces = currentBoard.getPieces();

        for (int currentRow = 1; currentRow <= currentBoard.getRows(); currentRow++) {
            for (int currentColumn = 1; currentColumn <= currentBoard.getColumns(); currentColumn++) {
                Coordinate currentDestination = new Coordinate(currentColumn, currentRow);
                checkMovesAndValidate(history, currentPlayer, currentBoard, pieces, currentDestination);
            }
        }
        return true;
    }

    private Color determineCurrentPlayer(Board previousBoard, Movement movement) {
        return previousBoard.getPieces().get(movement.getOrigin()).getColor() == Color.WHITE ? Color.BLACK : Color.WHITE;
    }

    private boolean checkMovesAndValidate(List<Board> history, Color currentPlayer, Board currentBoard,
                                       Map<Coordinate, Piece> pieces, Coordinate currentDestination) {
        for (Map.Entry<Coordinate, Piece> piece : pieces.entrySet()) {
            Movement move = new Movement(piece.getKey(), currentDestination);
            if (piece.getValue().getColor() == currentPlayer && piece.getValue().getValidator().isValid(history, move)) {
                Board newBoard = currentBoard.movePiece(move);
                List<Board> newHistory = new ArrayList<>(history);
                newHistory.add(newBoard);
                if (checkValidator.isValid(newHistory, move)) {
                    return false;
                }
            }
        } return true;
    }
}
