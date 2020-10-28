package org.softwire.training.bookish.models.page;

import org.softwire.training.bookish.models.database.Book;

import java.util.List;

public class ViewBookPageModel {
    private Book book;

    public Book getBook() {
        return book;
    }
    public void setBook(Book book) {
        this.book = book;
    }
}
