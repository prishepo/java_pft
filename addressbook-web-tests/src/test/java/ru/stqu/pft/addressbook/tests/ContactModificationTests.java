package ru.stqu.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqu.pft.addressbook.model.ContactData;
import ru.stqu.pft.addressbook.model.GroupData;

import java.util.Set;

public class ContactModificationTests extends TestBase {

   @BeforeMethod
    public void ensurePreconditions(){
       if (!app.contact().isThereAGroupInContactCreationForm("test1")){
           app.goTo().groupPage();
           app.group().create(new GroupData().withName("test1"));
       }

       if (app.contact().all().size() == 0){
           app.contact().create(new ContactData().withFirstName("Sergey").withMiddleName("Petrovich").withSecondName("Ivanov").withCompanyName("Burger King").
                   withAddress("Moscow, Tushinskaya st, 17").withMobilePhoneNumber("+71234567890").withEmail("ivanov1981@yandex123.ru").withGroup("test1"));
       }
   }

    @Test
    public void testContactModification(){
       Set<ContactData> before = app.contact().all();
       ContactData modifiedContact = before.iterator().next();
       ContactData contact = new ContactData().withId(modifiedContact.getId()).withFirstName("Mihail").withMiddleName("Ivanovich").
               withSecondName("Petrov").withCompanyName("KFC").withAddress("Moscow, Tallinskaya st., 17").
               withMobilePhoneNumber("+70987654321").withEmail("petrov991@yandex123.ru");
       app.contact().modify(contact);
       Set<ContactData> after = app.contact().all();
       Assert.assertEquals(after.size(), before.size());

       before.remove(modifiedContact);
       before.add(contact);
       Assert.assertEquals(before,after);

    }


}
