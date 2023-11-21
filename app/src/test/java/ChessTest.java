import common.*;
import javachess.factory.GameFactory;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
public class ChessTest {

    GameFactory gameFactory = new GameFactory();

    @Test
    void checkIfBoardSizeIsCorrect(){
        Game game = gameFactory.createGame();
        assertEquals(8, game.getCurrentBoard().getRows());
        assertEquals(8, game.getCurrentBoard().getColumns());
    }

    @Test
    void checkIfCheckWorksProperly(){
        Game game = gameFactory.createGame();
        game.move(new Movement(new Coordinate(6,2), new Coordinate(6,3)));
        game.move(new Movement(new Coordinate(5,7), new Coordinate(5,6)));
        game.move(new Movement(new Coordinate(2,1), new Coordinate(1,3)));
        game.move(new Movement(new Coordinate(4,8), new Coordinate(8,4)));
        GetResult<Game,Boolean> move = game.move(new Movement(new Coordinate(1,1), new Coordinate(2,1)));
        assertEquals(true, move.getErrorValue());
    }
}
