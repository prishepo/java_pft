package ru.stqu.pft.addressbook.tests;

import com.google.gson.Gson;
import com.google.gson.internal.bind.util.ISO8601Utils;
import com.google.gson.reflect.TypeToken;
import com.thoughtworks.xstream.XStream;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import ru.stqu.pft.addressbook.model.ContactData;
import ru.stqu.pft.addressbook.model.Contacts;
import ru.stqu.pft.addressbook.model.GroupData;
import ru.stqu.pft.addressbook.model.Groups;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.*;


public class ContactCreationTests extends TestBase {

    /*@BeforeMethod
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

    }*/

    @BeforeMethod
    public void ensurePreconditions() {
        if (app.db().groups().size() == 0) {
            app.goTo().groupPage();
            app.group().create(new GroupData().withName("test1"));
        }
    }


    @DataProvider
    public Iterator<Object[]> validContactsFromXml() throws IOException {
        try (BufferedReader reader = new BufferedReader(new FileReader(new File("src/test/resources/contacts.xml")))) {
            String xml = "";
            String line = reader.readLine();
            while (line != null) {
                xml += line;
                line = reader.readLine();
            }
            XStream xstream = new XStream();
            xstream.processAnnotations(ContactData.class);
            xstream.allowTypes(new Class[]{ContactData.class});
            List<ContactData> contacts = (List<ContactData>) xstream.fromXML(xml);
            return contacts.stream().map((c) -> new Object[] {c}).collect(Collectors.toList()).iterator();
        }
    }

    @DataProvider
    public Iterator<Object[]> validContactsFromJson() throws IOException {
        try (BufferedReader reader = new BufferedReader(new FileReader(new File("src/test/resources/contacts.json")))){
            String json = "";
            String line = reader.readLine();
            while (line != null) {
                json += line;
                line = reader.readLine();
            }
            Gson gson = new Gson();
            List<ContactData> contacts = gson.fromJson(json, new TypeToken<List<ContactData>>(){}.getType());
            return contacts.stream().map((c) -> new Object[] {c}).collect(Collectors.toList()).iterator();
        }
    }

    @Test (dataProvider = "validContactsFromJson")
    public void contactCreationTests(ContactData contact) throws Exception {
        Groups groups = app.db().groups();
        app.goTo().goToHomePage();
        Contacts before = app.db().contacts();
        File photo = new File("src/test/resources/stru.png");
        /*withPhoto(photo);*/
        app.contact().create(contact.inGroup(groups.iterator().next()));
        assertThat(app.contact().count(), equalTo(before.size()+1));
        Contacts after = app.db().contacts();
        assertThat(after, equalTo(
                before.withAdded(contact.withId(after.stream().mapToInt((c) -> c.getId()).max().getAsInt()))));

        verifyContactListInUI();
    }

}
