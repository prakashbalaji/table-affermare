package com.table.verification;

import com.table.datasource.SpringTableAdapter;
import com.table.helpers.AssertHelper;
import cucumber.api.DataTable;
import cucumber.api.java.en.Then;

public class TableVerification {

    @Then("^I verify that the table \"([^\"]*)\" has the following entries$")
    public void I_verify_that_the_table_has_entries(String tableName, DataTable table) throws Throwable {
        new AssertHelper(new SpringTableAdapter()).verifyEntriesInTable(tableName, table);
    }

    @Then("^I verify that the table \"([^\"]*)\" has the following entries in the same order$")
    public void I_verify_that_the_table_has_entries_in_order(String tableName, DataTable table) throws Throwable {
        new AssertHelper(new SpringTableAdapter()).verifyEntriesInTableInOrder(tableName, table);
    }

    @Then("^I verify that the table \"([^\"]*)\" is empty$")
    public void verifyEmptyTable(String tableName) throws Throwable {
        new AssertHelper(new SpringTableAdapter()).verifyForEmptyTable(tableName);
    }


}
