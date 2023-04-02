package ru.stqu.pft.addressbook.tests;

import org.checkerframework.checker.units.qual.C;
import org.hamcrest.CoreMatchers;
import org.hamcrest.MatcherAssert;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
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

import javax.swing.*;
import java.util.List;

public class AddContactToGroupTests extends TestBase {

    private SessionFactory sessionFactory;

    @BeforeMethod
    public void ensurePreconditions() {
        Groups groups = app.db().groups();

        if (!app.contact().isThereAGroupInContactCreationForm("test1")) {
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


    @Test
   public void testAddedContactToGroup() {

        app.contact().homePage();
        Contacts contacts = app.db().contacts();
        Groups group = app.db().groups();

        GroupData groupBeforeAddContact = group.iterator().next();
        ContactData addedToGroupContact = contacts.iterator().next();

        app.contact().addintToGroupContact(addedToGroupContact);
        app.contact().selectGroupFromList(groupBeforeAddContact.getId());
        app.contact().addContactToSelectedGroup();

        GroupData groupAfterAddContact = groupBeforeAddContact;
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        List<GroupData> Groups = session.createQuery("from GroupData").list();
        for (int i = 0; i < Groups.size(); i++) {
            if (groupBeforeAddContact.getId() == Groups.get(i).getId()){
                groupAfterAddContact = Groups.get(i);
                break;
            }
        }
     // MatcherAssert.assertThat(groupBeforeAddContact.getContacts(), CoreMatchers.equalTo(groupAfterAddContact.getContacts()));

    }

}

