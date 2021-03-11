create table book
(
    id                 serial primary key,
    isbn               varchar(254)  NOT NULL,
    price              numeric(8, 2) NOT NULL,
    available_quantity integer DEFAULT 0,
    CONSTRAINT unq_book_isbn UNIQUE (isbn)
);

create table order_book
(
    id       serial primary key,
    book_id  integer      NOT NULL,
    username varchar(254) NOT NULL,
    quantity integer      NOT NULL,
    CONSTRAINT fk_book_id
        FOREIGN KEY (book_id) REFERENCES book (id)
);

INSERT INTO book(isbn, price, available_quantity)
VALUES ('9789668602344', 50, 2),
       ('5784668612341', 40, 1),
       ('7789668602344', 110, 5),
       ('4789668602344', 30, 10),
       ('3789668602344', 80, 1),
       ('6789668602344', 12, 3);