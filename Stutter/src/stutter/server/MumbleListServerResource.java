package stutter.server;

import java.util.List;

import org.restlet.resource.ServerResource;

import stutter.Mumble;
import stutter.common.MumbleListManager;

public class MumbleListServerResource extends ServerResource implements MumbleListManager {

    // TODO: Fix for Client!
    @Override
    public List<Mumble> listAll() {
        return FakeStorage.getInstance().listMumbles();
    }

    @Override
    public void newMumble(Mumble newMumble) {
        FakeStorage.getInstance().newMumble(newMumble.getUsername(), newMumble.getMessage());
    }

}