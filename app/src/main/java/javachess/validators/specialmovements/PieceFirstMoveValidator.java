package javachess.validators.specialmovements;

import common.Board;
import common.Coordinate;
import common.Movement;
import common.Piece;
import common.validators.Validator;

import java.util.List;
import java.util.Map;

public class PieceFirstMoveValidator implements Validator {
    @Override
    public boolean isValid(List<Board> history, Movement movement) {
        Map<Coordinate, Piece> currentPieces = history.get(history.size()- 1).getPieces();
        int pieceId = currentPieces.get(movement.getOrigin()).getId();
        Coordinate initialCoordinate = history.get(0).getCoordinateByPieceId(pieceId);
        for (Board board : history){
            if (board.getCoordinateByPieceId(pieceId) != initialCoordinate) return false;
        }
        return true;
    }
}
//itero sobre el hist de boards y veo que la coord de la pieza en cada estado del board
//es igual a la inicial