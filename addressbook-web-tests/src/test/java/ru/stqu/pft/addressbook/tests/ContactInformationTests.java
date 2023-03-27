package ru.stqu.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqu.pft.addressbook.model.ContactData;
import ru.stqu.pft.addressbook.model.GroupData;
import ru.stqu.pft.addressbook.model.Groups;

import java.util.Arrays;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.*;

public class ContactInformationTests extends TestBase {


    @BeforeMethod
    public void ensurePreconditions() {

        Groups groups = app.db().groups();
        if (!app.contact().isThereAGroupInContactCreationForm("test1")) {
            app.goTo().groupPage();
            app.group().create(new GroupData().withName("test1"));
        } else
            app.contact().homePage();

        if (app.contact().all().size() == 0) {
            app.contact().create(new ContactData().withFirstName("Sergey").
                    withMiddleName("Petrovich").withLastName("Ivanov").withCompanyName("Burger King").
                    withAddress("Moscow, Tushinskaya st, 17").withMobilePhone("+71234567890").
                    withEmail("ivanov1981@yandex123.ru").inGroup(groups.iterator().next()));
        }
    }


    @Test
    public void testContactPhones(){
        app.goTo().goToHomePage();
        ContactData contact = app.contact().all().iterator().next();
        ContactData contactInfoFromEditForm = app.contact().infoFromEditForm(contact);

        assertThat(contact.getAllPhones(), equalTo(mergePhones(contactInfoFromEditForm)));
        assertThat(contact.getAllEmails(),equalTo(mergeEmails(contactInfoFromEditForm)));
        assertThat(contact.getAddress(),equalTo(cleanedAddresses(contactInfoFromEditForm.getAddress())));

    }


    private String mergeEmails(ContactData contact) {
        return Arrays.asList(contact.getEmail(),contact.getEmail2(), contact.getEmail3())
                .stream().filter((s)->! s.equals(""))
                .map(ContactInformationTests::cleanedEmails)
                .collect(Collectors.joining("\n"));
    }

    private String mergePhones(ContactData contact) {
        return Arrays.asList(contact.getHomePhone(),contact.getMobilePhone(), contact.getWorkPhone())
                .stream().filter((s)->! s.equals(""))
                .map(ContactInformationTests::cleanedPhones)
                .collect(Collectors.joining("\n"));
    }


    public static String cleanedPhones(String phone){
        return phone.replaceAll("\\s", "").replaceAll("[-()]","").replaceAll("^\\+70", "+7");
    }

    public static String cleanedEmails (String email){
        return email.replaceAll("\\s", " ");
    }

    public static String cleanedAddresses (String address){
        return address.replaceAll("\\s", " ").replaceAll("\\n", "");
    }



}
