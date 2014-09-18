package com.table.verification;

import cucumber.api.DataTable;
import cucumber.api.java.en.Then;

import static com.table.helpers.AssertHelper.verifyEntriesInTable;
import static com.table.helpers.AssertHelper.verifyEntriesInTableInOrder;
import static com.table.helpers.AssertHelper.verifyForEmptyTable;

public class TableVerification {

    @Then("^I verify that the table \"([^\"]*)\" has the following entries$")
    public void I_verify_that_the_table_has_entries(String tableName, DataTable table) throws Throwable {
        verifyEntriesInTable(tableName, table);
    }

    @Then("^I verify that the table \"([^\"]*)\" has the following entries in the same order$")
    public void I_verify_that_the_table_has_entries_in_order(String tableName, DataTable table) throws Throwable {
        verifyEntriesInTableInOrder(tableName, table);
    }

    @Then("^I verify table \"([^\"]*)\" is empty$")
    public void verifyEmptyTable(String tableName) throws Throwable {
        verifyForEmptyTable(tableName);
    }




}
