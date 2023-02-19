package ru.stqu.pft.addressbook.model;

public class ContactData {
    private final String firstName;
    private final String middleName;
    private final String secondName;
    private final String companyName;
    private final String address;
    private final String mobilePhoneNumber;
    private final String email;

    public ContactData(String firstName, String middleName, String secondName, String companyName, String address, String mobilePhoneNumber, String email) {
        this.firstName = firstName;
        this.middleName = middleName;
        this.secondName = secondName;
        this.companyName = companyName;
        this.address = address;
        this.mobilePhoneNumber = mobilePhoneNumber;
        this.email = email;
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
}
