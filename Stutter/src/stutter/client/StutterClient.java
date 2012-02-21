package stutter.client;

import org.restlet.resource.ClientResource;

import stutter.Mumble;
import stutter.User;
import stutter.common.MumbleListManager;
import stutter.common.MumbleManager;
import stutter.common.UserListManager;
import stutter.common.UserManager;

public class StutterClient {

    private static final String BASE = "http://localhost:8182";

    public static void main(String[] args) {
        // how cool is this? we get an instance of our interface and Restlet does the HTTP magic!!
        UserListManager userListManagerClient = ClientResource.create(BASE + "/users", UserListManager.class);
        User westley = new User("farmBoy", "westley@asyouwish.fl");
        userListManagerClient.addUser(westley);

        UserManager userManagerClient = ClientResource.create(BASE + "/users/farmBoy", UserManager.class);
        User beforeUpdate = userManagerClient.getUser();
        System.out.println("Got a user: " + beforeUpdate);

        westley.setName("Westley");
        userManagerClient.updateUser(westley);
        User afterUpdate = userManagerClient.getUser();
        System.out.println("Got an updated user: " + afterUpdate);

        MumbleListManager mumbleListManagerClient = ClientResource.create(BASE + "/mumbles", MumbleListManager.class);
        Mumble newMessage = new Mumble(afterUpdate.getUsername(), "As you wish.");
        Mumble madeMumble = mumbleListManagerClient.newMumble(newMessage);

        User userWithMumble = userManagerClient.getUser();
        System.out.println("Got a user: " + userWithMumble + " with mumbles=" + userWithMumble.getMumbles());

        MumbleManager mumbleManagerClient = ClientResource.create(madeMumble.getReference(), MumbleManager.class);
        Mumble requestedMumble = mumbleManagerClient.getMumble();
        System.out.println("Got this mumble: " + requestedMumble);

        userManagerClient.removeUser();
    }

}
