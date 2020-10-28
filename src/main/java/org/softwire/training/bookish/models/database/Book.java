package org.softwire.training.bookish.models.database;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Book {
    private int book_id;
    private String title;
    private String author;
    private String isbn;
    private int copies;
}
