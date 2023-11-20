package lan.client;

import com.fasterxml.jackson.core.type.TypeReference;
import edu.austral.dissis.chess.gui.*;
import edu.austral.ingsis.clientserver.Client;
import edu.austral.ingsis.clientserver.ClientBuilder;
import edu.austral.ingsis.clientserver.Message;
import javafx.application.Platform;
import lan.client.listeners.GameOverListener;
import lan.client.listeners.InitListener;
import lan.client.listeners.InvalidMoveListener;
import lan.client.listeners.NewStateListener;

import java.net.InetSocketAddress;

public class GameClient { //manda los moves al server
    private final Client client;
    private final GameView gameView;

    private final ClientBuilder builder;
    public GameClient(GameView gameView, ClientBuilder builder) {
        this.gameView = gameView;
        this.builder = builder;
        this.client = buildClient();
        gameView.addListener(new GameListener(this));
        client.connect();
    }

    public void handleInitialState(InitialState initialState) {
        Platform.runLater(() -> gameView.handleInitialState(initialState));
    }

    public void handleNewGameState(NewGameState newGameState) {
        Platform.runLater(() -> gameView.handleMoveResult(newGameState));
    }

    public void handleGameOver(GameOver gameOver) {
        Platform.runLater(() -> gameView.handleMoveResult(gameOver));
    }

    public void handleInvalidMove(InvalidMove invalidMove) {
        Platform.runLater(() -> gameView.handleMoveResult(invalidMove));
    }

    public void handleMove(Move move) {
        client.send(new Message<>("move", move));
    }

    private Client buildClient() {
        return builder
                .withAddress(new InetSocketAddress("localhost",8080))
                .addMessageListener("init", new TypeReference<>(){}, new InitListener(this))
                .addMessageListener("new-game-state", new TypeReference<>(){}, new NewStateListener(this))
                .addMessageListener("invalid-move", new TypeReference<>(){}, new InvalidMoveListener(this))
                .addMessageListener("game-over", new TypeReference<>(){}, new GameOverListener(this))
                .build();
    }
}