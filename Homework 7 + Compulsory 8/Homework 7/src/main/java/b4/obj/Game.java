package b4.obj;

import lombok.Data;
import lombok.extern.java.Log;

import java.util.ArrayList;
import java.util.List;

@Log
@Data
public class Game {
    private final Bag bag = new Bag();
    private final Board board = new Board();
    private final Dictionary dictionary = new Dictionary("src/main/resources/dictionary.in");
    private final List<Player> players = new ArrayList<>();

    private int playerTurnIndex;
    private boolean playing;

    public void addPlayer(Player player) {
        players.add(player);
        //player.setGame(this);
    }

    public void play() {
        TimeKeeper timeKeeper = new TimeKeeper(60);
        Thread timeDaemon = new Thread(timeKeeper);
        timeDaemon.setDaemon(true);
        timeDaemon.start();

        playerTurnIndex = 0;

        playing = true;

        //start a new Thread for each player;
        for (Player player : players) {
            new Thread(player).start();
        }
    }

    public void getWinner() {
        synchronized (this) {
            while (playing) {
                try {
                    wait();
                } catch (InterruptedException e) {
                    log.warning("Game cannot wait.");
                }
            }
        }

        Player winner = null;
        int maxScore = -1;

        for (Player player : players) {
            if (player.getScore() > maxScore) {
                winner = player;
                maxScore = player.getScore();
            }
        }

        assert winner != null;
        log.info("The winner of the game is: " + winner);
    }

    public static void main(String[] args) {
        Game game = new Game();

        game.addPlayer(new Player("Player 1", game));
        game.addPlayer(new Player("Player 2", game));
        game.addPlayer(new Player("Player 3", game));

        game.play();
        game.getWinner();

        log.info(game.getBoard().toString());
    }

}
