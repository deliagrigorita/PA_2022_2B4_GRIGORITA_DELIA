import java.util.List;

import static java.lang.Thread.sleep;

public class Player implements Runnable {
    private String name;
    private Game game;
    private boolean running;
    public Player(String name) { this.name = name; }

    private boolean submitWord() {
        List<Tile> extracted = game.getBag().extractTiles(7);
        if (extracted.isEmpty()) {
            return false;
        }
//        create a word with all the extracted tiles;
//        String word =
//        game.getBoard().addWord(this, word);
//        make the player sleep 50ms;
        System.out.println("Submitted");
        try{
            sleep(50);
        }catch (Exception e) {
            System.err.println(e.getMessage());
        }
        return true;
    }
//    implement the run method;


    @Override
    public void run() {
        System.out.println("Running on player" + this.getName());
        this.submitWord();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Game getGame() {
        return game;
    }

    public void setGame(Game game) {
        this.game = game;
    }

    public boolean isRunning() {
        return running;
    }

    public void setRunning(boolean running) {
        this.running = running;
    }
}