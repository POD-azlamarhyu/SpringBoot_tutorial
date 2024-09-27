package com.example.spring_boot_tutorial.domain;

public class DataBaseQuery {

    /*
     * User info table query
     * 
     */
    public static final String queryGetUUIDByLoginId = "select ui.id from user_info as ui where ui.login_id = ?1";

    public static final String queryGetEmailById = "select ui. from user_info as ui where ui.id = ?1";

    public static final String queryGetEmailByLoginId = "select * from user_info as ui where ui.login_id = ?1";

    public static final String queryGetUsernameByLoginId = "select * from user_info as ui where ui.login_id = 1?";
}
