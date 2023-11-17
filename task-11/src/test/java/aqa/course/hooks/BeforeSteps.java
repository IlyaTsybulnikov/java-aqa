package aqa.course.hooks;

import aqa.course.databaseConnection.JDBCConnection;
import io.cucumber.java.en.Given;

public class BeforeSteps {

    @Given("I am connected to mssql server")
    public void connectToServer() {
        JDBCConnection.connectToDB();
    }
}
