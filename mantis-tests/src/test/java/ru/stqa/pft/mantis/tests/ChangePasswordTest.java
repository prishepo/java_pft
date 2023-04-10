package ru.stqa.pft.mantis.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.mantis.appmanager.HttpSession;

import javax.mail.MessagingException;
import javax.mail.Session;
import java.io.IOException;

import static org.testng.Assert.assertTrue;
import static ru.stqa.pft.mantis.tests.TestBase.app;



public class ChangePasswordTest extends TestBase {

    @Test
    public void testChangePassword() throws IOException, MessagingException {
        app.getDriver();
        app.adminActions().authorization();
        app.adminActions().goToManagePage();
        app.adminActions().goToContactsManagePage();
        System.out.println(app.db().users());



    }
}
