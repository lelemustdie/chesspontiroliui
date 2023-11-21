package checkers.mover;

import common.*;
import common.Mover.Mover;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public class MovingSamePieceMover implements Mover {



    private boolean isMovingSamePiece(List<Board> history, Movement movement){
        Board currentBoard = history.get(history.size() - 1);
        Board previousBoard = history.get(history.size() - 2);
        int pieceId = currentBoard.getPieces().get(movement.getOrigin()).getId();
        Map<Coordinate, Piece> previousPieces = previousBoard.getPieces();
        for (Map.Entry<Coordinate,Piece> piece : previousPieces.entrySet()){
            if (piece.getValue().getId() == pieceId && !piece.getKey().equals(movement.getOrigin())){
                return true; //saco el anterior pq necesito saber si entre el current board y el previous board es la que movi antes
            }
        }
        return false;
    }

    @Override
    public GetResult<Game, Boolean> move(Game boardGame, Movement movement) {
        if (boardGame.previousTurn() != boardGame.turn())
            return new GetResult<>(Optional.of(boardGame), false);
        if (!isMovingSamePiece(boardGame.history(), movement))
            return new GetResult<>(Optional.of(boardGame), true);
        return new GetResult<>(Optional.of(boardGame), false);
    }
}
