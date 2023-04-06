package ru.stqa.pft.mantis.appmanager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.Browser;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

public class ApplicationManager {
    private final String browser;
    private final Properties properties;
    public WebDriver wd;

    public ApplicationManager(String browser) {
        this.browser = browser;
        properties = new Properties();

    }

    public void init() throws IOException {
        String target = System.getProperty("target", "local");
        properties.load(new FileReader(new File(String.format("src/test/resources/%s.properties", target))));

        if (Browser.CHROME.browserName().equals(browser)){
            wd = new ChromeDriver();
        } else if (Browser.FIREFOX.browserName().equals(browser)){
            wd = new FirefoxDriver();
        } else if (Browser.IE.browserName().equals(browser)) {
            wd = new InternetExplorerDriver();
        }


        wd.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));
        wd.get(properties.getProperty("web.baseUrl"));
    }

    public void stop() {
        wd.quit();
    }

}
