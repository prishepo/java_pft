package ru.stqu.pft.addressbook.tests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.Test;
import ru.stqu.pft.addressbook.appmanager.ApplicationManager;
import ru.stqu.pft.addressbook.model.ContactData;
import ru.stqu.pft.addressbook.model.GroupData;

public class ContactCreationTests extends TestBase{


    @Test
    public void contactCreationTests() throws Exception {

        /*app.getNavigationHelper().gotoGroupPage();
        *//*WebElement groupFromGroupPage = app.wd.findElement(By.name("selected[]"));
        String group = groupFromGroupPage.getText();*/

        if (!app.getContactHelper().isThereAGroupInContactCreationForm("//select[@name='new_group']//option[text()='test1']")){
            app.getNavigationHelper().gotoGroupPage();
            app.getGroupHelper().createGroup(new GroupData("test1", null, null));

        }

        app.getContactHelper().createContact(new ContactData("Sergey", "Petrovich", "Ivanov", "Burger King",
                "Moscow, Tushinskaya st, 17", "+71234567890",
                "ivanov1981@yandex123.ru", "test1"));
        app.getSessionHelper().logout();

    }

}
