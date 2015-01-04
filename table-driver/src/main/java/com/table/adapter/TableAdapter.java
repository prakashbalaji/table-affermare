package com.table.adapter;

import java.util.List;
import java.util.Map;

public interface TableAdapter {
    Long getCount(String tableName);

    List<Map<String, Object>> getResults(String tableName, List<Map<String, String>> maps);
}
