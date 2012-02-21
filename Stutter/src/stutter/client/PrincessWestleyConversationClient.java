package stutter.client;

import org.restlet.resource.ClientResource;

import stutter.Mumble;
import stutter.User;
import stutter.common.MumbleListManager;
import stutter.common.UserListManager;

public class PrincessWestleyConversationClient {

    private static final String BASE = "http://localhost:8182";

    public static void main(String[] args) {
        final String westleyUsername = "farmBoy";
        final String vizziniUsername = "brainiac";

        UserListManager userListManagerClient = ClientResource.create(BASE + "/users", UserListManager.class);
        User westley = new User(westleyUsername, "westley@asyouwish.fl", "Westley");
        userListManagerClient.addUser(westley);
        User vizzini = new User(vizziniUsername, "smartypants@genius.fl", "Vizzini");
        userListManagerClient.addUser(vizzini);

        MumbleListManager mumbleListManagerClient = ClientResource.create(BASE + "/mumbles", MumbleListManager.class);

        Mumble msg1 = new Mumble(vizziniUsername,
                "I can't compete with you physically, and you're no match for my brains.");
        mumbleListManagerClient.newMumble(msg1);

        Mumble msg2 = new Mumble(westleyUsername, "You're that smart?");
        mumbleListManagerClient.newMumble(msg2);

        Mumble msg3 = new Mumble(vizziniUsername,
                "Let me put it this way. Have you ever heard of Plato, Aristotle, Socrates?");
        mumbleListManagerClient.newMumble(msg3);

        Mumble msg4 = new Mumble(westleyUsername, "Yes.");
        mumbleListManagerClient.newMumble(msg4);

        Mumble msg5 = new Mumble(vizziniUsername, "Morons.");
        mumbleListManagerClient.newMumble(msg5);

        Mumble msg6 = new Mumble(westleyUsername, "I challenge you to a battle of wits.");
        mumbleListManagerClient.newMumble(msg6);

        Mumble msg7 = new Mumble(vizziniUsername, "For the Princess?");
        mumbleListManagerClient.newMumble(msg7);

        Mumble msg8 = new Mumble(westleyUsername, "[nods]");
        mumbleListManagerClient.newMumble(msg8);

        Mumble msg9 = new Mumble(vizziniUsername, "To the death?");
        mumbleListManagerClient.newMumble(msg9);

        Mumble msg10 = new Mumble(westleyUsername, "[nods]");
        mumbleListManagerClient.newMumble(msg10);

        Mumble msg11 = new Mumble(vizziniUsername, "I accept!");
        mumbleListManagerClient.newMumble(msg11);
    }

}
