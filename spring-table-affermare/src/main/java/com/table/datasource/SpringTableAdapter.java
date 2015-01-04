package com.table.datasource;

import com.table.adapter.TableAdapter;
import org.apache.commons.lang.StringUtils;

import java.util.List;
import java.util.Map;

import static com.table.datasource.SpringAdapter.template;

public class SpringTableAdapter implements TableAdapter {

    @Override
    public Long getCount(String tableName) {
        return template.queryForObject("select count(*) from " + tableName, Long.class);
    }

    @Override
    public List<Map<String, Object>> getResults(String tableName, List<Map<String, String>> maps) {
        String columns = StringUtils.join(maps.get(0).keySet().toArray(), ",");
        return template.queryForList(String.format("select " + columns + " from " + tableName));
    }
}
