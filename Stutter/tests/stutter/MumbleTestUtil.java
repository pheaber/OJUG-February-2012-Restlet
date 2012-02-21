package stutter;

import java.util.Date;

public final class MumbleTestUtil {

    /**
     * Utility class shouldn't be instantiated.
     */
    private MumbleTestUtil() {
    }

    public static Mumble buildMumble(String mumbleId, User mumbleUser, String mumbleMessage) {
        // TODO: need to specify time!
        return new Mumble(mumbleId, mumbleUser.getUsername(), mumbleMessage, new Date());
    }

}
