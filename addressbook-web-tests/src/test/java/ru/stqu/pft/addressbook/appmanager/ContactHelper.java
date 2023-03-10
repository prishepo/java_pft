package ru.stqu.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import ru.stqu.pft.addressbook.model.ContactData;

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

    public void homePage() {
        click(By.linkText("home"));
    }

    public void editContact() {
        click(By.xpath("//table[@id='maintable']/tbody/tr[2]/td[8]/a/img"));
    }

    public void editContactById(int index) {
        wd.findElements(By.xpath("//img[@alt='Edit']")).get(index).click();

        }

    public void modifyContact(int index, ContactData contact) {
        editContactById(index);
        fillContactForm(contact, false);
        submitContactModification();
        homePage();
    }


    public void delete() {
        click(By.xpath("//input[@value='Delete']"));
        wd.switchTo().alert().accept();
    }

    public void delete(int index) {
       select(index);
       delete();
       homePage();
    }

    public void select(int index) {
        wd.findElements(By.name("selected[]")).get(index).click();
    }

    public void gotoContactCreationPage() {
        click(By.linkText("add new"));
    }

    public void create(ContactData contact) {
        gotoContactCreationPage();
        fillContactForm(contact, true);
        submitContactCreation();
        homePage();
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


    public List<ContactData> list() {
        List <ContactData> contacts = new ArrayList<ContactData>();
        List <WebElement> elements = wd.findElements(By.xpath("//table[@class = 'sortcompletecallback-applyZebra']//tr[@name = 'entry']"));


        for (WebElement element : elements) {
            String secondName = element.findElement(By.xpath("./td[2]")).getText();
            String firstName = element.findElement(By.xpath("./td[3]")).getText();
            int id = Integer.parseInt(element.findElement(By.tagName("input")).getAttribute("value"));
            contacts.add(new ContactData().withId(id).withFirstName(firstName).withSecondName(secondName));
        }

        return contacts;

    }


}
