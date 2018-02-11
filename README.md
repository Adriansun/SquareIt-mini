# SquareIt

### Description

The core idea for this project is that it can be used as a
template for future projects that require rest-services, a database and
a frontend. All parts of the project are modular and easy to replace,
for example changing of database drivers from a mySQL database to any
other database takes less than a minute to change. All classes and
packages have a good layout which makes the project easy to overview.

The project has a frontend which communicates with a backend. The
backend rest-services manipulate the data, sends the data to a mySQL
database for saving and querying. E.g. an integer can be saved on the
database and then returned squared to the frontend for presentation.

### Flow

The flow of the data in the project:
Frontend -> Backend -> Database --> Backend --> Frontend.

### General information
Project features and general information.

#### Frontend

_The frontend is used for functionality only, thus its purpose is not
to look pretty._

A user can:
 * Save a number.
 * Get a number back squared.
 * Delete a number based on a number ID.
 * See the total number of items in the database.
 * See a list of all items in the database.

#### Backend
Contains all of the project parts. It includes:

 * A good overview of classes & packages. In other words it has a good
 structure.
 * Spring Boot.
 * Maven.
 * Modules:
    * API-module.
    * Application-module.
 * Error handling.
 * Personalized exceptions.
 * API domain models.
 * Bread crumb id enabled.
 * MDC request filters.
 * Swagger configuration.
 * Web-configuration for headers & routing.
 * API documentation with Swagger2.
 * Jackson-bind & annotations.
 * Logging.
 * CrudRepository.
 * DAO.
 * JPA.
 * DTOs.
 * Hibernate.
 * Rest-services.
 * Service interfaces & service implementations.
 * Liquibase for managing the database tables and its content.
 * mySQL connection driver.
 * Testing with jUnit4 integration tests through RestTemplates.
 * POM-files.
 * Builder - InnerBuilder.
 * Google checkstyle checker.
 * Profiles for developing and production.

#### Databases

An in-memory database (H2database) is used to simulate a database
during testing and an mySQL database when running Spring Boot.
Liquibase manages the creation and modifications of tables and its
content in the database automatically. In other words there is no need
to enter your own database for management at any point.

#### Testing

jUnit4 is used during testing with:

 * Assert.
 * Before & After annotations.
 * Error code tracking & information.
 * RestTemplates available with exchange:
    * Get.
    * GetList.
    * GetError.
    * Put.
    * PutError.
    * Post.
    * PostError.

## Installation

Localhost only installation guide on Windows 10. Install the programs
and files in this order. After installing these items the system should
be restarted even though it should not be required in theory.

#### 1. Java

 *
     1) Go to: ' http://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html '.
     2) Click accept and download e.g. 'jdk-8u162-windows-x64.exe'.
     3) Install Java JDK.
     4) In environmental variables under 'System variables' make sure
     you have a variable called 'JAVA_TOOL_OPTIONS' with the value
     '-Dfile.encoding=UTF8'. And a variable called 'JAVA_HOME' with
     e.g. value 'C:\Program Files\Java\jdk1.8.0_162'. In variable 'Path'
     add value e.g. 'C:\Program Files\Java\jdk1.8.0_162\bin' and also
     add value e.g. 'C:\Program Files\Java\jre1.8.0_162\bin'. Add
     variable 'JRE_HOME' with the value e.g.
     'C:\Program Files\Java\jre1.8.0_162'.

#### 2. Maven

 *
    1) Go to: ' https://maven.apache.org/download.cgi '.
    2) Download 'apache-maven-3.5.2-bin.zip'.
    3) Unzip and move the catalogue to e.g.
    'C:\Program Files\apache-maven-3.5.2'.
    4) In environmental variables under 'System variables' make sure you
    have a variable called 'MAVEN_HOME' with value
    'C:\Program Files\apache-maven-3.5.2' and a variable called
    'M2_HOME' with the value 'C:\Program Files\apache-maven-3.5.2'. In
    variable 'Path' add value '%M2_HOME%\bin'.

#### 3. ANT

 *
    1) Go to: ' https://ant.apache.org/bindownload.cgi '.
    2) Download 'apache-ant-1.10.2-bin.zip'.
    3) Unzip and move the catalogue to e.g.
    'C:\Program Files\apache-ant-1.10.1'.
    4) In environmental variables under 'System variables' make sure you
    have a variable called 'ANT_HOME' with the value e.g.
    'C:\Program Files\apache-ant-1.10.1'. In variable 'Path' add the
    value '%ANT_HOME%\bin'.

#### 4. mySQL

 *
    1) Go to: ' https://dev.mysql.com/downloads/windows/ '.
    2) Download: 'MySQL Installer'.
    3) Install mySQL with any root username and password.
    4) Start mySQL Workbench.
    5) Create a database named 'squareit'.
    6) Create a user named 'everyone' with password 'Adrian12'.
    7) Grant all privileges to user 'everyone' on database 'squareit'.
    8) Make sure that the server is running on port '3306'.

_Nothing else is required. Liquibase will handle the rest._

#### 5. Tomcat
_For now Tomcat does not work with Java 9. You must use Java 8 and only
have Java 8 installed on your system during the installing of Tomcat._

 *
    1) Go to: ' https://tomcat.apache.org/download-90.cgi '.
    2) Download: '64-bit Windows zip'.
    3) Install Tomcat.
    4) In environmental variables under 'System variables' make sure you
    have a variable called 'CATALINA_HOME' with the value e.g.
    'C:\Program Files\Apache Software Foundation\Tomcat 9.0'.
    5) In the 'Path' variable add the value '%CATALINA_HOME%\bin'.
    6) In Windows start-menu go to Apache Tomcat 9.0 Tomcat9 and start
    'Monitor Tomcat'.
    7) Right click on Tomcats tray icon and make sure that
    'Start service' is on.

 __For adding the project WAR file to Tomcat__

 *
    8) Go to URL: ' localhost:8080 '.
    9) Click on the 'Manager App' button and enter credentials.
    10) Go down to 'WAR file to deploy' and browse for the WAR file that
    the project produced for you and push the deploy button.
    11) The project is now accessable through the URL:
    'localhost:8080/squareit'.

#### 6. Download the project

 *
    1) In a terminal write:
    'git clone https://github.com/adriansun/SquareIt.git'.
    2) In Intellij pick 'Open' and select the downloaded project.
    3) In settings find Maven and point the path to your install path
    of maven, e.g. 'C:\Program Files\apache-maven-3.5.2'.
    4) Wait for Maven to automatically download all dependencies. This
    can take awhile.

## Running the project

To run the project it is assumed that mySQL and Tomcat are running, and
that the project has been imported into Intellij with all dependencies
downloaded by Maven. When it says "Write" then it refers to
Intellij's internal terminal where the input should be written.

#### Start the project

 * Alternative 1 - IDE running:
    1) Change the URLs in squareit-app/.../public/js/index.js to
        look at port 8082 with IP named 'localhost' on each service.
    2) Write 'mvn clean install'.
    3) Write 'cd squareit-app'.
    4) Write 'mvn spring-boot:run'.
    5) Go to a browser and write in the URL: "localhost:8082/squareit".

 * Alternative 2 - Tomcat running:
    1) Change the URLs in squareit-app/.../public/js/index.js to
    look at port 8080 with IP named 'localhost' or your specific IP
    on each service.
    2) Write 'mvn clean install'.
    3) Look in squareit-app/target and get the file named
    'squareit-app-0.0.1-SNAPSHOT.war'.
    4) Rename it to "squareit.war".
    5) Go to this manuals part called '5. Tomcat' under the installation
    section and follow the part 'For adding the project file to Tomcat'.

## API documentation - Swagger UI
The documentation of the API relies on Swagger2 documentation in favor
of standard Javadoc. This enables real-time usage of the system as well
as documentation of the API.

 * Localhost URL: http://localhost:8082/swagger-ui.html
 * Localhost URL in Tomcat: http://localhost:8080/squareit/swagger-ui.html
 * Remote URL: http://ip-number:8080/squareit/swagger-ui.html
