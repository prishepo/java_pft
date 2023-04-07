package ru.stqa.pft.mantis.appmanager;

import org.openqa.selenium.WebDriver;
import ru.stqu.pft.mantis.tests.TestBase;

public class RegistrationHelper extends TestBase {
    private final ApplicationManager app;
    private WebDriver wd;



    public RegistrationHelper(ApplicationManager app) {
        this.app = app;
        wd = app.getDriver();
    }


}
