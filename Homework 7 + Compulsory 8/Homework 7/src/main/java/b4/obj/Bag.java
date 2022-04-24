package b4.obj;

import lombok.Data;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Data
public class Bag {
    private final List<Tile> tileList = Collections.synchronizedList(new ArrayList<>());

    public Bag() {
        //random 10 tiles per letter for compulsory:
        /*for (char c = 'a'; c <= 'z'; c++) {
            int letterValue =  (int)(Math.random() * 9 + 1);
            for(int tileNo = 0; tileNo<10;++tileNo){
                //add a new tile to the collection;
                Tile tile = new Tile(c, letterValue);
                tileList.add(tile);
            }
        }*/

        //specific tiles generated as described in homework:
        addTiles('a', 1, 9);
        addTiles('b', 3, 2);
        addTiles('c', 3, 2);
        addTiles('d', 2, 4);
        addTiles('e', 1, 12);
        addTiles('f', 4, 2);
        addTiles('g', 2, 3);
        addTiles('h', 4, 2);
        addTiles('i', 1, 9);
        addTiles('j', 8, 1);
        addTiles('k', 5, 1);
        addTiles('l', 1, 4);
        addTiles('m', 3, 2);
        addTiles('n', 1, 6);
        addTiles('o', 1, 8);
        addTiles('p', 3, 2);
        addTiles('q', 10, 1);
        addTiles('r', 1, 6);
        addTiles('s', 1, 4);
        addTiles('t', 1, 6);
        addTiles('u', 1, 4);
        addTiles('v', 4, 2);
        addTiles('w', 4, 2);
        addTiles('x', 8, 1);
        addTiles('y', 4, 2);
        addTiles('z', 10, 1);
    }

    public void addTiles(char letter, int points, int numberOfTiles) {
        for (int i = 0; i < numberOfTiles; ++i) {
            Tile tile = new Tile(letter, points);
            tileList.add(tile);
        }
    }


    public synchronized List<Tile> extractTiles(int howMany) {
        List<Tile> extracted = new ArrayList<>();
        for (int i = 0; i < howMany; i++) {
            if (tileList.isEmpty()) {
                break;
            }

            //poll one random tile from the collection:
            int randomIndex = (int) (Math.random() * tileList.size());
            extracted.add(tileList.remove(randomIndex));
        }
        return extracted;
    }

}
