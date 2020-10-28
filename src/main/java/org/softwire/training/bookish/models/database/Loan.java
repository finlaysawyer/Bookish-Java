package org.softwire.training.bookish.models.database;

import lombok.Getter;
import lombok.Setter;

import java.text.SimpleDateFormat;

@Getter
@Setter
public class Loan {
    private int loan_id;
    private SimpleDateFormat return_date;
    private int member_id;
    private int book_id;
}
