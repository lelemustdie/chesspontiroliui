package common;

import checkers.factory.CheckerGameFactory;
import common.Adapters.Adapter;
import edu.austral.dissis.chess.gui.*;
import javachess.Factory.GameFactory;
import org.jetbrains.annotations.NotNull;

public class MyGameEngine implements GameEngine {
    private Game game;
    private final Adapter adapter = new Adapter();


    public MyGameEngine() {
        this.game = new CheckerGameFactory().createGame(); //change GameFactory o CheckerGameFactory
    }

    @NotNull
    @Override
    public MoveResult applyMove(@NotNull Move move) {
        Movement movement = adapter.moveToMovement(move);
        GetResult<Game, Boolean> result = game.move(movement);
        if (result.getErrorValue()){
            return new InvalidMove("Invalid Move");
        }else {
            Game resultGame = result.getOptional().get();
            if (resultGame.getWinningValidator().isValid(resultGame.getHistory(), movement))
                return new GameOver(adapter.colorToPlayerColor(resultGame.nextTurn()));
            this.game = resultGame;
            return new NewGameState(adapter.piecesToChessPieces(resultGame.getCurrentBoard().getPieces()), adapter.colorToPlayerColor(resultGame.getTurn()));
        }
    }

    @NotNull
    @Override
    public InitialState init() {
        return new InitialState(adapter.boardToBoardSize(game.getCurrentBoard()), adapter.piecesToChessPieces(game.getCurrentBoard().getPieces()), PlayerColor.WHITE);
    }
}
