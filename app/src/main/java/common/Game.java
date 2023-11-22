package common;

import common.enums.Color;
import common.mover.Mover;
import common.validators.Validator;
import edu.austral.dissis.chess.gui.GameOver;
import org.ietf.jgss.GSSContext;

import java.util.List;
import java.util.Optional;

public record Game(Color turn, Color previousTurn, List<Board> history, Validator winningValidator, Mover mover) {

    public GetResult<Game, Boolean> move(Movement movement) { //Optional es para no tener nulls
        if (!checkPieceEmpty(movement.getOrigin())) return new GetResult<>(Optional.of(this), true);
        if (!checkTurn(movement.getOrigin())) return new GetResult<>(Optional.of(this), true);
        GetResult<Game, Boolean> result = mover.move(this, movement);
        if (result.getErrorValue()){
            return result;
        }
        Game gameResult = result.getOptional().get();
        if (gameResult.winningValidator.isValid(gameResult.history(), movement))
            return new GetResult<>(Optional.of(gameResult), true, "Game Over");
        else {
            return result;
        }
    }


    public Board getCurrentBoard() {
        return history.get(history.size() - 1);
    }

    public Color nextTurn() {
        return (turn == Color.WHITE) ? Color.BLACK : Color.WHITE;
    }

    private boolean checkPieceEmpty(Coordinate coordinate) {
        return getCurrentBoard().getPieces().containsKey(coordinate);
    }

    private boolean checkTurn(Coordinate coordinate) {
        return getCurrentBoard().getPieces().get(coordinate).getColor() == turn;
    }

}
