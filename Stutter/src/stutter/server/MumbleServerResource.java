package stutter.server;

import org.restlet.resource.ResourceException;
import org.restlet.resource.ServerResource;

import stutter.Mumble;
import stutter.common.MumbleManager;

public class MumbleServerResource extends ServerResource implements MumbleManager {

    private String mumbleId;

    /**
     * Retrieve the identifier based on the URI path variable "mumbleId" declared in the URI template attached to the
     * application router.
     */
    @Override
    protected void doInit() throws ResourceException {
        this.mumbleId = (String) getRequestAttributes().get("mumbleId");
    }

    @Override
    public Mumble getMumble() {
        return FakeStorage.getInstance().getMumble(this.mumbleId);
    }

    @Override
    public void removeMumble() {
        FakeStorage.getInstance().removeMumble(this.mumbleId);
    }

}