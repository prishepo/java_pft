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

    public void selectUserById(int id) {
        wd.findElement(By.xpath("//a[@href='manage_user_edit_page.php?user_id=" + id + "']")).click();
    }


    public void pushChangePasswordButton() {
        wd.findElement(By.xpath("//input[@value='Сбросить пароль']")).click();
    }

    public void changePassword(String confirmationLink, String newPassword, String confirmNewPassword){
        wd.get(confirmationLink);
        type(By.name("password"), newPassword);
        type(By.name("password_confirm"), confirmNewPassword);
        click(By.xpath("//span[.='Изменить пользователя']"));
    }
}
