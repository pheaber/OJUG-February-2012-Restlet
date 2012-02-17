package stutter;

import java.util.List;
import java.util.Map;

public class FakeStorage {

    private final Map<String, User> userStorage;

    private final Map<String, Mumble> mumbleStorage;

    private final List<Mumble> mumbleTimeline;

    /* package */FakeStorage(Map<String, User> userStorage, Map<String, Mumble> mumbleStorage,
            List<Mumble> mumbleTimeline) {
        this.userStorage = userStorage;
        this.mumbleStorage = mumbleStorage;
        this.mumbleTimeline = mumbleTimeline;
    }

    public void addUser(User user) {
        // TODO: what if we already have that username?

        this.userStorage.put(user.getUsername(), user);
    }

    public User getUser(String username) {
        return this.userStorage.get(username);
    }

    public void updateUser(User user) {
        // TODO: handle if user doesn't exist!

        this.userStorage.put(user.getUsername(), user);
    }

    public void removeUser(String username) {
        // TODO: handle if user doesn't exist!

        this.userStorage.remove(username);
    }

    public void newMumble(String username, String message) {
        User user = this.userStorage.get(username);

        // TODO: what if user doesn't exist?

        Mumble newMumble = new Mumble(user, message);
        user.addMumble(newMumble);
        this.mumbleStorage.put(newMumble.getId(), newMumble);
        this.mumbleTimeline.add(newMumble);
    }

    public Mumble getMumble(String mumbleId) {
        return this.mumbleStorage.get(mumbleId);
    }

    public void removeMumble(String mumbleId) {
        // TODO: handle if mumble doesn't exist!

        Mumble removedMumble = this.mumbleStorage.remove(mumbleId);
        this.mumbleTimeline.remove(removedMumble);
    }

}
