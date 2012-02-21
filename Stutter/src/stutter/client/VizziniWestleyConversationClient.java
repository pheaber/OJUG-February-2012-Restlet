package stutter.client;

import org.restlet.resource.ClientResource;

import stutter.Mumble;
import stutter.User;
import stutter.common.MumbleListManager;
import stutter.common.UserListManager;

public class VizziniWestleyConversationClient {

    private static final String BASE = "http://localhost:8182";

    public static void main(String[] args) {
        final String westleyUsername = "farmBoy";
        final String princessUsername = "prettyPrincess";

        UserListManager userListManagerClient = ClientResource.create(BASE + "/users", UserListManager.class);
        User westley = new User(westleyUsername, "westley@asyouwish.fl", "Westley");
        userListManagerClient.addUser(westley);
        User princessButtercup = new User(princessUsername, "damsel@indistress.fl", "Princess Buttercup");
        userListManagerClient.addUser(princessButtercup);

        MumbleListManager mumbleListManagerClient = ClientResource.create(BASE + "/mumbles", MumbleListManager.class);

        Mumble msg1 = new Mumble(princessUsername,
                "Farm boy, polish my horse's saddle. I want to see my face shining in it by morning.");
        mumbleListManagerClient.newMumble(msg1);

        Mumble msg2 = new Mumble(westleyUsername, "As you wish.");
        mumbleListManagerClient.newMumble(msg2);

        Mumble msg3 = new Mumble(princessUsername, "Farm boy, fill these with water - please.");
        mumbleListManagerClient.newMumble(msg3);

        Mumble msg4 = new Mumble(westleyUsername, "As you wish.");
        mumbleListManagerClient.newMumble(msg4);

        Mumble msg5 = new Mumble(princessUsername, "Farm boy... fetch me that pitcher.");
        mumbleListManagerClient.newMumble(msg5);

        Mumble msg6 = new Mumble(westleyUsername, "As you wish.");
        mumbleListManagerClient.newMumble(msg6);
    }

}
