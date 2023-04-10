package ru.stqa.pft.mantis.appmanager;

import org.openqa.selenium.By;

public class AdminHelper extends HelperBase {





    public AdminHelper(ApplicationManager app) {
        super(app);
    }

    public void goToManagePage(){
        click(By.xpath("//span[.=' Управление ']"));
    }

    public void goToContactsManagePage(){
        click(By.xpath("//a[contains(text(),'Управление пользователями')]"));
    }


    public void authorization(){
        wd.findElement(By.id("username")).sendKeys("administrator");
        click(By.xpath("//input[@value='Вход']"));
        wd.findElement(By.id("password")).sendKeys("root");
        click(By.xpath("//input[@value='Вход']"));

    }








}
