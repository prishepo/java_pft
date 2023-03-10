package ru.stqu.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqu.pft.addressbook.model.ContactData;
import ru.stqu.pft.addressbook.model.GroupData;

import java.util.Comparator;
import java.util.List;

public class ContactModificationTests extends TestBase {

   @BeforeMethod
    public void ensurePreconditions(){
       if (!app.contact().isThereAGroupInContactCreationForm("test1")){
           app.goTo().groupPage();
           app.group().create(new GroupData().withName("test1"));
       }

       if (app.contact().list().size() == 0){
           app.contact().create(new ContactData("Ivan", "Petrovich", "Ivanov",
                   "Burger King", "Moscow, Tushinskaya st, 17", "+71234567890",
                   "ivanov1981@yandex123.ru", "test1"));
       }
   }

    @Test
    public void testContactModification(){
       List<ContactData> before = app.contact().list();
       int index = before.size()-1;
       ContactData contact = new ContactData(before.get(index).getId(),"Mihail", "Ivanovich",
               "Petrov", "KFC", "Moscow, Tallinskaya st., 17", "+70987654321",
               "petrov991@yandex123.ru", null);
       app.contact().modifyContact(index, contact);
       List<ContactData> after = app.contact().list();
       Assert.assertEquals(after.size(), before.size());

       before.remove(index);
       before.add(contact);
       Comparator<? super ContactData> byId = (c1, c2) -> Integer.compare(c1.getId(), c2.getId());
       before.sort(byId);
       after.sort(byId);

       Assert.assertEquals(before,after);

    }


}
