package ru.stqu.pft.addressbook.tests;
import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqu.pft.addressbook.model.ContactData;
import ru.stqu.pft.addressbook.model.GroupData;

import java.util.Comparator;
import java.util.HashSet;
import java.util.List;

public class ContactCreationTests extends TestBase{

    @Test
    public void contactCreationTests() throws Exception {

        /*app.getNavigationHelper().gotoGroupPage();
        if (!app.getGroupHelper().isThereAGroup()){
            app.getGroupHelper().createGroup(new GroupData("test23", null, null));
        }
        String group = app.wd.findElement(By.name("selected[]")).getAttribute("value");*/

        List<ContactData> before = app.getContactHelper().getContactList();

        if (!app.getContactHelper().isThereAGroupInContactCreationForm("test1")){
            app.getNavigationHelper().gotoGroupPage();
            app.getGroupHelper().createGroup(new GroupData("test1", null, null));
        }
        ContactData contact = new ContactData("Sergey", "Petrovich", "Ivanov", "Burger King",
                "Moscow, Tushinskaya st, 17", "+71234567890",
                "ivanov1981@yandex123.ru", "test1");
        app.getContactHelper().createContact(contact);
        List<ContactData> after = app.getContactHelper().getContactList();
        Assert.assertEquals(after.size(), before.size() + 1);


        contact.setId(after.stream().max((o1, o2) -> Integer.compare(o1.getId(), o2.getId())).get().getId());
        before.add(contact);
        Comparator<? super ContactData> byId = (c1, c2) -> Integer.compare(c1.getId(), c2.getId());
        before.sort(byId);
        after.sort(byId);
        Assert.assertEquals(before,after);

    }

}
