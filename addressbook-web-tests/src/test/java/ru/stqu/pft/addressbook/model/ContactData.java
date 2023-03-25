package ru.stqu.pft.addressbook.model;

import com.google.gson.annotations.Expose;
import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamOmitField;
import jakarta.persistence.*;
import org.hibernate.annotations.Type;

import java.io.File;
import java.util.Objects;


@XStreamAlias("contact")
@Entity
@Table(name = "addressbook")
public class ContactData {

    @XStreamOmitField
    @Id
    @Column (name = "id")
    private int id = Integer.MAX_VALUE;
    @Expose
    @Column (name = "firstname")
    private String firstName;
    @Expose
    @Column (name = "middlename")
    private String middleName;
    @Expose
    @Column (name = "lastname")
    private String lastName;
    @Expose
    @Column (name = "company")
    private String companyName;
    @Expose
    @Column (name = "address")
    private String address;
    @Expose
    @Column (name = "mobile")
    private String mobilePhone;
    @Column (name = "home")
    private String homePhone;
    @Column (name = "work")
    private String workPhone;
    @Expose
    @Column (name = "email")
    private String email;
    @Column (name = "email2")
    private String email2;
    @Column (name = "email3")
    private String email3;

    @Expose
    @Transient
    private String group;
    @Transient
    private String allPhones;
    @Transient
    private String allEmails;
    @Column (name = "photo")
    private String photo;



    public ContactData withPhoto(File photo) {
        this.photo = photo.getPath();
        return this;
    }

    public ContactData withAllEmails(String allEmails) {
        this.allEmails = allEmails;
        return this;
    }

    public ContactData withEmail2(String email2) {
        this.email2 = email2;
        return this;
    }

    public ContactData withEmail3(String email3) {
        this.email3 = email3;
        return this;
    }

    public ContactData withAllPhones(String allPhones) {
        this.allPhones = allPhones;
        return this;
    }


    public ContactData withId(int id) {
        this.id = id;
        return this;
    }

    public ContactData withFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public ContactData withMiddleName(String middleName) {
        this.middleName = middleName;
        return this;
    }

    public ContactData withLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public ContactData withCompanyName(String companyName) {
        this.companyName = companyName;
        return this;
    }

    public ContactData withAddress(String address) {
        this.address = address;
        return this;
    }

    public ContactData withWorkPhone(String workPhone) {
        this.workPhone = workPhone;
        return this;
    }
    public ContactData withHomePhone(String homePhone) {
        this.homePhone = homePhone;
        return this;
    }
    public ContactData withMobilePhone(String mobilePhone) {
        this.mobilePhone = mobilePhone;
        return this;
    }

    public ContactData withEmail(String email) {
        this.email = email;
        return this;
    }

    public ContactData withGroup(String group) {
        this.group = group;
        return this;
    }

    public String getAllPhones() {
        return allPhones;
    }

    public String getAllEmails() {
        return allEmails;
    }

    public int getId() {
        return id;
    }

    public File getPhoto() {
        return new File(photo);
    }

    public String getFirstName() {
        return firstName;
    }


    public String getMiddleName() {
        return middleName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getCompanyName() {
        return companyName;
    }

    public String getAddress() {
        return address;
    }

    public String getMobilePhone() {
        return mobilePhone;
    }

    public String getHomePhone() {
        return homePhone;
    }

    public String getWorkPhone() {
        return workPhone;
    }

    public String getEmail() {
        return email;
    }

    public String getEmail2() {
        return email2;
    }

    public String getEmail3() {
        return email3;
    }

    public String getGroup() {
        return group;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ContactData that = (ContactData) o;
        return id == that.id && Objects.equals(firstName, that.firstName) && Objects.equals(middleName, that.middleName) && Objects.equals(lastName, that.lastName) && Objects.equals(companyName, that.companyName) && Objects.equals(address, that.address) && Objects.equals(mobilePhone, that.mobilePhone) && Objects.equals(homePhone, that.homePhone) && Objects.equals(workPhone, that.workPhone) && Objects.equals(email, that.email) && Objects.equals(email2, that.email2) && Objects.equals(email3, that.email3) && Objects.equals(group, that.group);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, firstName, middleName, lastName, companyName, address, mobilePhone, homePhone, workPhone, email, email2, email3, group);
    }

    @Override
    public String toString() {
        return "ContactData{" +
                "id='" + id + '\'' +
                ", firstName='" + firstName + '\'' +
                ", secondName='" + lastName + '\'' +
                '}';
    }

}
