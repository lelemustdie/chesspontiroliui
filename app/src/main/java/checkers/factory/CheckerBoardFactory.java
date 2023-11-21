package checkers.factory;


import common.Board;
import common.Coordinate;
import common.Piece;

import java.util.HashMap;
import java.util.Map;

public class CheckerBoardFactory {

    private static final CheckerPieceFactory pieceFactory = new CheckerPieceFactory();

    public Board createCheckersBoard(){
        int id = 1;

        Map<Coordinate, Piece> pieces = new HashMap<>();

        for (int i = 1; i <= 8; i+= 2){
            pieces.put(new Coordinate(i, 1), pieceFactory.createWhitePiece(id++));
            pieces.put(new Coordinate(i + 1, 2), pieceFactory.createWhitePiece(id++));
            pieces.put(new Coordinate(i, 3), pieceFactory.createWhitePiece(id++));
            pieces.put(new Coordinate(i + 1, 6), pieceFactory.createBlackPiece(id++));
            pieces.put(new Coordinate(i, 7), pieceFactory.createBlackPiece(id++));
            pieces.put(new Coordinate(i + 1, 8), pieceFactory.createBlackPiece(id++));
        }

        return new Board(8, 8, pieces);
    }

    public Board createTinyCheckersBoard(){

        int id = 1;

        Map<Coordinate, Piece> pieces = new HashMap<>();

        for (int i = 1; i <= 10; i+= 2){
            pieces.put(new Coordinate(i, 1), pieceFactory.createWhitePiece(id++));
            pieces.put(new Coordinate(i + 1, 2), pieceFactory.createWhitePiece(id++));
            pieces.put(new Coordinate(i + 1, 6), pieceFactory.createBlackPiece(id++));
            pieces.put(new Coordinate(i, 5), pieceFactory.createBlackPiece(id++));

        }

        return new Board(6, 10, pieces);
    }


}
