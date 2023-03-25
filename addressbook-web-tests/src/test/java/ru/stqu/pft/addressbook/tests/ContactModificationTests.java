package ru.stqu.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqu.pft.addressbook.model.ContactData;
import ru.stqu.pft.addressbook.model.Contacts;
import ru.stqu.pft.addressbook.model.GroupData;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.*;

public class ContactModificationTests extends TestBase {

   @BeforeMethod
    public void ensurePreconditions(){
       if (!app.contact().isThereAGroupInContactCreationForm("test1")){
           app.goTo().groupPage();
           app.group().create(new GroupData().withName("test1"));
       }else
           app.contact().homePage();

       if (app.db().contacts().size()==0){
           app.contact().create(new ContactData().withFirstName("Sergey").withMiddleName("Petrovich").withLastName("Ivanov").withCompanyName("Burger King").
                   withAddress("Moscow, Tushinskaya st, 17").withMobilePhone("+71234567890").withEmail("ivanov1981@yandex123.ru").withGroup("test1"));
       }
   }

    /* if(app.db().groups().size()== 0) {
        app.goTo().groupPage();
        app.group().create(new GroupData().withName("test2"));
        app.group().returnToGroupPage();

    }*/

    @Test
    public void testContactModification(){
       app.contact().homePage();
       Contacts before = app.db().contacts();
       ContactData modifiedContact = before.iterator().next();
       ContactData contact = new ContactData().withId(modifiedContact.getId()).withFirstName("Mihail").withMiddleName("Ivanovich").
               withLastName("Petrov").withCompanyName("KFC").withAddress("Moscow, Tallinskaya st., 17").
               withMobilePhone("+70987654321").withEmail("petrov991@yandex123.ru");
       app.contact().modify(contact);
       assertThat(app.contact().count(), equalTo(before.size()));
       Contacts after = app.db().contacts();
       assertThat(after, equalTo(before.without(modifiedContact).withAdded(contact)));
    }


}
