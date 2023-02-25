package ru.stqu.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqu.pft.addressbook.model.ContactData;

public class ContactCreationTests extends TestBase{


    @Test
    public void contactCreationTests() throws Exception {
        app.getContactHelper().createContact(new ContactData("Ivan", "Petrovich", "Ivanov", "Burger King",
                "Moscow, Tushinskaya st, 17", "+71234567890",
                "ivanov1981@yandex123.ru", "test1"));
        app.getSessionHelper().logout();
    }

}
