package common;

import common.adapters.Adapter;
import edu.austral.dissis.chess.gui.*;
import javachess.factory.GameFactory;
import org.jetbrains.annotations.NotNull;

public class MyGameEngine implements GameEngine {
    private Game game;
    private final Adapter adapter = new Adapter();


    public MyGameEngine() {
        this.game = new GameFactory().createGame();
        //change GameFactory o CheckerGameFactory
    } //toggle createGame or createFirstToEatGame

    @NotNull
    @Override
    public MoveResult applyMove(@NotNull Move move) {
        Movement movement = adapter.moveToMovement(move);
        GetResult<Game, Boolean> result = game.move(movement);
        if (result.getErrorValue()){
            return new InvalidMove("Invalid Move");
        }else {
            Game resultGame = result.getOptional().get();
            if (resultGame.winningValidator().isValid(resultGame.history(), movement)) //mover al game
                return new GameOver(adapter.colorToPlayerColor(resultGame.nextTurn()));
            this.game = resultGame;
            return new NewGameState(adapter.piecesToChessPieces(resultGame.getCurrentBoard().getPieces()), adapter.colorToPlayerColor(resultGame.turn()));
        }
    }

    @NotNull
    @Override
    public InitialState init() {
        return new InitialState(adapter.boardToBoardSize(game.getCurrentBoard()), adapter.piecesToChessPieces(game.getCurrentBoard().getPieces()), PlayerColor.WHITE);
    }
}
