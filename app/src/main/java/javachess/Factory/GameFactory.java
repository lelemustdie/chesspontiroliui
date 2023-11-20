package javachess.Factory;

import common.CompositeOrMover;
import common.Enums.Color;
import common.Game;
import common.Mover.MoverWithValidator;
import javachess.ChessMover;
import javachess.PromotionMover;
import javachess.Validators.CheckNMate.CheckMateValidator;
import javachess.Validators.CheckNMate.CheckValidator;
import javachess.Validators.FirstToEatValidator;

import java.util.List;

public class GameFactory {
    private static final DefaultBoardFactory boardFactory = new DefaultBoardFactory();

    public Game createGame() {
        return new Game(Color.WHITE, Color.BLACK, List.of(boardFactory.createClassicBoard()), new CheckMateValidator(new CheckValidator()), new MoverWithValidator(new CheckValidator(), new CompositeOrMover(new PromotionMover(), new ChessMover())));
    }

    public Game createFirstToEatGame() {
        return new Game(Color.WHITE, Color.BLACK, List.of(boardFactory.createFirstToEatBoard()), new FirstToEatValidator(), new CompositeOrMover(new ChessMover()));
    }
}
