package common.Validators;

import common.Board;
import common.Movement;

import java.util.List;

public class InBoundsValidator implements Validator{
    @Override
    public boolean isValid(List<Board> history, Movement movement) {
        Board board = history.get(history.size() - 1);
        return movement.getDestination().column() >= 1 && movement.getDestination().column() <= board.getColumns() && movement.getDestination().row() >= 1 && movement.getDestination().row() <= board.getRows();
    }
}
