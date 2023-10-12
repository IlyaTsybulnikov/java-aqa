package aqa.course;

import org.junit.platform.suite.api.SelectClasses;
import org.junit.platform.suite.api.Suite;
import org.junit.platform.suite.api.SuiteDisplayName;

@Suite
@SelectClasses({ NumberOfProductsTest.class, SortProductsTest.class, WorkWithCartTest.class })
@SuiteDisplayName("SauceDemo Test Suite")
public class AppTest {
}