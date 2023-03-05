package ru.stqu.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqu.pft.addressbook.model.ContactData;
import java.util.Comparator;
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
        app.getContactHelper().editContactById(before.size() - 1);
        ContactData contact = new ContactData(before.get(before.size()-1).getId(),"Mihail", "Ivanovich",
                "Petrov", "KFC", "Moscow, Tallinskaya st., 17", "+70987654321",
                "petrov991@yandex123.ru", null);
        app.getContactHelper().fillContactForm(contact, false);
        app.getContactHelper().submitContactModification();
        app.getContactHelper().gotoHomePage();
       List<ContactData> after = app.getContactHelper().getContactList();
       Assert.assertEquals(after.size(), before.size());

       before.remove(before.size()-1);
       before.add(contact);

       Comparator<? super ContactData> byId = (c1, c2) -> Integer.compare(c1.getId(), c2.getId());
       before.sort(byId);
       after.sort(byId);

       Assert.assertEquals(before,after);

    }

}
