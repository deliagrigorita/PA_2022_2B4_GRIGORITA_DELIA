import java.util.HashMap;
import java.util.Map;

public class MockDictionary extends Dictionary {
    private final Map<String, Integer> dictionary = new HashMap<>();

    public MockDictionary() {
        this.dictionary.put("hello", 0);
        this.dictionary.put("world", 0);
        this.dictionary.put("abcde", 0);
        this.dictionary.put("word", 0);
        this.dictionary.put("fwegfj", 0);
    }


    @Override
    public boolean isWord(String str) {
        return true;
    }
}
