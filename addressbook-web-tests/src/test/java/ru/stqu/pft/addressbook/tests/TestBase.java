package ru.stqu.pft.addressbook.tests;

import org.hamcrest.CoreMatchers;
import org.hamcrest.MatcherAssert;
import org.openqa.selenium.remote.Browser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.*;
import ru.stqu.pft.addressbook.appmanager.ApplicationManager;
import ru.stqu.pft.addressbook.model.ContactData;
import ru.stqu.pft.addressbook.model.Contacts;
import ru.stqu.pft.addressbook.model.GroupData;
import ru.stqu.pft.addressbook.model.Groups;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.*;

public class TestBase {

    Logger logger = LoggerFactory.getLogger(TestBase.class);


    protected static final ApplicationManager app
            = new ApplicationManager(System.getProperty("browser", Browser.FIREFOX.browserName()));

    @BeforeSuite
    public void setUp() throws Exception {
        app.init();
    }


    @AfterSuite(alwaysRun = true)
    public void tearDown() throws Exception {
        app.stop();
    }

    public void verifyGroupListInUI() {
        if (Boolean.getBoolean("verifyUI")) {
            Groups dbGroups = app.db().groups();
            Groups uiGroups = app.group().all();
            assertThat(uiGroups, equalTo(dbGroups.stream()
                    .map((g) -> new GroupData().withId(g.getId()).withName(g.getName()))
                    .collect(Collectors.toSet())));

        }

    }

    public void verifyContactListInUI() {
        if (Boolean.getBoolean("verifyUI")) {
            Contacts dbContacts = app.db().contacts();
            Contacts uiContacts = app.contact().all();
            assertThat(uiContacts, equalTo(dbContacts.stream().map((c)-> new ContactData().withId(c.getId())
                    .withFirstName(c.getFirstName()).withMiddleName(c.getMiddleName()).withLastName(c.getLastName())
                    .withCompanyName(c.getCompanyName()).withAddress(c.getAddress()).withMobilePhone(c.getMobilePhone())
                    .withEmail(c.getEmail()).withGroup(c.getGroup()))
                    .collect(Collectors.toSet())));

        }

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
