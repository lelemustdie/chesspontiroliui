package checkers.mover;

import checkers.validators.ForcedToEatValidator;
import common.*;
import common.Mover.Mover;
import common.Validators.Validator;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class DefaultCheckersMover implements Mover {

    private Validator forcedToEatValidator = new ForcedToEatValidator(true);
    @Override
    public GetResult<Game, Boolean> move(Game boardGame, Movement movement) {
        Board board = boardGame.getCurrentBoard();
        Piece piece = board.getPieces().get(movement.getOrigin());
        if (!piece.getValidator().isValid(boardGame.history(), movement)) return new GetResult<>(Optional.of(boardGame), true);
        if(forcedToEatValidator.isValid(boardGame.history(), movement)) {
            return new GetResult<>(Optional.of(boardGame),true);
        }
        Board newBoard = executeMovement(board, piece, movement);
        List<Board> newHistory = new ArrayList<>(boardGame.history());
        newHistory.add(newBoard);
        return new GetResult<>(Optional.of(new Game(boardGame.turn(), boardGame.turn(), newHistory, boardGame.winningValidator(), boardGame.mover())), false);
    }


    private Board executeMovement(Board board, Piece piece, Movement movement){
        Board pieceMoved = board.movePiece(movement);
        if (board.getPieces().containsKey(getEatenCoordinate(movement))){
            return pieceMoved.removePiece(getEatenCoordinate(movement));
        }
        return pieceMoved;
    }


    public Coordinate getEatenCoordinate(Movement movement){
        int directionColumn = (movement.getOrigin().column() < movement.getDestination().column()) ? 1 : -1;
        int directionRow = (movement.getOrigin().row() < movement.getDestination().row()) ? 1 : -1;
        return new Coordinate(movement.getDestination().column() - directionColumn, movement.getDestination().row() - directionRow);
    }

}
