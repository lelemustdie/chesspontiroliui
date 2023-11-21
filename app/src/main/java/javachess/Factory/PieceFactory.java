package javachess.Factory;

import common.Enums.Color;
import common.Enums.PieceType;
import common.Piece;
import common.Validators.CompositeAndValidator;
import common.Validators.CompositeOrValidator;
import common.Validators.LimitedMoveValidator;
import common.Validators.Movements.DiagonalMoveValidator;
import common.Validators.Movements.HorizontalMoveValidator;
import common.Validators.Movements.VerticalMoveValidator;
import common.Validators.Validator;
import javachess.Validators.EatingValidator;
import javachess.Validators.JustMovingAroundValidator;
import javachess.Validators.SpecialMovements.KnightMoveValidator;
import javachess.Validators.SpecialMovements.PieceFirstMoveValidator;
import javachess.Validators.WeAreFriendsValidator;
import org.jetbrains.annotations.NotNull;

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
        Validator validator = rookValidators();
        return new Piece(id, Color.WHITE, PieceType.ROOK, validator);
    }

    public Piece createBlackRook(int id){
        Validator validator = rookValidators();
        return new Piece(id, Color.BLACK, PieceType.ROOK, validator);
    }

    @NotNull
    private static Validator rookValidators() {
        Validator validator = new CompositeAndValidator(
                new CompositeOrValidator(
                        new VerticalMoveValidator(true),
                        new VerticalMoveValidator(false),
                        new HorizontalMoveValidator(true),
                        new HorizontalMoveValidator(false)
                ),
                new WeAreFriendsValidator()
        );
        return validator;
    }

    public Piece createWhiteKnight(int id){
        Validator validator = knightValidators();
        return new Piece(id, Color.WHITE, PieceType.KNIGHT, validator);
    }

    @NotNull
    private static Validator knightValidators() {
        Validator validator = new CompositeAndValidator(
                new KnightMoveValidator(),
                new WeAreFriendsValidator()
        );
        return validator;
    }

    public Piece createBlackKnight(int id){
        Validator validator = knightValidators();
        return new Piece(id, Color.BLACK, PieceType.KNIGHT, validator);
    }

    public Piece createWhiteBishop(int id){
        Validator validator = bishopValidators();
        return new Piece(id, Color.WHITE, PieceType.BISHOP, validator);
    }

    @NotNull
    private static Validator bishopValidators() {
        Validator validator = new CompositeAndValidator(
                new CompositeOrValidator(
                        new DiagonalMoveValidator(true),
                        new DiagonalMoveValidator(false)
                ),
                new WeAreFriendsValidator()
        );
        return validator;
    }

    public Piece createBlackBishop(int id){
        Validator validator = bishopValidators();
        return new Piece(id, Color.BLACK, PieceType.BISHOP, validator);
    }

    public Piece createWhiteQueen(int id){
        Validator validator = queenValidators();
        return new Piece(id, Color.WHITE, PieceType.QUEEN, validator);
    }

    @NotNull
    private static Validator queenValidators() {
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
        return validator;
    }

    public Piece createBlackQueen(int id){
        Validator validator = queenValidators();
        return new Piece(id, Color.BLACK, PieceType.QUEEN, validator);
    }

    public Piece createWhiteKing(int id){
        Validator validator = kingValidators();
        return new Piece(id, Color.WHITE, PieceType.KING, validator);
    }

    public Piece createBlackKing(int id){
        Validator validator = kingValidators();
        return new Piece(id, Color.BLACK, PieceType.KING, validator);
    }

    @NotNull
    private static Validator kingValidators() {
        Validator validator = new CompositeAndValidator(
                new WeAreFriendsValidator(),

                new CompositeOrValidator(
                        new CompositeAndValidator(
                                new LimitedMoveValidator(1),
                                new CompositeOrValidator(
                                        new VerticalMoveValidator(true),
                                        new VerticalMoveValidator(false),
                                        new HorizontalMoveValidator(true),
                                        new HorizontalMoveValidator(false),
                                        new DiagonalMoveValidator(true),
                                        new DiagonalMoveValidator(false)
                                )
                        ),
                        new CompositeAndValidator(
                                new LimitedMoveValidator(2),
                                new CompositeOrValidator(
                                        new HorizontalMoveValidator(true),
                                        new HorizontalMoveValidator(false)
                                )
                        )
                )
        );
        return validator;
    }

    public Piece createWhiteArchbishop(int id){
        Validator validator = archbishopValidators();
        return new Piece(id, Color.WHITE, PieceType.ARCHBISHOP, validator);
    }

    public Piece createBlackArchbishop(int id){
        Validator validator = archbishopValidators();
        return new Piece(id, Color.BLACK, PieceType.ARCHBISHOP, validator);
    }

    @NotNull
    private static Validator archbishopValidators() {
        Validator validator = new CompositeAndValidator(
                new CompositeOrValidator(
                        new DiagonalMoveValidator(true),
                        new DiagonalMoveValidator(false),
                        new KnightMoveValidator()
                ),
                new WeAreFriendsValidator()
        );
        return validator;
    }

}
