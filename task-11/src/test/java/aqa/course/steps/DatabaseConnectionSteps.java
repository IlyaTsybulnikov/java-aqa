package aqa.course.steps;

import aqa.course.databaseConnection.JDBCConnection;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;

import java.sql.ResultSet;
import java.sql.SQLException;

public class DatabaseConnectionSteps {

    @When("^I send insert request$")
    public void sendInsertRequest() {
        String query = "INSERT INTO HotelMinistration (Id, Name, Price) VALUES ('123456', 'TestService', 15)";
        JDBCConnection.executeSQLUpdate(query);
    }

    @Then("^check if record was created$")
    public void checkRecordCreated() throws SQLException {
        String selectInsertedRecord = "SELECT * FROM HotelMinistration WHERE Id='123456'";
        ResultSet rs = JDBCConnection.executeSQLQuery(selectInsertedRecord);

        String id = rs.getString("Id");
        String name = rs.getString("Name");
        int price = rs.getInt("Price");

        Assert.assertEquals(id, "123456");
        Assert.assertEquals(name, "TestService");
        Assert.assertEquals(price, 15);
    }

    @When("^I send update request$")
    public void sendUpdateRequest() {
        String query = "UPDATE HotelMinistration SET Price = 20 WHERE Id='123456'";
        JDBCConnection.executeSQLUpdate(query);
    }

    @Then("^check if changes were saved to database$")
    public void checkRecordUpdated() {
        String selectUpdatedRecord = "SELECT * FROM HotelMinistration WHERE Id='123456'";
        ResultSet rs = JDBCConnection.executeSQLQuery(selectUpdatedRecord);

        try {
            Assert.assertEquals(rs.getString("Id"), "123456");
            Assert.assertEquals(rs.getString("Name"), "TestService");
            Assert.assertEquals(rs.getInt("Price"), 20);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @When("^I send delete request$")
    public void sendDeleteRequest() {
        String query = "DELETE FROM HotelMinistration WHERE Id='123456'";
        JDBCConnection.executeSQLUpdate(query);
    }

    @Then("^check if record was deleted$")
    public void checkRecordDeleted() {
        String selectUpdatedRecord = "SELECT * FROM HotelMinistration WHERE Id='123456'";
        ResultSet rs = JDBCConnection.executeSQLQuery(selectUpdatedRecord);
        try {
            Assert.assertFalse(rs.next());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
