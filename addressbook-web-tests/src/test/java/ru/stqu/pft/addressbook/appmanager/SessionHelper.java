package ru.stqu.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class SessionHelper extends HelperBase{
    public SessionHelper(WebDriver wd) {
        super(wd);
    }

    public void logout() {
        wd.findElement(By.linkText("Logout")).click();
    }

    public void login(String username, String password) {
        type(By.name("user"),username);
        type(By.name("pass"),password);
        click(By.xpath("//input[@value='Login']"));
    }
}
