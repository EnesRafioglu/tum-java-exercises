package de.tum.in.ase.eist;

import java.util.List;

public class BinarySearch implements SearchStrategy {
    public int performSearch(List<Chapter> list, String d) {
        return binarySearch(list, d);
    }


    private int binarySearch(List<Chapter> list, String chapterName) {
        int l = 0;
        int r = list.size() - 1;
        while (l <= r) {
            int m = (l + r) / 2;
            Chapter chapter = list.get(m);
           String currentChapterName = chapter.getName();
           int compareResult = chapterName.compareTo(currentChapterName);
           if (compareResult == 0) {
               return chapter.getPageNumber();
           }
           else if (compareResult < 0) {
               r = m - 1;
           }
           else {
               l = m + 1;
           }

        }
        return -1;
    }
}
