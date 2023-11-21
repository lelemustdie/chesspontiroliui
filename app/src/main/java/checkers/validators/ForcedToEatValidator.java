package checkers.validators;

import common.Board;
import common.Coordinate;
import common.Movement;
import common.Piece;
import common.validators.Validator;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ForcedToEatValidator implements Validator {
    private boolean forced;
    public ForcedToEatValidator(boolean forced) {
        this.forced = forced;
    }
    @Override
    public boolean isValid(List<Board> boardHistory,Movement movement) {
        if(!forced) return false;
        Board currentBoard = boardHistory.get(boardHistory.size() - 1);
        Piece currentPiece = currentBoard.getPieces().get(movement.getOrigin());
        Map<Coordinate,Coordinate> eatablePieces = getEatablePieces(currentBoard, currentPiece);
        if(!eatablePieces.isEmpty()) {
            if(eatablePieces.containsKey(movement.getOrigin())) {
                return !eatablePieces.get(movement.getOrigin()).equals(movement.getDestination());
            }
            return true;
        }
        return false;
    }


     private Map<Coordinate, Coordinate> getEatablePieces (Board board, Piece currentPiece) {
         Map<Coordinate,Coordinate> eatablePieces = new HashMap<>();
         for (Map.Entry<Coordinate, Piece> entry : board.getPieces().entrySet()) {
             Coordinate coordinate = entry.getKey();
             Piece piece = entry.getValue();
             if(piece.getColor() == currentPiece.getColor()) {
                 List<Coordinate> possibleMoves = getPossibleMoves(coordinate);
                 for (Coordinate possibleMove : possibleMoves) {
                     if (piece.getValidator().isValid(List.of(board), new Movement(coordinate, possibleMove))) {
                         eatablePieces.put(coordinate,possibleMove);
                     }
                 }
             }
         }
         return eatablePieces;
     }
    private List<Coordinate> getPossibleMoves(Coordinate current){
        List<Coordinate> possibleMoves = new ArrayList<>();
        possibleMoves.add(new Coordinate(current.column() + 2, current.row() + 2));
        possibleMoves.add(new Coordinate(current.column() - 2, current.row() + 2));
        possibleMoves.add(new Coordinate(current.column() + 2, current.row() - 2));
        possibleMoves.add(new Coordinate(current.column() - 2, current.row() - 2));
        return possibleMoves;
    }
}


