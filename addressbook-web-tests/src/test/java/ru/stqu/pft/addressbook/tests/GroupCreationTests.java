package ru.stqu.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.*;
import ru.stqu.pft.addressbook.model.GroupData;

import java.util.List;

public class GroupCreationTests extends TestBase {


    @Test
    public void testGroupCreation() throws Exception {
        app.getNavigationHelper().gotoGroupPage();
        List <GroupData> before = app.getGroupHelper().getGroupList();
        app.getGroupHelper().createGroup(new GroupData("test6", null, null));
        app.getGroupHelper().returnToGroupPage();
        List <GroupData> after = app.getGroupHelper().getGroupList();
        Assert.assertEquals(after.size(), before.size() + 1);

    }

}
