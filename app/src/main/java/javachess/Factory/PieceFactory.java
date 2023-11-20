package javachess.Factory;

import common.Enums.Color;
import common.Piece;
import common.Enums.PieceType;
import common.Validators.CompositeAndValidator;
import common.Validators.CompositeOrValidator;
import common.Validators.Validator;
import javachess.Validators.*;
import common.Validators.Movements.DiagonalMoveValidator;
import common.Validators.Movements.HorizontalMoveValidator;
import common.Validators.Movements.VerticalMoveValidator;
import javachess.Validators.SpecialMovements.KnightMoveValidator;
import common.Validators.LimitedMoveValidator;
import javachess.Validators.SpecialMovements.PieceFirstMoveValidator;

public class PieceFactory {


    public Piece createWhitePawn(int id){

        Validator validator = new CompositeAndValidator(
                new CompositeOrValidator(
                        new CompositeAndValidator(
                                new VerticalMoveValidator(true),
                                new JustMovingAroundValidator(),
                                new LimitedMoveValidator(1)
                        ),
                        new CompositeAndValidator(
                                new DiagonalMoveValidator(true),
                                new EatingValidator(),
                                new LimitedMoveValidator(1)
                        ),
                        new CompositeAndValidator(
                                new VerticalMoveValidator(true),
                                new JustMovingAroundValidator(),
                                new LimitedMoveValidator(2),
                                new PieceFirstMoveValidator()
                        )
                )
        );
        return new Piece(id, Color.WHITE, PieceType.PAWN, validator);

    }

    public Piece createBlackPawn(int id){

        Validator validator = new CompositeAndValidator(
                new CompositeOrValidator(
                        new CompositeAndValidator(
                                new VerticalMoveValidator(false),
                                new JustMovingAroundValidator(),
                                new LimitedMoveValidator(1)
                        ),
                        new CompositeAndValidator(
                                new DiagonalMoveValidator(false),
                                new EatingValidator(),
                                new LimitedMoveValidator(1)
                        ),
                        new CompositeAndValidator(
                                new VerticalMoveValidator(false),
                                new JustMovingAroundValidator(),
                                new LimitedMoveValidator(2),
                                new PieceFirstMoveValidator()
                        )
                )
        );
        return new Piece(id, Color.BLACK, PieceType.PAWN, validator);

    }

    public Piece createWhiteRook(int id){
        Validator validator = new CompositeAndValidator(
                new CompositeOrValidator(
                        new VerticalMoveValidator(true),
                        new VerticalMoveValidator(false),
                        new HorizontalMoveValidator(true),
                        new HorizontalMoveValidator(false)
                ),
                new WeAreFriendsValidator()
        );
        return new Piece(id, Color.WHITE, PieceType.ROOK, validator);
    }

    public Piece createBlackRook(int id){
        Validator validator = new CompositeAndValidator(
                new CompositeOrValidator(
                        new VerticalMoveValidator(true),
                        new VerticalMoveValidator(false),
                        new HorizontalMoveValidator(true),
                        new HorizontalMoveValidator(false)
                ),
                new WeAreFriendsValidator()
        );
        return new Piece(id, Color.BLACK, PieceType.ROOK, validator);
    }

    public Piece createWhiteKnight(int id){
        Validator validator = new CompositeAndValidator(
                new KnightMoveValidator(),
                new WeAreFriendsValidator()
        );
        return new Piece(id, Color.WHITE, PieceType.KNIGHT, validator);
    }

    public Piece createBlackKnight(int id){
        Validator validator = new CompositeAndValidator(
                new KnightMoveValidator(),
                new WeAreFriendsValidator()
        );
        return new Piece(id, Color.BLACK, PieceType.KNIGHT, validator);
    }

    public Piece createWhiteBishop(int id){
        Validator validator = new CompositeAndValidator(
                new CompositeOrValidator(
                        new DiagonalMoveValidator(true),
                        new DiagonalMoveValidator(false)
                ),
                new WeAreFriendsValidator()
        );
        return new Piece(id, Color.WHITE, PieceType.BISHOP, validator);
    }

    public Piece createBlackBishop(int id){
        Validator validator = new CompositeAndValidator(
                new CompositeOrValidator(
                        new DiagonalMoveValidator(true),
                        new DiagonalMoveValidator(false)
                ),
                new WeAreFriendsValidator()
        );
        return new Piece(id, Color.BLACK, PieceType.BISHOP, validator);
    }

    public Piece createWhiteQueen(int id){
        Validator validator = new CompositeAndValidator(
                new CompositeOrValidator(
                        new VerticalMoveValidator(true),
                        new VerticalMoveValidator(false),
                        new HorizontalMoveValidator(true),
                        new HorizontalMoveValidator(false),
                        new DiagonalMoveValidator(true),
                        new DiagonalMoveValidator(false)
                ),
                new WeAreFriendsValidator()
        );
        return new Piece(id, Color.WHITE, PieceType.QUEEN, validator);
    }

    public Piece createBlackQueen(int id){
        Validator validator = new CompositeAndValidator(
                new CompositeOrValidator(
                        new VerticalMoveValidator(true),
                        new VerticalMoveValidator(false),
                        new HorizontalMoveValidator(true),
                        new HorizontalMoveValidator(false),
                        new DiagonalMoveValidator(true),
                        new DiagonalMoveValidator(false)
                ),
                new WeAreFriendsValidator()
        );
        return new Piece(id, Color.BLACK, PieceType.QUEEN, validator);
    }

    public Piece createWhiteKing(int id){
        Validator validator = new CompositeAndValidator(
                new CompositeOrValidator(
                        new VerticalMoveValidator(true),
                        new VerticalMoveValidator(false),
                        new HorizontalMoveValidator(true),
                        new HorizontalMoveValidator(false),
                        new DiagonalMoveValidator(true),
                        new DiagonalMoveValidator(false)
                ),
                new LimitedMoveValidator(1),
                new WeAreFriendsValidator()
        );
        return new Piece(id, Color.WHITE, PieceType.KING, validator);
    }

    public Piece createBlackKing(int id){
        Validator validator = new CompositeAndValidator(
                new CompositeOrValidator(
                        new VerticalMoveValidator(true),
                        new VerticalMoveValidator(false),
                        new HorizontalMoveValidator(true),
                        new HorizontalMoveValidator(false),
                        new DiagonalMoveValidator(true),
                        new DiagonalMoveValidator(false)
                ),
                new LimitedMoveValidator(1),
                new WeAreFriendsValidator()
        );
        return new Piece(id, Color.BLACK, PieceType.KING, validator);
    }
    public Piece createWhiteArchbishop(int id){
        Validator validator = new CompositeAndValidator(
                new CompositeOrValidator(
                        new DiagonalMoveValidator(true),
                        new DiagonalMoveValidator(false),
                        new KnightMoveValidator()
                ),
                new WeAreFriendsValidator()
        );
        return new Piece(id, Color.WHITE, PieceType.ARCHBISHOP, validator);
    }

    public Piece createBlackArchbishop(int id){
        Validator validator = new CompositeAndValidator(
                new CompositeOrValidator(
                        new DiagonalMoveValidator(true),
                        new DiagonalMoveValidator(false),
                        new KnightMoveValidator()
                ),
                new WeAreFriendsValidator()
        );
        return new Piece(id, Color.BLACK, PieceType.ARCHBISHOP, validator);
    }

}
