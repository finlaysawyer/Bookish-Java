package org.softwire.training.bookish.services;

import org.softwire.training.bookish.models.database.Book;
import org.softwire.training.bookish.models.database.Loan;
import org.softwire.training.bookish.models.database.Member;
import org.springframework.stereotype.Service;

import java.util.List;

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
        jdbi.useHandle(handle ->
                handle.createUpdate("INSERT INTO loans VALUES (:loan_id, :return_date, :author, :isbn, :copies)")
                        .bind("loan_id", loan.getLoan_id())
                        .bind("return_date", loan.getReturn_date())
                        .bind("member_id", loan.getMember_id())
                        .bind("book_id", loan.getBook_id())
                        .execute()
        );
    }

    public void deleteLoan(int loan_id) {
        jdbi.useHandle(handle ->
                handle.createUpdate("DELETE FROM loans WHERE loan_id = :loan_id")
                        .bind("loan_id", loan_id)
                        .execute()
        );
    }
}
