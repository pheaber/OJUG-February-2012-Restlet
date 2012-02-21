package stutter.client;

import org.restlet.resource.ClientResource;

import stutter.Mumble;
import stutter.User;
import stutter.common.MumbleListManager;
import stutter.common.UserListManager;
import stutter.common.UserManager;

public class StutterClient {

    private static final String BASE = "http://localhost:8182";

    public static void main(String[] args) {
        // how cool is this? we get an instance of our interface and Restlet does the HTTP magic!!
        UserListManager userListManagerClient = ClientResource.create(BASE + "/users", UserListManager.class);
        User westley = new User("farmBoy", "westley@asyouwish.fl");
        userListManagerClient.addUser(westley);

        // TODO: why are these strings and not Users?
        // Collection<User> users = userListManagerClient.listAll();
        // for (User user : users) {
        // System.out.println("Got a user: " + user);
        // }

        UserManager userManagerClient = ClientResource.create(BASE + "/users/farmBoy", UserManager.class);
        User beforeUpdate = userManagerClient.getUser();
        System.out.println("Got a user: " + beforeUpdate);

        westley.setName("Westley");
        userManagerClient.updateUser(westley);
        User afterUpdate = userManagerClient.getUser();
        System.out.println("Got an updated user: " + afterUpdate);

        MumbleListManager mumbleManagerClient = ClientResource.create(BASE + "/mumbles", MumbleListManager.class);
        Mumble newMessage = new Mumble(afterUpdate.getUsername(), "As you wish.");
        mumbleManagerClient.newMumble(newMessage);

        // TODO: why are these strings and not Mumbles?
        // List<Mumble> mumbles = mumbleManagerClient.listAll();
        // for (Mumble mumble : mumbles) {
        // System.out.println("Got a mumble: " + mumble);
        // }

        userManagerClient.removeUser();
    }

}
