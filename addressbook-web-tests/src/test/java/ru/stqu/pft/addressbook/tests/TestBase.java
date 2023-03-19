package ru.stqu.pft.addressbook.tests;

import org.openqa.selenium.remote.Browser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.*;
import ru.stqu.pft.addressbook.appmanager.ApplicationManager;

import java.lang.reflect.Method;
import java.util.Arrays;

public class TestBase {

    Logger logger = LoggerFactory.getLogger(TestBase.class);


    protected static final ApplicationManager app
            = new ApplicationManager(System.getProperty("browser", Browser.FIREFOX.browserName()));

    @BeforeSuite
    public void setUp() throws Exception {
        app.init();
    }


    @AfterSuite (alwaysRun = true)
    public void tearDown() throws Exception {
        app.stop();
    }

   /* @BeforeMethod (enabled = false)
        public void logTestStart(Method m, Object[] p) {
        logger.info("Start test" + m.getName() + " with parameters " + Arrays.asList(p));

    }

    @AfterMethod (enabled = false)
        public void logTestStop(Method m, Object[] p) {
        logger.info("Stop test" + m.getName()+ " with parameters " + Arrays.asList(p));

    }
*/
}
