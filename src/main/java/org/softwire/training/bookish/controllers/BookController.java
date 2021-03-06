package org.softwire.training.bookish.controllers;

import org.softwire.training.bookish.models.database.Book;
import org.softwire.training.bookish.models.page.BooksPageModel;
import org.softwire.training.bookish.models.page.ViewBookPageModel;
import org.softwire.training.bookish.services.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import java.util.List;

@Controller
@RequestMapping("/books")
public class BookController {

    private final BookService bookService;

    @Autowired
    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @RequestMapping("")
    ModelAndView books(@RequestParam(value = "search", required = false) String search) {
        BooksPageModel booksPageModel = new BooksPageModel();
        List<Book> allBooks;
        if (search == null) {
            allBooks = bookService.getAllBooks();
        } else {
            allBooks = bookService.getBookSearch(search);
        }
        booksPageModel.setBooks(allBooks);
        return new ModelAndView("books", "model", booksPageModel);
    }

    @RequestMapping("/delete-book")
    RedirectView deleteBook(@RequestParam int book_id) {

        bookService.deleteBook(book_id);

        return new RedirectView("/books");
    }

    @RequestMapping("/view-book")
    ModelAndView viewBook(@RequestParam int book_id) {

        List<Book> allBooks = bookService.getBook(book_id);
        ViewBookPageModel viewBookPageModel = new ViewBookPageModel();
        viewBookPageModel.setBook(allBooks.get(0));

        return new ModelAndView("view-book", "model", viewBookPageModel);
    }

    @RequestMapping("/add-book")
    ModelAndView addBook() {
        return new ModelAndView("add-book");
    }

    @RequestMapping("/add-book/action")
    RedirectView addBookAction(@ModelAttribute Book book) {
        bookService.addBook(book);
        return new RedirectView("/books");
    }

    @RequestMapping("/edit-book")
    RedirectView editMember(@RequestParam int book_id, @RequestParam String new_title, @RequestParam String new_author, @RequestParam String new_isbn, @RequestParam String new_copies) {

        bookService.editBook(book_id, new_title, new_author, new_isbn, new_copies);

        return new RedirectView("/books");
    }
}
