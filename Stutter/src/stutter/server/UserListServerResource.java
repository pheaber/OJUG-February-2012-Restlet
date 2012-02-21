package stutter.server;

import java.util.Collection;

import org.restlet.resource.ServerResource;

import stutter.User;
import stutter.common.UserListManager;

public class UserListServerResource extends ServerResource implements UserListManager {

    // TODO: Fix for client!
    @Override
    public Collection<User> listAll() {
        return FakeStorage.getInstance().listUsers();
    }

    @Override
    public void addUser(User user) {
        FakeStorage.getInstance().addUser(user);
    }

}