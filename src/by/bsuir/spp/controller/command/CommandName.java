package by.bsuir.spp.controller.command;

public enum CommandName {
    DB_TEST,

    LOAD_PACKAGES,
    DELETE_PACKAGE,
    ADD_PACKAGE,
    SELECT_PACKAGE,
    UPDATE_PACKAGE,
    GET_NEW_PACKAGES,
    PREPARE_DATA_FOR_CREATION_PACKAGE,

    LOAD_PASSPORTS,
    UPDATE_PASSPORT,
    ADD_PASSPORT,
    SELECT_PASSPORT,
    DELETE_PASSPORT,

    PREPARE_DATA_FOR_CREATION_USER,
    SELECT_USER,
    DELETE_USER,
    UPDATE_USER,
    LOAD_USERS,

    ADD_USER,
    LOAD_RECEIPTS,
    ADD_RECEIPT,
    PREPARE_DATA_FOR_CREATION_RECEIPT,
    SELECT_RECEIPT,
    DELETE_RECEIPT,

    LOAD_ADVERTISEMENTS,
    ADD_ADVERTISEMENT,
    PREPARE_DATA_FOR_CREATION_ADVERTISEMENT,
    DELETE_ADVERTISEMENT,
    UPDATE_ADVERTISEMENT,
    SELECT_ADVERTISEMENT,

    PREPARE_DATA_FOR_CREATION_PREPAYMENT_BOOK,
    LOAD_PREPAYMENT_BOOKS,
    ADD_PREPAYMENT_BOOK,
    SELECT_PREPAYMENT_BOOK,
    UPDATE_PREPAYMENT_BOOK,
    DELETE_PREPAYMENT_BOOK,

    PREPARE_DATA_FOR_CREATION_SEARCH_STATEMENT,
    LOAD_SEARCH_STATEMENTS,
    ADD_SEARCH_STATEMENT,
    SELECT_SEARCH_STATEMENT,
    UPDATE_SEARCH_STATEMENT,
    DELETE_SEARCH_STATEMENT,

    LOGIN_COMMAND,
    REGISTRATION_COMMAND,
    LOGOUT_COMMAND,

    GET_PACKAGES_FOR_USER,
    GET_USER_RECEIPTS,
    GET_USER_ADVERTISEMENTS,
    GET_USER_SEARCH_STATEMENTS,
    GET_USER_PREPAYMENT_BOOKS,


}
