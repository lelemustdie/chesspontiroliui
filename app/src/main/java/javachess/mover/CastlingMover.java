package javachess.mover;
import common.*;
import common.Enums.PieceType;
import common.Mover.Mover;
import common.Validators.Validator;
import javachess.Validators.SpecialMovements.PieceFirstMoveValidator;

import java.util.*;

public class CastlingMover implements Mover {

    Validator isFirstMove = new PieceFirstMoveValidator();
    @Override
    public GetResult move(Game game, Movement move) {
        Board currentBoard = game.getCurrentBoard();
        Piece currentPiece = currentBoard.getPieces().get(move.getDestination());
        if( currentPiece.getType() != PieceType.KING
                || Math.abs(move.getOrigin().column() - move.getDestination().column()) == 1
                || Math.abs(move.getOrigin().row() - move.getDestination().row()) != 0
        ){
            return new GetResult(Optional.of(game), false);
        }
        if( Integer.compare(move.getDestination().column(), move.getOrigin().column()) == 1 ){
            return executeShortCastling(game, move);
        }
        return executeLongCastling(game,move);
    }

    private GetResult executeShortCastling(Game game, Movement move){
        Coordinate rookCoordinate = new Coordinate (move.getDestination().column() + 1, move.getDestination().row() );
        Coordinate rookDestiny = new Coordinate (move.getDestination().column() - 1, move.getDestination().row() );
        return executeCastling(game,rookCoordinate,rookDestiny);
    }

    private GetResult executeLongCastling(Game game, Movement move){
        Coordinate rookCoordinate = new Coordinate (move.getDestination().column() - 2, move.getDestination().row() );
        Coordinate rookDestiny = new Coordinate (move.getDestination().column() + 1, move.getDestination().row() );
        return executeCastling(game, rookCoordinate,rookDestiny);
    }

    private GetResult executeCastling(Game game, Coordinate rookCoordinate, Coordinate rookDestiny){
        Board currentBoard = game.getCurrentBoard();
        Piece rook = currentBoard.getPiece(rookCoordinate);
        if ( rook == null ) {
            return new GetResult(Optional.of(game), true);
        }
        if (!isFirstMove.isValid(game.history(), new Movement(rookCoordinate, rookDestiny))){
            return new GetResult(Optional.of(game), true);
        }
        Board newBoard = currentBoard.addAndRemovePiece(rookCoordinate, rook, rookDestiny);
        return new GetResult(Optional.of(newGame (game,newBoard)), false);
    }

    private Game newGame (Game currentGame, Board newBoard){
        List<Board> newHistory = new ArrayList<>(currentGame.history());
        newHistory.remove(newHistory.size()-1);
        newHistory.add(newBoard);
        return new Game(currentGame.nextTurn(), currentGame.turn(),
                newHistory,
                currentGame.winningValidator(),
                currentGame.mover());
    }
}