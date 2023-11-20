package javachess.Factory;

import common.Board;
import common.Coordinate;
import common.Piece;

import java.util.HashMap;
import java.util.Map;

public class DefaultBoardFactory {

    private static final PieceFactory pieceFactory = new PieceFactory();

    public Board createClassicBoard(){
        int id = 1;

        Map<Coordinate, Piece> pieces = new HashMap<>();
        //create white pawns
        for(int i = 1; i<= 8; i++){
            pieces.put(new Coordinate(i, 2), pieceFactory.createWhitePawn(id++));
            pieces.put(new Coordinate(i, 7), pieceFactory.createBlackPawn(id++));
        }

        //create white rooks
        pieces.put(new Coordinate(1, 1), pieceFactory.createWhiteRook(id++));
        pieces.put(new Coordinate(8, 1), pieceFactory.createWhiteRook(id++));
        pieces.put(new Coordinate(1, 8), pieceFactory.createBlackRook(id++));
        pieces.put(new Coordinate(8, 8), pieceFactory.createBlackRook(id++));

        //create white knights
        pieces.put(new Coordinate(2, 1), pieceFactory.createWhiteKnight(id++));
        pieces.put(new Coordinate(7, 1), pieceFactory.createWhiteKnight(id++));
        pieces.put(new Coordinate(2, 8), pieceFactory.createBlackKnight(id++));
        pieces.put(new Coordinate(7, 8), pieceFactory.createBlackKnight(id++));

        //create white bishops
        pieces.put(new Coordinate(3, 1), pieceFactory.createWhiteBishop(id++));
        pieces.put(new Coordinate(6, 1), pieceFactory.createWhiteBishop(id++));
        pieces.put(new Coordinate(3, 8), pieceFactory.createBlackBishop(id++));
        pieces.put(new Coordinate(6, 8), pieceFactory.createBlackBishop(id++));

        //create white queen
        pieces.put(new Coordinate(4, 1), pieceFactory.createWhiteQueen(id++));
        pieces.put(new Coordinate(4, 8), pieceFactory.createBlackQueen(id++));

        //create white king
        pieces.put(new Coordinate(5, 1), pieceFactory.createWhiteKing(id++));
        pieces.put(new Coordinate(5, 8), pieceFactory.createBlackKing(id++));


        return new Board(8, 8, pieces);
    }


    public Board createFirstToEatBoard() {
        int id = 1;

        Map<Coordinate, Piece> pieces = new HashMap<>();
        //create white pawns
        for (int i = 1; i <= 10; i++) {
            pieces.put(new Coordinate(i, 2), pieceFactory.createWhitePawn(id++));
            pieces.put(new Coordinate(i, 7), pieceFactory.createBlackPawn(id++));
        }

        //create white rooks
        pieces.put(new Coordinate(2, 1), pieceFactory.createWhiteRook(id++));
        pieces.put(new Coordinate(9, 1), pieceFactory.createWhiteRook(id++));
        pieces.put(new Coordinate(2, 8), pieceFactory.createBlackRook(id++));
        pieces.put(new Coordinate(9, 8), pieceFactory.createBlackRook(id++));

        //create white knights
        pieces.put(new Coordinate(3, 1), pieceFactory.createWhiteKnight(id++));
        pieces.put(new Coordinate(8, 1), pieceFactory.createWhiteKnight(id++));
        pieces.put(new Coordinate(3, 8), pieceFactory.createBlackKnight(id++));
        pieces.put(new Coordinate(8, 8), pieceFactory.createBlackKnight(id++));

        //create white bishops
        pieces.put(new Coordinate(4, 1), pieceFactory.createWhiteBishop(id++));
        pieces.put(new Coordinate(7, 1), pieceFactory.createWhiteBishop(id++));
        pieces.put(new Coordinate(4, 8), pieceFactory.createBlackBishop(id++));
        pieces.put(new Coordinate(7, 8), pieceFactory.createBlackBishop(id++));

        //create white queen
        pieces.put(new Coordinate(5, 1), pieceFactory.createWhiteQueen(id++));
        pieces.put(new Coordinate(5, 8), pieceFactory.createBlackQueen(id++));

        //create white king
        pieces.put(new Coordinate(6, 1), pieceFactory.createWhiteKing(id++));
        pieces.put(new Coordinate(6, 8), pieceFactory.createBlackKing(id++));

        //create archbishops
        pieces.put(new Coordinate(1, 1), pieceFactory.createWhiteArchbishop(id++));
        pieces.put(new Coordinate(1, 8), pieceFactory.createBlackArchbishop(id++));
        pieces.put(new Coordinate(10, 1), pieceFactory.createWhiteArchbishop(id++));
        pieces.put(new Coordinate(10, 8), pieceFactory.createBlackArchbishop(id++));

        return new Board(8, 10, pieces);
    }
}
