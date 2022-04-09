import java.util.ArrayList;
import java.util.List;

public class Bag {
    private final List<Tile> tiles = new ArrayList<>();

    public Bag() {
        for(char ch = 'a'; ch < 'z'; ++ch) {
            for(int idx = 0; idx < 10; ++idx) {
                tiles.add(new Tile(ch, Game.getRandomNumber(1, 10)));
            }
        }
    }

    public synchronized List<Tile> extractTiles(int howMany) {
        List<Tile> extracted = new ArrayList<>();
        for (int i = 0; i < howMany; i++) {
            if (tiles.isEmpty()) {
                break;
            }

            int randomInt = Game.getRandomNumber(0, tiles.size());
            var tile = tiles.stream().skip(randomInt).findFirst().get();

            extracted.add(tile);
            tiles.remove(tile);
        }
        return extracted;
    }

}
