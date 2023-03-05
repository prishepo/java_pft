package ru.stqu.pft.addressbook.model;

import java.util.Objects;

public class ContactData {

    private int id;
    private final String firstName;
    private final String middleName;
    private final String secondName;
    private final String companyName;
    private final String address;
    private final String mobilePhoneNumber;
    private final String email;
    private String group;

    public ContactData(int id, String firstName, String middleName, String secondName, String companyName, String address, String mobilePhoneNumber, String email, String group) {
        this.id = id;
        this.firstName = firstName;
        this.middleName = middleName;
        this.secondName = secondName;
        this.companyName = companyName;
        this.address = address;
        this.mobilePhoneNumber = mobilePhoneNumber;
        this.email = email;
        this.group = group;
    }


    public ContactData(String firstName, String middleName, String secondName, String companyName, String address, String mobilePhoneNumber, String email, String group) {
        this.id = Integer.MAX_VALUE;
        this.firstName = firstName;
        this.middleName = middleName;
        this.secondName = secondName;
        this.companyName = companyName;
        this.address = address;
        this.mobilePhoneNumber = mobilePhoneNumber;
        this.email = email;
        this.group = group;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }


    public String getMiddleName() {
        return middleName;
    }

    public String getSecondName() {
        return secondName;
    }

    public String getCompanyName() {
        return companyName;
    }

    public String getAddress() {
        return address;
    }

    public String getMobilePhoneNumber() {
        return mobilePhoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public String getGroup() {
        return group;
    }

    @Override
    public String toString() {
        return "ContactData{" +
                "id='" + id + '\'' +
                ", firstName='" + firstName + '\'' +
                ", secondName='" + secondName + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ContactData that = (ContactData) o;
        return Objects.equals(firstName, that.firstName) && Objects.equals(secondName, that.secondName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(firstName, secondName);
    }

}
