package ru.stqu.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import ru.stqu.pft.addressbook.model.ContactData;

public class ContactHelper extends HelperBase {

    public ContactHelper(WebDriver wd) {
        super(wd);
    }

    public void submitContactCreation() {
        click(By.xpath("//div[@id='content']/form/input[21]"));
    }

    public void fillContactForm(ContactData contactData) {
        type(By.name("firstname"),contactData.getFirstName());
        type(By.name("middlename"), contactData.getMiddleName());
        type(By.name("lastname"),contactData.getSecondName());
        type(By.name("company"),contactData.getCompanyName());
        type(By.name("address"),contactData.getAddress());
        type(By.name("mobile"),contactData.getMobilePhoneNumber());
        type(By.name("email"),contactData.getEmail());
    }
}
