package org.softwire.training.bookish.services;

import org.softwire.training.bookish.models.database.Book;
import org.softwire.training.bookish.models.database.Loan;
import org.softwire.training.bookish.models.database.Member;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class LoansService extends DatabaseService {

    public List<Loan> getAllLoans() {
        return jdbi.withHandle(handle ->
                handle.createQuery("SELECT * FROM loans")
                        .mapToBean(Loan.class)
                        .list()
        );
    }

    public List<Loan> getLoan(int id) {
        return jdbi.withHandle(handle ->
                handle.createQuery("SELECT * FROM loans WHERE loan_id = " + Integer.toString(id))
                        .mapToBean(Loan.class)
                        .list()
        );
    }

    public List<Loan> getLoansSearch(String member_id) {
        return jdbi.withHandle(handle ->
                handle.createQuery("SELECT * FROM loans WHERE member_id LIKE '%" + member_id + "%'")
                        .mapToBean(Loan.class)
                        .list()
        );
    }

    public void addLoan(Loan loan) {
        Optional<Integer> copies = jdbi.withHandle(handle ->
                handle.createQuery("SELECT copies FROM books WHERE book_id = " + loan.getBook_id())
                        .mapTo(Integer.class)
                        .findFirst());

        if (copies.get() >= 1) {
            jdbi.useHandle(handle ->
                    handle.createUpdate("INSERT INTO loans VALUES (:loan_id, :return_date', :member_id, :book_id)")
                            .bind("loan_id", loan.getLoan_id())
                            .bind("return_date", loan.getReturn_date())
                            .bind("member_id", loan.getMember_id())
                            .bind("book_id", loan.getBook_id())
                            .execute()
            );
        }
    }

    public void deleteLoan(int loan_id) {
        jdbi.useHandle(handle ->
                handle.createUpdate("DELETE FROM loans WHERE loan_id = :loan_id")
                        .bind("loan_id", loan_id)
                        .execute()
        );
    }
}
