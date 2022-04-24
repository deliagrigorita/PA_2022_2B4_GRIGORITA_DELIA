package b4.obj;

import b4.trie.Trie;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.java.Log;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Log
@Data
@NoArgsConstructor
public class Dictionary {
    private final Set<String> words = new HashSet<>();
    private final Trie wordsTrie = new Trie();

    public Dictionary(String inputFile) {
        try (BufferedReader reader = new BufferedReader(new FileReader(inputFile))) {
            String line = reader.readLine();
            while (line != null) {
                this.addWord(line);
                line = reader.readLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        generateTrie();
    }

    private void generateTrie() {
        for (String word : words) {
            wordsTrie.insert(word);
        }
    }

    public List<String> findPrefixParallelWork(String word) {
        long stamp1 = System.nanoTime();

        List<String> possibleWords = new ArrayList<>();
        words.parallelStream().forEach(dictionaryWord -> {
            if (dictionaryWord.indexOf(word) == 0) {
                possibleWords.add(dictionaryWord);
            }
        });

        long stamp2 = System.nanoTime();
        log.info("Time for parallel stream search: " + (stamp2 - stamp1));

        return possibleWords;
    }

    public void addWord(String word) {
        words.add(word);
    }

    public boolean isWord(String word) {
        return words.contains(word);
    }

    public int getScoreFromWord(List<Tile> tiles, String word) {
        int score = 0;
        for (int i = 0; i < word.length(); ++i) {
            for (Tile tile : tiles) {
                if (tile.getLetter() == word.charAt(i)) {
                    score += tile.getPoints();
                    break;
                }
            }
        }
        score *= word.length();
        return score;
    }

    public boolean canMatch(List<Tile> tiles, String word) {
        List<Character> letters = new ArrayList<>();
        for (Tile tile : tiles) {
            letters.add(tile.getLetter());
        }

        for (int i = 0; i < word.length(); ++i) {
            if (!letters.contains(word.charAt(i))) {
                return false;
            }
            letters.remove((Character) word.charAt(i));
        }
        return true;
    }

    public static void main(String[] args) {
        Dictionary dictionary = new Dictionary("src/main/resources/dictionary.in");

        log.info(dictionary.findPrefixParallelWork("wor").toString());

        log.info(dictionary.getWordsTrie().findPrefix("wor").toString());
    }
}
