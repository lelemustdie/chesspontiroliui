package common.Validators;

import common.Board;
import common.Movement;

import java.util.List;

public interface Validator {

    boolean isValid(List<Board> history, Movement movement); //list de boards y objeto tipo movement
}
