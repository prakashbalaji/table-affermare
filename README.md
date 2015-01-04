Table-affermare
=========

Cucumber JVM add on library to verify Database Tables.

Architecture
=============

![Image of Architecture]
(arch.png)

Available datasource connectivity -

*        JDBI - register JDBI handle using JdbiAdapter.initialize(Handle)
*        Spring Adapter - register Spring jdbc template using SpringAdapter.initialize(JdbcTemplate)

To add description


Usage
=========

Table verification Examples
-----

The following examples shows various options with asserting the database tables

                Then I verify that the table "authors" has the following entries
                      | id      | name          |
                      | 1000    | Martin Fowler |
                      | 1001    | Kent Beck     |

                Then I verify that the table "authors" has the following entries in the same order
                      | id      | name          |
                      | 1000    | Martin Fowler |
                      | 1001    | Kent Beck     |

                Then I verify that the table "authors" is empty


Version
----

1.0

Tech
-----------

To be updated

Installation
--------------
Should be available in maven central soon.

License
----

MIT

Contribution
------------

Fork and send pull request

What's In the Name
------

All good english names are already taken by other json libraries - lets try italian.
