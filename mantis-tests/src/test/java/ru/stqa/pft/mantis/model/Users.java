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

    public Users(Collection<UserData> users) {
        this.delegate = new HashSet<UserData>(users);
    }

    @Override
    protected Set<UserData> delegate() {
        return delegate;
    }

    public Users withAdded (UserData users){
        Users users1 = new Users(this);
        users1.add(users);
        return users1;
    }

    public Users without (UserData users){
        Users users1 = new Users(this);
       users1.remove(users);
        return users1;
    }
}