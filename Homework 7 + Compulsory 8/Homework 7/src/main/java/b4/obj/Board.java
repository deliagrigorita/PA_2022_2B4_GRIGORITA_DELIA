package b4.obj;

import lombok.Data;
import org.javatuples.Pair;

import java.util.ArrayList;
import java.util.List;

@Data
public class Board {
    private final List<Pair<Player, String>> words = new ArrayList<>();

    public synchronized void addWord(Player player, String word) {
        words.add(new Pair<>(player, word));
        //System.out.println(player.getName() + ": " + word);
    }

    @Override
    public String toString() {
        StringBuilder wordsSubmited = new StringBuilder();
        for (Pair<Player, String> submit : words) {
            wordsSubmited.append(submit.toString());
            wordsSubmited.append("\n");
        }
        return new String(wordsSubmited);
    }
}
