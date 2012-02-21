package stutter.server;

import org.restlet.resource.ResourceException;
import org.restlet.resource.ServerResource;

import stutter.User;
import stutter.common.UserManager;

public class UserServerResource extends ServerResource implements UserManager {

    private String username;

    /**
     * Retrieve the identifier based on the URI path variable "username" declared in the URI template attached to the
     * application router.
     */
    @Override
    protected void doInit() throws ResourceException {
        this.username = (String) getRequestAttributes().get("username");
    }

    @Override
    public User getUser() {
        return FakeStorage.getInstance().getUser(this.username);
    }

    // TODO: FIX THIS
    @Override
    public void updateUser(User user) {
        FakeStorage.getInstance().updateUser(user);
    }

    @Override
    public void removeUser() {
        FakeStorage.getInstance().removeUser(this.username);
    }

}