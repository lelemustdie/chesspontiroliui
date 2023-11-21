package common.mover;

import common.Game;
import common.GetResult;
import common.Movement;

public interface Mover {
    GetResult<Game, Boolean> move(Game game, Movement movement);
}
