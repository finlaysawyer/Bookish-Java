package org.softwire.training.bookish.services;

import org.softwire.training.bookish.models.database.Book;
import org.softwire.training.bookish.models.database.Technology;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookService extends DatabaseService {

    public List<Book> getAllBooks() {
        return jdbi.withHandle(handle ->
                handle.createQuery("SELECT * FROM books")
                        .mapToBean(Book.class)
                        .list()
        );
    }

    public List<Book> getBook(int id) {
        return jdbi.withHandle(handle ->
                handle.createQuery("SELECT * FROM books WHERE book_id = " + Integer.toString(id))
                        .mapToBean(Book.class)
                        .list()
        );
    }

    public List<Book> getBookSearch(String name) {
        return jdbi.withHandle(handle ->
                handle.createQuery("SELECT * FROM books WHERE title LIKE '%" + name + "%'")
                        .mapToBean(Book.class)
                        .list()
        );
    }

    public void addBook(Book book) {
        jdbi.useHandle(handle ->
                handle.createUpdate("INSERT INTO books VALUES (:book_id, :title, :author, :isbn, :copies)")
                        .bind("book_id", book.getBook_id())
                        .bind("title", book.getTitle())
                        .bind("author", book.getAuthor())
                        .bind("isbn", book.getIsbn())
                        .bind("copies", book.getCopies())
                        .execute()
        );
    }

    public void deleteBook(int book_id) {
        jdbi.useHandle(handle ->
                handle.createUpdate("DELETE FROM books WHERE book_id = :book_id")
                        .bind("book_id", book_id)
                        .execute()
        );
    }

    public void editBook(int book_id, String new_title, String new_author, String new_isbn, String new_copies) {
        jdbi.useHandle(handle ->
                handle.createUpdate("UPDATE books SET title = :title, author = :author, isbn = :isbn, copies = :copies WHERE book_id = :book_id")
                        .bind("title", new_title)
                        .bind("author", new_author)
                        .bind("isbn", new_isbn)
                        .bind("copies", new_copies)
                        .bind("book_id", book_id)
                        .execute()
        );
    }
}
