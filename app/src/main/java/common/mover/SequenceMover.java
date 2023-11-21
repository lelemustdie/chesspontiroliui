package common.mover;

import common.Game;
import common.GetResult;
import common.Movement;

public class SequenceMover implements Mover { //secuenciador de mov

    private final Mover[] movers;

    public SequenceMover(Mover... movers){
        this.movers = movers;
    }

    @Override
    public GetResult<Game, Boolean> move(Game boardGame, Movement movement) {
        GetResult<Game, Boolean> result = movers[0].move(boardGame, movement);
        if (result.getErrorValue()) return result;
        for (int i = 1; i < movers.length; i++) { //loopea los movers y llama a .move
            Mover mover = movers[i];
            result = mover.move(result.getOptional().get(), movement);
            if (result.getErrorValue()) return result;
        }
        return result;
    }
}
