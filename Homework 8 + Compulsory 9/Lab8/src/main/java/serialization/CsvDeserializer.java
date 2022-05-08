package serialization;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class CsvDeserializer {
    public static List<String[]> deserializerCoordinates(File csvFile) throws IOException {
        var contentList = new ArrayList<String[]>();
        Scanner scanner = new Scanner(csvFile);
        scanner.useDelimiter(",|\n");

        for (var it = 0; it < 6; it++) {
            if (scanner.hasNext() == false) {
                break;
            }
            scanner.next();
        }

        while (scanner.hasNext()) {
            var contentRow = new String[6];
            for (var it = 0; it < 6; it++) {
                if (scanner.hasNext() == false){
                    break;
                }
                contentRow[it] = scanner.next();
            }
            contentList.add(contentRow);
        }

        scanner.close();
        return contentList;
    }
}
