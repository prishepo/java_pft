package ru.stqu.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqu.pft.addressbook.model.ContactData;
import ru.stqu.pft.addressbook.model.GroupData;

import java.util.List;

public class ContactDeletionTests extends TestBase{


    @Test (enabled = false)
    public void testContactDeletion() {

        if (!app.contact().isThereAGroupInContactCreationForm("test1")){
            app.goTo().groupPage();
            app.group().create(new GroupData().withName("test1"));
        }

        if (!app.contact().isThereAContact()){
            app.contact().create(new ContactData("Ivan", "Petrovich", "Ivanov",
                    "Burger King", "Moscow, Tushinskaya st, 17", "+71234567890",
                    "ivanov1981@yandex123.ru", "test1"));
        }

        List<ContactData> before = app.contact().list();
        app.contact().selectContact(before.size() - 1);
        app.contact().deleteContact();
        app.contact().gotoHomePage();
        List<ContactData> after = app.contact().list();
        Assert.assertEquals(after.size(), before.size() - 1);


        before.remove(before.size()-1);
        Assert.assertEquals(before, after);
    }
}
