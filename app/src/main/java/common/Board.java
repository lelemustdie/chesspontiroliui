package common;

import common.enums.Color;
import common.enums.PieceType;

import java.util.HashMap;
import java.util.Map;

public class Board {


    private final Map<Coordinate, Piece> pieces;
    private final int rows;
    private final int columns;

    public Board(int rows, int columns, Map<Coordinate, Piece> pieces){
        this.rows = rows;
        this.columns = columns;
        this.pieces = pieces;
    }

    public int getRows(){
        return rows;
    }

    public int getColumns(){
        return columns;
    }

    public Map<Coordinate, Piece> getPieces() {
        return pieces; //clone
    }

    public Board movePiece(Movement movement){

        Map<Coordinate, Piece> copyPieces = new HashMap<>(pieces); //copia las piezas actuales
        Coordinate origin = movement.getOrigin(); //agarra la cord origen del move a realizar
        Coordinate destination = movement.getDestination(); //same pero final
        Piece piece = copyPieces.get(origin); //agarra la pieza del origen
        copyPieces.remove(origin); //la saca
        copyPieces.put(destination, piece); //la pone en el final place
        return new Board(rows, columns, new HashMap<>(copyPieces)); //devuelve un nuevo board haciendo un map de las nuevas piezas
    }

    public Board removePiece(Coordinate coordinate){
        Map<Coordinate, Piece> copyPieces = new HashMap<>(pieces);
        copyPieces.remove(coordinate);
        return new Board(rows, columns, new HashMap<>(copyPieces));
    }

    public Board addPiece(Coordinate coordinate, Piece piece) {
        Map<Coordinate, Piece> copyPieces = new HashMap<>(pieces);
        copyPieces.put(coordinate, piece);
        return new Board(rows, columns, new HashMap<>(copyPieces));
    }

    public Coordinate getKingsCoordinate(Color color){
        for(Map.Entry<Coordinate, Piece> entry : pieces.entrySet()){
            Piece value = entry.getValue();
            if (value.getColor() == color && value.getType() == PieceType.KING) return entry.getKey();
        }
        return null;
    }

    public Coordinate getCoordinateByPieceId(int targetPieceId) {
        for (Map.Entry<Coordinate, Piece> entry : pieces.entrySet()) {
            if (entry.getValue().getId() == targetPieceId) {
                return entry.getKey();
            }
        }
        return null; // nunca va a llegar a esto pq los peones no vuelven al mismo lugar
    }
    public Piece getPiece(Coordinate coordinate) {
        return pieces.get(coordinate);
    }

    public Board addAndRemovePiece (Coordinate pieceToRemove, Piece pieceToAdd, Coordinate coordinate){
        Map<Coordinate, Piece> copyPieces = new HashMap<>(pieces);
        copyPieces.remove(pieceToRemove);
        copyPieces.put(coordinate, pieceToAdd);
        return new Board(rows, columns, new HashMap<>(copyPieces));
    }
}
