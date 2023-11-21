package common.Mover;

import common.Game;
import common.GetResult;
import common.Movement;
import common.Mover.Mover;

import java.util.Optional;

public class CompositeOrMover implements Mover {

    private final Mover[] movers;

    public CompositeOrMover(Mover... movers) {
        this.movers = movers;
    }


    @Override
    public GetResult<Game, Boolean> move(Game boardGame, Movement movement) {
        for(Mover mover : this.movers){
            GetResult<Game, Boolean> result = mover.move(boardGame, movement);
            if (!result.getErrorValue()) return result;
        }
        return new GetResult<>(Optional.of(boardGame), true);
    }
}
