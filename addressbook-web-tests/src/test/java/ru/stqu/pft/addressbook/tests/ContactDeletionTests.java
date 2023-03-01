package ru.stqu.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqu.pft.addressbook.model.ContactData;
import ru.stqu.pft.addressbook.model.GroupData;

public class ContactDeletionTests extends TestBase{


    @Test
    public void testContactDeletion() {

        if (!app.getContactHelper().isThereAGroupInContactCreationForm("test1")){
            app.getNavigationHelper().gotoGroupPage();
            app.getGroupHelper().createGroup(new GroupData("test1", null, null));
        }

        if (!app.getContactHelper().isThereAContact()){
            app.getContactHelper().createContact(new ContactData("Ivan", "Petrovich", "Ivanov",
                    "Burger King", "Moscow, Tushinskaya st, 17", "+71234567890",
                    "ivanov1981@yandex123.ru", "test1"));
        }
        app.getContactHelper().selectContact();
        app.getContactHelper().deleteContact();
    }
}
