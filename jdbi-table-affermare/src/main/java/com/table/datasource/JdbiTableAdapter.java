package com.table.datasource;

import com.table.adapter.TableAdapter;
import org.apache.commons.lang.StringUtils;

import java.util.Collection;
import java.util.List;
import java.util.Map;

import static com.table.datasource.JdbiAdapter.HANDLE;

public class JdbiTableAdapter implements TableAdapter {

    @Override
    public Long getCount(String tableName) {
        Collection<Object> values = HANDLE.select("select count(*) from " + tableName).get(0).values();
        return (Long) values.iterator().next();
    }

    @Override
    public List<Map<String, Object>> getResults(String tableName, List<Map<String, String>> maps) {
        String columns = StringUtils.join(maps.get(0).keySet().toArray(), ",");
        return HANDLE.select(String.format("select " + columns + " from " + tableName));
    }
}
