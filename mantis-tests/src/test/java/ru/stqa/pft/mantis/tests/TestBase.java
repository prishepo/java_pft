package ru.stqa.pft.mantis.tests;
import org.openqa.selenium.remote.Browser;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import ru.stqa.pft.mantis.appmanager.ApplicationManager;

import java.io.File;


public class TestBase {

    protected static final ApplicationManager app
            = new ApplicationManager(System.getProperty("browser", Browser.FIREFOX.browserName()));


    @BeforeSuite
    public void setUp() throws Exception {
        app.init();
        /*app.ftp().upload(new File("src/test/resources/config_inc.php"), "config_inc.php", "config_inc.php.bak");*/
    }

    @AfterSuite(alwaysRun = true)
    public void tearDown() throws Exception {
        /*app.ftp().restore("config_inc.php", "config_inc.php.bak");*/
        app.stop();
    }

}
