package common.validators;

import common.Board;
import common.Movement;

import java.util.List;

public class CompositeOrValidator implements Validator {

        private final Validator[] validators;

        public CompositeOrValidator(Validator... validators){
            this.validators = validators;
        }

        @Override
        public boolean isValid(List<Board> board, Movement movement){
            for (Validator validator : validators){ //alguna condicion de victoria no condicional o un tablero no condicional
                if (validator.isValid(board, movement)) return true;
            }
            return false;
        }
}
