package ru.stqu.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import ru.stqu.pft.addressbook.model.ContactData;
import ru.stqu.pft.addressbook.model.Contacts;
import ru.stqu.pft.addressbook.model.GroupData;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.*;


public class ContactCreationTests extends TestBase {

    @BeforeMethod
    public void ensurePreconditions() throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(new File("src/test/resources/contacts.csv")));
        String line = reader.readLine();
        while (line != null){
            String[] split = line.split(";");
                if (!app.contact().isThereAGroupInContactCreationForm(split[7])) {
                    app.goTo().groupPage();
                    app.group().create(new GroupData().withName(split[7]));
                }
                line = reader.readLine();
        }

    }

    @DataProvider
    public Iterator<Object[]> validContacts() throws IOException {
        List<Object[]> list = new ArrayList<Object[]>();
        BufferedReader reader = new BufferedReader(new FileReader(new File("src/test/resources/contacts.csv")));
        String line = reader.readLine();
        while (line != null) {
            String[] split = line.split(";");
            list.add(new Object[] {new ContactData().withFirstName(split[0]).withMiddleName(split[1]).withLastName(split[2]).withCompanyName(split[3]).
                    withAddress(split[4]).withMobilePhone(split[5]).withEmail(split[6]).withGroup(split[7])});
            line = reader.readLine();
        }

        return list.iterator();
    }

    @Test (dataProvider = "validContacts")
    public void contactCreationTests(ContactData contact) throws Exception {
        app.goTo().goToHomePage();
        Contacts before = app.contact().all();
        File photo = new File("src/test/resourses/stru.png");
        /*withPhoto(photo);*/
        app.contact().create(contact);
        app.contact().homePage();
        assertThat(app.contact().count(), equalTo(before.size()+1));
        Contacts after = app.contact().all();
        assertThat(after, equalTo(
                before.withAdded(contact.withId(after.stream().mapToInt((c) -> c.getId()).max().getAsInt()))));
    }




}
