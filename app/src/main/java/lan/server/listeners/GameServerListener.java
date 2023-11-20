package lan.server.listeners;


import edu.austral.ingsis.clientserver.ServerConnectionListener;
import lan.server.GameServer;
import org.jetbrains.annotations.NotNull;

public class GameServerListener implements ServerConnectionListener {

    GameServer gameServer;  //para conectar el sv

    public GameServerListener(GameServer gameServer) {
        this.gameServer = gameServer;
    }

    @Override
    public void handleClientConnection(@NotNull String s) {
        gameServer.handleNewGame();
    }

    @Override
    public void handleClientConnectionClosed(@NotNull String s) {

    }
}