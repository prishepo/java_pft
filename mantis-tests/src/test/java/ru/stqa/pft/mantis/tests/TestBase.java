package ru.stqa.pft.mantis.tests;
import biz.futureware.mantis.rpc.soap.client.IssueData;
import biz.futureware.mantis.rpc.soap.client.MantisConnectLocator;
import biz.futureware.mantis.rpc.soap.client.MantisConnectPortType;
import biz.futureware.mantis.rpc.soap.client.ObjectRef;
import org.hamcrest.core.Is;
import org.openqa.selenium.remote.Browser;
import org.testng.SkipException;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import ru.stqa.pft.mantis.appmanager.ApplicationManager;
import ru.stqa.pft.mantis.model.Issue;

import javax.xml.rpc.ServiceException;
import java.math.BigInteger;
import java.net.MalformedURLException;
import java.net.URL;
import java.rmi.RemoteException;


public class TestBase {

    protected static final ApplicationManager app
            = new ApplicationManager(System.getProperty("browser", Browser.FIREFOX.browserName()));


    @BeforeSuite
    public void setUp() throws Exception {
        app.init();
        /*app.ftp().upload(new File("src/test/resources/config_inc.php"), "config_inc.php", "config_inc.php.bak");*/
    }

    @AfterSuite(alwaysRun = true)
    public void tearDown() throws Exception {
        /*app.ftp().restore("config_inc.php", "config_inc.php.bak");*/
        app.stop();
    }

    public void skipIfNotFixed(int issueId) throws RemoteException, ServiceException, MalformedURLException {
        if (isIssueOpen(issueId)) {
            throw new SkipException("Ignored because of issue " + issueId);
        }
    }

    private MantisConnectPortType getMantisConnect() throws ServiceException, MalformedURLException {
        MantisConnectPortType mc = new MantisConnectLocator().getMantisConnectPort(new URL("http://localhost/mantisbt-2.25.6/api/soap/mantisconnect.php"));
        return mc;
    }

    private boolean isIssueOpen(int issueId) throws MalformedURLException, ServiceException, RemoteException {
        MantisConnectPortType mc = getMantisConnect();
        IssueData findIssueById = mc.mc_issue_get("administrator", "root", BigInteger.valueOf(issueId));
        Issue issue = new Issue().withId(findIssueById.getId().intValue());
        System.out.println(findIssueById.getStatus());
        return true;



    }


}

