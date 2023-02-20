package ru.stqu.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import ru.stqu.pft.addressbook.model.ContactData;

import java.time.Duration;

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

    public void submitContactModification() {
        click(By.name("update"));
    }

    public void gotoHomePage() {
        click(By.linkText("home page"));
    }

    public void editContact() {
        click(By.xpath("//table[@id='maintable']/tbody/tr[2]/td[8]/a/img"));
    }

    public void deleteContact() {
        click(By.xpath("//input[@value='Delete']"));
        wd.switchTo().alert().accept();
    }

    public void selectContact() {
        click(By.xpath("//input[@id='4']"));

    }
}
