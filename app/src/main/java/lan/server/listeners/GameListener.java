package lan.server.listeners;

import edu.austral.dissis.chess.gui.GameEventListener;
import edu.austral.dissis.chess.gui.Move;
import lan.server.GameServer;
import org.jetbrains.annotations.NotNull;

public class GameListener implements GameEventListener {

    GameServer gameServer;

    public GameListener(GameServer gameServer) {
        this.gameServer = gameServer;
    }

    @Override
    public void handleMove(@NotNull Move move) {
        gameServer.handleMove(move);
    }
}