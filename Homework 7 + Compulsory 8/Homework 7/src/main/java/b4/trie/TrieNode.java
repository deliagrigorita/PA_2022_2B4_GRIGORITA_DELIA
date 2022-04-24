package b4.trie;

import lombok.Data;

import java.util.HashMap;

@Data
public class TrieNode {
    private HashMap<Character, TrieNode> children = new HashMap<>();
    private String content;
    private boolean isWord = false;
}
