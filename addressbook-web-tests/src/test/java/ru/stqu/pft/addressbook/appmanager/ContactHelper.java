package ru.stqu.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import ru.stqu.pft.addressbook.model.ContactData;
import ru.stqu.pft.addressbook.model.Contacts;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

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

    public void editContactById(int id) {
        wd.findElement(By.xpath("//a[@href='view.php?id=" + id + "']")).click();
        wd.findElement(By.name("modifiy")).click();
    }

    public void modify(ContactData contact) {
        editContactById(contact.getId());
        fillContactForm(contact, false);
        submitContactModification();
        homePage();
    }

    public void deleteButton() {
        click(By.xpath("//input[@value='Delete']"));
        wd.switchTo().alert().accept();
    }

    public void selectContactById(int id) {
        wd.findElement(By.cssSelector("input[value='" + id + "']")).click();
    }


    public void delete(ContactData contact) {
        selectContactById(contact.getId());
        deleteButton();
        homePage();
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


    public Contacts all() {
        Contacts contacts = new Contacts();
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
