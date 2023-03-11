package ru.stqu.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import ru.stqu.pft.addressbook.model.ContactData;
import ru.stqu.pft.addressbook.model.Contacts;

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
        type(By.name("lastname"),contactData.getLastName());
        type(By.name("company"),contactData.getCompanyName());
        type(By.name("address"),contactData.getAddress());
        type(By.name("mobile"),contactData.getMobilePhone());
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

    public void initContactModificationById(int id) {
        wd.findElement(By.cssSelector(String.format("a[href='edit.php?id=%s']",id))).click();
    }

    public void modify(ContactData contact) {
        initContactModificationById(contact.getId());
        fillContactForm(contact, false);
        submitContactModification();
        contactCache = null;
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
        contactCache = null;
        homePage();
    }

    public void gotoContactCreationPage() {
        click(By.linkText("add new"));
    }

    public void create(ContactData contact) {
        gotoContactCreationPage();
        fillContactForm(contact, true);
        submitContactCreation();
        contactCache = null;
        homePage();
    }

    public boolean isThereAContact() {
        return isElementPresent(By.name("selected[]"));
    }

    public boolean isThereAGroupInContactCreationForm(String group) {
        gotoContactCreationPage();
        return isElementPresent(By.xpath("//select[@name='new_group']//option[text()='"+group + "']"));

    }

    public int count() {
        return wd.findElements(By.name("selected[]")).size();
    }


    private Contacts contactCache = null;

    public Contacts all() {
        if (contactCache != null){
            return new Contacts(contactCache);
        }

        contactCache = new Contacts();
        List <WebElement> elements = wd.findElements(By.xpath("//table[@class = 'sortcompletecallback-applyZebra']//tr[@name = 'entry']"));
        for (WebElement element : elements) {

            String lastName = element.findElement(By.xpath("./td[2]")).getText();
            String firstName = element.findElement(By.xpath("./td[3]")).getText();
            int id = Integer.parseInt(element.findElement(By.tagName("input")).getAttribute("value"));
            String allPhones = element.findElement(By.xpath("./td[6]")).getText();
            contactCache.add(new ContactData().withId(id).withFirstName(firstName).withLastName(lastName)
                    .withAllPhones(allPhones));
        }

        return new Contacts(contactCache);

    }


    public ContactData infoFromEditForm(ContactData contact) {
        initContactModificationById(contact.getId());
        String firstname = wd.findElement(By.name("firstname")).getAttribute("value");
        String middlename = wd.findElement(By.name("middlename")).getAttribute("value");
        String lastname = wd.findElement(By.name("lastname")).getAttribute("value");
        String companyname = wd.findElement(By.name("company")).getAttribute("value");
        String address = wd.findElement(By.name("address")).getAttribute("value");
        String homephone = wd.findElement(By.name("home")).getAttribute("value");
        String mobilephone = wd.findElement(By.name("mobile")).getAttribute("value");
        String workphone = wd.findElement(By.name("work")).getAttribute("value");
        String email = wd.findElement(By.name("email")).getAttribute("value");
        wd.navigate().back();
        return new ContactData().withId(contact.getId()).withFirstName(firstname).withLastName(lastname).withHomePhone(homephone)
                .withMobilePhone(mobilephone).withWorkPhone(workphone);

    }
}
