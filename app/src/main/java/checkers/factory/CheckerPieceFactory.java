package checkers.factory;


import checkers.validators.DiagonalMoveValidator;
import checkers.validators.HasEatenValidator;
import checkers.validators.WeAreFriends;
import checkers.validators.NotBlockedValidator;
import common.Enums.Color;
import common.Enums.PieceType;
import common.Piece;
import common.Validators.*;

public class CheckerPieceFactory {

    public Piece createWhitePiece(int id){
        Validator validator = new CompositeAndValidator(
                new CompositeOrValidator(
                        new CompositeAndValidator(
                                new DiagonalMoveValidator(true),
                                new LimitedMoveValidator(1)
                        ),
                        new CompositeAndValidator(
                                new DiagonalMoveValidator(true),
                                new LimitedMoveValidator(2),
                                new WeAreFriends(),
                                new HasEatenValidator()
                        )
                ),
                new NotBlockedValidator(),
                new InBoundsValidator()
        );
        return new Piece(id, Color.WHITE, PieceType.PAWN, validator);
    }

    public Piece createBlackPiece(int id){
        Validator validator = new CompositeAndValidator(
                new CompositeOrValidator(
                        new CompositeAndValidator(
                                new DiagonalMoveValidator(false),
                                new LimitedMoveValidator(1)
                        ),
                        new CompositeAndValidator(
                                new DiagonalMoveValidator(false),
                                new LimitedMoveValidator(2),
                                new WeAreFriends(),
                                new HasEatenValidator()
                        )
                ),
                new NotBlockedValidator(),
                new InBoundsValidator()
        );
        return new Piece(id, Color.BLACK, PieceType.PAWN, validator);
    }

    public Piece createWhiteQueen(int id){
        Validator validator = new CompositeAndValidator(
                new CompositeOrValidator(
                        new CompositeAndValidator(
                                new CompositeOrValidator(
                                        new DiagonalMoveValidator(true),
                                        new DiagonalMoveValidator(false)
                                ),
                                new LimitedMoveValidator(1)
                        ),
                        new CompositeAndValidator(
                                new CompositeOrValidator(
                                        new DiagonalMoveValidator(true),
                                        new DiagonalMoveValidator(false)
                                ),
                                new LimitedMoveValidator(2),
                                new WeAreFriends(),
                                new HasEatenValidator()
                        )
                ),
                new NotBlockedValidator(),
                new InBoundsValidator()
        );
        return new Piece(id, Color.WHITE, PieceType.QUEEN, validator);
    }

    public Piece createBlackQueen(int id){
        Validator validator = new CompositeAndValidator(
                new CompositeOrValidator(
                        new CompositeAndValidator(
                                new CompositeOrValidator(
                                        new DiagonalMoveValidator(true),
                                        new DiagonalMoveValidator(false)
                                ),
                                new LimitedMoveValidator(1)
                        ),
                        new CompositeAndValidator(
                                new CompositeOrValidator(
                                        new DiagonalMoveValidator(true),
                                        new DiagonalMoveValidator(false)
                                ),
                                new LimitedMoveValidator(2),
                                new WeAreFriends(),
                                new HasEatenValidator()
                        )
                ),
                new NotBlockedValidator(),
                new InBoundsValidator()
        );
        return new Piece(id, Color.BLACK, PieceType.QUEEN, validator);
    }
}