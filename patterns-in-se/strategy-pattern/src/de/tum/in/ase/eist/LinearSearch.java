package de.tum.in.ase.eist;

import java.util.List;

public class LinearSearch implements SearchStrategy {
    public  int performSearch(List<Chapter> list, String d) {
        return linearSearch(list, d);
    }
    private int linearSearch(List<Chapter> list, String chapterName) {
        for (Chapter chapter : list) {
            if (chapter.getName().equals(chapterName)) {
                return chapter.getPageNumber();
            }
        }
        return -1;
    }
}
