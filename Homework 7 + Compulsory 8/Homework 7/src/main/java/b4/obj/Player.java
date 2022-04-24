package b4.obj;

import lombok.Data;
import lombok.extern.java.Log;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

@Log
@Data
public class Player implements Runnable {
    private String name;
    private final Game game;
    private boolean running;

    private int score;
    private List<Tile> extracted = new ArrayList<>();
    public int cardsNoExtracted = 7;

    public Player(String name, Game game) {
        this.name = name;
        this.game = game;
        this.score = 0;
        this.running = true;
    }

    private void waitTurn(int timeout) {
        try {
            TimeUnit.MILLISECONDS.sleep(timeout);
        } catch (InterruptedException exception) {
            log.warning("Error at putting players to sleep for" + timeout + "ms.");
        }
    }

    private boolean submitWord() {
        List<Tile> extractedRound = game.getBag().extractTiles(cardsNoExtracted);
        if (extractedRound.isEmpty()) {
            return false;
        }
        //log.info("lista: " + game.getBag().getTileList().size());
        //log.info("extrase: " + extractedRound.toString());

        extracted.addAll(extractedRound);

        //check if the tiles can match a word from the dictionary;
        for (String word : game.getDictionary().getWords()) {
            if (game.getDictionary().canMatch(extracted, word)) {
                //get score from the word submitted to the board:
                score += game.getDictionary().getScoreFromWord(extracted, word);
                game.getBoard().addWord(this, word);

                //remove matched tiles from the extracted list:
                for (int i = 0; i < word.length(); ++i) {
                    char letter = word.charAt(i);
                    extracted.removeIf(tile -> tile.getLetter() == letter);
                }
                break;
            }
        }

        //if all the tiles can't match any word they are thrown away
        if (extracted.size() == 7) {
            extracted = new ArrayList<>();
        }

        //for next round extract only a number of tiles equal to the ones removed(that matched the word)
        cardsNoExtracted = 7 - extracted.size();

        waitTurn(300);
        return true;
    }

    @Override
    public void run() {
        synchronized (this.game) {
            while (running) {
                if (game.getPlayers().get(game.getPlayerTurnIndex()).equals(this)) {
                    if (!submitWord()) {
                        //end of game, show result for player:
                        log.info(String.format("Player's %s score is: %d", this.name, this.score));
                        running = false;

                        game.notifyAll();
                        game.setPlaying(false);
                    }

                    //sets up the turn for the next player:
                    if (game.getPlayerTurnIndex() == game.getPlayers().size() - 1) {
                        game.setPlayerTurnIndex(0);
                    } else {
                        game.setPlayerTurnIndex(game.getPlayerTurnIndex() + 1);
                    }

                    game.notifyAll();
                } else {
                    //wait to be notified to check if it is the current player's turn:
                    try {
                        game.wait();
                    } catch (InterruptedException e) {
                        log.warning("Player " + this.name + " cannot wait.");
                    }
                }
            }
        }
    }

    @Override
    public String toString() {
        return "Player{" +
                "name='" + name + '\'' +
                ", score=" + score +
                '}';
    }
}
