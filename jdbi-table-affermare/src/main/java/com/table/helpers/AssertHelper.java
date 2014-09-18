package com.table.helpers;

import cucumber.api.DataTable;
import org.apache.commons.lang.StringUtils;

import java.util.Collection;
import java.util.List;
import java.util.Map;

import static com.table.datasource.JdbiAdapter.HANDLE;
import static java.util.Objects.isNull;

public class AssertHelper {

    public static void verifyEntriesInTable(String tableName, DataTable table) {
        List<Map<String, String>> maps = table.asMaps();
        List<Map<String, Object>> results = getResults(tableName, maps);

        for (Map<String, String> map : maps) {
            if (!rowContainsInTable(map, results)) {
                throw new RuntimeException("Row doesn't match:" + StringUtils.join(map.values(), ',') + " in " + results);
            }
        }
    }

    public static void verifyEntriesInTableInOrder(String tableName, DataTable table) {
        List<Map<String, String>> maps = table.asMaps();
        List<Map<String, Object>> results = getResults(tableName, maps);

        if (maps.size() != results.size()) {
            throw new RuntimeException("Number of rows in table does not match with expected rows <" + maps.size() + ":" + results.size() + ">");
        }

        for (int i = 0; i < maps.size(); i++) {
            Map<String, String> map = maps.get(i);
            Map<String, Object> result = results.get(i);
            if (!rowMatchesWithTableEntry(map, result)) {
                throw new RuntimeException("Row doesn't match:" + StringUtils.join(map.values(), ',') + " eq " + StringUtils.join(result.values(), ','));
            }
        }
    }

    public static void verifyForEmptyTable(String tableName) {
        if (getCount(tableName) != 0) {
            throw new RuntimeException("Table is not empty " + tableName);
        }
    }

    private static Long getCount(String tableName) {
        Collection<Object> values = HANDLE.select("select count(*) from " + tableName).get(0).values();
        return (Long) values.iterator().next();
    }

    private static List<Map<String, Object>> getResults(String tableName, List<Map<String, String>> maps) {
        String columns = StringUtils.join(maps.get(0).keySet().toArray(), ",");
        return HANDLE.select(String.format("select " + columns + " from " + tableName));
    }

    private static boolean rowContainsInTable(Map<String, String> map, List<Map<String, Object>> results) {
        boolean rowMatches = false;
        for (Map<String, Object> result : results) {

            if (rowMatchesWithTableEntry(map, result)) {
                rowMatches = true;
                break;
            }
        }
        return rowMatches;
    }

    private static boolean rowMatchesWithTableEntry(Map<String, String> map, Map<String, Object> result) {
        int numberOfKeys = map.keySet().size();
        int matchedKeys = 0;
        for (String key : map.keySet()) {
            String expected = map.get(key);
            String actual = isNull(result.get(key)) ? "null" : result.get(key).toString();
            if (expected.equals(actual)) {
                matchedKeys++;
            }
        }
        return numberOfKeys == matchedKeys;
    }
}
