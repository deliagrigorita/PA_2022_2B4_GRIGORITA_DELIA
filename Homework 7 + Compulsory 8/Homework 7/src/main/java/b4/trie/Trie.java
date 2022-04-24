package b4.trie;

import lombok.Data;
import lombok.extern.java.Log;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Stack;

@Log
@Data
public class Trie {
    private TrieNode root = new TrieNode();

    public void insert(String word) {
        TrieNode current = root;

        for (char l : word.toCharArray()) {
            current = current.getChildren().computeIfAbsent(l, c -> new TrieNode());
        }
        current.setWord(true);
        current.setContent(word);
    }

    public boolean find(String word) {
        TrieNode current = root;
        for (int i = 0; i < word.length(); i++) {
            char ch = word.charAt(i);
            TrieNode node = current.getChildren().get(ch);
            if (node == null) {
                return false;
            }
            current = node;
        }
        return current.isWord();
    }


    public void delete(String word) {
        delete(root, word, 0);
    }

    private boolean delete(TrieNode current, String word, int index) {
        if (index == word.length()) {
            if (!current.isWord()) {
                return false;
            }
            current.setWord(false);
            return current.getChildren().isEmpty();
        }
        char ch = word.charAt(index);
        TrieNode node = current.getChildren().get(ch);
        if (node == null) {
            return false;
        }
        boolean shouldDeleteCurrentNode = delete(node, word, index + 1) && !node.isWord();

        if (shouldDeleteCurrentNode) {
            current.getChildren().remove(ch);
            return current.getChildren().isEmpty();
        }
        return false;
    }


    public List<String> findPrefix(String word) {
        long stamp1 = System.nanoTime();

        List<String> possibleWords = new ArrayList<>();

        TrieNode current = root;
        for (int i = 0; i < word.length(); i++) {
            char ch = word.charAt(i);
            TrieNode node = current.getChildren().get(ch);
            if (node == null) {
                return possibleWords;
            }
            current = node;
        }

        Stack<TrieNode> stack = new Stack<>();
        stack.push(current);
        while (!stack.empty()) {
            current = stack.peek();
            stack.pop();
            if (current.isWord()) {
                possibleWords.add(current.getContent());
            }
            for (Map.Entry<Character, TrieNode> entry : current.getChildren().entrySet()) {
                stack.push(entry.getValue());
            }
        }

        long stamp2 = System.nanoTime();
        log.info("Time for Trie search: " + (stamp2 - stamp1));

        return possibleWords;
    }


    public static void main(String[] args) {
        Trie trie = new Trie();

        trie.insert("lifeful");
        trie.insert("is");
        trie.insert("lifeless");
        trie.insert("way");
        trie.insert("of");
        trie.insert("life");

        log.info(trie.toString());
        log.info("found? " + trie.find("vida"));
        log.info("found? " + trie.find("life"));

        trie.delete("life");
        log.info("found? " + trie.find("life"));

        log.info(trie.findPrefix("life").toString());
    }
}
