package ru.stqu.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqu.pft.addressbook.model.ContactData;
import ru.stqu.pft.addressbook.model.GroupData;

public class ContactPhoneTests extends TestBase {


    @BeforeMethod
    public void ensurePreconditions() {

        if (!app.contact().isThereAGroupInContactCreationForm("test1")) {
            app.goTo().groupPage();
            app.group().create(new GroupData().withName("test1"));
        }

        if (app.contact().all().size() == 0) {
            app.contact().create(new ContactData().withFirstName("Sergey").
                    withMiddleName("Petrovich").withSecondName("Ivanov").withCompanyName("Burger King").
                    withAddress("Moscow, Tushinskaya st, 17").withMobilePhoneNumber("+71234567890").
                    withEmail("ivanov1981@yandex123.ru").withGroup("test1"));
        }
    }


   /* @Test
    public void testContactPhones(){
        app.goTo().goToHomePage();
        ContactData contact = app.contact().all().iterator().next();
        ContactData contactInfoFromEditForm = app.contact().infoFromEditForm(contact);
    }
*/
}
