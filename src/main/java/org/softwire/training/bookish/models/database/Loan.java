package org.softwire.training.bookish.models.database;

import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class Loan {
    private int loan_id;
    private String return_date;
    private int member_id;
    private int book_id;
}
