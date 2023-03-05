package ru.stqu.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import ru.stqu.pft.addressbook.model.ContactData;
import ru.stqu.pft.addressbook.model.GroupData;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

public class ContactHelper extends HelperBase {

    public ContactHelper(WebDriver wd) {
        super(wd);
    }

    public void submitContactCreation() {
        click(By.xpath("//div[@id='content']/form/input[21]"));
    }

    public void fillContactForm(ContactData contactData, boolean creation) {
        type(By.name("firstname"),contactData.getFirstName());
        type(By.name("middlename"), contactData.getMiddleName());
        type(By.name("lastname"),contactData.getSecondName());
        type(By.name("company"),contactData.getCompanyName());
        type(By.name("address"),contactData.getAddress());
        type(By.name("mobile"),contactData.getMobilePhoneNumber());
        type(By.name("email"),contactData.getEmail());


       if (creation){
           new Select(wd.findElement(By.name("new_group"))).selectByVisibleText(contactData.getGroup());
       } else {
           Assert.assertFalse(isElementPresent(By.name("new_group")));
       }

    }

    public void submitContactModification() {
        click(By.name("update"));
    }

    public void gotoHomePage() {
        click(By.linkText("home"));
    }

    public void editContact(int index) {

        click(By.xpath("//table[@id='maintable']/tbody/tr[2]/td[8]/a/img"));
        /*wd.findElements(By.xpath("//table[@class = 'sortcompletecallback-applyZebra']//tr[@name = 'entry']")).get(index).click();*/
    }

    public void deleteContact() {
        click(By.xpath("//input[@value='Delete']"));
        wd.switchTo().alert().accept();
    }

    public void selectContact(int index) {
        wd.findElements(By.name("selected[]")).get(index).click();
    }

    public void gotoContactCreationPage() {
        click(By.linkText("add new"));
    }

    public void createContact(ContactData contact) {
        gotoContactCreationPage();
        fillContactForm(contact, true);
        submitContactCreation();
        gotoHomePage();
    }

    public boolean isThereAContact() {
        return isElementPresent(By.name("selected[]"));
    }

    public boolean isThereAGroupInContactCreationForm(String group) {
        gotoContactCreationPage();
        return isElementPresent(By.xpath("//select[@name='new_group']//option[text()='"+group + "']"));
    }

    public int getContactCount() {
        return wd.findElements(By.name("selected[]")).size();
    }


    public List<ContactData> getContactList() {
        List <ContactData> contacts = new ArrayList<ContactData>();
        List <WebElement> elements = wd.findElements(By.xpath("//table[@class = 'sortcompletecallback-applyZebra']//tr[@name = 'entry']"));


        for (WebElement element : elements) {
            String lastName = element.findElement(By.xpath("./td[2]")).getText();
            String firstName = element.findElement(By.xpath("./td[3]")).getText();
            int id = Integer.parseInt(element.findElement(By.tagName("input")).getAttribute("value"));
            ContactData contact = new ContactData(id, firstName, null, lastName, null, null,null,null,null);
            contacts.add(contact);
        }

        return contacts;

    }
}
