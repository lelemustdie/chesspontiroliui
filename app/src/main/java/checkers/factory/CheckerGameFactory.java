package checkers.factory;

import checkers.mover.CanEatAgainMover;
import checkers.mover.DefaultCheckersMover;
import checkers.mover.MovingSamePieceMover;
import checkers.mover.PromotionMover;
import checkers.validators.GameOverValidator;
import checkers.validators.NoEnemyPiecesAtSight;
import common.Enums.Color;
import common.Game;
import common.Mover.SequenceMover;
import common.Validators.CompositeOrValidator;

import java.util.List;

public class CheckerGameFactory {
    private static final CheckerBoardFactory boardFactory = new CheckerBoardFactory();

    public Game createGame() {
        return new Game(
                Color.WHITE,
                Color.BLACK,
                List.of(boardFactory.createCheckersBoard()),
                new CompositeOrValidator( //win
                        new NoEnemyPiecesAtSight(),
                        new GameOverValidator()
                ),
                new SequenceMover( //steps
                        new MovingSamePieceMover(),
                        new DefaultCheckersMover(),
                        new CanEatAgainMover(),
                        new PromotionMover()
                )
                );
    }
}
