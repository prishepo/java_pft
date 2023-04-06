package ru.stqu.pft.addressbook.tests;

import org.hamcrest.CoreMatchers;
import org.hamcrest.MatcherAssert;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqu.pft.addressbook.model.ContactData;
import ru.stqu.pft.addressbook.model.Contacts;
import ru.stqu.pft.addressbook.model.GroupData;
import ru.stqu.pft.addressbook.model.Groups;

import java.util.Collections;
import java.util.List;

public class AddContactToGroupTests extends TestBase {

    private SessionFactory sessionFactory;


    @BeforeClass
    protected void setUpInTestOfAddingAContactToGroup() throws Exception {
        // A SessionFactory is set up once for an application!
        final StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
                .configure() // configures settings from hibernate.cfg.xml
                .build();
        try {
            sessionFactory = new MetadataSources(registry).buildMetadata().buildSessionFactory();
        } catch (Exception e) {
            e.printStackTrace();
            // The registry would be destroyed by the SessionFactory, but we had trouble building the SessionFactory
            // so destroy it manually.
            StandardServiceRegistryBuilder.destroy(registry);
        }
    }

    @BeforeMethod
    public void ensurePreconditions() {
        Groups groups = app.db().groups();

        if (app.db().groups().size() == 0) {
            app.goTo().groupPage();
            app.group().create(new GroupData().withName("test1"));
            groups = app.db().groups();
        }else
            app.contact().homePage();

        if (app.db().contacts().size() == 0) {
            app.contact().create(new ContactData().withFirstName("Sergey").
                    withMiddleName("Petrovich").withLastName("Ivanov").withCompanyName("Burger King").
                    withAddress("Moscow, Tushinskaya st, 17").withMobilePhone("+71234567890").
                    withEmail("ivanov1981@yandex123.ru"));
        }
    }



    @Test
   public void testAddedContactToGroup() {
        Contacts contacts = app.db().contacts();
        ContactData contact = contacts.iterator().next();
        Groups group = app.db().groups();
        GroupData groupBeforeAddContact = group.iterator().next();
        Session session = sessionFactory.openSession();
        Groups contactGroups = contact.getGroups();
        session.beginTransaction();
        List<GroupData> groups = session.createQuery("from GroupData").list();
        session.getTransaction().commit();

      if (contactGroups.contains(groupBeforeAddContact)) {
          app.goTo().groupPage();
          GroupData groupForContact = new GroupData().withName("GroupForContact");
          app.group().create(groupForContact);
          app.goTo().goToHomePage();
          Transaction transaction = session.beginTransaction();
          List<GroupData> groupsAfterCreateGr = session.createQuery("from GroupData").list();
          transaction.commit();
          groupsAfterCreateGr.sort((g1, g2) -> Integer.compare(g1.getId(), g2.getId()));
          groupBeforeAddContact = groupsAfterCreateGr.get(groupsAfterCreateGr.size() - 1);
      }

        app.contact().homePage();
        app.contact().addintToGroupContact(contact);
        app.contact().selectGroupFromListToAddContact(groupBeforeAddContact.getId());
        app.contact().addContactToSelectedGroup();
        app.goTo().goToHomePage();
        session.clear();
        Transaction transaction = session.beginTransaction();
        List<GroupData> groupsAfterAddContact = session.createQuery("from GroupData").list();
        transaction.commit();
        GroupData groupAfterAddContact = groupBeforeAddContact;
        for (int i = 0; i < groupsAfterAddContact.size(); i++) {
            if (groupBeforeAddContact.getId() == groupsAfterAddContact.get(i).getId()){
                groupAfterAddContact = groupsAfterAddContact.get(i);
                break;
            }
        }

        Assert.assertTrue(groupAfterAddContact.getContacts().contains(contact));


    }
}

