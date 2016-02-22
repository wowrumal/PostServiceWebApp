package by.bsuir.spp.controller.constant;

import by.bsuir.spp.dao.SearchStatementDao;

public final class RequestParameterName {
    private RequestParameterName(){}

    public static final String COMMAND_NAME = "command";

    public static final String DB_CONTENT = "db_content";

    //package data
    public static final String PACKAGES = "packages";
    public static final String PACKAGE_ID = "package_id";
    public static final String PACKAGE_TYPE = "package_type";
    public static final String PACKAGE_DATE = "package_date";
    public static final String PACKAGE_SENDER_NAME = "package_sender_name";
    public static final String PACKAGE_GETTER_NAME = "package_getter_name";
    public static final String PACKAGE_ADDRESS = "package_address";
    public static final String PACKAGE_POST_INDEX = "package_post_index";
    public static final String PACKAGE_BARCODE = "package_barcode";

    //user data
    public static final String USER = "user";
    public static final String USER_ID = "user_id";
    public static final String LOGIN_FIELD = "login_field";
    public static final String PASSWORD = "password";
    public static final String FIRST_NAME = "first_name";
    public static final String MIDDLE_NAME = "middle_name";
    public static final String SEC_NAME = "sec_name";
    public static final String USER_ROLE = "user_role";
    public static final String USERS = "users";

    public static final String PASSPORT_IDS = "passport_ids";
    public static final String USER_ROLES = "user_roles";

    //passport data
    public static final String PASSPORT = "passport";
    public static final String PASSPORT_ID = "passport_id";
    public static final String PASSPORT_NUMBER = "passport_number";
    public static final String PASSPORT_ADDRESS = "address";
    public static final String INSTITUTION = "institution";
    public static final String ISSUING_DATE = "issuing_date";
    public static final String PASSPORTS = "passports";
}
