package checkers.mover;

import checkers.validators.CanEatAgainValidator;
import checkers.validators.HasEatenValidator;
import common.*;
import common.Mover.Mover;

import java.util.List;
import java.util.Optional;

public class CanEatAgainMover implements Mover {

    private static final CanEatAgainValidator eatAgainValidator = new CanEatAgainValidator(new HasEatenValidator());

    @Override
    public GetResult<Game, Boolean> move(Game boardGame, Movement movement) {
        if (!checkNumberOfPieces(boardGame.getHistory())) return new GetResult<>(Optional.of(new Game(boardGame.nextTurn(), boardGame.getTurn(), boardGame.getHistory(), boardGame.getWinningValidator(), boardGame.getMover())), false);
        if (eatAgainValidator.isValid(boardGame.getHistory(), movement))
            return new GetResult<>(Optional.of(boardGame), false);
        return new GetResult<>(Optional.of(new Game(boardGame.nextTurn(), boardGame.getTurn(), boardGame.getHistory(), boardGame.getWinningValidator(), boardGame.getMover())), false);
    }

    private boolean checkNumberOfPieces(List<Board> history){
        return history.get(history.size() - 1).getPieces().size()  < history.get(history.size() - 2).getPieces().size();
    }

}
