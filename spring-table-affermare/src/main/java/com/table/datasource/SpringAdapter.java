package com.table.datasource;


import org.skife.jdbi.v2.Handle;
import org.springframework.jdbc.core.JdbcTemplate;

public class SpringAdapter {

    public static JdbcTemplate template;

    public static void initialize(JdbcTemplate template) {
        SpringAdapter.template = template;
    }


}
