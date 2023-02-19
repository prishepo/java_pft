package ru.stqu.pft.addressbook;

import org.testng.annotations.Test;

public class ContactCreationTests extends TestBase{


    @Test
    public void contactCreationTests() throws Exception {
        gotoContactCreationPage();
        fillContactForm(new ContactData("Ivan", "Petrovich", "Ivanov", "Burger King", "Moscow, Tushinskaya st, 17", "+71234567890", "ivanov1981@yandex123.ru"));
        submitContactCreation();
        logout();
    }

}
