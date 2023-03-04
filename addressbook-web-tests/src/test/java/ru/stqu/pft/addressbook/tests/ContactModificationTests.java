package ru.stqu.pft.addressbook.tests;

import org.checkerframework.checker.units.qual.C;
import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqu.pft.addressbook.model.ContactData;
import ru.stqu.pft.addressbook.model.GroupData;

import java.util.HashSet;
import java.util.List;

public class ContactModificationTests extends TestBase {

   @Test
    public void testContactModification(){

        if (!app.getContactHelper().isThereAContact()){
            app.getContactHelper().createContact(new ContactData("Ivan", "Petrovich", "Ivanov",
                    "Burger King", "Moscow, Tushinskaya st, 17", "+71234567890",
                    "ivanov1981@yandex123.ru", "test1"));
        }
       List<ContactData> before = app.getContactHelper().getContactList();
        app.getContactHelper().editContact();
        ContactData contact = new ContactData("Mihail", "Ivanovich",
                "Petrov", "KFC", "Moscow, Tallinskaya st., 17", "+70987654321",
                "petrov991@yandex123.ru", null);
        app.getContactHelper().fillContactForm(contact, false);
        app.getContactHelper().submitContactModification();
        app.getContactHelper().gotoHomePage();
       List<ContactData> after = app.getContactHelper().getContactList();
       Assert.assertEquals(after.size(), before.size());

       before.remove(before.size()-1);
       before.add(contact);
       Assert.assertEquals(new HashSet<Object>(before),new HashSet<Object>(after));


    }

}
