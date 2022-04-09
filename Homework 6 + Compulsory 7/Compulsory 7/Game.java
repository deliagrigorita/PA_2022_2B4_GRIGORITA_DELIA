import java.util.ArrayList;
import java.util.List;

public class Game {
    private final Bag bag = new Bag();
    private final Board board = new Board();
    private final Dictionary dictionary = new MockDictionary();
    private final List<Player> players = new ArrayList<>();

    public void addPlayer(Player player) {
        players.add(player);
        player.setGame(this);
    }

    public void play() {
        for (Player player : players) {
//          start a new Thread representing the player;
            new Thread(player).start();
        }
//        while(true) {
//            for(PLayer player: players) {
//
//            }
//        }
    }

    public static void main(String args[]) {
        Game game = new Game();
        game.addPlayer(new Player("Player1"));
        game.addPlayer(new Player("Player2"));
        game.addPlayer(new Player("Player3"));
        game.play();
    }

    public Bag getBag() {
        return bag;
    }

    public Board getBoard() {
        return board;
    }

    public Dictionary getDictionary() {
        return dictionary;
    }

    public List<Player> getPlayers() {
        return players;
    }

    /**
     * A random number generator
     * @param min
     * @param max
     * @return a number in the given interval
     */
    public static int getRandomNumber(int min, int max) {
        return (int) ((Math.random() * (max - min)) + min);
    }
}