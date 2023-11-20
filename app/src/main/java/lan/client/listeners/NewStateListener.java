package lan.client.listeners;

import edu.austral.dissis.chess.gui.NewGameState;
import edu.austral.ingsis.clientserver.Message;
import lan.client.GameClient;
import org.jetbrains.annotations.NotNull;
import edu.austral.ingsis.clientserver.MessageListener;

public class NewStateListener implements MessageListener<NewGameState> {

    private final GameClient gameClient;

    public NewStateListener(GameClient gameClient) {
        this.gameClient = gameClient;
    }

    @Override
    public void handleMessage(@NotNull Message<NewGameState> message) {
        gameClient.handleNewGameState(message.getPayload());
    }
}