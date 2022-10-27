-- Table user_account:
create table if not exists user_account
(
    id                bigserial    NOT NULL,
    name              varchar(64)  NOT NULL,
    surname           varchar(64)  NOT NULL,
    email             varchar(100) NOT NULL,
    password          varchar(250) NOT NULL,
    role              varchar(20)  NOT NULL,
    birthday          date         NOT NULL,
    registration_date date         NOT NULL,

    CONSTRAINT PK_user_account_id PRIMARY KEY (id),
    CONSTRAINT UQ_user_account_email UNIQUE (email)
);

-- Table author:
create table if not exists author
(
    id      bigserial    NOT NULL,
    name    varchar(100) NOT NULL,
    surname varchar(100) NOT NULL,

    CONSTRAINT PK_author_id PRIMARY KEY (id)
);

-- Table book:
create table if not exists book
(
    id           bigserial    NOT NULL,
    title        varchar(100) NOT NULL,
    author_id    bigint       NOT NULL,
    co_author_id bigint,
    genre        varchar(100) NOT NULL,
    description  varchar(1000),
    ISBN         varchar(13)  NOT NULL,

    CONSTRAINT PK_book_id PRIMARY KEY (id),
    CONSTRAINT FK_book_author_id FOREIGN KEY (author_id) REFERENCES author (id),
    CONSTRAINT FK_book_co_author_id FOREIGN KEY (author_id) REFERENCES author (id),
    CONSTRAINT UQ_book_ISBN UNIQUE (ISBN)
);

-- Table history_of_request:
create table if not exists history_of_request
(
    id               bigserial   NOT NULL,
    user_id          bigint      NOT NULL,
    book_id          bigint      NOT NULL,
    date_of_issue    date        NOT NULL,
    should_be_return date        NOT NULL,
    return_date      date,
    request_processing date,
    status           varchar(64) NOT NULL,

    CONSTRAINT PK_history_of_request_id PRIMARY KEY (id),
    CONSTRAINT FK_history_of_request_user_id FOREIGN KEY (user_id) REFERENCES user_account (id),
    CONSTRAINT FK_history_of_request_book_id FOREIGN KEY (book_id) REFERENCES book (id)
);

-- Table history_of_request:
create table if not exists quantity
(
    id      bigserial NOT NULL,
    book_id bigint    NOT NULL,
    type    varchar(64),

    CONSTRAINT PK_quantity_id PRIMARY KEY (id),
    CONSTRAINT FK_quantity_book_id FOREIGN KEY (book_id) REFERENCES book (id)
);
-- Table book_author:
/*create table if not exists book_author(
                                          book_id bigint NOT NULL,
                                          author_id bigint NOT NULL,
                                          CONSTRAINT PK_book_author_book_id_author_id PRIMARY KEY(book_id, author_id)
);*/