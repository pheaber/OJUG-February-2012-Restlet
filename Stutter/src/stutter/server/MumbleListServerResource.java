package stutter.server;

import java.util.List;

import org.restlet.resource.ServerResource;

import stutter.Mumble;
import stutter.common.MumbleListManager;

public class MumbleListServerResource extends ServerResource implements MumbleListManager {

    @Override
    public List<Mumble> listAll() {
        return FakeStorage.getInstance().listMumbles();
    }

    // TODO: FIX THIS
    @Override
    public void newMumble(String username, String message) {
        FakeStorage.getInstance().newMumble(username, (String) getRequestAttributes().get("message"));
    }

}