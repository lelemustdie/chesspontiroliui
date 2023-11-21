package checkers.mover;

import checkers.validators.CanEatAgainValidator;
import checkers.validators.HasEatenValidator;
import common.Board;
import common.Game;
import common.GetResult;
import common.Movement;
import common.Mover.Mover;

import java.util.List;
import java.util.Optional;

public class CanEatAgainMover implements Mover {

    private static final CanEatAgainValidator eatAgainValidator = new CanEatAgainValidator(new HasEatenValidator());

    @Override
    public GetResult<Game, Boolean> move(Game boardGame, Movement movement) {
        if (!checkNumberOfPieces(boardGame.history())) return new GetResult<>(Optional.of(new Game(boardGame.nextTurn(), boardGame.turn(), boardGame.history(), boardGame.winningValidator(), boardGame.mover())), false);
        if (eatAgainValidator.isValid(boardGame.history(), movement))
            return new GetResult<>(Optional.of(boardGame), false);
        return new GetResult<>(Optional.of(new Game(boardGame.nextTurn(), boardGame.turn(), boardGame.history(), boardGame.winningValidator(), boardGame.mover())), false);
    }

    private boolean checkNumberOfPieces(List<Board> history){
        return history.get(history.size() - 1).getPieces().size()  < history.get(history.size() - 2).getPieces().size();
    }

}
