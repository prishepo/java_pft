package ru.stqu.pft.addressbook.appmanager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.Browser;

import java.time.Duration;

public class ApplicationManager {
    private final String browser;
    public WebDriver wd;

    private ContactHelper contactHelper;
    private NavigationHelper navigationHelper;
    private GroupHelper groupHelper;
    private SessionHelper sessionHelper;

    public ApplicationManager(String browser) {
        this.browser = browser;
    }

    public void init() {
        if (Browser.CHROME.browserName().equals(browser)){
            wd = new ChromeDriver();
        } else if (Browser.FIREFOX.browserName().equals(browser)){
            wd = new FirefoxDriver();
        } else if (Browser.IE.browserName().equals(browser)) {
            wd = new InternetExplorerDriver();
        }

        wd.manage().timeouts().implicitlyWait(Duration.ofSeconds(0));
        wd.get("http://localhost/addressbook/");
        groupHelper = new GroupHelper(wd);
        navigationHelper = new NavigationHelper(wd);
        sessionHelper = new SessionHelper(wd);
        contactHelper = new ContactHelper(wd);
        sessionHelper.login("admin", "secret");
    }

    public void stop() {
        wd.quit();
    }


    public GroupHelper getGroupHelper() {
        return groupHelper;
    }

    public NavigationHelper getNavigationHelper() {
        return navigationHelper;
    }

    public SessionHelper getSessionHelper() {
        return sessionHelper;
    }

    public ContactHelper getContactHelper() {
        return contactHelper;
    }
}
