package javachess.mover;

import common.*;
import common.enums.Color;
import common.enums.PieceType;
import common.mover.Mover;
import javachess.factory.PieceFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class PromotionMover implements Mover {

    private final static PieceFactory pieceFactory = new PieceFactory();


    @Override
    public GetResult<Game, Boolean> move(Game boardGame, Movement movement) {
        Board board = boardGame.getCurrentBoard(); //abstraer metodos del if
        Piece piece = board.getPieces().get(movement.getOrigin());
        if (piece.getColor() != boardGame.turn() || !piece.getValidator().isValid(boardGame.history(), movement))
            return new GetResult<>(Optional.of(boardGame), true);
        if (checkIfPawnCanPromote(board, movement)) return promotePawn(boardGame, movement);
        return new GetResult<>(Optional.of(boardGame), true);
    }

    private GetResult<Game, Boolean> promotePawn(Game boardGame, Movement movement) {
        Board board = boardGame.getCurrentBoard();
        Piece piece = board.getPieces().get(movement.getOrigin());
        Board newBoard = board.movePiece(movement);
        Board newBoard2 = piece.getColor() == Color.WHITE ? newBoard.addPiece(movement.getDestination(), pieceFactory.createWhiteQueen(piece.getId())) : newBoard.addPiece(movement.getDestination(), pieceFactory.createBlackQueen(piece.getId()));
        List<Board> newHistory = new ArrayList<>(boardGame.history());
        newHistory.add(newBoard2);
        return new GetResult<>(Optional.of(new Game(boardGame.nextTurn(), boardGame.turn(), newHistory, boardGame.winningValidator(), boardGame.mover())), false);
    }

    private boolean checkIfPawnCanPromote(Board board, Movement movement){
        Piece piece = board.getPieces().get(movement.getOrigin());
        if(piece.getType() == PieceType.PAWN){ //algebra de cords
            if(piece.getColor() == Color.WHITE){
                return movement.getDestination().row() == board.getRows();
            }
            else{
                return movement.getDestination().row() == 1;
            }
        }
        return false;
    }


}