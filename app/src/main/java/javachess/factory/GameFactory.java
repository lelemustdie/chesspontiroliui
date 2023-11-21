package javachess.factory;

import common.mover.CompositeOrMover;
import common.enums.Color;
import common.Game;
import common.mover.MoverWithValidator;
import common.mover.SequenceMover;
import javachess.mover.CastlingMover;
import javachess.mover.ChessMover;
import javachess.mover.PromotionMover;
import javachess.validators.checknmate.CheckMateValidator;
import javachess.validators.checknmate.CheckValidator;
import common.validators.FirstToEatValidator;

import java.util.List;

public class GameFactory {
    private static final DefaultBoardFactory boardFactory = new DefaultBoardFactory();

    public Game createGame() {
        return new Game(Color.WHITE, Color.BLACK, List.of(boardFactory.createClassicBoard()),
                new CheckMateValidator(new CheckValidator()),
                new MoverWithValidator(new CheckValidator(),
                        new SequenceMover(
                                new CompositeOrMover(
                                        new PromotionMover(),
                                        new ChessMover()
                                        ),
                                new CastlingMover()
                        )));

    }

    public Game createFirstToEatGame() {
        return new Game(Color.WHITE, Color.BLACK, List.of(boardFactory.createFirstToEatBoard()),
                new FirstToEatValidator(),
                new CompositeOrMover(
                        new ChessMover()
                ));
    }
}
