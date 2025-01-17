package common;

import common.adapters.Adapter;
import edu.austral.dissis.chess.gui.*;
import javachess.factory.GameFactory;
import org.jetbrains.annotations.NotNull;

public class MyGameEngine implements GameEngine {
    private Game game;
    private final Adapter adapter = new Adapter();


    public MyGameEngine() {
        this.game = new GameFactory().createFirstToEatGame();
        //change GameFactory o CheckerGameFactory
    } //toggle createGame or createFirstToEatGame

    @NotNull
    @Override
    public MoveResult applyMove(@NotNull Move move) {
        Movement movement = adapter.moveToMovement(move);
        GetResult<Game, Boolean> result = game.move(movement);
        if (result.getErrorValue()){
            if (result.getMessage().equals("Game Over")) {
                return new GameOver(adapter.colorToPlayerColor(result.getOptional().get().nextTurn()));
            }
            return new InvalidMove("Invalid Move"); //extraccion de logica de get winner al game modificando el getresult
        } else {
            Game resultGame = result.getOptional().get();
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
