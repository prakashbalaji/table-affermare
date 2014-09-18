package com.table.datasource;


import org.skife.jdbi.v2.Handle;

public class JdbiAdapter {

    public static Handle HANDLE;

    public static void initialize(Handle handle){
        JdbiAdapter.HANDLE = handle;
    }
}
