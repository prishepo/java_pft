package ru.stqa.pft.mantis.tests;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.testng.annotations.Test;
import ru.stqa.pft.mantis.appmanager.HttpSession;
import ru.stqa.pft.mantis.model.UserData;

import javax.mail.MessagingException;
import java.io.IOException;
import java.util.List;

import static org.testng.Assert.assertTrue;
import static ru.stqa.pft.mantis.tests.TestBase.app;



public class ChangePasswordTest extends TestBase {
    private SessionFactory sessionFactory;

    @Test
    public void testChangePassword() throws IOException, MessagingException {
        app.getDriver();
        app.adminActions().authorization();
        app.adminActions().goToManagePage();
        app.adminActions().goToContactsManagePage();
        System.out.println(app.db().users());
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        List<UserData> result = session.createQuery("from UserData").list();
        System.out.println(result);



    }
}
