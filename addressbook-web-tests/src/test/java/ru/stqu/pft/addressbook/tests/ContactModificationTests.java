package ru.stqu.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqu.pft.addressbook.model.ContactData;

public class ContactModificationTests extends TestBase {

   @Test
    public void testContactModification(){
        app.getContactHelper().editContact();
        app.getContactHelper().fillContactForm(new ContactData("Ivan", "Ivanovich", "Petrov", "KFC", "Moscow, Tallinskaya st., 17", "+70987654321", "petrov991@yandex123.ru"));
        app.getContactHelper().submitContactModification();
        app.getContactHelper().gotoHomePage();
    }

}
