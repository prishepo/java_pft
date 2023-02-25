package ru.stqu.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqu.pft.addressbook.model.ContactData;

public class ContactModificationTests extends TestBase {

   @Test
    public void testContactModification(){

        if (!app.getContactHelper().isThereAContact()){
            app.getContactHelper().createContact(new ContactData("Ivan", "Petrovich", "Ivanov",
                    "Burger King", "Moscow, Tushinskaya st, 17", "+71234567890",
                    "ivanov1981@yandex123.ru", "test1"));
        }
        app.getContactHelper().editContact();
        app.getContactHelper().fillContactForm(new ContactData("Mihail", "Ivanovich",
                "Petrov", "KFC", "Moscow, Tallinskaya st., 17", "+70987654321",
                "petrov991@yandex123.ru", null), false);
        app.getContactHelper().submitContactModification();
        app.getContactHelper().gotoHomePage();
    }

}
