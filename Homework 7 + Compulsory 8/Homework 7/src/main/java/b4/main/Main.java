package b4.main;

import b4.obj.Tile;
import lombok.extern.java.Log;

@Log
public class Main {
    public static void main(String[] args) {
        Tile tile = new Tile('a', 10);
        log.info(tile.toString());
    }
}
