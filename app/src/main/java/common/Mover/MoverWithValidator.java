package common.Mover;

import common.Game;
import common.GetResult;
import common.Movement;
import common.Validators.Validator;

import java.util.Optional;

public class MoverWithValidator implements Mover {

    private final Validator validator;
    private final Mover mover;

    public MoverWithValidator(Validator validator, Mover mover) {
        this.validator = validator;
        this.mover = mover;
    }

    @Override
    public GetResult<Game, Boolean> move(Game boardGame, Movement movement) {
        GetResult<Game, Boolean> result = mover.move(boardGame, movement);
        if (!result.getErrorValue()){
            if (validator.isValid(result.getOptional().get().getHistory(), movement)){
                return result;
            }
        }
        return new GetResult<>(Optional.of(boardGame), true);
    }
}
