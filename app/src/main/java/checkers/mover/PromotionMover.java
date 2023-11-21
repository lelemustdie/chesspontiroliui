package checkers.mover;

import checkers.factory.CheckerPieceFactory;
import common.*;
import common.Enums.Color;
import common.Enums.PieceType;
import common.Mover.Mover;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class PromotionMover implements Mover {

    private final static CheckerPieceFactory pieceFactory = new CheckerPieceFactory();


    @Override
    public GetResult<Game, Boolean> move(Game boardGame, Movement movement) {
        if (checkIfPieceCanPromote(boardGame.getCurrentBoard(), movement)){
            Board newBoard = promotePiece(boardGame.getCurrentBoard(), movement.getDestination());
            List<Board> newHistory = new ArrayList<>(boardGame.history());
            newHistory.remove(newHistory.size() - 1);
            newHistory.add(newBoard);
            return new GetResult<>(Optional.of(new Game(boardGame.turn(), boardGame.previousTurn(), newHistory, boardGame.winningValidator(), boardGame.mover())), false);
        }
        else{
            return new GetResult<>(Optional.of(boardGame), false);
        }
    }

    private Board promotePiece(Board board, Coordinate coordinate){
        Piece piece = board.getPieces().get(coordinate);
        Board newBoard = board.removePiece(coordinate);
        if (piece.getColor() == Color.WHITE){
            newBoard = newBoard.addPiece(coordinate, pieceFactory.createWhiteQueen(piece.getId()));
        }
        else{
            newBoard = newBoard.addPiece(coordinate, pieceFactory.createBlackQueen(piece.getId()));
        }
        return newBoard;
    }

    private boolean checkIfPieceCanPromote(Board board, Movement movement){
        Piece piece = board.getPieces().get(movement.getDestination());
        if(piece.getType() == PieceType.PAWN){
            return movement.getDestination().row() == board.getRows() || movement.getDestination().row() == 1;
        }
        return false;
    }

}
