package de.tum.in.ase.eist;

import java.util.List;

public class Context {

    private SearchStrategy searchAlgorithm;
    private List<Chapter> book;





    public int search(String chapterName) {
        return searchAlgorithm.performSearch(book, chapterName);
    }
    public boolean isChaptersSortedByName() {
        for (int i = 0; i < book.size() - 1; i++) {
            if (book.get(i).getName().compareTo(book.get(i + 1).getName()) > 0) {
                return false;
            }
        }
        return true;
    }

    public List<Chapter> getBook() {
        return book;
    }

    public SearchStrategy getSearchAlgorithm() {
        return searchAlgorithm;
    }

    public void setBook(List<Chapter> book) {
        this.book = book;
    }

    public void setSearchAlgorithm(SearchStrategy searchAlgorithm) {
        this.searchAlgorithm = searchAlgorithm;
    }
}
