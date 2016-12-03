# Codecool Online Shop

From Python To Java, 2nd TW week, Project skeleton

# install

Import this project to IntelliJ as a Maven project.
IntelliJ can auto-install the dependencies from the pom.xml

# before running the application
* (create a database manually, named codecoolshop)
* run create_tables.sql (src/main/resources/sql/) to create the tables
* set your database username and password in DBConnection (src/main/java/com/codecool/shop/controller/)
* run PopulateData (src/main/java/) to fill up the tables

# before running tests
* (create a database manually, named codecoolshop)
* run create_tables.sql (src/main/resources/sql/) to empty the tables
* if you haven't yet, set your username and pw in DBConnection
