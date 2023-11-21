package common.mover;

import common.Game;
import common.GetResult;
import common.Movement;
import common.validators.Validator;

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
            if (validator.isValid(result.getOptional().get().history(), movement)){
                return result;
            }
        }
        return new GetResult<>(Optional.of(boardGame), true);
    }
}
