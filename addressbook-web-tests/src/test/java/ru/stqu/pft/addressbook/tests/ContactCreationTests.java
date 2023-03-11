package ru.stqu.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqu.pft.addressbook.model.ContactData;
import ru.stqu.pft.addressbook.model.Contacts;
import ru.stqu.pft.addressbook.model.GroupData;

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

    @Test
    public void contactCreationTests() throws Exception {

        app.goTo().goToHomePage();
        Contacts before = app.contact().all();
        ContactData contact = new ContactData().withFirstName("Sergey").withMiddleName("Petrovich").withLastName("Ivanov").withCompanyName("Burger King").
                withAddress("Moscow, Tushinskaya st, 17").withMobilePhone("+71234567890").withEmail("ivanov1981@yandex123.ru").withGroup("test1");
        app.contact().create(contact);
        assertThat(app.contact().count(), equalTo(before.size() + 1));
        Contacts after = app.contact().all();
        assertThat(after, equalTo(
                before.withAdded(contact.withId(after.stream().mapToInt((c) -> c.getId()).max().getAsInt()))));

    }

}
