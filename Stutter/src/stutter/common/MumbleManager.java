package stutter.common;

import org.restlet.resource.Delete;
import org.restlet.resource.Get;

import stutter.Mumble;

public interface MumbleManager {

    @Get
    Mumble getMumble();

    @Delete
    void removeMumble();

}
