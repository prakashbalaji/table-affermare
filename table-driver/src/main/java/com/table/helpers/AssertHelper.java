package com.table.helpers;

import com.table.adapter.TableAdapter;
import cucumber.api.DataTable;
import org.apache.commons.lang.StringUtils;

import java.util.List;
import java.util.Map;


public class AssertHelper {

    private TableAdapter tableAdapter;

    public AssertHelper(TableAdapter tableAdapter) {
        this.tableAdapter = tableAdapter;
    }

    public void verifyEntriesInTable(String tableName, DataTable table) {
        List<Map<String, String>> maps = table.asMaps();
        List<Map<String, Object>> results = tableAdapter.getResults(tableName, maps);

        for (Map<String, String> map : maps) {
            if (!rowContainsInTable(map, results)) {
                throw new RuntimeException("Row doesn't match:" + StringUtils.join(map.values(), ',') + " in " + results);
            }
        }
    }

    public void verifyEntriesInTableInOrder(String tableName, DataTable table) {
        List<Map<String, String>> maps = table.asMaps();
        List<Map<String, Object>> results = tableAdapter.getResults(tableName, maps);

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

    public void verifyForEmptyTable(String tableName) {
        if (tableAdapter.getCount(tableName) != 0) {
            throw new RuntimeException("Table is not empty " + tableName);
        }
    }





    private boolean rowContainsInTable(Map<String, String> map, List<Map<String, Object>> results) {
        boolean rowMatches = false;
        for (Map<String, Object> result : results) {

            if (rowMatchesWithTableEntry(map, result)) {
                rowMatches = true;
                break;
            }
        }
        return rowMatches;
    }

    private boolean rowMatchesWithTableEntry(Map<String, String> map, Map<String, Object> result) {
        int numberOfKeys = map.keySet().size();
        int matchedKeys = 0;
        for (String key : map.keySet()) {
            String expected = map.get(key);
            String actual = result.get(key) == null ? "null" : result.get(key).toString();
            if (expected.equals(actual)) {
                matchedKeys++;
            }
        }
        return numberOfKeys == matchedKeys;
    }
}
