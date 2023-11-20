package javachess;
import common.*;
import common.Mover.Mover;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ChessMover implements Mover { //movimiento default del chess, no es promotion ni nada, comer y mover

    @Override
    public GetResult<Game, Boolean> move(Game boardGame, Movement movement) {
        Board board = boardGame.getCurrentBoard();
        Piece piece = board.getPieces().get(movement.getOrigin());
        if(piece.getColor() != boardGame.getTurn() || !piece.getValidator().isValid(boardGame.getHistory(), movement)) return new GetResult<>(Optional.of(boardGame), true);
        Board newBoard = board.movePiece(movement);
        List<Board> newHistory = new ArrayList<>(boardGame.getHistory());
        newHistory.add(newBoard);
        return new GetResult<>(Optional.of(new Game(boardGame.nextTurn(), boardGame.getTurn(), newHistory, boardGame.getWinningValidator(), boardGame.getMover())), false);
    }
}