package stutter.common;

import java.util.Collection;

import org.restlet.resource.Get;
import org.restlet.resource.Post;

import stutter.User;

public interface UserListManager {

    @Get
    Collection<User> listAll();

    @Post
    void addUser(User user);

}
