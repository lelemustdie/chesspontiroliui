package lan.server;

import com.fasterxml.jackson.core.type.TypeReference;
import edu.austral.dissis.chess.gui.*;
import edu.austral.ingsis.clientserver.Message;
import edu.austral.ingsis.clientserver.Server;
import edu.austral.ingsis.clientserver.ServerBuilder;
import lan.server.listeners.GameListener;
import lan.server.listeners.GameServerListener;
import lan.server.listeners.MoveListener;

public class GameServer { //se suscribe al topic q se llama move entonces cuando le llega, se lo manda al cliente, dependiendo al resultado lo q mande al cliente es el move
    //una vez el server procesa ese result

    private final Server server;
    private final GameEngine gameEngine;
    private final GameView gameView;

    public GameServer(GameEngine gameEngine, GameView gameView, ServerBuilder serverBuilder) {
        this.gameEngine = gameEngine; //serverbuilder, funciona como server pero muestra como client
        this.gameView = gameView; //solo para verlo no need
        addStatesToGameView(new GameListener(this));
        this.server = buildServer(serverBuilder);
        server.start(); //comienza
    }

    public void handleMove(Move move) {
        MoveResult result = gameEngine.applyMove(move);
        gameView.handleMoveResult(result);
        broadcastState(result);
    }

    public void broadcastState(MoveResult state) { //es como un switch, si es un nuewgamestate el server manda al topic un newgamestate
        if (state instanceof NewGameState) {
            server.broadcast(new Message<>("new-game-state", state));
        } else if (state instanceof GameOver) {
            server.broadcast(new Message<>("game-over", state));
        } else if (state instanceof InvalidMove) {
            server.broadcast(new Message<>("invalid-move", state));
        } //brodcastea a todos los clientes suscriptos al topics
    }

    public void handleNewGame() {
        InitialState initialState = gameEngine.init();
        gameView.handleInitialState(initialState);
        server.broadcast(new Message<>("init", initialState));
    }

    public Server buildServer(ServerBuilder builder){
        return builder
                .withPort(8080)
                .withConnectionListener(new GameServerListener(this)) //usa el server listener para llamar al handle new game
                .addMessageListener("move", new TypeReference<>(){}, new MoveListener(new GameListener(this)))
                .build();
    }

    private void addStatesToGameView(GameEventListener gameListener) {
        gameView.addListener(gameListener);
        gameView.handleInitialState(gameEngine.init());
    }
}