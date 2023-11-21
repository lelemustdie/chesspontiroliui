import common.*;
import javachess.factory.GameFactory;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
public class WeAreFriendsTest {

    GameFactory gameFactory = new GameFactory();
    @Test
    void check_If_Can_Eat_When_We_Are_Friends(){

        Game game = gameFactory.createGame();
        game.move(new Movement(new Coordinate(6,2), new Coordinate(6,3)));
        game.move(new Movement(new Coordinate(1,7), new Coordinate(1,6)));
        GetResult<Game,Boolean> move = game.move(new Movement(new Coordinate(7,1), new Coordinate(6,3)));
        assertEquals(false, move.getErrorValue());
    }
}