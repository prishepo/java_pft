package ru.stqu.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import ru.stqu.pft.addressbook.model.ContactData;
import ru.stqu.pft.addressbook.model.Contacts;
import ru.stqu.pft.addressbook.model.GroupData;

import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.*;


public class ContactCreationTests extends TestBase {

    @BeforeMethod
    public void ensurePreconditions() {
        if (!app.contact().isThereAGroupInContactCreationForm("test1")) {
            app.goTo().groupPage();
            app.group().create(new GroupData().withName("test1"));
        }
    }

    @DataProvider
    public Iterator<Object[]> validContacts() {
        List<Object[]> list = new ArrayList<Object[]>();
        list.add(new Object[]{new ContactData().withFirstName("Sergey").withMiddleName("Petrovich").withLastName("Ivanov").withCompanyName("Burger King").
                withAddress("Moscow, Tushinskaya st, 17").withMobilePhone("+71234567890").withEmail("ivanov1981@yandex123.ru").withGroup("test1")});
        list.add(new Object[]{new ContactData().withFirstName("Fedor").withMiddleName("Alexandrovich").withLastName("Sidorov").withCompanyName("KFC").
                withAddress("Moscow, Tverskaya st, 22").withMobilePhone("+7262344").withEmail("Sidorov@mail.ru").withGroup("test2")});
        list.add(new Object[]{new ContactData().withFirstName("Nikolay").withMiddleName("Mihaylovich").withLastName("Semenov").withCompanyName("Mcdonalds").
                withAddress("Tula, Isacovskovo st, 16a").withMobilePhone("+788888888").withEmail("ivanov1981@yandex123.ru").withGroup("test3")});
        return list.iterator();
    }

    @Test (dataProvider = "validContacts")
    public void contactCreationTests(ContactData contact) throws Exception {
        app.goTo().goToHomePage();
        Contacts before = app.contact().all();
        File photo = new File("src/test/resourses/stru.png");
        /*withPhoto(photo);*/
        app.contact().create(contact);
        app.contact().homePage();
        assertThat(app.contact().count(), equalTo(before.size()+1));
        Contacts after = app.contact().all();
        assertThat(after, equalTo(
                before.withAdded(contact.withId(after.stream().mapToInt((c) -> c.getId()).max().getAsInt()))));
    }




}
