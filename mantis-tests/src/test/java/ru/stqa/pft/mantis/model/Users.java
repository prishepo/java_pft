package ru.stqu.pft.addressbook.model;

import com.google.common.collect.ForwardingSet;
import ru.stqa.pft.mantis.model.UserData;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

public class Users extends ForwardingSet <UserData> {

    private Set <UserData> delegate;

    public Users(Users users) {
        this.delegate = new HashSet<UserData>(users.delegate);
    }

    public Users() {
        this.delegate = new HashSet<UserData>();
    }

    public Users(Collection<UserData> contacts) {
        this.delegate = new HashSet<UserData>(contacts);
    }

    @Override
    protected Set<UserData> delegate() {
        return delegate;
    }

    public Users withAdded (UserData users){
        Users contacts = new Users(this);
        contacts.add(users);
        return contacts;
    }

    public Users without (UserData users){
        Users contacts = new Users(this);
        contacts.remove(users);
        return contacts;
    }
}