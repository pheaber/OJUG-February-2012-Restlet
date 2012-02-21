package stutter.common;

import org.restlet.resource.Delete;
import org.restlet.resource.Get;
import org.restlet.resource.Put;

import stutter.User;

public interface UserManager {

    @Get
    User getUser();

    @Put
    void updateUser(User user);

    @Delete
    void removeUser();

}
