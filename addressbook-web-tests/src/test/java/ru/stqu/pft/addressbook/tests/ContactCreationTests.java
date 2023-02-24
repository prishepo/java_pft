package ru.stqu.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqu.pft.addressbook.model.ContactData;

public class ContactCreationTests extends TestBase{


    @Test
    public void contactCreationTests() throws Exception {
        app.getNavigationHelper().gotoContactCreationPage();
        app.getContactHelper().fillContactForm(new ContactData(null, "Petrovich", "Ivanov", "Burger King", "Moscow, Tushinskaya st, 17", "+71234567890", "ivanov1981@yandex123.ru"));
        app.getContactHelper().submitContactCreation();
        app.getSessionHelper().logout();
    }

}
