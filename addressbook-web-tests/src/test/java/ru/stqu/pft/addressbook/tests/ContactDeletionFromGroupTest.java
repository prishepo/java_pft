package ru.stqu.pft.addressbook.tests;

import org.hamcrest.CoreMatchers;
import org.hamcrest.MatcherAssert;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqu.pft.addressbook.model.ContactData;
import ru.stqu.pft.addressbook.model.Contacts;
import ru.stqu.pft.addressbook.model.GroupData;
import ru.stqu.pft.addressbook.model.Groups;

import java.util.Collections;
import java.util.List;

public class ContactDeletionFromGroupTest extends TestBase {

    private SessionFactory sessionFactory;


    @BeforeClass
    protected void setUpInTestOfDeletionFromGroupContact() throws Exception {
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



    @Test
    public void testDeletionContactFromGroup() {
        Contacts contacts = app.db().contacts();
        ContactData contact = contacts.iterator().next();
        Groups group = app.db().groups();
        GroupData groupForDeletionContact = group.iterator().next();
        Session session = sessionFactory.openSession();
        Groups contactGroups = contact.getGroups();
        Contacts contactsInGroup = groupForDeletionContact.getContacts();
        session.beginTransaction();

        List<GroupData> groupsList = session.createQuery("from GroupData").list();
        List<ContactData> contactsList = session.createQuery("from ContactData").list();

        app.goTo().goToHomePage();
        app.contact().selectGroupFromList(groupForDeletionContact.getId());

        if (!contactsInGroup.contains(contact)) {
            app.contact().selectAll();
            app.contact().addintToGroupContact(contact);
            app.contact().selectGroupFromListToAddContact(groupForDeletionContact.getId());
            app.contact().addContactToSelectedGroup();
            app.goTo().goToHomePage();
            app.contact().selectGroupFromList(groupForDeletionContact.getId());
            /*ContactData newContactForGroup = new ContactData()
                    .withFirstName("Alexander")
                    .withMiddleName("Petrovich")
                    .withLastName("Ivanov").inGroup(groupForDeletionContact);
            app.contact().create(newContactForGroup);
            Collections.sort(contactsList, (c1, c2) -> c1.getId() - c2.getId());
            contact = contactsList.get(contactsList.size()-1);*/
        }

        app.contact().selectContactByIdForAddingToGroup(contact.getId());
        app.contact().deleteFromGroup();
        app.goTo().goToHomePage();
        GroupData groupAfterDeleteContact = groupForDeletionContact;
        for (int i = 0; i < groupsList.size(); i++) {
            if (groupForDeletionContact.getId() == groupsList.get(i).getId()){
                groupAfterDeleteContact = groupsList.get(i);
                break;
            }
        }
        MatcherAssert.assertThat(groupForDeletionContact.getContacts(), CoreMatchers.equalTo(groupAfterDeleteContact.getContacts()));

    }

}
