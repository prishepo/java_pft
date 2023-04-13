package ru.stqa.pft.mantis.tests;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import ru.lanwen.verbalregex.VerbalExpression;
import ru.stqa.pft.mantis.model.MailMessage;
import ru.stqa.pft.mantis.model.UserData;
import ru.stqu.pft.addressbook.model.Users;

import javax.mail.MessagingException;
import java.io.IOException;
import java.util.List;

import static org.testng.Assert.assertTrue;


public class ChangePasswordTest extends TestBase {
    private SessionFactory sessionFactory;

    @BeforeClass
    protected void setUP() throws Exception {
        final StandardServiceRegistry registry = new StandardServiceRegistryBuilder().configure().build();
        try {
            sessionFactory = new MetadataSources(registry).buildMetadata().buildSessionFactory();
        } catch (Exception e) {
            StandardServiceRegistryBuilder.destroy(registry);
        }

    }


    @Test
    public void testChangePassword() throws IOException, MessagingException {
        Long now = System.currentTimeMillis();
        Users users = app.db().users();
        UserData userForChangePassword = users.iterator().next();
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        List<UserData> usersList = session.createQuery("from UserData").list();
        if (userForChangePassword.getUsername().equals("administrator")){
            for (int i = 0; i < usersList.size(); i++) {
                if (userForChangePassword.getUsername().equals(usersList.get(i).getUsername())){
                    userForChangePassword = usersList.get(i+1);
                    break;
                }
            }
        }


        app.getDriver();
        String email = String.format(userForChangePassword.getEmail());
        String username = String.format(userForChangePassword.getUsername());
        String password = String.format(userForChangePassword.getPassword());
        String passwordForMail = "password";
        String newPassword = String.format("password%s", now);
        app.adminActions().authorization();
        app.adminActions().goToManagePage();
        app.adminActions().goToContactsManagePage();
        app.adminActions().selectUserById(userForChangePassword.getId());
        app.adminActions().pushChangePasswordButton();
        if (!app.james().doesUserExist(username)){
            app.james().createUser(username,passwordForMail);
        }
        app.james().drainEmail(username,passwordForMail);
        List<MailMessage> mailMessages = app.james().waitForMail(username, passwordForMail, 60000);
        String confirmationLink = findConfirmationLink(mailMessages, email);
        app.adminActions().changePassword(confirmationLink, newPassword, newPassword);
        assertTrue(app.newSession().login(username, newPassword));

    }

    private String findConfirmationLink(List<MailMessage> mailMessages, String email) {
        MailMessage mailMessage = mailMessages.stream().filter((m) -> m.to.equals(email)).findFirst().get();
        VerbalExpression regex = VerbalExpression.regex().find("http://").nonSpace().oneOrMore().build();
        return regex.getText(mailMessage.text);
    }
}
