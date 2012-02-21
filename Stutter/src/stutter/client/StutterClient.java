package stutter.client;

import java.util.List;

import org.restlet.resource.ClientResource;

import stutter.Mumble;
import stutter.User;
import stutter.common.MumbleListManager;
import stutter.common.UserManager;

public class StutterClient {

    public static void main(String[] args) {
        UserManager userManagerClient = ClientResource.create("http://localhost:8182/users/farmBoy", UserManager.class);

        // how cool is this, we get an instance of our interface and Restlet does the HTTP magic?!?
        User got = userManagerClient.getUser();
        System.out.println("Got a user: " + got);

        MumbleListManager mumbleManagerClient = ClientResource.create("http://localhost:8182/mumbles",
                MumbleListManager.class);

        // TODO: why are these strings and not Mumbles?
        List<Mumble> mumbles = mumbleManagerClient.listAll();
        System.out.println("Got some mumbles: " + mumbles);

        mumbleManagerClient.newMumble(got.getUsername(), "As you wish again.");
    }

}
