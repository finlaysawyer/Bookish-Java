CREATE TABLE IF NOT EXISTS books (
    book_id int AUTO_INCREMENT,
    title varchar(50) NOT NULL,
    author varchar(30) NOT NULL,
    isbn varchar(20) NOT NULL,
    copies int NOT NULL,
    PRIMARY KEY (book_id)
);

CREATE TABLE IF NOT EXISTS members (
    member_id int AUTO_INCREMENT,
    name varchar(30) NOT NULL,
    PRIMARY KEY (member_id)
);

CREATE TABLE IF NOT EXISTS loans (
    loan_id int AUTO_INCREMENT,
    return_date datetime NOT NULL,
    member_id int,
    book_id int,
    PRIMARY KEY (loan_id),
    FOREIGN KEY (member_id) REFERENCES members(member_id),
    FOREIGN KEY (book_id) REFERENCES books(book_id)
);